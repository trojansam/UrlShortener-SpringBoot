package com.syedsamiuddin.urlshortener.service;

import com.syedsamiuddin.urlshortener.entity.Url;
import com.syedsamiuddin.urlshortener.error.InvalidUrlException;
import com.syedsamiuddin.urlshortener.error.UrlNotFoundException;
import com.syedsamiuddin.urlshortener.repository.UrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UrlServiceImpl implements UrlService{

    @Autowired
    private UrlRepository urlRepository;

    @Override
    public Url generateShortUrl(Url url) throws InvalidUrlException {
        String pattern = "https?:\\/\\/(www\\.)?[-a-zA-Z0-9@:%._\\+~#=]{1,256}\\.[a-zA-Z0-9()]{1,6}\\b([-a-zA-Z0-9()@:%_\\+.~#?&//=]*)";
        String original_url = url.getOriginal_url();

        if(original_url.matches(pattern)){
            return urlRepository.save(url);
        } else {
            throw new InvalidUrlException("Invalid URL");
        }
    }

    @Override
    public String getOriginalUrl(Long id) throws UrlNotFoundException {
        Optional<Url> originalUrl = urlRepository.findById(id);

        if(!originalUrl.isPresent()){
            throw new UrlNotFoundException("No URL found for "+id+" code");
        }

        return originalUrl.get().getOriginal_url();

    }
}
