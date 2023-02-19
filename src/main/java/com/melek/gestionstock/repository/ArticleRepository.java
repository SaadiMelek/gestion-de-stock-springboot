package com.melek.gestionstock.repository;

import com.melek.gestionstock.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Integer> {

    /*Article findByCodeArticle(String code);

    @Query("select a from Article a where codeArticle=:code and designation=:designation")
    List<Article> findByCustomJpqlQuery(@Param("code") String c, String designation);

    @Query(value = "select * from article where codeArticle = :code", nativeQuery = true)
    List<Article> findByCustomNativeQuery(@Param("code") String c);

    List<Article> findByCodeArticleIgnoreCaseAndDesignationIgnoreCase(String codeArticle, String designation);*/
}
