package com.melek.gestionstock.controller;

import com.melek.gestionstock.controller.api.IVenteApi;
import com.melek.gestionstock.dto.VenteDto;
import com.melek.gestionstock.service.IVenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class VenteController implements IVenteApi {

    private IVenteService venteService;

    @Autowired
    public VenteController(IVenteService venteService) {
        this.venteService = venteService;
    }

    @Override
    public VenteDto save(VenteDto dto) {
        return venteService.save(dto);
    }

    @Override
    public VenteDto findById(Integer id) {
        return venteService.findById(id);
    }

    @Override
    public VenteDto findByCode(String code) {
        return venteService.findByCode(code);
    }

    @Override
    public List<VenteDto> findAll() {
        return venteService.findAll();
    }

    @Override
    public void delete(Integer id) {
        venteService.delete(id);
    }
}
