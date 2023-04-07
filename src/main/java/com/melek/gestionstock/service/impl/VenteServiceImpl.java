package com.melek.gestionstock.service.impl;

import com.melek.gestionstock.dto.CommandeFournisseurDto;
import com.melek.gestionstock.dto.LigneVenteDto;
import com.melek.gestionstock.dto.UtilisateurDto;
import com.melek.gestionstock.dto.VenteDto;
import com.melek.gestionstock.exception.EntityNotFoundException;
import com.melek.gestionstock.exception.ErrorCodes;
import com.melek.gestionstock.exception.InvalidEntityException;
import com.melek.gestionstock.model.Article;
import com.melek.gestionstock.model.LigneVente;
import com.melek.gestionstock.model.Vente;
import com.melek.gestionstock.repository.ArticleRepository;
import com.melek.gestionstock.repository.LigneVenteRepository;
import com.melek.gestionstock.repository.VenteRepository;
import com.melek.gestionstock.service.IArticleService;
import com.melek.gestionstock.service.IVenteService;
import com.melek.gestionstock.validator.VenteValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class VenteServiceImpl implements IVenteService {

    private ArticleRepository articleRepository;
    private VenteRepository venteRepository;
    private LigneVenteRepository ligneVenteRepository;

    public VenteServiceImpl(ArticleRepository articleRepository, VenteRepository venteRepository, LigneVenteRepository ligneVenteRepository) {
        this.articleRepository = articleRepository;
        this.venteRepository = venteRepository;
        this.ligneVenteRepository = ligneVenteRepository;
    }

    @Override
    public VenteDto save(VenteDto dto) {
        List<String> errors = VenteValidator.validate(dto);
        if (!errors.isEmpty()) {
            log.error("L'objet vente n'est pas valide !");
            throw new InvalidEntityException("", ErrorCodes.VENTE_NOT_VALID, errors);
        }
        List<String> articleErrors = new ArrayList<>();
        dto.getLigneVentes().forEach(ligneVenteDto -> {
            Optional<Article> article = articleRepository.findById(ligneVenteDto.getArticle().getId());
            if (article.isEmpty()) {
                articleErrors.add("Aucun article avec l'ID " + ligneVenteDto.getArticle().getId() + " n'a été trouvé dans la BDD");
            }
        });
        if (!articleErrors.isEmpty()) {
            log.error("Au moins un article n'est pas valide !");
            throw new InvalidEntityException("Au moins un article n'est pas valide !", ErrorCodes.VENTE_NOT_VALID, articleErrors);
        }

        Vente savedVente = venteRepository.save(VenteDto.toEntity(dto));
        dto.getLigneVentes().forEach(ligVenteDto -> {
            LigneVente ligneVente = LigneVenteDto.toEntity(ligVenteDto);
            ligneVente.setVente(savedVente);
            ligneVenteRepository.save(ligneVente);
        });
        return VenteDto.fromEntity(savedVente);
    }

    @Override
    public VenteDto findById(Integer id) {
        if (id == null) {
            log.error("Id Vente is null");
            return null;
        }
        return VenteDto.fromEntity(
                venteRepository.findById(id)
                        .orElseThrow(() -> new EntityNotFoundException("Vente not found avec l'id " + id, ErrorCodes.VENTE_NOT_FOUND))
        );
    }

    @Override
    public VenteDto findByCode(String code) {
        if (code.isEmpty()) {
            log.error("Code Vente is null");
            return null;
        }
        return venteRepository.findByCode(code)
                .map(VenteDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException("Aucune Vente n'est trouvé avec le code ' " + code, ErrorCodes.VENTE_NOT_FOUND));
    }

    @Override
    public List<VenteDto> findAll() {
        return venteRepository.findAll().stream()
                .map(VenteDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        if (id == null) {
            log.error("Id Vente is null");
            return;
        }
        venteRepository.deleteById(id);
    }
}
