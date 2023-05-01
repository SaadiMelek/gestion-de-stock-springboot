package com.melek.gestionstock.model;

import lombok.*;

import javax.persistence.*;
import java.time.Instant;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "CommandeClient")
public class CommandeClient extends AbstractEntity {

    private String code;
    private Instant dateCommande;
    private EtatCommande etatCommande;
    @Column(name="idEntreprise")
    private Integer idEntreprise;

    @ManyToOne
    @JoinColumn(name = "idclient")
    private Client client;

    @OneToMany(mappedBy = "commandeClient", cascade = CascadeType.ALL)
    private List<LigneCommandeClient> ligneCommandeClients;
}
