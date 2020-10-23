package com.mebr0.dto;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@QuarkusTest
public class HttpErrorTest {

    @Test
    public void testOf() {
        var error = HttpError.of("message");

        assertNotNull(error);
        assertEquals("message", error.getMessage());
    }
}
