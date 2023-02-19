package com.melek.gestionstock.dto;

import com.melek.gestionstock.model.LigneCommandeClient;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class LigneCommandeClientDto {
    private Integer id;
    private BigDecimal quantite;
    private BigDecimal prixUnitaire;
    private ArticleDto article;
    private CommandeClientDto commandeClient;

    public static LigneCommandeClientDto fromEntity(LigneCommandeClient ligneCommandeClient) {
        if (ligneCommandeClient == null) {
            return null;
            // TODO throw an exception
        }
        return LigneCommandeClientDto.builder()
                .id(ligneCommandeClient.getId())
                .quantite(ligneCommandeClient.getQuantite())
                .prixUnitaire(ligneCommandeClient.getPrixUnitaire())
                .build();
    }

    public static LigneCommandeClient toEntity(LigneCommandeClientDto ligneCommandeClientDto) {
        if (ligneCommandeClientDto == null) {
            return null;
            // TODO throw an exception
        }
        LigneCommandeClient category = new LigneCommandeClient();
        category.setId(category.getId());
        category.setQuantite(ligneCommandeClientDto.getQuantite());
        category.setPrixUnitaire(ligneCommandeClientDto.getPrixUnitaire());
        return category;
    }
}
