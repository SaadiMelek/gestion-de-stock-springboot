package com.melek.gestionstock.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
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
    private Integer idEntreprise;

    @ManyToOne
    @JoinColumn(name = "idvente")
    private Vente vente;
}
