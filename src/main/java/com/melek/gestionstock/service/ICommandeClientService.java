package com.melek.gestionstock.service;

import com.melek.gestionstock.dto.ArticleDto;
import com.melek.gestionstock.dto.CommandeClientDto;
import com.melek.gestionstock.dto.LigneCommandeClientDto;
import com.melek.gestionstock.model.EtatCommande;

import java.math.BigDecimal;
import java.util.List;

public interface ICommandeClientService {
    CommandeClientDto save(CommandeClientDto dto);
    CommandeClientDto updateEtatCommande(Integer idCommande, EtatCommande etatCommande);
    CommandeClientDto updateQuantiteCommande(Integer idCommande, Integer idLigneCommande, BigDecimal quantity);
    CommandeClientDto updateClient(Integer idCommande, Integer idClient);
    CommandeClientDto updateArticle(Integer idCommande, Integer idLigneCommande, Integer newIdArticle);
    // Delete article ==> delete LigneCommandeClient
    CommandeClientDto deleteArticle(Integer idCommande, Integer idLigneCommande);
    CommandeClientDto findById(Integer id);
    CommandeClientDto findByCode(String code);
    List<CommandeClientDto> findAll();
    List<LigneCommandeClientDto> findAllLignesCommandesClientByCommandeClientId(Integer idCommande);
    void delete(Integer id);
}
