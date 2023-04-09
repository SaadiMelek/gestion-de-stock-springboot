package com.melek.gestionstock.controller.api;

import com.melek.gestionstock.dto.CategoryDto;
import io.swagger.annotations.*;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.melek.gestionstock.utils.Constants.APP_ROOT;

@Api(APP_ROOT + "/categories")
public interface ICategoryApi {

    @PostMapping(value = APP_ROOT + "/categories/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Enregistre une category", notes = "Cette méthode permet d'ajouter / modifier une category", response = CategoryDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'objet category créé / modifié"),
            @ApiResponse(code = 400, message = "L'objet category n'esy pas valide")
    })
    CategoryDto save(@RequestBody CategoryDto dto);

    @GetMapping(value = APP_ROOT + "/categories/{idCategory}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher une category par ID", notes = "Cette méthode permet de chercher une category par son ID", response = CategoryDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'objet category a été trouvé dans la BDD"),
            @ApiResponse(code = 404, message = "Aucun category n'existe dans la BDD avec l'ID fourni")
    })
    CategoryDto findById(@PathVariable("idCategory") Integer idCategory);

    @GetMapping(value = APP_ROOT + "/categories/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des categories", notes = "Cette méthode permet de renvoyer la liste des categories qui existent dans la BDD", responseContainer = "List<CategoryDto>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des categories / liste vide")
    })
    List<CategoryDto> findAll();

    @GetMapping(value = APP_ROOT + "/categories/{codeCategory}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher une category par son code", notes = "Cette méthode permet de chercher une category par son code", response = CategoryDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'objet category a été trouvé dans la BDD"),
            @ApiResponse(code = 404, message = "Aucun category n'existe dans la BDD avec le code fourni")
    })
    CategoryDto findByCode(/*@ApiParam(value = "Accepted values [CAT, CAT2, CAT3]")*/ @PathVariable("codeCategory") String codeCategory);

    @DeleteMapping(value = APP_ROOT + "/categories/delete/{idCategory}")
    @ApiOperation(value = "Supprimer une category par son ID", notes = "Cette méthode permet de supprimer une category par son ID", response = CategoryDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La category a été supprimé")
    })
    void delete(@PathVariable("idCategory") Integer id);
}
