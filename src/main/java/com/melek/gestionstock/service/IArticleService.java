package com.melek.gestionstock.service;

import com.melek.gestionstock.dto.ArticleDto;
import com.melek.gestionstock.dto.LigneCommandeClientDto;
import com.melek.gestionstock.dto.LigneCommandeFournisseurDto;
import com.melek.gestionstock.dto.LigneVenteDto;

import java.util.List;

public interface IArticleService {
    ArticleDto save(ArticleDto dto);
    ArticleDto findById(Integer id);
    ArticleDto findByCodeArticle(String codeArticle);
    List<ArticleDto> findAll();
    List<LigneVenteDto> findHistoriqueVentes(Integer idArticle);
    List<LigneCommandeClientDto> findHistoriqueCommandesClient(Integer idArticle);
    List<LigneCommandeFournisseurDto> findHistoriqueCommandesFournisseur(Integer idArticle);
    List<ArticleDto> findAllArticlesByIdCategory(Integer idCategory);
    void delete(Integer id);
}