package com.melek.gestionstock.repository;

import com.melek.gestionstock.model.MouvementStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface MouvementStockRepository extends JpaRepository<MouvementStock, Integer> {
    @Query("select sum(m.quantite) from MouvementStock m where m.article.id = :idArticle")
    BigDecimal stockReelArticle(Integer idArticle);

    List<MouvementStock> findAllByArticleId(Integer idArticle);
}
