package com.melek.gestionstock.controller.api;

import com.melek.gestionstock.dto.UtilisateurDto;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.melek.gestionstock.utils.Constants.COMMANDE_FOURNISSEUR_ENDPOINT;
import static com.melek.gestionstock.utils.Constants.UTILISATEUR_ENDPOINT;

@Api(UTILISATEUR_ENDPOINT)
public interface IUtilisateurApi {

    @PostMapping(value = UTILISATEUR_ENDPOINT + "/create")
    UtilisateurDto save(@RequestBody UtilisateurDto dto);
    @GetMapping(value = UTILISATEUR_ENDPOINT + "/{idUtilisateur}")
    UtilisateurDto findById(@PathVariable("idUtilisateur") Integer id);
    @GetMapping(value = UTILISATEUR_ENDPOINT + "/all")
    List<UtilisateurDto> findAll();
    @DeleteMapping(value = UTILISATEUR_ENDPOINT + "/{idUtilisateur}")
    void delete(@PathVariable("idUtilisateur") Integer id);
}
