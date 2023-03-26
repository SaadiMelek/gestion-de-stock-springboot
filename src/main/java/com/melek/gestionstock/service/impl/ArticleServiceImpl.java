package com.melek.gestionstock.service.impl;

import com.melek.gestionstock.dto.ArticleDto;
import com.melek.gestionstock.exception.EntityNotFoundException;
import com.melek.gestionstock.exception.ErrorCodes;
import com.melek.gestionstock.exception.InvalidEntityException;
import com.melek.gestionstock.model.Article;
import com.melek.gestionstock.repository.ArticleRepository;
import com.melek.gestionstock.service.IArticleService;
import com.melek.gestionstock.validator.ArticleValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service("ArticleServiceImpl")
@Slf4j
public class ArticleServiceImpl implements IArticleService {

    private ArticleRepository articleRepository;

    @Autowired
    public ArticleServiceImpl(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @Override
    public ArticleDto save(ArticleDto dto) {
        List<String> errors = ArticleValidator.validate(dto);
        if (!errors.isEmpty()) {
            log.error("Article is not valid {}", dto);
            throw new InvalidEntityException("L'article n'est pas valide", ErrorCodes.ARTICLE_NOT_VALID, errors);
        }
        return ArticleDto.fromEntity(articleRepository.save(ArticleDto.toEntity(dto)));
    }

    @Override
    public ArticleDto findById(Integer id) {
        if (id == null) {
            log.error("Article id is null");
            return null;
        }
        Optional<Article> article = articleRepository.findById(id);
        ArticleDto dto = ArticleDto.fromEntity(article.get());
        return Optional.of(dto).orElseThrow(
                () -> new EntityNotFoundException(
                        "Aucun article avec l'id ' " + id + " ' n'est trouvé dans la BDD",
                        ErrorCodes.ARTICLE_NOT_FOUND)
        );
    }

    @Override
    public ArticleDto findByCodeArticle(String codeArticle) {
        if (StringUtils.hasLength(codeArticle)) {
            log.error("Article code is null");
            return null;
        }
        Optional<Article> article = articleRepository.findByCodeArticle(codeArticle);
        ArticleDto dto = ArticleDto.fromEntity(article.get());
        return Optional.of(dto).orElseThrow(
                () -> new EntityNotFoundException(
                        "Aucun article avec le code ' " + codeArticle + " ' n'est trouvé dans la BDD",
                        ErrorCodes.ARTICLE_NOT_FOUND)
        );
    }

    @Override
    public List<ArticleDto> findAll() {
        return articleRepository.findAll().stream()
                .map(article -> ArticleDto.fromEntity(article))
                //.map(ArticleDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        if (id == null) {
            log.error("Article id is null");
            return;
        }
        articleRepository.deleteById(id);
    }
}
