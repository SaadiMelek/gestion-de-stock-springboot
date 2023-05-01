package com.melek.gestionstock.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "LigneVente")
public class LigneVente extends AbstractEntity {

    private BigDecimal quantite;
    private BigDecimal prixUnitaire;
    @Column(name="idEntreprise")
    private Integer idEntreprise;

    @ManyToOne
    @JoinColumn(name = "idvente")
    private Vente vente;

    @ManyToOne
    @JoinColumn(name = "idArticle")
    private Article article;
}
