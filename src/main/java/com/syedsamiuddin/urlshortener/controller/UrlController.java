package com.syedsamiuddin.urlshortener.controller;

import com.syedsamiuddin.urlshortener.entity.Url;
import com.syedsamiuddin.urlshortener.error.InvalidUrlException;
import com.syedsamiuddin.urlshortener.error.UrlNotFoundException;
import com.syedsamiuddin.urlshortener.service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
public class UrlController {

    @Autowired
    private UrlService urlService;


    @RequestMapping(value = "/api/shorturl",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public @ResponseBody Url generateShortUrl(Url url) throws InvalidUrlException {
        return urlService.generateShortUrl(url);
    }

    @GetMapping(value="/shorturl/{id}")
    public void redirectToDestination(@PathVariable("id") Long id, HttpServletResponse httpServletResponse) throws UrlNotFoundException {
        String originalUrl = urlService.getOriginalUrl(id);
        httpServletResponse.setHeader("Location",originalUrl);
        httpServletResponse.setStatus(302);
    }
}
