package com.melek.gestionstock.controller.api;

import com.melek.gestionstock.dto.ArticleDto;
import com.melek.gestionstock.dto.LigneCommandeClientDto;
import com.melek.gestionstock.dto.LigneCommandeFournisseurDto;
import com.melek.gestionstock.dto.LigneVenteDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import static com.melek.gestionstock.utils.Constants.APP_ROOT;

import java.util.List;

@Api(APP_ROOT + "/articles")
public interface IArticleApi {

    @PostMapping(value = APP_ROOT + "/articles/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Enregistre un article", notes = "Cette méthode permet d'ajouter / modifier un article", response = ArticleDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'objet article créé / modifié"),
            @ApiResponse(code = 400, message = "L'objet article n'esy pas valide")
    })
    ArticleDto save(@RequestBody ArticleDto dto);

    @GetMapping(value = APP_ROOT + "/articles/id/{idArticle}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher un article par ID", notes = "Cette méthode permet de chercher un article par son ID", response = ArticleDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'objet article a été trouvé dans la BDD"),
            @ApiResponse(code = 404, message = "Aucun article n'existe dans la BDD avec l'ID fourni")
    })
    ArticleDto findById(@PathVariable("idArticle") Integer id);

    @GetMapping(value = APP_ROOT + "/articles/code/{codeArticle}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher un article par CODE", notes = "Cette méthode permet de chercher un article par son code", response = ArticleDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'objet article a été trouvé dans la BDD"),
            @ApiResponse(code = 404, message = "Aucun article n'existe dans la BDD avec le code fourni")
    })
    ArticleDto findByCodeArticle(@PathVariable("codeArticle") String codeArticle);

    @GetMapping(value = APP_ROOT + "/articles/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des articles", notes = "Cette méthode permet de renvoyer la liste des article qui existent dans la BDD", responseContainer = "List<ArticleDto>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des articles / liste vide")
    })
    List<ArticleDto> findAll();

    @DeleteMapping(value = APP_ROOT + "/articles/delete/{idArticle}")
    @ApiOperation(value = "Supprimer un article par son ID", notes = "Cette méthode permet de supprimer un article par son ID", response = ArticleDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'article a été supprimé")
    })
    void delete(@PathVariable("idArticle") Integer id);
    @GetMapping(value = APP_ROOT + "/articles/historique/vente/{idArticle}")
    List<LigneVenteDto> findHistoriqueVentes(Integer idArticle);
    @GetMapping(value = APP_ROOT + "/articles/historique/commandeclient/{idArticle}")
    List<LigneCommandeClientDto> findHistoriqueCommandesClient(Integer idArticle);
    @GetMapping(value = APP_ROOT + "/articles/historique/commandefournisseur/{idArticle}")
    List<LigneCommandeFournisseurDto> findHistoriqueCommandesFournisseur(Integer idArticle);
    @GetMapping(value = APP_ROOT + "/articles/filter/{idCategory}")
    List<ArticleDto> findAllArticlesByIdCategory(Integer idCategory);
}
