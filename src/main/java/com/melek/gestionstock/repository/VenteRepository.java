package com.melek.gestionstock.repository;

import com.melek.gestionstock.model.Utilisateur;
import com.melek.gestionstock.model.Vente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VenteRepository extends JpaRepository<Integer, Vente> {
}
