package com.mebr0.dao;

import com.mebr0.entity.Blog;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interface for manipulating over {@link Blog} objects in database
 */
public interface BlogDao extends JpaRepository<Blog, Long> {

}
