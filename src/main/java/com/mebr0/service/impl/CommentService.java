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
            throw blogNotFoundException(blogId);
        }
    }

    @Override
    public Comment create(Long blogId, Comment createComment) {
        Blog blog = blogDao.findById(blogId).
                orElseThrow(() -> blogNotFoundException(blogId));

        createComment.setBlog(blog);

        return dao.save(createComment);
    }

    @Override
    public Comment get(Long blogId, Long id) {
        if (blogDao.existsById(blogId)) {
            return dao.findByIdAndBlogId(id, blogId).
                    orElseThrow(() -> commentNotFoundException(id));
        }
        else {
            throw blogNotFoundException(blogId);
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
                throw commentNotFoundException(id);
            }
        }
        else {
            throw blogNotFoundException(blogId);
        }
    }

    private NotFoundException blogNotFoundException(Long blogId) {
        return new NotFoundException("Blog with id " + blogId + " not found");
    }

    private NotFoundException commentNotFoundException(Long id) {
        return new NotFoundException("Comment with id " + id + " not found");
    }
}
