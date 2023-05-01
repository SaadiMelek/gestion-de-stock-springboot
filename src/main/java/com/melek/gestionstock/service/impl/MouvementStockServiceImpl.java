package com.melek.gestionstock.service.impl;

import com.melek.gestionstock.dto.MouvementStockDto;
import com.melek.gestionstock.exception.ErrorCodes;
import com.melek.gestionstock.exception.InvalidEntityException;
import com.melek.gestionstock.model.TypeMouvementStock;
import com.melek.gestionstock.repository.MouvementStockRepository;
import com.melek.gestionstock.service.IArticleService;
import com.melek.gestionstock.service.IMouvementStockService;
import com.melek.gestionstock.validator.MouvementStockValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class MouvementStockServiceImpl implements IMouvementStockService {

    private MouvementStockRepository mouvementStockRepository;
    private IArticleService articleService;

    @Autowired
    public MouvementStockServiceImpl(MouvementStockRepository mouvementStockRepository, IArticleService articleService) {
        this.mouvementStockRepository = mouvementStockRepository;
        this.articleService = articleService;
    }

    @Override
    public BigDecimal stockReelArticle(Integer idArticle) {
        if (idArticle == null) {
            log.warn("ID article is null");
            return BigDecimal.valueOf(-1);
        }
        articleService.findById(idArticle);
        return mouvementStockRepository.stockReelArticle(idArticle);
    }

    @Override
    public List<MouvementStockDto> mouvementStockArticle(Integer idArticle) {
        return mouvementStockRepository.findAllByArticleId(idArticle).stream()
                .map(MouvementStockDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public MouvementStockDto entreeStock(MouvementStockDto dto) {
        return entreePositive(dto, TypeMouvementStock.ENTREE);
    }

    @Override
    public MouvementStockDto sortieStock(MouvementStockDto dto) {
        return sortieNegative(dto, TypeMouvementStock.SORTIE);
    }

    @Override
    public MouvementStockDto correctionStockPositive(MouvementStockDto dto) {
        return entreePositive(dto, TypeMouvementStock.CORRECTION_POSITIVE);
    }

    @Override
    public MouvementStockDto correctionStockNegative(MouvementStockDto dto) {
        return sortieNegative(dto, TypeMouvementStock.CORRECTION_NEGATIVE);
    }

    private MouvementStockDto entreePositive(MouvementStockDto dto, TypeMouvementStock typeMouvementStock) {
        List<String> errors = MouvementStockValidator.validate(dto);
        if (!errors.isEmpty()) {
            log.error("MouvementStock is not valid {}", dto);
            throw new InvalidEntityException("Le MouvementStock n'est pas valide", ErrorCodes.MOUVEMENT_STOCK_NOT_VALID, errors);
        }
        dto.setQuantite(
                BigDecimal.valueOf(
                        Math.abs(// valeur positive
                                dto.getQuantite().doubleValue()
                        )
                )
        );
        dto.setTypeMouvementStock(typeMouvementStock);
        return MouvementStockDto.fromEntity(
                mouvementStockRepository.save(
                        MouvementStockDto.toEntity(dto)
                )
        );
    }

    private MouvementStockDto sortieNegative(MouvementStockDto dto, TypeMouvementStock typeMouvementStock) {
        List<String> errors = MouvementStockValidator.validate(dto);
        if (!errors.isEmpty()) {
            log.error("MouvementStock is not valid {}", dto);
            throw new InvalidEntityException("Le MouvementStock n'est pas valide", ErrorCodes.MOUVEMENT_STOCK_NOT_VALID, errors);
        }
        dto.setQuantite(
                BigDecimal.valueOf(
                        Math.abs(// valeur positive
                                dto.getQuantite().doubleValue()
                        ) * -1 // valeur négative
                )
        );
        dto.setTypeMouvementStock(typeMouvementStock);
        return MouvementStockDto.fromEntity(
                mouvementStockRepository.save(
                        MouvementStockDto.toEntity(dto)
                )
        );
    }
}
