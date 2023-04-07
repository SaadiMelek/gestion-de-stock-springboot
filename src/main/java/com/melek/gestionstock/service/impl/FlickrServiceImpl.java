package com.melek.gestionstock.service.impl;

import com.flickr4java.flickr.Flickr;
import com.flickr4java.flickr.REST;
import com.flickr4java.flickr.RequestContext;
import com.flickr4java.flickr.auth.Auth;
import com.flickr4java.flickr.auth.Permission;
import com.melek.gestionstock.service.FlickrService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.InputStream;

@Service
@Slf4j
public class FlickrServiceImpl implements FlickrService {

    @Value("${flickr.api-key}")
    private String apiKey;
    @Value("${flickr.api-secret}")
    private String apiSecret;

    @Value("${flickr.app-key}")
    private String appKey;
    @Value("${flickr.app-secret}")
    private String appSecret;

    private Flickr flickr;

    @Override
    public String savePhoto(InputStream photo, String title) {
        return null;
    }

    private void connect() {
        flickr = new Flickr(apiKey, apiSecret, new REST());
        Auth auth = new Auth();
        auth.setPermission(Permission.DELETE);
        auth.setToken(appKey);
        auth.setTokenSecret(appSecret);

        RequestContext requestContext = RequestContext.getRequestContext();
        requestContext.setAuth(auth);

        flickr.setAuth(auth);
    }
}
