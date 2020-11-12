package com.mebr0.handler;

import com.mebr0.dto.HttpError;
import lombok.extern.slf4j.Slf4j;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Handle {@link NotFoundException} from jax-rs
 * Return as {@link HttpError} with http status 404
 * Log its message with ERROR level
 */
@Slf4j
@Provider
public class NotFoundExceptionMapper implements ExceptionMapper<NotFoundException> {

    @Override
    public Response toResponse(NotFoundException exception) {
        log.error(exception.getLocalizedMessage());

        return Response.
                status(404).
                entity(HttpError.of(exception.getLocalizedMessage())).
                build();
    }
}
