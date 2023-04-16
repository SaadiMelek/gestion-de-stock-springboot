package com.melek.gestionstock.model;

import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
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
    @JoinColumn(name="idEntreprise")
    private Integer idEntreprise;

    @Embedded
    private Adresse adresse;

    @OneToMany(mappedBy = "client")
    private List<CommandeClient> commandeClients;
}
