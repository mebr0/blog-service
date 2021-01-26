package com.mebr0.service.impl;

import com.mebr0.dao.BlogDao;
import com.mebr0.entity.Blog;
import com.mebr0.service.IBlogService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.NotFoundException;
import java.util.List;

@ApplicationScoped
public class BlogService implements IBlogService {

    @SuppressWarnings("CdiInjectionPointsInspection")
    @Inject
    BlogDao dao;

    @Override
    public List<Blog> list() {
        return dao.findAll();
    }

    @Override
    public Blog create(Blog createBlog) {
        return dao.save(createBlog);
    }

    @Override
    public Blog get(Long id) {
        return dao.findById(id).
                orElseThrow(() -> new NotFoundException("Blog with id " + id + " not found"));
    }

    @Override
    public Blog update(Long id, Blog updateBlog) {
        Blog instance = get(id);

        instance.setTitle(updateBlog.getTitle());
        instance.setText(updateBlog.getText());

        return dao.save(instance);
    }

    @Override
    public void delete(Long id) {
        if (dao.existsById(id)) {
            dao.deleteById(id);
        }
        else {
            throw new NotFoundException("Blog with id " + id + " not found");
        }
    }
}
