package com.melek.gestionstock.repository;

import com.melek.gestionstock.model.Article;
import com.melek.gestionstock.model.CommandeClient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CommandeClientRepository extends JpaRepository<CommandeClient, Integer> {
    Optional<CommandeClient> findCommandeClientByCode(String code);
}
