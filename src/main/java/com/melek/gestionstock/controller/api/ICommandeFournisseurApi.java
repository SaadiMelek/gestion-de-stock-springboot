package com.melek.gestionstock.controller.api;

import com.melek.gestionstock.dto.CommandeFournisseurDto;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
}
