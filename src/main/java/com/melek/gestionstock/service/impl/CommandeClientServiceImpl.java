package com.melek.gestionstock.service.impl;

import com.melek.gestionstock.dto.CommandeClientDto;
import com.melek.gestionstock.dto.LigneCommandeClientDto;
import com.melek.gestionstock.exception.EntityNotFoundException;
import com.melek.gestionstock.exception.ErrorCodes;
import com.melek.gestionstock.exception.InvalidEntityException;
import com.melek.gestionstock.model.Article;
import com.melek.gestionstock.model.Client;
import com.melek.gestionstock.model.CommandeClient;
import com.melek.gestionstock.model.LigneCommandeClient;
import com.melek.gestionstock.repository.ArticleRepository;
import com.melek.gestionstock.repository.ClientRepository;
import com.melek.gestionstock.repository.CommandeClientRepository;
import com.melek.gestionstock.repository.LigneCommandeClientRepository;
import com.melek.gestionstock.service.ICommandeClientService;
import com.melek.gestionstock.validator.CommandeClientValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CommandeClientServiceImpl implements ICommandeClientService {

    private CommandeClientRepository commandeClientRepository;
    private ClientRepository clientRepository;
    private ArticleRepository articleRepository;

    private LigneCommandeClientRepository ligneCommandeClientRepository;

    @Autowired
    public CommandeClientServiceImpl(CommandeClientRepository commandeClientRepository,
                                     ClientRepository clientRepository,
                                     ArticleRepository articleRepository,
                                     LigneCommandeClientRepository ligneCommandeClientRepository) {
        this.commandeClientRepository = commandeClientRepository;
        this.clientRepository = clientRepository;
        this.articleRepository = articleRepository;
        this.ligneCommandeClientRepository = ligneCommandeClientRepository;
    }

    @Override
    public CommandeClientDto save(CommandeClientDto dto) {
        List<String> errors = CommandeClientValidator.validate(dto);
        if (!errors.isEmpty()) {
            log.error("Commande client n'est pas valide");
            throw new InvalidEntityException("Commande client n'est pas valide", ErrorCodes.COMMANDE_CLIENT_NOT_VALID, errors);
        }
        Optional<Client> client = clientRepository.findById(dto.getClient().getId());
        if (!client.isEmpty()) {
            log.warn("Client with ID {} was not found in the DB", dto.getClient().getId());
            throw new EntityNotFoundException("Aucun client avec l'id '"+dto.getClient().getId()+"' n'est trouvé dans la BDD", ErrorCodes.COMMANDE_CLIENT_NOT_FOUND);
        }
        List<String> articleErrors = new ArrayList<>();
        if (dto.getLigneCommandeClients() != null) {
            dto.getLigneCommandeClients().forEach(lgCmdClt -> {
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

        CommandeClient savedCommandeClient = commandeClientRepository.save(CommandeClientDto.toEntity(dto));
        if (dto.getLigneCommandeClients() != null) {
            dto.getLigneCommandeClients().forEach(lgCmdClt -> {
                LigneCommandeClient ligneCommandeClient = LigneCommandeClientDto.toEntity(lgCmdClt);
                ligneCommandeClient.setCommandeClient(savedCommandeClient);
                ligneCommandeClientRepository.save(ligneCommandeClient);
            });
        }
        return CommandeClientDto.fromEntity(savedCommandeClient);
    }

    @Override
    public CommandeClientDto findById(Integer id) {
        if (id == null) {
            log.error("ID CommandeClient is null");
            return null;
        }
        return commandeClientRepository.findById(id)
                .map(CommandeClientDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException("Aucune commande client n'est trouvé avec l'ID' " + id, ErrorCodes.COMMANDE_CLIENT_NOT_FOUND));
    }

    @Override
    public CommandeClientDto findByCode(String code) {
        if (code.isEmpty()) {
            log.error("Code CommandeClient is null");
            return null;
        }
        return commandeClientRepository.findCommandeClientByCode(code)
                .map(CommandeClientDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException("Aucune commande client n'est trouvé avec le code ' " + code, ErrorCodes.COMMANDE_CLIENT_NOT_FOUND));
    }

    @Override
    public List<CommandeClientDto> findAll() {
        return commandeClientRepository.findAll().stream()
                .map(CommandeClientDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        if (id == null) {
            log.error("ID CommandeClient is null");
            return;
        }
        commandeClientRepository.deleteById(id);
    }
}
