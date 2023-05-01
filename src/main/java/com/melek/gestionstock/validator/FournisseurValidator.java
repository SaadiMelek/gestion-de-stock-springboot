package com.melek.gestionstock.validator;

import com.melek.gestionstock.dto.FournisseurDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class FournisseurValidator {

    public static List<String> validate(FournisseurDto dto) {
        List<String> errors = new ArrayList<>();
        if(dto == null) {
            errors.add("Veuillez renseigner le nom du fournisseur");
            errors.add("Veuillez renseigner le prénom du fournisseur");
            errors.add("Veuillez renseigner l'adresse mail du fournisseur");
            errors.add("Veuillez renseigner le numéro de téléphone du fournisseur");
        } else {
            if (!StringUtils.hasLength(dto.getNom())) {
                errors.add("Veuillez renseigner le nom du fournisseur");
            }
            if (!StringUtils.hasLength(dto.getPrenom())) {
                errors.add("Veuillez renseigner le prénom du fournisseur");
            }
            if (!StringUtils.hasLength(dto.getEmail())) {
                errors.add("Veuillez renseigner l'adresse mail du fournisseur");
            }
            if (dto.getNumTel() == null) {
                errors.add("Veuillez renseigner le numéro de téléphone du fournisseur");
            }
            errors.addAll(AdresseValidator.validate(dto.getAdresse()));
        }
        return errors;
    }
}
