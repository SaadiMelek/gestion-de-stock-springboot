package com.melek.gestionstock.repository;

import com.melek.gestionstock.model.LigneCommandeClient;
import com.melek.gestionstock.model.LigneVente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LigneVenteRepository extends JpaRepository<LigneVente, Integer> {
}
