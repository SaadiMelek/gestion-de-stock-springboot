package com.melek.gestionstock.dto;

import com.melek.gestionstock.model.MouvementStock;
import com.melek.gestionstock.model.TypeMouvementStock;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;

@Data
@Builder
public class MouvementStockDto {
    private Integer id;
    private Instant dateMouvement;
    private BigDecimal quantite;
    private TypeMouvementStock typeMouvementStock;
    private ArticleDto article;

    public MouvementStockDto fromEntity(MouvementStock mouvementStock) {
        if (mouvementStock == null) {
            return null;
            // TODO throw an exception
        }
        return MouvementStockDto.builder()
                .id(mouvementStock.getId())
                .dateMouvement(mouvementStock.getDateMouvement())
                .quantite(mouvementStock.getQuantite())
                .build();
    }

    public MouvementStock toEntity(MouvementStockDto mouvementStockDto) {
        if (mouvementStockDto == null) {
            return null;
            // TODO throw an exception
        }
        MouvementStock mouvementStock = new MouvementStock();
        mouvementStock.setId(mouvementStockDto.getId());
        mouvementStock.setDateMouvement(mouvementStockDto.getDateMouvement());
        mouvementStock.setQuantite(mouvementStockDto.getQuantite());
        return mouvementStock;
    }
}
