package com.mebr0.resource;

import com.mebr0.entity.Comment;
import com.mebr0.service.CommentService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("blogs/{blogId}/comments")
@Produces(MediaType.APPLICATION_JSON)
public class CommentResource {

    @Inject
    CommentService service;

    @GET
    public List<Comment> list(@PathParam("blogId") Long blogId) {
        return service.list(blogId);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Comment create(@PathParam("blogId") Long blogId, Comment comment) {
        return service.create(blogId, comment);
    }

    @GET
    @Path("{id}")
    public Comment get(@PathParam("blogId") Long blogId, @PathParam("id") Long id) {
        return service.get(blogId, id);
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Comment update(@PathParam("blogId") Long blogId, @PathParam("id") Long id, Comment comment) {
        return service.update(blogId, id, comment);
    }

    @DELETE
    @Path("{id}")
    public void delete(@PathParam("blogId") Long blogId, @PathParam("id") Long id) {
        service.delete(blogId, id);
    }
}
