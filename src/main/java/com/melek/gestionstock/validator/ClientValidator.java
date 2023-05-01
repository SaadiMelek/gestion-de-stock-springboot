package com.melek.gestionstock.validator;

import com.melek.gestionstock.dto.ArticleDto;
import com.melek.gestionstock.dto.ClientDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class ClientValidator {

    public static List<String> validate(ClientDto dto) {
        List<String> errors = new ArrayList<>();
        if(dto == null) {
            errors.add("Veuillez renseigner le nom du client");
            errors.add("Veuillez renseigner le prénom du client");
            errors.add("Veuillez renseigner l'adresse mail du client");
            errors.add("Veuillez renseigner le numéro de téléphone du client");
        } else {
            if (!StringUtils.hasLength(dto.getNom())) {
                errors.add("Veuillez renseigner le nom du client");
            }
            if (!StringUtils.hasLength(dto.getPrenom())) {
                errors.add("Veuillez renseigner le prénom du client");
            }
            if (!StringUtils.hasLength(dto.getEmail())) {
                errors.add("Veuillez renseigner l'adresse mail du client");
            }
            if (dto.getNumTel() == null) {
                errors.add("Veuillez renseigner le numéro de téléphone du client");
            }
            errors.addAll(AdresseValidator.validate(dto.getAdresse()));
        }
        return errors;
    }
}
