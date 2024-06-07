package com.example.encurtador_link.controller;

import com.example.encurtador_link.dto.UrlRequestDto;
import com.example.encurtador_link.entity.DailyAccess;
import com.example.encurtador_link.entity.Url;
import com.example.encurtador_link.service.DailyAccessService;
import com.example.encurtador_link.service.UrlService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/v1")
public class UrlController {

    private final UrlService urlService;
    private final DailyAccessService dailyAccessService;
    public UrlController(UrlService urlService, DailyAccessService dailyAccessService) {
        this.urlService = urlService;
        this.dailyAccessService = dailyAccessService;
    }
    @GetMapping("/{shortenedUrl}")
    public ResponseEntity<StatisticsDto> getUrlStatistics(@PathVariable String shortenedUrl) {
        StatisticsDto statisticsDto = urlService.getUrlStatistics(shortenedUrl);
        return ResponseEntity.status(200).body(statisticsDto);
    }


    @PostMapping("/redirect")
    public ResponseEntity<Void> getOriginalUrlPost(@RequestBody UrlRequestDto urlRequestDto) {
        String original = urlService.getOriginalUrl(urlRequestDto.getUrl());
        return ResponseEntity.status(HttpStatus.FOUND)
                .location(URI.create(original))
                .build();
    }

    @PostMapping()
    public ResponseEntity<Url> createShortenedUrl(@RequestBody Url url) {
        Url createdUrl = urlService.updateUrlWithShortenedUrl(url);
        return ResponseEntity.status(201).body(createdUrl);
    }


}
