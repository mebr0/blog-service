package com.mebr0.dao;

import com.mebr0.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * Interface for manipulating over {@link Comment} objects in database
 */
public interface CommentDao extends JpaRepository<Comment, Long> {

    /**
     * List all {@link Comment}s with blogId
     */
    List<Comment> findAllByBlogId(Long blogId);

    /**
     * Find {@link Comment} with blogId and id
     */
    Optional<Comment> findByIdAndBlogId(Long id, Long blogId);
}
