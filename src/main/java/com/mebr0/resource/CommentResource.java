package com.mebr0.resource;

import com.mebr0.dto.HttpError;
import com.mebr0.entity.Comment;
import com.mebr0.service.ICommentService;
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

@Tag(name = "comments")
@Path("blogs/{blogId}/comments")
@Produces(MediaType.APPLICATION_JSON)
public class CommentResource {

    @Inject
    ICommentService service;

    @Operation(summary = "List comments", description = "List all comments attached to blog with id")
    @APIResponse(responseCode = "200", description = "Comments listed", content = @Content(schema = @Schema(implementation = Comment.class, type = SchemaType.ARRAY)))
    @APIResponse(responseCode = "400", description = "Blog not found", content = @Content(schema = @Schema(implementation = HttpError.class)))
    @GET
    public List<Comment> list(@PathParam("blogId") Long blogId) {
        return service.list(blogId);
    }

    @Operation(summary = "Create comment", description = "Create new comment for blog with id")
    @APIResponse(responseCode = "201", description = "Comment created", content = @Content(schema = @Schema(implementation = Comment.class)))
    @APIResponse(responseCode = "400", description = "Blog not found", content = @Content(schema = @Schema(implementation = HttpError.class)))
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(@PathParam("blogId") Long blogId, @Valid Comment comment) {
        return Response.status(201).entity(service.create(blogId, comment)).build();
    }

    @Operation(summary = "Get comment", description = "Get comment by blog id and comment id")
    @APIResponse(responseCode = "200", description = "Comment retrieved")
    @APIResponse(responseCode = "400", description = "Blog or comment not found", content = @Content(schema = @Schema(implementation = HttpError.class)))
    @GET
    @Path("{id}")
    public Comment get(@PathParam("blogId") Long blogId, @PathParam("id") Long id) {
        return service.get(blogId, id);
    }

    @Operation(summary = "Update comment", description = "Update comment by blog id and comment id")
    @APIResponse(responseCode = "200", description = "Comment updated")
    @APIResponse(responseCode = "400", description = "Blog or comment not found", content = @Content(schema = @Schema(implementation = HttpError.class)))
    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Comment update(@PathParam("blogId") Long blogId, @PathParam("id") Long id, @Valid Comment comment) {
        return service.update(blogId, id, comment);
    }

    @Operation(summary = "Delete comment", description = "Delete comment by blog id and comment id")
    @APIResponse(responseCode = "204", description = "Comment deleted")
    @APIResponse(responseCode = "400", description = "Blog or comment not found", content = @Content(schema = @Schema(implementation = HttpError.class)))
    @DELETE
    @Path("{id}")
    public void delete(@PathParam("blogId") Long blogId, @PathParam("id") Long id) {
        service.delete(blogId, id);
    }
}
