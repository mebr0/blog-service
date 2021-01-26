package com.mebr0.resource;

import com.mebr0.dto.HttpError;
import com.mebr0.entity.Blog;
import com.mebr0.service.IBlogService;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Tag(name = "blogs")
@Path("blogs")
@Produces(MediaType.APPLICATION_JSON)
public class BlogResource {

    @Inject
    IBlogService service;

    @Operation(summary = "List blogs", description = "List all blogs")
    @APIResponse(responseCode = "200", description = "Blogs listed", content = @Content(schema = @Schema(implementation = Blog.class, type = SchemaType.ARRAY)))
    @GET
    public List<Blog> list() {
        return service.list();
    }

    @Operation(summary = "Create blog", description = "Create new blog")
    @APIResponse(responseCode = "201", description = "Blog created", content = @Content(schema = @Schema(implementation = Blog.class)))
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(@Valid Blog createBlog) {
        return Response.status(201).entity(service.create(createBlog)).build();
    }

    @Operation(summary = "Get blog", description = "Get blog by id")
    @APIResponse(responseCode = "200", description = "Blog retrieved")
    @APIResponse(responseCode = "400", description = "Blog not found", content = @Content(schema = @Schema(implementation = HttpError.class)))
    @GET
    @Path("{id}")
    public Blog get(@PathParam("id") Long id) {
        return service.get(id);
    }

    @Operation(summary = "Update blog", description = "Update blog with id")
    @APIResponse(responseCode = "200", description = "Blog updated")
    @APIResponse(responseCode = "400", description = "Blog not found", content = @Content(schema = @Schema(implementation = HttpError.class)))
    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Blog update(@PathParam("id") Long id, @Valid Blog updateBlog) {
        return service.update(id, updateBlog);
    }

    @Operation(summary = "Delete blog", description = "Delete blog with id")
    @APIResponse(responseCode = "204", description = "Blog deleted")
    @APIResponse(responseCode = "400", description = "Blog not found", content = @Content(schema = @Schema(implementation = HttpError.class)))
    @DELETE
    @Path("{id}")
    public void delete(@PathParam("id") Long id) {
        service.delete(id);
    }
}
