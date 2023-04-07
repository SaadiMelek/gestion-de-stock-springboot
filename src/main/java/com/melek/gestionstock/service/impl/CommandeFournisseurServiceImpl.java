package com.melek.gestionstock.service.impl;

import com.melek.gestionstock.dto.CommandeFournisseurDto;
import com.melek.gestionstock.dto.LigneCommandeFournisseurDto;
import com.melek.gestionstock.exception.EntityNotFoundException;
import com.melek.gestionstock.exception.ErrorCodes;
import com.melek.gestionstock.exception.InvalidEntityException;
import com.melek.gestionstock.model.Article;
import com.melek.gestionstock.model.CommandeFournisseur;
import com.melek.gestionstock.model.Fournisseur;
import com.melek.gestionstock.model.LigneCommandeFournisseur;
import com.melek.gestionstock.repository.ArticleRepository;
import com.melek.gestionstock.repository.CommandeFournisseurRepository;
import com.melek.gestionstock.repository.FournisseurRepository;
import com.melek.gestionstock.repository.LigneCommandeFournisseurRepository;
import com.melek.gestionstock.service.ICommandeFournisseurService;
import com.melek.gestionstock.validator.CommandeFournisseurValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CommandeFournisseurServiceImpl implements ICommandeFournisseurService {


    private CommandeFournisseurRepository commandeFournisseurRepository;
    private FournisseurRepository fournisseurRepository;
    private ArticleRepository articleRepository;
    private LigneCommandeFournisseurRepository ligneCommandeFournisseurRepository;

    @Autowired
    public CommandeFournisseurServiceImpl(CommandeFournisseurRepository commandeFournisseurRepository,
                                          FournisseurRepository fournisseurRepository,
                                     ArticleRepository articleRepository,
                                          LigneCommandeFournisseurRepository ligneCommandeFournisseurRepository) {
        this.commandeFournisseurRepository = commandeFournisseurRepository;
        this.fournisseurRepository = fournisseurRepository;
        this.articleRepository = articleRepository;
        this.ligneCommandeFournisseurRepository = ligneCommandeFournisseurRepository;
    }

    @Override
    public CommandeFournisseurDto save(CommandeFournisseurDto dto) {
        List<String> errors = CommandeFournisseurValidator.validate(dto);
        if (!errors.isEmpty()) {
            log.error("Commande Fournisseur n'est pas valide");
            throw new InvalidEntityException("Commande fournisseur n'est pas valide", ErrorCodes.COMMANDE_FOURNISSEUR_NOT_VALID, errors);
        }
        Optional<Fournisseur> fournisseur = fournisseurRepository.findById(dto.getFournisseur().getId());
        if (!fournisseur.isEmpty()) {
            log.warn("Fournisseur with ID {} was not found in the DB", dto.getFournisseur().getId());
            throw new EntityNotFoundException("Aucun Fournisseur avec l'id '"+dto.getFournisseur().getId()+"' n'est trouvé dans la BDD", ErrorCodes.COMMANDE_FOURNISSEUR_NOT_FOUND);
        }
        List<String> articleErrors = new ArrayList<>();
        if (dto.getLigneCommandeFournisseurs() != null) {
            dto.getLigneCommandeFournisseurs().forEach(lgCmdClt -> {
                if (lgCmdClt.getArticle() != null) {
                    Optional<Article> article = articleRepository.findById(lgCmdClt.getArticle().getId());
                    if (article.isEmpty()) {
                        articleErrors.add("L'article avec l'ID '"+lgCmdClt.getArticle().getId()+"' n'existe pas");
                    }
                } else {
                    articleErrors.add("Impossible d'enregistrer une commande avec un article NULL");
                }
            });
        }
        if (!articleErrors.isEmpty()) {
            log.warn("Article n'esite pas dans la BDD");
            throw new InvalidEntityException("Article n'esite pas dans la BDD", ErrorCodes.ARTICLE_NOT_FOUND, articleErrors);
        }

        CommandeFournisseur savedCommandeFournisseur = commandeFournisseurRepository.save(CommandeFournisseurDto.toEntity(dto));
        if (dto.getLigneCommandeFournisseurs() != null) {
            dto.getLigneCommandeFournisseurs().forEach(lgCmdClt -> {
                LigneCommandeFournisseur ligneCommandeFournisseur = LigneCommandeFournisseurDto.toEntity(lgCmdClt);
                ligneCommandeFournisseur.setCommandeFournisseur(savedCommandeFournisseur);
                ligneCommandeFournisseurRepository.save(ligneCommandeFournisseur);
            });
        }
        return CommandeFournisseurDto.fromEntity(savedCommandeFournisseur);
    }

    @Override
    public CommandeFournisseurDto findById(Integer id) {
        if (id == null) {
            log.error("ID Commande ligneCommandeFournisseur is null");
            return null;
        }
        return commandeFournisseurRepository.findById(id)
                .map(CommandeFournisseurDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException("Aucune commande Fournisseur n'est trouvé avec l'ID' " + id, ErrorCodes.COMMANDE_FOURNISSEUR_NOT_FOUND));
    }

    @Override
    public CommandeFournisseurDto findByCode(String code) {
        if (code.isEmpty()) {
            log.error("Code CommandeFournisseur is null");
            return null;
        }
        return commandeFournisseurRepository.findCommandeFournisseurByCode(code)
                .map(CommandeFournisseurDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException("Aucune commande Fournisseur n'est trouvé avec le code ' " + code, ErrorCodes.COMMANDE_FOURNISSEUR_NOT_FOUND));
    }

    @Override
    public List<CommandeFournisseurDto> findAll() {
        return commandeFournisseurRepository.findAll().stream()
                .map(CommandeFournisseurDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        if (id == null) {
            log.error("ID CommandeFournisseur is null");
            return;
        }
        commandeFournisseurRepository.deleteById(id);
    }
}
