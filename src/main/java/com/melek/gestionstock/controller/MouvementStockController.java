package com.melek.gestionstock.controller;

import com.melek.gestionstock.controller.api.IMouvementStockApi;
import com.melek.gestionstock.dto.MouvementStockDto;
import com.melek.gestionstock.service.IMouvementStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
public class MouvementStockController implements IMouvementStockApi {
    private IMouvementStockService mouvementStockService;

    @Autowired
    public MouvementStockController(IMouvementStockService mouvementStockService) {
        this.mouvementStockService = mouvementStockService;
    }

    @Override
    public BigDecimal stockReelArticle(Integer idArticle) {
        return mouvementStockService.stockReelArticle(idArticle);
    }

    @Override
    public List<MouvementStockDto> mouvementStockArticle(Integer idArticle) {
        return mouvementStockService.mouvementStockArticle(idArticle);
    }

    @Override
    public MouvementStockDto entreeStock(MouvementStockDto dto) {
        return mouvementStockService.entreeStock(dto);
    }

    @Override
    public MouvementStockDto sortieStock(MouvementStockDto dto) {
        return mouvementStockService.sortieStock(dto);
    }

    @Override
    public MouvementStockDto correctionStockPositive(MouvementStockDto dto) {
        return mouvementStockService.correctionStockPositive(dto);
    }

    @Override
    public MouvementStockDto correctionStockNegative(MouvementStockDto dto) {
        return mouvementStockService.correctionStockNegative(dto);
    }
}
