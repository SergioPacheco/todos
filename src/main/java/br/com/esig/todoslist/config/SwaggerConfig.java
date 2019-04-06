package br.com.esig.todoslist.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;

import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {                                    
    @Bean
    public Docket api() { 
        return new Docket(DocumentationType.SWAGGER_2)  
          .select()                                  
          .apis(RequestHandlerSelectors.any())              
          .paths(Predicates.not(PathSelectors.regex("/error.*")))                         
          .build();
    }
    
    private Predicate<RequestHandler> apis() {
        return RequestHandlerSelectors.basePackage("br.com.esig.todoslist");
    }
    
    private ApiInfo apiInfo() {
    	 
        ApiInfo apiInfo = new ApiInfoBuilder()
                .title ("API ToDo List")
                .description ("This is API for ToDo List.")
                .license("Apache License Version 2.0")
                .licenseUrl("https://www.apache.org/licenses/LICENSE-2.0")
                .version("1.0.0")
                .build();
 
        return apiInfo;
    }
    
}