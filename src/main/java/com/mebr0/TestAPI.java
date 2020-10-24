package com.mebr0;

import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Contact;
import org.eclipse.microprofile.openapi.annotations.info.Info;

import javax.ws.rs.core.Application;

@OpenAPIDefinition(
        info = @Info(
                title="Test API",
                description = "Quarkus Test",
                version = "1.0.0",
                contact = @Contact(
                        name = "Azamat Yergali",
                        email = "aza17e@gmail.com"
                )
        )
)
public class TestAPI extends Application {

}
