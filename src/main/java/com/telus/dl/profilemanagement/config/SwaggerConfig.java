package com.telus.dl.profilemanagement.config;

import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.media.StringSchema;
import io.swagger.v3.oas.models.parameters.Parameter;
import io.swagger.v3.oas.models.tags.Tag;

import org.springdoc.core.customizers.OperationCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.HandlerMethod;

@Configuration
public class SwaggerConfig {
    @Bean
    public OperationCustomizer customGlobalHeaders() {
        return (Operation operation, HandlerMethod handlerMethod) -> {

            Parameter missingParam1 = new Parameter()
                    .in(ParameterIn.HEADER.toString())
                    .schema(new StringSchema())
                    .name("correlation-id")
                    .description(" A Correlation ID is a unique, randomly generated identifier value that is added to every request and response. \n" +
                            "  In a microservice architecture, the initial Correlation ID is passed to your sub-processes. \n" +
                            "  If a sub-system also makes sub-requests, it will also pass the Correlation ID to those systems.\n" +
                            "  It is used to keep track of requests and responses processed by multiple systems.")
                    .required(true);

            operation.addParametersItem(missingParam1);

            return operation;
        };
    }

    @Bean
    public OpenAPI productApi() {
        return new OpenAPI()
                .addTagsItem(new Tag().name("Primary User Profile"))
                .addTagsItem(new Tag().name("Sub User Profile"))
                .addTagsItem(new Tag().name("User Profile Link"))
                .addTagsItem(new Tag().name("User Profile"))
                .addTagsItem(new Tag().name("Vertical"))
                .addTagsItem(new Tag().name("Vertical Role"))
                .addTagsItem(new Tag().name("Vertical Role Permissions"))
                .addTagsItem(new Tag().name("User Vertical Permissions"))
                .info(apiInfo());
    }


    private Info apiInfo() {
        return new Info()
                .title("Profile Management API")
                .description("Profile Management API reference for developers")
                .termsOfService("http://telus.com")
                .contact(new Contact().name("henry").email("henry.huang@telus.com"))
                .license(new License().name("Examples License").url("henry.huang@telus.com"))
                .version("1.0");
    }

}
