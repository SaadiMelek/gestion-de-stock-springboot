package com.melek.gestionstock.service;

import com.melek.gestionstock.dto.ClientDto;

import java.util.List;

public interface IClientService {
    ClientDto save(ClientDto dto);
    ClientDto findById(Integer id);
    List<ClientDto> findAll();
    void delete(Integer id);
}
