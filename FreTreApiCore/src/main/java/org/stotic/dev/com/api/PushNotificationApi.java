package org.stotic.dev.com.api;

import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.stotic.dev.com.api.exception.ApiServiceException;
import org.stotic.dev.com.exception.SystemException;
import org.stotic.dev.com.dto.SendNotificationReq;
import org.stotic.dev.com.dto.SendNotificationRes;
import org.stotic.dev.com.logger.ApiLogger;
import org.stotic.dev.com.logic.SendNotificationLogic;

@Path("/push-notification")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PushNotificationApi {

    @Inject
    SendNotificationLogic logic;

    @POST
    @Path("/send")
    public SendNotificationRes sendNotification(@Valid SendNotificationReq req) throws ApiServiceException, SystemException {
        ApiLogger.log.info("[In] " + req.toStringContent());
        SendNotificationRes result = logic.process(req);
        ApiLogger.log.info("[Out] " + result.toStringContent());
        return result;
    }
}
