package com.example.encurtador_link.service;

import com.example.encurtador_link.controller.StatisticsDto;
import com.example.encurtador_link.entity.Url;
import com.example.encurtador_link.repository.UrlRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UrlService {

    private static final String BASE_URL = "www.encurtadortds-";
    private final UrlRepository urlRepository;
    private final BaseConversion baseConversion;
    private final DailyAccessService dailyAccessService;

    public UrlService(UrlRepository urlRepository, BaseConversion baseConversion, DailyAccessService dailyAccessService) {
        this.urlRepository = urlRepository;
        this.baseConversion = baseConversion;
        this.dailyAccessService = dailyAccessService;
    }

    public Url createUrl(Url url) {
        return urlRepository.save(url);
    }

    public Url getUrlById(Long id) {
        return urlRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Entity not found with id: " + id));

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
        Long urlId = getShortenedUrlId(url);
        Url queriedUrl = getUrlById(urlId);

        dailyAccessService.handleDailyAccessEntryCreation(queriedUrl);
        return queriedUrl.getOriginal_url();
    }


    private String extractBase62Code(String url) {
        return url.substring(url.lastIndexOf("-") + 1);
    }

    private Long decodeUrl(String url) {
        return baseConversion.decode(url);
    }

    private Long getShortenedUrlId(String url) {
        String base62Code = extractBase62Code(url);
        return decodeUrl(base62Code);
    }

    public StatisticsDto getUrlStatistics(String url) {
        Long urlId = getShortenedUrlId(url);
        Url queriedUrl = getUrlById(urlId);

        return dailyAccessService.getStatisticsDto(queriedUrl);
    }

}
