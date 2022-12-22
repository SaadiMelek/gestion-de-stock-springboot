package com.melek.gestionstock.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

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

    @ManyToOne
    @JoinColumn(name = "idvente")
    private Vente vente;
}
