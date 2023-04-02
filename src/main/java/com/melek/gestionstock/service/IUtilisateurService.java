package com.melek.gestionstock.service;

import com.melek.gestionstock.dto.UtilisateurDto;

import java.util.List;

public interface IUtilisateurService {
    UtilisateurDto save(UtilisateurDto dto);
    UtilisateurDto findById(Integer id);
    List<UtilisateurDto> findAll();
    void delete(Integer id);
}
