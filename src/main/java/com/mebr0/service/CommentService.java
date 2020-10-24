package com.mebr0.service;

import com.mebr0.dao.BlogDao;
import com.mebr0.dao.CommentDao;
import com.mebr0.entity.Blog;
import com.mebr0.entity.Comment;
import com.mebr0.exception.NotFoundException;
import lombok.SneakyThrows;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

@ApplicationScoped
public class CommentService {

    @Inject
    CommentDao dao;

    @Inject
    BlogDao blogDao;

    @SneakyThrows({NotFoundException.class})
    public List<Comment> list(Long blogId) {
        if (blogDao.existsById(blogId)) {
            return dao.findAllByBlogId(blogId);
        }
        else {
            throw new NotFoundException("Blog with id " + blogId + " not found");
        }
    }

    @SneakyThrows({NotFoundException.class})
    public Comment create(Long blogId, Comment createComment) {
        Blog blog = blogDao.findById(blogId).
                orElseThrow(() -> new NotFoundException("Blog with id " + blogId + " not found"));

        createComment.setBlog(blog);

        return dao.save(createComment);
    }

    @SneakyThrows({NotFoundException.class})
    public Comment get(Long blogId, Long id) {
        if (blogDao.existsById(blogId)) {
            return dao.findByIdAndBlogId(id, blogId).
                    orElseThrow(() -> new NotFoundException("Comment with id " + id + " not found"));
        }
        else {
            throw new NotFoundException("Blog with id " + blogId + " not found");
        }
    }

    public Comment update(Long blogId, Long id, Comment updateComment) {
        Comment instance = get(blogId, id);

        instance.setText(updateComment.getText());

        return dao.save(instance);
    }

    @SneakyThrows({NotFoundException.class})
    public void delete(Long blogId, Long id) {
        if (blogDao.existsById(blogId)) {
            if (dao.existsById(id)) {
                dao.deleteById(id);
            }
            else {
                throw new NotFoundException("Comment with id " + id + " not found");
            }
        }
        else {
            throw new NotFoundException("Blog with id " + blogId + " not found");
        }
    }
}
