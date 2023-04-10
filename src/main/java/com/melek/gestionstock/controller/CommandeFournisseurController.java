package com.melek.gestionstock.controller;

import com.melek.gestionstock.controller.api.ICommandeFournisseurApi;
import com.melek.gestionstock.dto.CommandeFournisseurDto;
import com.melek.gestionstock.service.ICommandeFournisseurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
public class CommandeFournisseurController implements ICommandeFournisseurApi {

    private ICommandeFournisseurService commandeFournisseurService;

    @Autowired
    public CommandeFournisseurController(ICommandeFournisseurService iCommandeFournisseurService) {
        this.commandeFournisseurService = iCommandeFournisseurService;
    }

    @Override
    public CommandeFournisseurDto save(CommandeFournisseurDto dto) {
        return commandeFournisseurService.save(dto);
    }

    @Override
    public CommandeFournisseurDto findById(Integer idCommandeFournisseur) {
        return commandeFournisseurService.findById(idCommandeFournisseur);
    }

    @Override
    public CommandeFournisseurDto findByCode(String code) {
        return commandeFournisseurService.findByCode(code);
    }

    @Override
    public List<CommandeFournisseurDto> findAll() {
        return commandeFournisseurService.findAll();
    }

    @Override
    public void delete(Integer id) {
        commandeFournisseurService.delete(id);
    }
}
