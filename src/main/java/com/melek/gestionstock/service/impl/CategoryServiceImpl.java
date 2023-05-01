package com.melek.gestionstock.service.impl;

import com.melek.gestionstock.dto.CategoryDto;
import com.melek.gestionstock.exception.EntityNotFoundException;
import com.melek.gestionstock.exception.ErrorCodes;
import com.melek.gestionstock.exception.InvalidEntityException;
import com.melek.gestionstock.exception.InvalidOperationException;
import com.melek.gestionstock.model.*;
import com.melek.gestionstock.repository.ArticleRepository;
import com.melek.gestionstock.repository.CategoryRepository;
import com.melek.gestionstock.service.ICategoryService;
import com.melek.gestionstock.validator.CategoryValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CategoryServiceImpl implements ICategoryService {

    private CategoryRepository categoryRepository;
    private ArticleRepository articleRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository, ArticleRepository articleRepository) {
        this.categoryRepository = categoryRepository;
        this.articleRepository = articleRepository;
    }

    @Override
    public CategoryDto save(CategoryDto dto) {
        List<String> errors = CategoryValidator.validate(dto);
        if (!errors.isEmpty()) {
            log.error("Category is not valid {}", dto);
            throw new InvalidEntityException("La Category n'est pas valide", ErrorCodes.CATEGORY_NOT_VALID, errors);
        }
        Category dd = categoryRepository.save(CategoryDto.toEntity(dto));
        CategoryDto cc = CategoryDto.fromEntity(
                dd
        );
        return cc;
    }

    @Override
    public CategoryDto findById(Integer id) {
        if (id == null) {
            log.error("Category id is null");
            return null;
        }
        /*Optional<Category> category = categoryRepository.findById(id);
        CategoryDto dto = CategoryDto.fromEntity(category.get());
        return Optional.of(dto).orElseThrow(
                () -> new EntityNotFoundException(
                        "Aucun Category avec l'id ' " + id + " ' n'est trouvé dans la BDD",
                        ErrorCodes.CATEGORY_NOT_FOUND)
        );*/
        return categoryRepository.findById(id)
                .map(CategoryDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Aucun Category avec l'id ' " + id + " ' n'est trouvé dans la BDD",
                        ErrorCodes.CATEGORY_NOT_FOUND
                ));
    }

    @Override
    public List<CategoryDto> findAll() {
        return categoryRepository.findAll().stream()
                .map(CategoryDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        if (id == null) {
            log.error("Category id is null");
            return;
        }
        List<Article> articles = articleRepository.findAllByCategoryId(id);
        if (!articles.isEmpty()) {
            throw new InvalidOperationException("Impossible de supprimer cette catégorie, elle est déjà utilisé", ErrorCodes.CATEGORY_ALREADY_IN_USE);
        }
        categoryRepository.deleteById(id);
    }

    @Override
    public CategoryDto findByCode(String code) {
        if (StringUtils.hasLength(code)) {
            log.error("Category code is null");
            return null;
        }
        return categoryRepository.findCategoryByCode(code)
                .map(CategoryDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Aucun Category avec le code ' " + code + " ' n'est trouvé dans la BDD",
                        ErrorCodes.CATEGORY_NOT_FOUND
                ));
    }
}
