package com.melek.gestionstock.controller.api;

import com.melek.gestionstock.dto.CommandeFournisseurDto;
import com.melek.gestionstock.dto.CommandeFournisseurDto;
import com.melek.gestionstock.dto.LigneCommandeClientDto;
import com.melek.gestionstock.dto.LigneCommandeFournisseurDto;
import com.melek.gestionstock.model.EtatCommande;
import io.swagger.annotations.Api;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

import static com.melek.gestionstock.utils.Constants.APP_ROOT;
import static com.melek.gestionstock.utils.Constants.COMMANDE_FOURNISSEUR_ENDPOINT;

@Api(COMMANDE_FOURNISSEUR_ENDPOINT)
public interface ICommandeFournisseurApi {

    @PostMapping(value = COMMANDE_FOURNISSEUR_ENDPOINT + "/create")
    CommandeFournisseurDto save(@RequestBody CommandeFournisseurDto dto);
    @GetMapping(value = COMMANDE_FOURNISSEUR_ENDPOINT + "/{idCommandeFournisseur}")
    CommandeFournisseurDto findById(Integer idCommandeFournisseur);
    @GetMapping(value = COMMANDE_FOURNISSEUR_ENDPOINT + "/{codeCommandeFournisseur}")
    CommandeFournisseurDto findByCode(@PathVariable("codeCommandeFournisseur") String code);
    @GetMapping(value = COMMANDE_FOURNISSEUR_ENDPOINT + "/all")
    List<CommandeFournisseurDto> findAll();
    @DeleteMapping(value = COMMANDE_FOURNISSEUR_ENDPOINT + "/{idCommandeFournisseur}")
    void delete(@PathVariable("idCommandeFournisseur") Integer id);
    @PatchMapping(value = COMMANDE_FOURNISSEUR_ENDPOINT + "/update/etat/{idCommandeFournisseur}/{etatCommande}")
    CommandeFournisseurDto updateEtatCommande(@PathVariable("idCommandeFournisseur") Integer idCommandeFournisseur, @PathVariable("etatCommande") EtatCommande etatCommande);
    @PatchMapping(value = COMMANDE_FOURNISSEUR_ENDPOINT + "/update/quantite/{idCommandeFournisseur}/{idLigneCommande}/{quantity}")
    CommandeFournisseurDto updateQuantiteCommande(@PathVariable("idCommandeFournisseur") Integer idCommande, @PathVariable("idLigneCommande") Integer idLigneCommande, @PathVariable("quantity") BigDecimal quantity);
    @PatchMapping(value = COMMANDE_FOURNISSEUR_ENDPOINT + "/update/fournisseur/{idCommande}/{idFournisseur}")
    CommandeFournisseurDto updateFournisseur(@PathVariable("idCommande") Integer idCommande, @PathVariable("idFournisseur") Integer idFournisseur);
    @DeleteMapping(value = COMMANDE_FOURNISSEUR_ENDPOINT + "/delete/article/{idCommandeFournisseur}/{idLigneCommande}")
    CommandeFournisseurDto deleteArticle(@PathVariable("idCommande") Integer idCommande,  @PathVariable("idLigneCommande") Integer idLigneCommande);
    @PatchMapping(value = COMMANDE_FOURNISSEUR_ENDPOINT + "/update/article/{idCommande}/{idLigneCommande}/{idArticle}")
    CommandeFournisseurDto updateArticle(@PathVariable("idCommande") Integer idCommande, @PathVariable("idLigneCommande") Integer idLigneCommande, @PathVariable("idArticle") Integer idArticle);
    @GetMapping(value = COMMANDE_FOURNISSEUR_ENDPOINT + "/lignesCommande/{idCommande}")
    List<LigneCommandeFournisseurDto> findAllLignesCommandesFournisseurByCommandeFournisseurId(@PathVariable("idCommande") Integer idCommande);
}
