package com.mebr0.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.inject.Inject;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

/**
 * Filter for logging requests and responses with {@link ContainerRequestFilter} and {@link ContainerResponseFilter}
 */
@Slf4j
@Provider
public class LoggingFilter implements ContainerRequestFilter, ContainerResponseFilter {

    @SuppressWarnings("CdiInjectionPointsInspection")
    @Inject
    ObjectMapper mapper;

    @Override
    public void filter(ContainerRequestContext request) {
        // Do not log anything, if log level higher than INFO
        if (!log.isInfoEnabled())
            return;

        var method = request.getMethod();
        var path = request.getUriInfo().getPath();
        var body = getBody(request);

        if (body.isBlank()) {
            log.info("[{}] {}", method, path);
        }
        else {
            log.info("[{}] {} - {}", method, path, body);

            request.setEntityStream(new ByteArrayInputStream(body.getBytes()));
        }
    }

    @Override
    public void filter(ContainerRequestContext request,
                       ContainerResponseContext response) throws IOException {
        // Do not log anything, if log level higher than INFO
        if (!log.isInfoEnabled())
            return;

        var method = request.getMethod();
        var path = request.getUriInfo().getPath();
        var status = response.getStatus();
        var body = mapper.writeValueAsString(response.getEntity());

        if ("null".equals(body) || body.isBlank()) {
            log.info("[{}] {} ({})", method, path, status);
        }
        else {
            log.info("[{}] {} ({}) - {}", method, path, status, body);
        }
    }

    /**
     * Get string representation of body of {@link ContainerRequestContext}
     * As well remove whitespaces from begin and end, remove end lines from end
     */
    private String getBody(ContainerRequestContext request) {
        return new BufferedReader(new InputStreamReader(request.getEntityStream())).
                lines().
                map(String::trim).
                map(StringUtils::chomp).
                collect(Collectors.joining());
    }
}
