package org.stotic.dev.com.api.provider;

import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.container.PreMatching;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;
import org.stotic.dev.com.api.exception.ApiResultCode;
import org.stotic.dev.com.dto.ApiResultDto;
import org.stotic.dev.com.exception.SystemException;
import org.stotic.dev.com.logger.ApiLogger;
import org.stotic.dev.com.model.property.SystemPropertyKey;
import org.stotic.dev.com.utilities.SystemVariableUtility;

import java.io.IOException;

@PreMatching
@Provider
public class ApiAuthFilter implements ContainerRequestFilter {

    private static final String AUTH_HEADER_KEY = "authenticate";

    @Override
    public void filter(ContainerRequestContext containerRequestContext) throws IOException {
        ApiLogger.log.info("[In]");
        String auth = containerRequestContext.getHeaderString(AUTH_HEADER_KEY);
        try {
            if(validateAuth(auth)) { return; }
            ApiLogger.log.info(String.format("NOT AUTHORIZATION: $s.", auth));
            containerRequestContext.abortWith(createAbortResponse(ApiResultCode.NOT_AUTHRIZATION));
        } catch (SystemException e) {
            containerRequestContext.abortWith(createAbortResponse(ApiResultCode.SYSTEM_ERROR));
        }
    }

    private boolean validateAuth(String auth) throws SystemException {
        if(auth == null) { return false; }
        String originalAuth = SystemVariableUtility.getValue(SystemPropertyKey.API_AUTH);
        return auth.equals(originalAuth);
    }

    private Response createAbortResponse(ApiResultCode code) {
        return Response
                .status(Response.Status.UNAUTHORIZED)
                .type(MediaType.APPLICATION_JSON)
                .entity(new ApiResultDto(code.getCode()))
                .build();
    }
}
