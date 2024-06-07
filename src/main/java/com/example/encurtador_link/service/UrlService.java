package com.example.encurtador_link.service;

import com.example.encurtador_link.entity.Url;
import com.example.encurtador_link.repository.UrlRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.crossstore.ChangeSetPersister;
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

    public String getOriginalUrl(String url) {
        String base62Code = extractBase62Code(url);
        Long urlId = decodeUrl(base62Code);
        return urlRepository.findById(urlId)
                .orElseThrow(() -> new EntityNotFoundException("Entity not found with url: " + url))
                .getOriginal_url();
    }

    private String extractBase62Code(String url) {
        return url.substring(url.lastIndexOf("/") + 1);
    }

    public Long decodeUrl(String url) {
        return baseConversion.decode(url);
    }
}
