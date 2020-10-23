package com.mebr0.service;

import com.mebr0.dao.BlogDao;
import com.mebr0.entity.Blog;
import com.mebr0.exception.NotFoundException;
import lombok.SneakyThrows;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

@ApplicationScoped
public class BlogService {

    @Inject
    BlogDao dao;

    public List<Blog> list() {
        return dao.findAll();
    }

    public Blog create(Blog createBlog) {
        return dao.save(createBlog);
    }

    @SneakyThrows({NotFoundException.class})
    public Blog get(Long id) {
        return dao.findById(id).
                orElseThrow(() -> new NotFoundException("Blog with id " + id + " not found"));
    }

    public Blog update(Long id, Blog updateBlog) {
        Blog instance = get(id);

        instance.setTitle(updateBlog.getTitle());
        instance.setText(updateBlog.getText());

        return dao.save(instance);
    }

    @SneakyThrows({NotFoundException.class})
    public void delete(Long id) {
        if (dao.existsById(id)) {
            dao.deleteById(id);
        }
        else {
            throw new NotFoundException("Blog with id " + id + " not found");
        }
    }
}
