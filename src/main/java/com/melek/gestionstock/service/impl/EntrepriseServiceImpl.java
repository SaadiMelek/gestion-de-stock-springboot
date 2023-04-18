package com.melek.gestionstock.service.impl;

import com.melek.gestionstock.dto.EntrepriseDto;
import com.melek.gestionstock.dto.RoleDto;
import com.melek.gestionstock.dto.UtilisateurDto;
import com.melek.gestionstock.exception.EntityNotFoundException;
import com.melek.gestionstock.exception.ErrorCodes;
import com.melek.gestionstock.exception.InvalidEntityException;
import com.melek.gestionstock.repository.EntrepriseRepository;
import com.melek.gestionstock.repository.RoleRepository;
import com.melek.gestionstock.service.IEntrepriseService;
import com.melek.gestionstock.service.IUtilisateurService;
import com.melek.gestionstock.validator.EntrepriseValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class EntrepriseServiceImpl implements IEntrepriseService {

    EntrepriseRepository entrepriseRepository;
    IUtilisateurService utilisateurService;
    RoleRepository roleRepository;

    @Autowired
    public EntrepriseServiceImpl(EntrepriseRepository entrepriseRepository,
                                 IUtilisateurService utilisateurService,
                                 RoleRepository roleRepository) {
        this.entrepriseRepository = entrepriseRepository;
        this.utilisateurService = utilisateurService;
        this.roleRepository = roleRepository;
    }

    @Override
    public EntrepriseDto save(EntrepriseDto dto) {
        List<String> errors = EntrepriseValidator.validate(dto);
        if (!errors.isEmpty()) {
            log.error("Entreprise is not valid {}", dto);
            throw new InvalidEntityException("erreur de validation de l'entreprise", ErrorCodes.ENTREPRISE_NOT_VALID, errors);
        }
        EntrepriseDto savedEntreprise = EntrepriseDto.fromEntity(
                entrepriseRepository.save(EntrepriseDto.toEntity(dto))
        );

        UtilisateurDto utilisateur = fromEntreprise(savedEntreprise);
        UtilisateurDto savedUser = utilisateurService.save(utilisateur);
        RoleDto roleDto = RoleDto.builder()
                .roleName("ADMIN")
                .utilisateur(savedUser)
                .build();

        roleRepository.save(RoleDto.toEntity(roleDto));

        return savedEntreprise;
    }

    private UtilisateurDto fromEntreprise(EntrepriseDto dto) {
        return UtilisateurDto.builder()
                .adresse(dto.getAdresse())
                .nom(dto.getNom())
                .prenom(dto.getCodeFiscal())
                .email(dto.getEmail())
                .password(generateRandomPassword())
                .entreprise(dto)
                .dateNaissance(Instant.now())
                .photo(dto.getPhoto())
                .build();
    }

    private String generateRandomPassword() {
        return "password";
    }

    @Override
    public EntrepriseDto findById(Integer id) {
        if (id == null) {
            log.error("Id de l'entreprise is null");
            return null;
        }
        return EntrepriseDto.fromEntity(
                entrepriseRepository.findById(id)
                .orElseThrow( () -> new EntityNotFoundException("Entreprise not found", ErrorCodes.ENTREPRISE_NOT_FOUND))
        );
    }

    @Override
    public List<EntrepriseDto> findAll() {
        return entrepriseRepository.findAll().stream()
                .map(EntrepriseDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        if (id == null) {
            log.warn("Id is null");
        }
        entrepriseRepository.deleteById(id);
    }
}
