package com.melek.gestionstock.controller.api;

import com.melek.gestionstock.dto.FournisseurDto;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.melek.gestionstock.utils.Constants.FOURNISSEUR_ENDPOINT;

@Api(FOURNISSEUR_ENDPOINT)
public interface IFournisseurApi {

    @PostMapping(value = FOURNISSEUR_ENDPOINT + "/create")
    FournisseurDto save(@RequestBody FournisseurDto dto);
    @GetMapping(value = FOURNISSEUR_ENDPOINT + "/{idFournisseur}")
    FournisseurDto findById(@PathVariable("idFournisseur") Integer id);
    @GetMapping(value = FOURNISSEUR_ENDPOINT + "/all")
    List<FournisseurDto> findAll();
    @DeleteMapping(value = FOURNISSEUR_ENDPOINT + "/{idFournisseur}")
    void delete(@PathVariable("idFournisseur") Integer id);
}
