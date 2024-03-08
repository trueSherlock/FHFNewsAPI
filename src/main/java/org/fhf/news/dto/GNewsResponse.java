package org.fhf.news.dto;

import java.util.List;

public record GNewsResponse (int totalArticles, List<Article> articles) {
}
