package com.melek.gestionstock.service.impl;

import com.melek.gestionstock.dto.EntrepriseDto;
import com.melek.gestionstock.exception.EntityNotFoundException;
import com.melek.gestionstock.exception.ErrorCodes;
import com.melek.gestionstock.exception.InvalidEntityException;
import com.melek.gestionstock.repository.EntrepriseRepository;
import com.melek.gestionstock.service.IEntrepriseService;
import com.melek.gestionstock.validator.EntrepriseValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class EntrepriseServiceImpl implements IEntrepriseService {

    EntrepriseRepository entrepriseRepository;

    @Autowired
    public EntrepriseServiceImpl(EntrepriseRepository entrepriseRepository) {
        this.entrepriseRepository = entrepriseRepository;
    }

    @Override
    public EntrepriseDto save(EntrepriseDto dto) {
        List<String> errors = EntrepriseValidator.validate(dto);
        if (!errors.isEmpty()) {
            log.error("Entreprise is not valid {}", dto);
            throw new InvalidEntityException("erreur de validation de l'entreprise", ErrorCodes.ENTREPRISE_NOT_VALID);
        }
        return EntrepriseDto.fromEntity(
                entrepriseRepository.save(EntrepriseDto.toEntity(dto))
        );
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
