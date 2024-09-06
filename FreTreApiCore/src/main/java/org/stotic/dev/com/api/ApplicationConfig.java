//package org.stotic.dev.com.api;
//
//import io.swagger.jaxrs.config.BeanConfig;
//
//import javax.ws.rs.ApplicationPath;
//import java.util.Set;
//
//@ApplicationPath("/fretre")
//public class ApplicationConfig extends javax.ws.rs.core.Application {
//
//    // "simple.maven.glassfish.jaxrs.resource"
//    private static final String RESOURCE_PACKAGE = PushNotificationApi.class.getPackage().getName();
//
//    public ApplicationConfig() {
//        BeanConfig beanConfig = new BeanConfig();
//        beanConfig.setTitle("FreTreDoc");
//        beanConfig.setDescription("A FreTre project.");
//        beanConfig.setVersion("1.0.0");
//        beanConfig.setSchemes(new String[]{"http", "https"});
//        beanConfig.setHost("localhost:8080"); // ex. "localhost:8002"
//        beanConfig.setBasePath("/FreTreDoc/fretre");
//        beanConfig.setPrettyPrint(true);
//
//        beanConfig.setResourcePackage(RESOURCE_PACKAGE); // ex. "io.swagger.resources"
//        beanConfig.setScan(true);
//    }
//
//    @Override
//    public Set<Class<?>> getClasses() {
//        Set<Class<?>> resources = new java.util.HashSet<>();
//        addRestResourceClasses(resources);
//
//        // enable Swagger
//        resources.add(io.swagger.jaxrs.listing.ApiListingResource.class);
//        resources.add(io.swagger.jaxrs.listing.SwaggerSerializers.class);
//
//        return resources;
//    }
//
//    private void addRestResourceClasses(Set<Class<?>> resources) {
//        resources.add(PushNotificationApi.class);
//    }
//}
