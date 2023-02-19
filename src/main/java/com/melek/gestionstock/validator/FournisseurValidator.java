package com.melek.gestionstock.validator;

import com.melek.gestionstock.dto.FournisseurDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class FournisseurValidator {

    public static List<String> validate(FournisseurDto dto) {
        List<String> errors = new ArrayList<>();
        if(dto == null) {
            errors.add("Veuillez renseigner le nom du founisseur");
            errors.add("Veuillez renseigner le prénom du founisseur");
            errors.add("Veuillez renseigner l'adresse mail du founisseur");
            errors.add("Veuillez renseigner le numéro de téléphone du founisseur");
        } else {
            if (!StringUtils.hasLength(dto.getNom())) {
                errors.add("Veuillez renseigner le nom du founisseur");
            }
            if (!StringUtils.hasLength(dto.getPrenom())) {
                errors.add("Veuillez renseigner le prénom du founisseur");
            }
            if (!StringUtils.hasLength(dto.getEmail())) {
                errors.add("Veuillez renseigner l'adresse mail du founisseur");
            }
            if (dto.getNumTel() == null) {
                errors.add("Veuillez renseigner le numéro de téléphone du founisseur");
            }
        }
        return errors;
    }
}
