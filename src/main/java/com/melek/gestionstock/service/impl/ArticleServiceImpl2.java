package com.melek.gestionstock.service.impl;

import com.melek.gestionstock.dto.ArticleDto;
import com.melek.gestionstock.dto.LigneCommandeClientDto;
import com.melek.gestionstock.dto.LigneCommandeFournisseurDto;
import com.melek.gestionstock.dto.LigneVenteDto;
import com.melek.gestionstock.service.IArticleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("ArticleServiceImpl2")
public class ArticleServiceImpl2 implements IArticleService {

    @Override
    public ArticleDto save(ArticleDto dto) {
        return null;
    }

    @Override
    public ArticleDto findById(Integer id) {
        return null;
    }

    @Override
    public ArticleDto findByCodeArticle(String codeArticle) {
        return null;
    }

    @Override
    public List<ArticleDto> findAll() {
        return null;
    }

    @Override
    public List<LigneVenteDto> findHistoriqueVentes(Integer idArticle) {
        return null;
    }

    @Override
    public List<LigneCommandeClientDto> findHistoriqueCommandesClient(Integer idArticle) {
        return null;
    }

    @Override
    public List<LigneCommandeFournisseurDto> findHistoriqueCommandesFournisseur(Integer idArticle) {
        return null;
    }

    @Override
    public List<ArticleDto> findAllArticlesByIdCategory(Integer idCategory) {
        return null;
    }

    @Override
    public void delete(Integer id) {

    }
}
