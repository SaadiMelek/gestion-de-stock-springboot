package com.melek.gestionstock.validator;

import com.melek.gestionstock.dto.AdresseDto;
import com.melek.gestionstock.dto.CategoryDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class AdresseValidator {
    public static List<String> validate(AdresseDto dto) {
        List<String> errors = new ArrayList<>();

        if (dto == null) {
            errors.add("Veuillez renseigner une adresse");
            errors.add("Veuillez renseigner Adresse1");
            errors.add("Veuillez renseigner la Ville");
            errors.add("Veuillez renseigner le pays");
            errors.add("Veuillez renseigner le code postal");
            return errors;
        }
        if (!StringUtils.hasLength(dto.getAdresse1())) {
            errors.add("Veuillez renseigner Adresse1");
        }
        if (!StringUtils.hasLength(dto.getVille())) {
            errors.add("Veuillez renseigner la Ville");
        }
        if (!StringUtils.hasLength(dto.getPays())) {
            errors.add("Veuillez renseigner le pays");
        }
        if (!StringUtils.hasLength(dto.getCodePostale())) {
            errors.add("Veuillez renseigner le code postal");
        }
        return errors;
    }
}
