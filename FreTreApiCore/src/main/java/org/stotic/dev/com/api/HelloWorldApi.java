package org.stotic.dev.com.api;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.stotic.dev.com.logger.ApiLogger;

import java.util.Map;

@Path("hello")
public class HelloWorldApi {
    @GET
    @Path("/data")
    @Produces(MediaType.APPLICATION_JSON)
    public Map<String, String> getData() {
        ApiLogger.log.info("[In]");
        Map<String, String> map = Map.of("message", "Hello World!");
        return map;
    }
}
