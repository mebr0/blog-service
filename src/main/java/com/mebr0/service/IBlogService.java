package com.mebr0.service;

import com.mebr0.entity.Blog;

import java.util.List;

/**
 * Interface for manipulating over {@link Blog} objects
 */
public interface IBlogService {

    /**
     * List all {@link Blog} as {@link List}
     */
    List<Blog> list();

    /**
     * Create new {@link Blog}
     */
    Blog create(Blog createBlog);

    /**
     * Get {@link Blog} by id
     */
    Blog get(Long id);

    /**
     * Update {@link Blog} with id by data from another {@link Blog} object
     */
    Blog update(Long id, Blog updateBlog);

    /**
     * Delete {@link Blog} by id
     */
    void delete(Long id);
}
