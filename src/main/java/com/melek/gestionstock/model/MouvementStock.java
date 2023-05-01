package com.melek.gestionstock.model;

import lombok.*;

import javax.persistence.*;
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
    @Column(name="idEntreprise")
    private Integer idEntreprise;
    private TypeMouvementStock typeMouvementStock;
    private SourceMouvementStock sourceMouvementStock;
    @ManyToOne
    @JoinColumn(name = "idArticle")
    private Article article;
}
