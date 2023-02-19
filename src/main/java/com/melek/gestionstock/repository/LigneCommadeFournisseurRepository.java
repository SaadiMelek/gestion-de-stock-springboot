package com.melek.gestionstock.repository;

import com.melek.gestionstock.model.Article;
import com.melek.gestionstock.model.LigneCommandeFournisseur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LigneCommadeFournisseurRepository extends JpaRepository<Integer, LigneCommandeFournisseur> {
}
