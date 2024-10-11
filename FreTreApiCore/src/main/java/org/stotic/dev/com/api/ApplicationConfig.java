package org.stotic.dev.com.api;

import io.swagger.v3.jaxrs2.integration.resources.OpenApiResource;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;
import org.glassfish.jersey.jackson.internal.jackson.jaxrs.json.JacksonJaxbJsonProvider;
import org.glassfish.jersey.jackson.internal.jackson.jaxrs.json.JacksonJsonProvider;
import org.glassfish.jersey.jsonb.internal.JsonBindingProvider;
import org.stotic.dev.com.logger.ApiLogger;

import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/service")
@OpenAPIDefinition(info = @Info(
        title = "FreTreBackEndServer",
        version = "1.0.0")
)
public class ApplicationConfig extends Application {

    public ApplicationConfig() {
        super();
        ApiLogger.log.info("[In]");
    }

    public Set<Class<?>> getClasses() {
        ApiLogger.log.info("[In]");
        Set<Class<?>> s = new HashSet<Class<?>>();
        s.add(HelloWorldApi.class);
        s.add(PushNotificationApi.class);
        s.add(JsonBindingProvider.class);
        s.add(JacksonJaxbJsonProvider.class);
        s.add(JacksonJsonProvider.class);
        s.add(OpenApiResource.class);
        return s;
    }
}
