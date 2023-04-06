package com.melek.gestionstock.service;

import com.melek.gestionstock.dto.ArticleDto;
import com.melek.gestionstock.dto.CommandeClientDto;

import java.util.List;

public interface ICommandeClientService {
    CommandeClientDto save(CommandeClientDto dto);
    CommandeClientDto findById(Integer id);
    CommandeClientDto findByCode(String code);
    List<CommandeClientDto> findAll();
    void delete(Integer id);
}
