package org.fhf.news.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Article (String title, String description, String content, String url, String image,
                       Date publishedAt, Source source){ }
