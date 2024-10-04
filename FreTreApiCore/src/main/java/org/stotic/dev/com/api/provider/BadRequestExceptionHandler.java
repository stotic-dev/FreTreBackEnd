package org.stotic.dev.com.api.provider;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import org.stotic.dev.com.api.exception.ApiResultCode;
import org.stotic.dev.com.dto.ApiResultDto;
import org.stotic.dev.com.logger.ApiLogger;

@Provider
public class BadRequestExceptionHandler implements ExceptionMapper<NullPointerException> {

    @Override
    public Response toResponse(NullPointerException e) {
        ApiLogger.log.error(e.toString());
        return Response.status(Response.Status.BAD_REQUEST)
                .entity(new ApiResultDto(ApiResultCode.BAD_REQUEST.getCode()))
                .build();
    }
}
