package com.mebr0.handler;

import com.mebr0.dto.HttpError;
import lombok.extern.slf4j.Slf4j;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Handle {@link ConstraintViolationException} from hibernate validator
 * Return as {@link HttpError} with http status 400
 * Log its message with ERROR level
 */
@Slf4j
@Provider
public class ConstraintViolationExceptionMapper implements ExceptionMapper<ConstraintViolationException> {

    @Override
    public Response toResponse(ConstraintViolationException exception) {
        var violations = exception.getConstraintViolations().toArray(new ConstraintViolation[1]);
        var violation = violations[0];

        log.error(violation.getMessage());

        return Response.
                status(400).
                entity(HttpError.of(violation.getMessage())).
                build();
    }
}
