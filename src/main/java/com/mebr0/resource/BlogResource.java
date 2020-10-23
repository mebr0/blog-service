package com.mebr0.resource;

import com.mebr0.entity.Blog;
import com.mebr0.service.BlogService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("blogs")
@Produces(MediaType.APPLICATION_JSON)
public class BlogResource {

    @Inject
    BlogService service;

    @GET
    public List<Blog> list() {
        return service.list();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Blog create(Blog createBlog) {
        return service.create(createBlog);
    }

    @GET
    @Path("{id}")
    public Blog get(@PathParam("id") Long id) {
        return service.get(id);
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Blog update(@PathParam("id") Long id, Blog updateBlog) {
        return service.update(id, updateBlog);
    }

    @DELETE
    @Path("{id}")
    public void delete(@PathParam("id") Long id) {
        service.delete(id);
    }
}
