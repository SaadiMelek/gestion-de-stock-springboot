package com.melek.gestionstock.repository;

import com.melek.gestionstock.model.Article;
import com.melek.gestionstock.model.MouvementStock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MvtStkRepository extends JpaRepository<Integer, MouvementStock> {
}
