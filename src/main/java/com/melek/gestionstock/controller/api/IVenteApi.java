package com.melek.gestionstock.controller.api;

import com.melek.gestionstock.dto.VenteDto;
import io.swagger.annotations.Api;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.melek.gestionstock.utils.Constants.*;

@Api(VENTE_ENDPOINT)
public interface IVenteApi {
    @PostMapping(value = VENTE_ENDPOINT + "/create")
    VenteDto save(@RequestBody VenteDto dto);
    @GetMapping(value = VENTE_ENDPOINT + "/{idVente}")
    VenteDto findById(@PathVariable("idVente") Integer id);
    @GetMapping(value = VENTE_ENDPOINT + "/{codeVente}")
    VenteDto findByCode(@PathVariable("codeVente") String code);
    @GetMapping(value = VENTE_ENDPOINT + "/all")
    List<VenteDto> findAll();
    @DeleteMapping(value = VENTE_ENDPOINT + "/{idVente}")
    void delete(@PathVariable("idVente") Integer id);
}
