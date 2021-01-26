package com.mebr0.handler;

import com.mebr0.resource.BlogResource;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
@TestHTTPEndpoint(BlogResource.class)
class NotFoundExceptionMapperTest {

    @Test
    void testToResponse() {
        given().when().
                get("-1").
                then().
                statusCode(404).
                body("message", is("Blog with id -1 not found"));
    }
}
