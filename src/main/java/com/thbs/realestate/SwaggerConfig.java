package com.thbs.realestate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    public final Logger LOG = LoggerFactory.getLogger(this.getClass());

    public Docket api() {
        LOG.info("Swagger initilized...");
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(abcd()).select().apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any()).build();
    }

    private ApiInfo abcd() {
        LOG.info("Api Info");
        return new ApiInfoBuilder().title("Torry Harris REST API")
                .description("Spring boot depo app for Torry Harris trainees Dec 2021")
                .contact(new Contact("Torry Harris", "torryharris.com", "contact@torryharris.com"))
                .license("Apache 2.0").version("1.0.0").build();
    }

    // more methods

}
