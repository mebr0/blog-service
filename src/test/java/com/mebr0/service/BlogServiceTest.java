package com.mebr0.service;

import com.mebr0.entity.Blog;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import javax.inject.Inject;
import javax.ws.rs.NotFoundException;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
@TestMethodOrder(OrderAnnotation.class)
class BlogServiceTest {

    @Inject
    IBlogService service;

    private static Long id;
    private static final Long notFoundId = -1L;

    @Order(1)
    @Test
    void testList() {
        var blogs = service.list();

        assertNotNull(blogs);

        blogs.parallelStream().forEach(blog -> {
            assertNotNull(blog);

            assertNotNull(blog.getId());
            assertNotNull(blog.getTitle());
            assertNotNull(blog.getText());
        });
    }

    @Order(2)
    @Test
    void testCreate() {
        var blog = Blog.of("test", "create");

        service.create(blog);

        assertNotNull(blog);
        assertNotNull(blog.getId());
        assertEquals("qwe", blog.getTitle());
        assertEquals("qwe", blog.getText());

        id = blog.getId();
    }

    @Order(3)
    @Test
    void testGet() {
        Blog blog = service.get(id);

        assertNotNull(blog);
        assertNotNull(blog.getId());
        assertNotNull(blog.getTitle());
        assertNotNull(blog.getText());
    }

    @Order(3)
    @Test
    void testGet_notFound() {
        assertThrows(NotFoundException.class, () -> service.get(notFoundId));
    }

    @Order(4)
    @Test
    void testUpdate() {
        Blog blog = service.get(id);

        assertNotNull(blog);
        assertNotNull(blog.getId());
        assertNotNull(blog.getTitle());
        assertNotNull(blog.getText());

        blog.setTitle(blog.getTitle() + "q");

        Blog saved = service.update(id, blog);

        assertNotNull(saved);
        assertEquals(blog.getId(), saved.getId());
        assertEquals(blog.getTitle(), saved.getTitle());
        assertEquals(blog.getText(), saved.getText());
    }

    @Order(5)
    @Test
    void testDelete() {
        assertDoesNotThrow(() -> service.delete(id));
    }

    @Order(5)
    @Test
    void testDelete_notFound() {
        assertThrows(NotFoundException.class, () -> service.delete(notFoundId));
    }
}
