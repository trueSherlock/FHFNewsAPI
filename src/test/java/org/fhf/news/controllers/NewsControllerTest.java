package org.fhf.news.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class NewsControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void testGetTopNArticles() throws Exception {
        mockMvc.perform(get("/api/topNews")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void testFindArticleByKeyword() throws Exception {
        mockMvc.perform(get("/api/searchByKeyword?q=abc")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void testFindArticleByKeyword_Nokeyword_error() throws Exception {
        mockMvc.perform(get("/api/searchByKeyword")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
    }

    @Test
    void testFindArticleByTitle() throws Exception {
        mockMvc.perform(get("/api/searchByTitle?q=abc")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}