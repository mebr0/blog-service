package com.mebr0;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Contact;
import org.eclipse.microprofile.openapi.annotations.info.Info;

import javax.ws.rs.core.Application;

@OpenAPIDefinition(
        info = @Info(
                title="Blog API",
                description = "API of blog service",
                version = "1.0.0",
                contact = @Contact(
                        name = "Azamat Yergali",
                        email = "aza17e@gmail.com"
                )
        )
)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BlogAPI extends Application {
    // Empty Application class for OpenAPI definition
}
