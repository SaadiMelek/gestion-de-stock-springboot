package com.melek.gestionstock.service.impl;

import com.melek.gestionstock.dto.CategoryDto;
import com.melek.gestionstock.dto.ChangePasswordUtilisateurDto;
import com.melek.gestionstock.dto.UtilisateurDto;
import com.melek.gestionstock.exception.EntityNotFoundException;
import com.melek.gestionstock.exception.ErrorCodes;
import com.melek.gestionstock.exception.InvalidEntityException;
import com.melek.gestionstock.exception.InvalidOperationException;
import com.melek.gestionstock.model.Utilisateur;
import com.melek.gestionstock.repository.UtilisateurRepository;
import com.melek.gestionstock.service.IUtilisateurService;
import com.melek.gestionstock.validator.UtilisateurValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UtilisateurServiceImpl implements IUtilisateurService {

    private UtilisateurRepository utilisateurRepository;

    @Autowired
    public UtilisateurServiceImpl(UtilisateurRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
    }

    @Override
    public UtilisateurDto save(UtilisateurDto dto) {
        List<String> errors = UtilisateurValidator.validate(dto);
        if (!errors.isEmpty()) {
            log.error("Utilisateur is not valid");
            throw new InvalidEntityException("Utilisateur is not valid", ErrorCodes.UTILISATEUR_NOT_VALID, errors);
        }
        if(userAlreadyExists(dto.getEmail())) {
            throw new InvalidEntityException("Un autre utilisateur avec le meme email existe deja", ErrorCodes.UTILISATEUR_ALREADY_EXISTS,
                    Collections.singletonList("Un autre utilisateur avec le meme email existe deja dans la BDD"));
        }
        return UtilisateurDto.fromEntity(
                utilisateurRepository.save(UtilisateurDto.toEntity(dto))
        );
    }

    private boolean userAlreadyExists(String email) {
        Optional<Utilisateur> user = utilisateurRepository.findUtilisateurByEmail(email);
        return user.isPresent();
    }

    @Override
    public UtilisateurDto findById(Integer id) {
        if (id == null) {
            log.error("Id Utilisateur is null");
            return null;
        }
        return UtilisateurDto.fromEntity(
                utilisateurRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Utilisateur not found", ErrorCodes.UTILISATEUR_NOT_FOUND))
        );
    }

    @Override
    public UtilisateurDto findByEmail(String email) {
        if (!StringUtils.hasLength(email)) {
            log.error("email Utilisateur is null");
            return null;
        }
        return utilisateurRepository.findUtilisateurByEmail(email)
                .map(UtilisateurDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Aucun Utilisateur avec le mail ' " + email + " ' n'est trouvé dans la BDD",
                        ErrorCodes.UTILISATEUR_NOT_FOUND
                ));
    }

    @Override
    public List<UtilisateurDto> findAll() {
        return utilisateurRepository.findAll().stream()
                .map(UtilisateurDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        if (id == null) {
            log.error("Id Utilisateur is null");
        }
        utilisateurRepository.deleteById(id);
    }

    @Override
    public UtilisateurDto changePassword(ChangePasswordUtilisateurDto dto) {
        validate(dto);
        Optional<Utilisateur> utilisateurOptional = utilisateurRepository.findById(dto.getId());
        if (utilisateurOptional.isEmpty()) {
            log.warn("Aucun utilisateur n'a été trouvé avec l'ID " + dto.getId());
            throw new EntityNotFoundException("Aucun utilisateur n'a été trouvé avec l'ID " + dto.getId(), ErrorCodes.UTILISATEUR_NOT_FOUND);
        }
        Utilisateur utilisateur = utilisateurOptional.get();
        utilisateur.setPassword(dto.getPassword());
        return UtilisateurDto.fromEntity(
                utilisateurRepository.save(utilisateur)
        );
    }

    private void validate(ChangePasswordUtilisateurDto dto) {
        if (dto == null) {
            log.warn("Impossible de modifier le mot de passe avec un objet Null");
            throw new InvalidOperationException("Aucune information n'a été fourni pour pouvoir changer le mot de passe", ErrorCodes.UTILISATEUR_CHANGE_PASSWORD_OBJECT_NOT_VALID);
        }
        if (dto.getId() == null) {
            log.warn("Impossible de modifier le mot de passe avec un ID Null");
            throw new InvalidOperationException("ID utilisateur NULL, impossible de modifier le mot de passe", ErrorCodes.UTILISATEUR_CHANGE_PASSWORD_OBJECT_NOT_VALID);
        }
        if (StringUtils.hasLength(dto.getPassword()) || StringUtils.hasLength(dto.getConfirmPassword())) {
            log.warn("Impossible de modifier le mot de passe avec un password Null");
            throw new InvalidOperationException("password utilisateur NULL, impossible de modifier le mot de passe", ErrorCodes.UTILISATEUR_CHANGE_PASSWORD_OBJECT_NOT_VALID);
        }
        if (!dto.getPassword().equals(dto.getConfirmPassword())) {
            log.warn("Impossible de modifier le mot de passe avec 2 mot de passe différents");
            throw new InvalidOperationException("password utilisateur non conforme, impossible de modifier le mot de passe", ErrorCodes.UTILISATEUR_CHANGE_PASSWORD_OBJECT_NOT_VALID);
        }
    }
}
