package com.mebr0.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BlogTest {

    @Test
    public void testOf() {
        var blog = Blog.of("qwe", "qwe");

        assertNotNull(blog);
        assertNull(blog.getId());
        assertEquals("qwe", blog.getTitle());
        assertEquals("qwe", blog.getText());
    }
}
