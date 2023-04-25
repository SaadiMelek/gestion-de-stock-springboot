package com.melek.gestionstock.service;

import com.melek.gestionstock.dto.ArticleDto;
import com.melek.gestionstock.dto.CommandeClientDto;
import com.melek.gestionstock.model.EtatCommande;

import java.math.BigDecimal;
import java.util.List;

public interface ICommandeClientService {
    CommandeClientDto save(CommandeClientDto dto);
    CommandeClientDto updateEtatCommande(Integer idCommande, EtatCommande etatCommande);
    CommandeClientDto updateQuantiteCommande(Integer idCommande, Integer idLigneCommande, BigDecimal quantity);
    CommandeClientDto updateClient(Integer idCommande, Integer idClient);
    CommandeClientDto findById(Integer id);
    CommandeClientDto findByCode(String code);
    List<CommandeClientDto> findAll();
    void delete(Integer id);
}
