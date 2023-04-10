package com.melek.gestionstock.controller.api;

import com.melek.gestionstock.dto.EntrepriseDto;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.melek.gestionstock.utils.Constants.COMMANDE_FOURNISSEUR_ENDPOINT;
import static com.melek.gestionstock.utils.Constants.ENTREPRISE_ENDPOINT;

@Api(ENTREPRISE_ENDPOINT)
public interface IEntrepriseApi {

    @PostMapping(value = ENTREPRISE_ENDPOINT + "/create")
    EntrepriseDto save(@RequestBody EntrepriseDto dto);
    @GetMapping(value = ENTREPRISE_ENDPOINT + "/{idEntreprise}")
    EntrepriseDto findById(@PathVariable("idEntreprise") Integer id);
    @GetMapping(value = ENTREPRISE_ENDPOINT + "/all")
    List<EntrepriseDto> findAll();
    @DeleteMapping(value = ENTREPRISE_ENDPOINT + "/{idEntreprise}")
    void delete(@PathVariable("idEntreprise") Integer id);
}
