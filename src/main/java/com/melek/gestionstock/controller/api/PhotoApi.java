package com.melek.gestionstock.controller.api;

import com.flickr4java.flickr.FlickrException;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import static com.melek.gestionstock.utils.Constants.APP_ROOT;
import static com.melek.gestionstock.utils.Constants.VENTE_ENDPOINT;

@Api(APP_ROOT + "/photos")
public interface PhotoApi {
    @PostMapping(APP_ROOT + "/photos/{id}/{photo}/{context}")
    Object savePhoto(String context, @PathVariable("id") Integer id, @RequestPart("file") MultipartFile multipartFile, String titre) throws IOException, FlickrException;
}
