package com.melek.gestionstock.controller;

import com.melek.gestionstock.controller.api.IEntrepriseApi;
import com.melek.gestionstock.dto.EntrepriseDto;
import com.melek.gestionstock.service.IEntrepriseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EntrepriseController implements IEntrepriseApi {

    private IEntrepriseService entrepriseService;

    @Autowired
    public EntrepriseController(IEntrepriseService entrepriseService) {
        this.entrepriseService = entrepriseService;
    }

    @Override
    public EntrepriseDto save(EntrepriseDto dto) {
        return entrepriseService.save(dto);
    }

    @Override
    public EntrepriseDto findById(Integer id) {
        return entrepriseService.findById(id);
    }

    @Override
    public List<EntrepriseDto> findAll() {
        return entrepriseService.findAll();
    }

    @Override
    public void delete(Integer id) {
        entrepriseService.delete(id);
    }
}
