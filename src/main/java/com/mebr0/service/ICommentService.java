package com.mebr0.service;

import com.mebr0.entity.Blog;
import com.mebr0.entity.Comment;

import java.util.List;

/**
 * Interface for manipulating over {@link Comment} objects
 */
public interface ICommentService {

    /**
     * List all {@link Comment} as {@link List} by blogId
     */
    List<Comment> list(Long blogId);

    /**
     * Create new {@link Comment} for {@link Blog} with blogId
     */
    Comment create(Long blogId, Comment createComment);

    /**
     * Get {@link Comment} by blogId and id
     */
    Comment get(Long blogId, Long id);

    /**
     * Update {@link Comment} with blogId and id by data from another {@link Comment} object
     */
    Comment update(Long blogId, Long id, Comment updateComment);

    /**
     * Delete {@link Comment} by blogId and id
     */
    void delete(Long blogId, Long id);
}
