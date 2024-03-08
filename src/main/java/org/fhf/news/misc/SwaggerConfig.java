package org.fhf.news.misc;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.ExternalDocumentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Value("${spring.cache.caffeine.spec}")
    private String caffeineConfig;
    @Bean
    public OpenAPI newsAPI() {
        return new OpenAPI()
                .info(new Info().title("News API")
                        .description("API to interact with GNews service.\n Caffeine Cache config: "+caffeineConfig
                                +". Empty responses are not cached.")
                        .version("1.0")
                ).externalDocs(new ExternalDocumentation()
                .description("Ritin Suthagaran, ritinms@gmail.com"));
    }
}

