package com.melek.gestionstock.validator;

import com.melek.gestionstock.dto.ArticleDto;
import com.melek.gestionstock.dto.CategoryDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class ArticleValidator {

    public static List<String> validate(ArticleDto dto) {
        List<String> errors = new ArrayList<>();
        if(dto == null) {
            errors.add("Veuillez renseigner le code de l'article'");
            errors.add("Veuillez renseigner la désignation de l'article'");
            errors.add("Veuillez renseigner le prix unitaire HT de l'article'");
            errors.add("Veuillez renseigner le taux TVA de l'article'");
            errors.add("Veuillez renseigner le prix TTC de l'article'");
            errors.add("Veuillez renseigner une catégorie de l'article'");
        } else {
            if (!StringUtils.hasLength(dto.getCodeArticle())) {
                errors.add("Veuillez renseigner le code de l'article'");
            }
            if (!StringUtils.hasLength(dto.getDesignation())) {
                errors.add("Veuillez renseigner la désignation de l'article'");
            }
            if (dto.getPrixUnitaireHt() == null) {
                errors.add("Veuillez renseigner le prix unitaire HT de l'article'");
            }
            if (dto.getTaxTva() == null) {
                errors.add("Veuillez renseigner le taux TVA de l'article'");
            }
            if (dto.getPrixUnitaireTtc() == null) {
                errors.add("Veuillez renseigner le prix TTC de l'article'");
            }
            if (dto.getCategory() == null) {
                errors.add("Veuillez renseigner une catégorie de l'article'");
            }
        }
        return errors;
    }
}
