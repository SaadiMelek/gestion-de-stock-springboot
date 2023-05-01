package com.melek.gestionstock.service.impl;

import com.melek.gestionstock.dto.FournisseurDto;
import com.melek.gestionstock.exception.EntityNotFoundException;
import com.melek.gestionstock.exception.ErrorCodes;
import com.melek.gestionstock.exception.InvalidOperationException;
import com.melek.gestionstock.model.CommandeFournisseur;
import com.melek.gestionstock.model.LigneCommandeClient;
import com.melek.gestionstock.model.LigneCommandeFournisseur;
import com.melek.gestionstock.repository.CommandeFournisseurRepository;
import com.melek.gestionstock.repository.FournisseurRepository;
import com.melek.gestionstock.service.IFournisseurService;
import com.melek.gestionstock.validator.FournisseurValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class FournisseurServiceImpl implements IFournisseurService {

    private FournisseurRepository fournisseurRepository;
    private CommandeFournisseurRepository commandeFournisseurRepository;

    @Autowired
    public FournisseurServiceImpl(FournisseurRepository fournisseurRepository, CommandeFournisseurRepository commandeFournisseurRepository) {
        this.fournisseurRepository = fournisseurRepository;
        this.commandeFournisseurRepository = commandeFournisseurRepository;
    }

    @Override
    public FournisseurDto save(FournisseurDto dto) {
        List<String> errors = FournisseurValidator.validate(dto);
        if (!errors.isEmpty()) {
            log.error("Erreur de validation du founisseur");
            return null;
        }
        return FournisseurDto.fromEntity(
                fournisseurRepository.save(FournisseurDto.toEntity(dto))
        );
    }

    @Override
    public FournisseurDto findById(Integer id) {
        if (id == null) {
            log.error("Id Fournisseur is null");
            return null;
        }
        return FournisseurDto.fromEntity(fournisseurRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Fournisseur not found", ErrorCodes.ENTREPRISE_NOT_FOUND))
        );
    }

    @Override
    public List<FournisseurDto> findAll() {
        return fournisseurRepository.findAll().stream()
                .map(FournisseurDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        if (id == null) {
            log.warn("Id is null");
        }
        List<CommandeFournisseur> commandeFournisseurs = commandeFournisseurRepository.findAllByFournisseurId(id);
        if (!commandeFournisseurs.isEmpty()) {
            throw new InvalidOperationException("Impossible de supprimer une commande fournisseur déjà utilisé", ErrorCodes.FOURNISSEUR_ALREADY_IN_USE);
        }
        fournisseurRepository.deleteById(id);
    }
}
