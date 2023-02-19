package com.melek.gestionstock.validator;

import com.melek.gestionstock.dto.CategoryDto;
import com.melek.gestionstock.dto.UtilisateurDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class UtilisateurValidator {

    public static List<String> validate(UtilisateurDto utilisateurDto) {
        List<String> errors = new ArrayList<>();

        if (utilisateurDto == null) {
            errors.add("Veuillez renseigner le nom de l'utilisateur");
            errors.add("Veuillez renseigner le prénom de l'utilisateur");
            errors.add("Veuillez renseigner le mot de passe de l'utilisateur");
            errors.add("Veuillez renseigner l'adresse de l'utilisateur");
        } else {
            if (!StringUtils.hasLength(utilisateurDto.getNom())) {
                errors.add("Veuillez renseigner le nom de l'utilisateur");
            }
            if (!StringUtils.hasLength(utilisateurDto.getPrenom())) {
                errors.add("Veuillez renseigner le prénom de l'utilisateur");
            }
            if (!StringUtils.hasLength(utilisateurDto.getEmail())) {
                errors.add("Veuillez renseigner l'émail de l'utilisateur");
            }
            if (!StringUtils.hasLength(utilisateurDto.getPassword())) {
                errors.add("Veuillez renseigner le mot de passe de l'utilisateur");
            }
            if (utilisateurDto.getDateNaissance() == null) {
                errors.add("Veuillez renseigner la date de naissance de l'utilisateur");
            }
            if (utilisateurDto.getAdresse() == null) {
                errors.add("Veuillez renseigner l'adresse de l'utilisateur");
            } else {
                if (!StringUtils.hasLength(utilisateurDto.getAdresse().getAdresse1())) {
                    errors.add("Veuillez renseigner l'adresse 1 de l'utilisateur");
                }
                if (!StringUtils.hasLength(utilisateurDto.getAdresse().getVille())) {
                    errors.add("Veuillez renseigner la ville de l'utilisateur");
                }
                if (!StringUtils.hasLength(utilisateurDto.getAdresse().getCodePostale())) {
                    errors.add("Veuillez renseigner le code postale de l'utilisateur");
                }
                if (!StringUtils.hasLength(utilisateurDto.getAdresse().getPays())) {
                    errors.add("Veuillez renseigner le pays de l'utilisateur");
                }
            }
        }
        return errors;
    }
}
