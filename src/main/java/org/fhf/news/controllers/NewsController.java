package org.fhf.news.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.fhf.news.dto.Article;
import org.fhf.news.services.GNewsFetcherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class NewsController {

    private final GNewsFetcherService gNewsFetcherService;


    @Autowired
    public NewsController(GNewsFetcherService gNewsFetcherService) {
        this.gNewsFetcherService = gNewsFetcherService;
    }

    @Operation(summary = "Get the Top News Articles")
    @ApiResponse(responseCode = "200", description = "Response OK",
            content = { @Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = Article.class)))})
    @GetMapping("/topNews")
    public List<Article> getTopNArticles(@Parameter(description = "Number of articles to get")
                                             @RequestParam(required = false) Optional<Integer> max) {
        return gNewsFetcherService.getTopHeadLines(max);
    }

    @Operation(summary = "Keyword Search for an Article")
    @ApiResponse(responseCode = "200", description = "Response OK",
            content = { @Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = Article.class)))})
    @GetMapping("/searchByKeyword")
    public List<Article> findArticleByKeyword(@Parameter(description = "Keyword string") @RequestParam String q,
                                     @Parameter(description = "Number of articles to get")
                                     @RequestParam(required = false) Optional<Integer> max) {
        return gNewsFetcherService.searchArticles(q, max, false);
    }

    @Operation(summary = "Title Search for an Article")
    @ApiResponse(responseCode = "200", description = "Response OK",
            content = { @Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = Article.class)))})
    @GetMapping("/searchByTitle")
    public List<Article> findArticleByTitle(@Parameter(description = "Keyword string") @RequestParam String q,
                                            @Parameter(description = "Number of articles to get")
                                            @RequestParam(required = false) Optional<Integer> max) {
        return gNewsFetcherService.searchArticles(q, max, true);
    }
}
