package com.melek.gestionstock.service;

import com.melek.gestionstock.dto.ArticleDto;

import java.util.List;

public interface IArticleService {

    ArticleDto save(ArticleDto dto);

    ArticleDto findById(Integer id);

    ArticleDto findByCodeArticle(String codeArticle);

    List<ArticleDto> findAll();

    void delete(Integer id);
}
