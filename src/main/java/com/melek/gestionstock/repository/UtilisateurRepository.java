package com.melek.gestionstock.repository;

import com.melek.gestionstock.model.Article;
import com.melek.gestionstock.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Integer> {
    Optional<Utilisateur> findUtilisateurByEmail(String email);
}
