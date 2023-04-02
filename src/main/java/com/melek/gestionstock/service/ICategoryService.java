package com.melek.gestionstock.service;

import com.melek.gestionstock.dto.CategoryDto;
import com.melek.gestionstock.dto.ClientDto;
import com.melek.gestionstock.model.Category;

import java.util.List;
import java.util.Optional;

public interface ICategoryService {
    CategoryDto save(CategoryDto dto);
    CategoryDto findById(Integer id);
    List<CategoryDto> findAll();
    void delete(Integer id);
    CategoryDto findByCode(String code);
}
