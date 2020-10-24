package com.mebr0.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CommentTest {

    @Test
    public void testOf() {
        var comment = Comment.of("qwe");

        assertNotNull(comment);
        assertNull(comment.getId());
        assertEquals("qwe", comment.getText());
        assertNull(comment.getBlog());
    }
}
