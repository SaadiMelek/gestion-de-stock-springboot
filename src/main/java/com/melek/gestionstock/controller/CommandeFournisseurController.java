package com.melek.gestionstock.controller;

import com.melek.gestionstock.controller.api.ICommandeFournisseurApi;
import com.melek.gestionstock.dto.CommandeFournisseurDto;
import com.melek.gestionstock.dto.LigneCommandeClientDto;
import com.melek.gestionstock.dto.LigneCommandeFournisseurDto;
import com.melek.gestionstock.model.EtatCommande;
import com.melek.gestionstock.service.ICommandeFournisseurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;
@RestController
public class CommandeFournisseurController implements ICommandeFournisseurApi {
    private ICommandeFournisseurService commandeFournisseurService;
    @Autowired
    public CommandeFournisseurController(ICommandeFournisseurService iCommandeFournisseurService) {
        this.commandeFournisseurService = iCommandeFournisseurService;
    }
    @Override
    public CommandeFournisseurDto save(CommandeFournisseurDto dto) {
        return commandeFournisseurService.save(dto);
    }
    @Override
    public CommandeFournisseurDto findById(Integer idCommandeFournisseur) {
        return commandeFournisseurService.findById(idCommandeFournisseur);
    }
    @Override
    public CommandeFournisseurDto findByCode(String code) {
        return commandeFournisseurService.findByCode(code);
    }
    @Override
    public List<CommandeFournisseurDto> findAll() {
        return commandeFournisseurService.findAll();
    }
    @Override
    public void delete(Integer id) {
        commandeFournisseurService.delete(id);
    }
    @Override
    public CommandeFournisseurDto updateEtatCommande(Integer idCommandeFournisseur, EtatCommande etatCommande) {
        return commandeFournisseurService.updateEtatCommande(idCommandeFournisseur, etatCommande);
    }
    @Override
    public CommandeFournisseurDto updateQuantiteCommande(Integer idCommande, Integer idLigneCommande, BigDecimal quantity) {
        return commandeFournisseurService.updateQuantiteCommande(idCommande, idLigneCommande, quantity);
    }
    @Override
    public CommandeFournisseurDto updateFournisseur(Integer idCommande, Integer idFournisseur) {
        return commandeFournisseurService.updateArticle(idCommande, idFournisseur);
    }
    @Override
    public CommandeFournisseurDto deleteArticle(Integer idCommande, Integer idLigneCommande) {
        return commandeFournisseurService.deleteArticle(idCommande, idLigneCommande);
    }
    @Override
    public CommandeFournisseurDto updateArticle(Integer idCommande, Integer idLigneCommande, Integer idArticle) {
        return commandeFournisseurService.updateArticle(idCommande,idLigneCommande, idArticle);
    }
    @Override
    public List<LigneCommandeFournisseurDto> findAllLignesCommandesFournisseurByCommandeFournisseurId(Integer idCommande) {
        return commandeFournisseurService.findAllLignesCommandesFournisseurByCommandeFournisseurId(idCommande);
    }
}
