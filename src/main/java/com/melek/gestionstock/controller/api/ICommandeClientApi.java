package com.melek.gestionstock.controller.api;

import com.melek.gestionstock.dto.CommandeClientDto;
import com.melek.gestionstock.model.EtatCommande;
import io.swagger.annotations.Api;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

import static com.melek.gestionstock.utils.Constants.APP_ROOT;

@Api(APP_ROOT + "/commandesclients")
public interface ICommandeClientApi {

    @PostMapping(value = APP_ROOT + "/commandesclients/create")
    ResponseEntity<CommandeClientDto> save(@RequestBody CommandeClientDto dto);

    @PatchMapping(value = APP_ROOT + "/commandesclients/etat/update/{idCommandeClient}/{etatCommande}")
    ResponseEntity<CommandeClientDto> updateEtatCommande(@PathVariable("idCommandeClient") Integer idCommandeClient, @PathVariable("etatCommande") EtatCommande etatCommande);
    @PatchMapping(value = APP_ROOT + "/commandesclients/quantite/update/{idCommandeClient}/{idLigneCommande}/{quantity}")
    ResponseEntity<CommandeClientDto> updateQuantiteCommande(@PathVariable("idCommandeClient") Integer idCommande, @PathVariable("idLigneCommande") Integer idLigneCommande, @PathVariable("quantity") BigDecimal quantity);
    @PatchMapping(value = APP_ROOT + "/commandesclients/client/update/{idCommande}/{idClient}")
    ResponseEntity<CommandeClientDto> updateClient(@PathVariable("idCommande") Integer idCommande, @PathVariable("idClient") Integer idClient);
    @GetMapping(value = APP_ROOT + "/commandesclients/{idCommandeClient}")
    ResponseEntity<CommandeClientDto> findById(Integer idCommandeClient);
    @GetMapping(value = APP_ROOT + "/commandesclients/{codeCommandeClient}")
    ResponseEntity<CommandeClientDto> findByCode(@PathVariable("codeCommandeClient") String code);
    @GetMapping(value = APP_ROOT + "/commandesclients/all")
    ResponseEntity<List<CommandeClientDto>> findAll();
    @DeleteMapping(value = APP_ROOT + "/commandesclients/{idCommandeClient}")
    ResponseEntity<Void> delete(@PathVariable("idCommandeClient") Integer id);
}
