package com.melek.gestionstock.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "Vente")
public class Vente extends AbstractEntity {

    private String code;
    private Instant dateVente;
    private String commentaire;
    private Integer idEntreprise;
}
