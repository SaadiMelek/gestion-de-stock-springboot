package com.melek.gestionstock.service;

import com.melek.gestionstock.dto.FournisseurDto;

import java.util.List;

public interface IFournisseurService {
    FournisseurDto save(FournisseurDto dto);
    FournisseurDto findById(Integer id);
    List<FournisseurDto> findAll();
    void delete(Integer id);
}
