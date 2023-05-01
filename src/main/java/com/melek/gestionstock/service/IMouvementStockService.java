package com.melek.gestionstock.service;

import com.melek.gestionstock.dto.MouvementStockDto;

import java.math.BigDecimal;
import java.util.List;

public interface IMouvementStockService {

    BigDecimal stockReelArticle(Integer idArticle);
    List<MouvementStockDto> mouvementStockArticle(Integer idArticle);
    MouvementStockDto entreeStock(MouvementStockDto dto);
    MouvementStockDto sortieStock(MouvementStockDto dto);
    MouvementStockDto correctionStockPositive(MouvementStockDto dto);
    MouvementStockDto correctionStockNegative(MouvementStockDto dto);
}
