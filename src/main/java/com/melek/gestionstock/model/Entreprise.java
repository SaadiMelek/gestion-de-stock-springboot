package com.melek.gestionstock.model;

import lombok.*;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "Entreprise")
public class Entreprise extends AbstractEntity {
    private String nom;
    private String description;
    private String codeFiscal;
    private String photo;
    private String email;
    private String numTel;
    private String siteWeb;

    @Embedded
    private Adresse adresse;

    @OneToMany(mappedBy = "entreprise")
    private List<Utilisateur> utilisateurs;
}
