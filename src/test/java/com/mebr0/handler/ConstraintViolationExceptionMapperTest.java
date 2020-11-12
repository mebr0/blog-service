package com.mebr0.handler;

import com.mebr0.entity.Blog;
import com.mebr0.resource.BlogResource;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
@TestHTTPEndpoint(BlogResource.class)
public class ConstraintViolationExceptionMapperTest {

    @Test
    public void testToResponse() {
        given().when().
                body(Blog.of("", "")).
                header("Content-Type", "application/json").
                post().
                then().
                statusCode(400).
                body("message", is("Text of blog cannot be blank"));
    }
}
