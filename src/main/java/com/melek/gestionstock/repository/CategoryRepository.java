package com.melek.gestionstock.repository;

import com.melek.gestionstock.model.Article;
import com.melek.gestionstock.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Integer, Category> {
}
