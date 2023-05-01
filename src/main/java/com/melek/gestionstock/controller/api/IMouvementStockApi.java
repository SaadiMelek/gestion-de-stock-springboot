package com.melek.gestionstock.controller.api;

import com.melek.gestionstock.dto.MouvementStockDto;
import com.melek.gestionstock.utils.Constants;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.math.BigDecimal;
import java.util.List;

import static com.melek.gestionstock.utils.Constants.APP_ROOT;
import static com.melek.gestionstock.utils.Constants.VENTE_ENDPOINT;

@Api(Constants.APP_ROOT + "/mouvementstock")
public interface IMouvementStockApi {

    @GetMapping(value = APP_ROOT + "/mouvementstock/stockreel/{idArticle}")
    BigDecimal stockReelArticle(@PathVariable("idArticle") Integer idArticle);
    @GetMapping(value = APP_ROOT + "/mouvementstock/filter/article/{idArticle}")
    List<MouvementStockDto> mouvementStockArticle(@PathVariable("idArticle") Integer idArticle);
    @PostMapping(value = APP_ROOT + "/mouvementstock/entree")
    MouvementStockDto entreeStock(@RequestBody MouvementStockDto dto);
    @PostMapping(value = APP_ROOT + "/mouvementstock/sortie")
    MouvementStockDto sortieStock(@RequestBody MouvementStockDto dto);
    @PostMapping(value = APP_ROOT + "/mouvementstock/correctionpositive")
    MouvementStockDto correctionStockPositive(@RequestBody MouvementStockDto dto);
    @PostMapping(value = APP_ROOT + "/mouvementstock/correctionnegative")
    MouvementStockDto correctionStockNegative(@RequestBody MouvementStockDto dto);
}
