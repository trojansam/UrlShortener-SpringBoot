package com.syedsamiuddin.urlshortener.service;

import com.syedsamiuddin.urlshortener.entity.Url;
import com.syedsamiuddin.urlshortener.error.InvalidUrlException;
import com.syedsamiuddin.urlshortener.error.UrlNotFoundException;

public interface UrlService {
    public Url generateShortUrl(Url url) throws InvalidUrlException;

    public String getOriginalUrl(Long id) throws UrlNotFoundException;
}
