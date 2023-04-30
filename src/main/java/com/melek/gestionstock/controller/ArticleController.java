package com.melek.gestionstock.controller;

import com.melek.gestionstock.controller.api.IArticleApi;
import com.melek.gestionstock.dto.ArticleDto;
import com.melek.gestionstock.dto.LigneCommandeClientDto;
import com.melek.gestionstock.dto.LigneCommandeFournisseurDto;
import com.melek.gestionstock.dto.LigneVenteDto;
import com.melek.gestionstock.service.IArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ArticleController implements IArticleApi {

    //@Autowired // field injection
    //@Qualifier("ArticleServiceImpl")
    private IArticleService articleService;

    /*@Autowired // Getter injection
    public IArticleService getArticleService() {
        return articleService;
    }*/

    @Autowired // contractor injection
    public ArticleController(@Qualifier("ArticleServiceImpl") IArticleService articleService) {
        this.articleService = articleService;
    }

    @Override
    public ArticleDto save(ArticleDto dto) {
        return articleService.save(dto);
    }

    @Override
    public ArticleDto findById(Integer id) {
        return articleService.findById(id);
    }

    @Override
    public ArticleDto findByCodeArticle(String codeArticle) {
        return articleService.findByCodeArticle(codeArticle);
    }

    @Override
    public List<ArticleDto> findAll() {
        return articleService.findAll();
    }

    @Override
    public void delete(Integer id) {
        articleService.delete(id);
    }

    @Override
    public List<LigneVenteDto> findHistoriqueVentes(Integer idArticle) {
        return articleService.findHistoriqueVentes(idArticle);
    }

    @Override
    public List<LigneCommandeClientDto> findHistoriqueCommandesClient(Integer idArticle) {
        return articleService.findHistoriqueCommandesClient(idArticle);
    }

    @Override
    public List<LigneCommandeFournisseurDto> findHistoriqueCommandesFournisseur(Integer idArticle) {
        return articleService.findHistoriqueCommandesFournisseur(idArticle);
    }

    @Override
    public List<ArticleDto> findAllArticlesByIdCategory(Integer idCategory) {
        return articleService.findAllArticlesByIdCategory(idCategory);
    }
}
