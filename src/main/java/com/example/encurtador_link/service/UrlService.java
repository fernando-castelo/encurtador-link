package com.example.encurtador_link.service;

import com.example.encurtador_link.entity.Url;
import com.example.encurtador_link.repository.UrlRepository;
import org.springframework.stereotype.Service;

@Service
public class UrlService {

    private static final String BASE_URL = "www.encurtadortds/";
    private final UrlRepository urlRepository;
    private final BaseConversion baseConversion;

    public UrlService(UrlRepository urlRepository, BaseConversion baseConversion) {
        this.urlRepository = urlRepository;
        this.baseConversion = baseConversion;
    }

    public Url createUrl(Url url) {
        return urlRepository.save(url);
    }

    public Url updateUrlWithShortenedUrl(Url url) {
        Url createdUrl = createUrl(url);
        String shortenedUrl = generateShortenedUrl(createdUrl.getId());
        createdUrl.setShortened_url(shortenedUrl);
        return urlRepository.save(createdUrl);
    }

    private String generateShortenedUrl(Long id) {
        return BASE_URL.concat(baseConversion.encode(id));
    }
}
