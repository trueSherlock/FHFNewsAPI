package org.fhf.news.services;

import lombok.extern.slf4j.Slf4j;
import org.fhf.news.dto.Article;
import org.fhf.news.dto.GNewsResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class GNewsFetcherService {
    @Value("${api.key}")
    private String apiKey;

    private final RestTemplate restTemplate;

    public GNewsFetcherService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Cacheable(value = "articleCache", key="{ #numArticles }", unless = "#result == null or #result.isEmpty()")
    public List<Article> getTopHeadLines(Optional<Integer> numArticles) {
        UriComponentsBuilder urlBuilder = UriComponentsBuilder.fromUriString("https://gnews.io/api/v4/top-headlines")
                .queryParam("category","general" )
                .queryParam("apikey",apiKey )
                .queryParam("lang","en");
        if(numArticles.isPresent()) {
            urlBuilder.queryParam("max", numArticles).build().toUri();
        }
        URI url = urlBuilder.build().toUri();
        GNewsResponse response =  restTemplate.getForObject(url, GNewsResponse.class);
        log.info("getTopHeadLines: Firing call to GNews Service.");
        if (response != null) {
            return response.articles();
        } else {
            return List.of();
        }
    }

    @Cacheable(value = "articleCache", key="{ #queryStr, #isTitleSearch, #numArticles }",
            unless = "#result == null or #result.isEmpty()")
    public List<Article> searchArticles(String queryStr, Optional<Integer> numArticles, boolean isTitleSearch){
        UriComponentsBuilder urlBuilder = UriComponentsBuilder.fromUriString("https://gnews.io/api/v4/search?")
                .queryParam("apikey",apiKey )
                .queryParam("lang", "en");
        if(isTitleSearch) {
            urlBuilder.queryParam("q", "\""+queryStr+"\"")
                    .queryParam("in","title");
        }else{
            urlBuilder.queryParam("q", queryStr);
        }
        if(numArticles.isPresent()) {
            urlBuilder = urlBuilder.queryParam("max", numArticles);
        }

        URI url = urlBuilder.build().toUri();
        log.info("searchArticles: Firing call to GNews Service.");
        GNewsResponse response =  restTemplate.getForObject(url, GNewsResponse.class);

        if (response != null) {
            return response.articles();
        } else {
            return List.of();
        }
    }
}
