package com.melek.gestionstock.model;

import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

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
@Table(name = "Client")
public class Client extends AbstractEntity{

    private String nom;
    private String prenom;
    private String photo;
    private String email;
    private String numTel;
    private Integer idEntreprise;

    @Embedded
    private Adresse adresse;

    @OneToMany(mappedBy = "client")
    private List<CommandeClient> commandeClients;
}
