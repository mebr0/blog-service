package com.mebr0.service.impl;

import com.mebr0.dao.BlogDao;
import com.mebr0.dao.CommentDao;
import com.mebr0.entity.Blog;
import com.mebr0.entity.Comment;
import com.mebr0.service.ICommentService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.NotFoundException;
import java.util.List;

@ApplicationScoped
public class CommentService implements ICommentService {

    @SuppressWarnings("CdiInjectionPointsInspection")
    @Inject
    CommentDao dao;

    @SuppressWarnings("CdiInjectionPointsInspection")
    @Inject
    BlogDao blogDao;

    @Override
    public List<Comment> list(Long blogId) {
        if (blogDao.existsById(blogId)) {
            return dao.findAllByBlogId(blogId);
        }
        else {
            throw new NotFoundException("Blog with id " + blogId + " not found");
        }
    }

    @Override
    public Comment create(Long blogId, Comment createComment) {
        Blog blog = blogDao.findById(blogId).
                orElseThrow(() -> new NotFoundException("Blog with id " + blogId + " not found"));

        createComment.setBlog(blog);

        return dao.save(createComment);
    }

    @Override
    public Comment get(Long blogId, Long id) {
        if (blogDao.existsById(blogId)) {
            return dao.findByIdAndBlogId(id, blogId).
                    orElseThrow(() -> new NotFoundException("Comment with id " + id + " not found"));
        }
        else {
            throw new NotFoundException("Blog with id " + blogId + " not found");
        }
    }

    @Override
    public Comment update(Long blogId, Long id, Comment updateComment) {
        Comment instance = get(blogId, id);

        instance.setText(updateComment.getText());

        return dao.save(instance);
    }

    @Override
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
