package com.mebr0.dao;

import com.mebr0.entity.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogDao extends JpaRepository<Blog, Long> {

}
