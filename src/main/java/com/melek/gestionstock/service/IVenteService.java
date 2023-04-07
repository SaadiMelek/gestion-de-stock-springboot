package com.melek.gestionstock.service;

import com.melek.gestionstock.dto.VenteDto;

import java.util.List;

public interface IVenteService {

    VenteDto save(VenteDto dto);
    VenteDto findById(Integer id);
    VenteDto findByCode(String code);
    List<VenteDto> findAll();
    void delete(Integer id);
}
