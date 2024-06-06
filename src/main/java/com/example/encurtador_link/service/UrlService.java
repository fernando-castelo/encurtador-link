package com.example.encurtador_link.service;

import com.example.encurtador_link.entity.Url;
import com.example.encurtador_link.repository.UrlRepository;
import org.springframework.stereotype.Service;

@Service
public class UrlService {

    private final UrlRepository urlRepository;

    public UrlService(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    public Url createUrl(Url url) {
        return urlRepository.save(url);
    }
}
