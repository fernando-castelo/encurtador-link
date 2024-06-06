package com.example.encurtador_link.controller;

import com.example.encurtador_link.entity.Url;
import com.example.encurtador_link.service.UrlService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class UrlController {

    private final UrlService urlService;
    public UrlController(UrlService urlService) {
        this.urlService = urlService;
    }
    @PostMapping()
    public ResponseEntity<Url> createShortenedUrl(@RequestBody Url url) {
        Url createdUrl = urlService.updateUrlWithShortenedUrl(url);
        return ResponseEntity.status(201).body(createdUrl);
    }
}
