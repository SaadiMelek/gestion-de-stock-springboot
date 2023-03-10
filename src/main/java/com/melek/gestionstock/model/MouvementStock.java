package com.melek.gestionstock.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "MouvementStock")
public class MouvementStock extends AbstractEntity {

    private Instant dateMouvement;
    private BigDecimal quantite;
    private Integer idEntreprise;

    private TypeMouvementStock typeMouvementStock;

    @ManyToOne
    @JoinColumn(name = "idarticle")
    private Article article;
}
