package org.stotic.dev.com.api;

//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.stotic.dev.com.dto.SendNotificationReq;
import org.stotic.dev.com.dto.SendNotificationRes;
import org.stotic.dev.com.logic.ApiLogic;

import java.util.Map;

@Path("/push-notification")
//@Api(tags = "push-notification")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
//@ApplicationScoped
public class PushNotificationApi {

    @Inject
    private ApiLogic logic;

    @POST
    @Path("/send")
//    @ApiOperation(value = "Push Notification For iOS",
//            response = SendNotificationRes.class)
    public SendNotificationRes sendNotification(@Valid SendNotificationReq req) {
        return new SendNotificationRes("000");
    }

    @GET
    public Map<String, String> testGetApi() {
        Map<String, String> map = Map.of("message", "Hello World!");
        return map;
    }
}
