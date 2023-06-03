package jpabook.jpashop.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        info = @Info(title = "Spring JPA 활용 - 1편",
                description = "활용편 예시에 대한 API 명세",
                version = "v1")

)
@RequiredArgsConstructor
@Configuration
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi jpaOpenAPI(){
        String[] paths = {"/**"};

        return GroupedOpenApi.builder()
                .group("JPA 활용편 API v1")
                .pathsToMatch(paths)
                .build();
    }
}
