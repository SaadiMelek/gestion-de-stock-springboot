package com.melek.gestionstock.validator;

import com.melek.gestionstock.dto.MouvementStockDto;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class MouvementStockValidator {

    public static List<String> validate(MouvementStockDto dto) {
        List<String> errors = new ArrayList<>();
        if (dto == null) {
            errors.add("DateMouvement null");
            errors.add("veuillez renseigner la quantité");
            errors.add("veuillez renseigner l'article");
            errors.add("veuillez renseigner le type de mouvement");
            return errors;
        }
        if (dto.getDateMouvement() == null) {
            errors.add("DateMouvement null");
        }
        if (dto.getQuantite() == null || dto.getQuantite().compareTo(BigDecimal.ZERO) == 0) {
            errors.add("veuillez renseigner la quantité");
        }
        if (dto.getArticle() == null || dto.getArticle().getId() == null) {
            errors.add("veuillez renseigner l'article");
        }
        if (!StringUtils.hasLength(dto.getTypeMouvementStock().name())) {
            errors.add("veuillez renseigner le type de mouvement");
        }

        return errors;
    }
}
