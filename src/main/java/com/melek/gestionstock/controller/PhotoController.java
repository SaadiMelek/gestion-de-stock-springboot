package com.melek.gestionstock.controller;

import com.flickr4java.flickr.FlickrException;
import com.melek.gestionstock.controller.api.PhotoApi;
import com.melek.gestionstock.service.strategy.StrategyPhotoContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class PhotoController implements PhotoApi {

    private StrategyPhotoContext strategyPhotoContext;

    @Autowired
    public PhotoController(StrategyPhotoContext strategyPhotoContext) {
        this.strategyPhotoContext = strategyPhotoContext;
    }

    @Override
    public Object savePhoto(String context, Integer id, MultipartFile multipartFile, String titre) throws IOException, FlickrException {
        return strategyPhotoContext.savePhoto(context, id, multipartFile.getInputStream(), titre);
    }
}
