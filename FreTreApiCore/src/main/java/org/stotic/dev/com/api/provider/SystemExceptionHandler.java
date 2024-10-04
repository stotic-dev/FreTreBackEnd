package org.stotic.dev.com.api.provider;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import org.stotic.dev.com.api.exception.ApiResultCode;
import org.stotic.dev.com.exception.SystemException;
import org.stotic.dev.com.dto.ApiResultDto;
import org.stotic.dev.com.logger.ApiLogger;

public class SystemExceptionHandler implements ExceptionMapper<SystemException> {

    @Override
    public Response toResponse(SystemException e) {
        ApiLogger.log.error(e.toString());
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(new ApiResultDto(ApiResultCode.SYSTEM_ERROR.getCode()))
                .build();
    }
}
