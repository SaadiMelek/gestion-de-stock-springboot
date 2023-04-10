package com.melek.gestionstock.controller;

import com.melek.gestionstock.controller.api.IFournisseurApi;
import com.melek.gestionstock.dto.FournisseurDto;
import com.melek.gestionstock.service.IFournisseurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FournisseurController implements IFournisseurApi {

    private IFournisseurService fournisseurService;

    @Autowired
    public FournisseurController(IFournisseurService fournisseurService) {
        this.fournisseurService = fournisseurService;
    }

    @Override
    public FournisseurDto save(FournisseurDto dto) {
        return fournisseurService.save(dto);
    }

    @Override
    public FournisseurDto findById(Integer id) {
        return fournisseurService.findById(id);
    }

    @Override
    public List<FournisseurDto> findAll() {
        return fournisseurService.findAll();
    }

    @Override
    public void delete(Integer id) {
        fournisseurService.delete(id);
    }
}
