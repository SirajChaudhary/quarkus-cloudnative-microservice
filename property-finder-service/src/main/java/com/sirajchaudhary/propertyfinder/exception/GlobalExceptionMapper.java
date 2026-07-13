package com.sirajchaudhary.propertyfinder.exception;

import com.sirajchaudhary.propertyfinder.dto.response.ErrorResponse;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import org.jboss.logging.Logger;

import java.time.LocalDateTime;
import java.util.List;

@Provider
public class GlobalExceptionMapper implements ExceptionMapper<Exception> {

    private static final Logger LOGGER =
            Logger.getLogger(GlobalExceptionMapper.class);

    @Context
    HttpHeaders headers;

    @Override
    public Response toResponse(Exception exception) {

        LOGGER.error("Unhandled exception", exception);

        ErrorResponse response = new ErrorResponse(
                LocalDateTime.now(),
                Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(),
                Response.Status.INTERNAL_SERVER_ERROR.getReasonPhrase(),
                exception.getMessage(),
                "",
                List.of()
        );

        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(response)
                .build();
    }
}