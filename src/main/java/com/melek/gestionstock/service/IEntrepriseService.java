package com.melek.gestionstock.service;

import com.melek.gestionstock.dto.ClientDto;
import com.melek.gestionstock.dto.EntrepriseDto;

import java.util.List;

public interface IEntrepriseService {
    EntrepriseDto save(EntrepriseDto dto);
    EntrepriseDto findById(Integer id);
    List<EntrepriseDto> findAll();
    void delete(Integer id);
}
