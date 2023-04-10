package com.melek.gestionstock.controller.api;

import com.melek.gestionstock.dto.CommandeClientDto;
import io.swagger.annotations.Api;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.melek.gestionstock.utils.Constants.APP_ROOT;

@Api(APP_ROOT + "/commandesclients")
public interface ICommandeClientApi {

    @PostMapping(value = APP_ROOT + "/commandesclients/create")
    ResponseEntity<CommandeClientDto> save(@RequestBody CommandeClientDto dto);
    @GetMapping(value = APP_ROOT + "/commandesclients/{idCommandeClient}")
    ResponseEntity<CommandeClientDto> findById(Integer idCommandeClient);
    @GetMapping(value = APP_ROOT + "/commandesclients/{codeCommandeClient}")
    ResponseEntity<CommandeClientDto> findByCode(@PathVariable("codeCommandeClient") String code);
    @GetMapping(value = APP_ROOT + "/commandesclients/all")
    ResponseEntity<List<CommandeClientDto>> findAll();
    @DeleteMapping(value = APP_ROOT + "/commandesclients/{idCommandeClient}")
    ResponseEntity delete(@PathVariable("idCommandeClient") Integer id);
}
