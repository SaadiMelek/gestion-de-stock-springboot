package com.melek.gestionstock.controller;

import com.melek.gestionstock.controller.api.ICommandeClientApi;
import com.melek.gestionstock.dto.CommandeClientDto;
import com.melek.gestionstock.model.EtatCommande;
import com.melek.gestionstock.service.ICommandeClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
public class CommandeClientController implements ICommandeClientApi {

    private ICommandeClientService commandeClientService;

    @Autowired
    public CommandeClientController(ICommandeClientService commandeClientService) {
        this.commandeClientService = commandeClientService;
    }

    @Override
    public ResponseEntity<CommandeClientDto> save(CommandeClientDto dto) {
        return ResponseEntity.ok(commandeClientService.save(dto));
        /*return ResponseEntity.status(HttpStatus.OK)
                .body(commandeClientService.save(dto));*/
    }

    @Override
    public ResponseEntity<CommandeClientDto> updateEtatCommande(Integer idCommandeClient, EtatCommande etatCommande) {
        return ResponseEntity.ok(
                commandeClientService.updateEtatCommande(idCommandeClient, etatCommande)
        );
    }

    @Override
    public ResponseEntity<CommandeClientDto> updateQuantiteCommande(Integer idCommande, Integer idLigneCommande, BigDecimal quantity) {
        return ResponseEntity.ok(
                commandeClientService.updateQuantiteCommande(idCommande, idLigneCommande, quantity)
        );
    }

    @Override
    public ResponseEntity<CommandeClientDto> updateClient(Integer idCommande, Integer idClient) {
        return ResponseEntity.ok(
                commandeClientService.updateClient(idCommande, idClient)
        );
    }

    @Override
    public ResponseEntity<CommandeClientDto> findById(Integer id) {
        return ResponseEntity.ok(commandeClientService.findById(id));
    }

    @Override
    public ResponseEntity<CommandeClientDto> findByCode(String code) {
        return ResponseEntity.ok(commandeClientService.findByCode(code));
    }

    @Override
    public ResponseEntity<List<CommandeClientDto>> findAll() {
        return ResponseEntity.ok(commandeClientService.findAll());
    }

    @Override
    public ResponseEntity delete(Integer id) {
        commandeClientService.delete(id);
        return ResponseEntity.ok()
                .build();
    }
}
