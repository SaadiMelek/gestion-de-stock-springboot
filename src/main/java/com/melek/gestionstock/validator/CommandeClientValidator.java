package com.melek.gestionstock.validator;

import com.melek.gestionstock.dto.ArticleDto;
import com.melek.gestionstock.dto.CommandeClientDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class CommandeClientValidator {

    public static List<String> validate(CommandeClientDto dto) {
        List<String> errors = new ArrayList<>();
        if (dto == null) {
            errors.add("Veuillez renseigner le code de la commande ");
            errors.add("Veuillez renseigner la date de la commande ");
            errors.add("Veuillez renseigner l'état de la commande ");
            errors.add("Veuillez renseigner le client de la commande ");
            return errors;
        }
        if (!StringUtils.hasLength(dto.getCode())) {
            errors.add("Veuillez renseigner le code de la commande ");
        }
        if (dto.getDateCommande() == null) {
            errors.add("Veuillez renseigner la date de la commande ");
        }
        if (!StringUtils.hasLength(dto.getEtatCommande().toString())) {
            errors.add("Veuillez renseigner l'état de la commande ");
        }
        if (dto.getClient() == null || dto.getClient().getId() == null) {
            errors.add("Veuillez renseigner le client de la commande ");
        }
        return errors;
    }
}
