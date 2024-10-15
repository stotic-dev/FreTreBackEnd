package org.stotic.dev.com.api.provider;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import org.stotic.dev.com.api.exception.ApiServiceException;
import org.stotic.dev.com.dto.ApiResultDto;
import org.stotic.dev.com.logger.ApiLogger;

@Provider
public class ApiServiceExceptionHandler implements ExceptionMapper<ApiServiceException> {

    @Override
    public Response toResponse(ApiServiceException e) {
        ApiLogger.log.error(String.format("[In] %s", e.toString()));
        return Response.status(Response.Status.NOT_ACCEPTABLE)
                .entity(new ApiResultDto(e.getResultCode().getCode()))
                .build();
    }
}
