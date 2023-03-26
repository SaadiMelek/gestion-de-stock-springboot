package com.melek.gestionstock.service.impl;

import com.melek.gestionstock.dto.ArticleDto;
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
    public void delete(Integer id) {

    }
}
