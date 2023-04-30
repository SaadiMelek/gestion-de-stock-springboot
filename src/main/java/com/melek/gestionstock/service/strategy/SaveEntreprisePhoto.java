package com.melek.gestionstock.service.strategy;

import com.flickr4java.flickr.FlickrException;
import com.melek.gestionstock.dto.EntrepriseDto;
import com.melek.gestionstock.exception.ErrorCodes;
import com.melek.gestionstock.exception.InvalidOperationException;
import com.melek.gestionstock.model.Entreprise;
import com.melek.gestionstock.service.FlickrService;
import com.melek.gestionstock.service.IEntrepriseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.InputStream;

@Service("entrepriseStrategy")
@Slf4j
public class SaveEntreprisePhoto implements Strategy<EntrepriseDto> {

    private IEntrepriseService entrepriseService;
    private FlickrService flickrService;

    @Autowired
    public SaveEntreprisePhoto(IEntrepriseService entrepriseService, FlickrService flickrService) {
        this.entrepriseService = entrepriseService;
        this.flickrService = flickrService;
    }

    @Override
    public EntrepriseDto savePhoto(Integer id, InputStream photo, String titre) throws FlickrException {
        EntrepriseDto entreprise = entrepriseService.findById(id);
        String urlPhoto = flickrService.savePhoto(photo, titre);
        if (!StringUtils.hasLength(urlPhoto)) {
            throw new InvalidOperationException("Erreur lors de l'enregistrement de photo de l'entreprise", ErrorCodes.UPDATE_PHOTO_EXCEPTION);
        }
        entreprise.setPhoto(urlPhoto);
        return entrepriseService.save(entreprise);
    }
}
