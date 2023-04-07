package com.melek.gestionstock.service;

import com.melek.gestionstock.dto.CommandeFournisseurDto;

import java.util.List;

public interface ICommandeFournisseurService {

    CommandeFournisseurDto save(CommandeFournisseurDto dto);
    CommandeFournisseurDto findById(Integer id);
    CommandeFournisseurDto findByCode(String code);
    List<CommandeFournisseurDto> findAll();
    void delete(Integer id);
}
