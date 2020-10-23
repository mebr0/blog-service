package com.mebr0.handler;

import com.mebr0.dto.HttpError;
import com.mebr0.exception.NotFoundException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class NotFoundExceptionMapper implements ExceptionMapper<NotFoundException> {

    @Override
    public Response toResponse(NotFoundException exception) {
        return Response.
                status(400).
                entity(HttpError.of(exception.getLocalizedMessage())).
                build();
    }
}
