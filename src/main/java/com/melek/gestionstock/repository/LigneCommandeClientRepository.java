package com.melek.gestionstock.repository;

import com.melek.gestionstock.model.LigneCommandeClient;
import com.melek.gestionstock.model.LigneCommandeFournisseur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LigneCommandeClientRepository extends JpaRepository<LigneCommandeClient, Integer> {
    List<LigneCommandeClient> findAllByCommandeClientId(Integer id);
    List<LigneCommandeClient> findAllByArticleId(Integer idArticle);
}
