package org.stotic.dev.com.api;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.util.Map;

@Path("api")
public class HelloWorldApi {
    @GET
    @Path("data")
    @Produces(MediaType.APPLICATION_JSON)
    public Map<String, String> getData() {
        Map<String, String> map = Map.of("message", "Hello World!");
        return map;
    }
}
