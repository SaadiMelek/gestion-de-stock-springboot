package com.melek.gestionstock.repository;

import com.melek.gestionstock.model.Utilisateur;
import com.melek.gestionstock.model.Vente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VenteRepository extends JpaRepository<Vente, Integer> {

    Optional<Vente> findByCode(String code);
}
