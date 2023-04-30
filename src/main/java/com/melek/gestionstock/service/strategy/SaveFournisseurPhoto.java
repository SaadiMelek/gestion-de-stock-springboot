package com.melek.gestionstock.service.strategy;

import com.flickr4java.flickr.FlickrException;
import com.melek.gestionstock.dto.FournisseurDto;
import com.melek.gestionstock.exception.ErrorCodes;
import com.melek.gestionstock.exception.InvalidOperationException;
import com.melek.gestionstock.model.Fournisseur;
import com.melek.gestionstock.service.FlickrService;
import com.melek.gestionstock.service.IFournisseurService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.InputStream;

@Service("fournisseurStrategy")
@Slf4j
public class SaveFournisseurPhoto implements Strategy<FournisseurDto> {

    private IFournisseurService fournisseurService;
    private FlickrService flickrService;

    @Autowired
    public SaveFournisseurPhoto(IFournisseurService fournisseurService, FlickrService flickrService) {
        this.fournisseurService = fournisseurService;
        this.flickrService = flickrService;
    }

    @Override
    public FournisseurDto savePhoto(Integer id, InputStream photo, String titre) throws FlickrException {
        FournisseurDto fournisseur = fournisseurService.findById(id);
        String urlPhoto = flickrService.savePhoto(photo, titre);
        if (!StringUtils.hasLength(urlPhoto)) {
            throw new InvalidOperationException("Erreur lors de l'enregistrement de photo de l'entreprise", ErrorCodes.UPDATE_PHOTO_EXCEPTION);
        }
        fournisseur.setPhoto(urlPhoto);
        return fournisseurService.save(fournisseur);
    }
}
