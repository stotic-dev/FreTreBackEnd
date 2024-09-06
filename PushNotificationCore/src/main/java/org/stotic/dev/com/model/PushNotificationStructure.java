package org.stotic.dev.com.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public class PushNotificationStructure {

    @JsonCreator
    public PushNotificationStructure(@JsonProperty("app_id") String appId,
                                     @JsonProperty("title") String title,
                                     @JsonProperty("message") String message,
                                     @JsonProperty("destination") List<String> destination,
                                     @JsonProperty("type") String typeKey) {
        this.appId = appId;
        this.title = title;
        this.message = message;
        this.destination = destination;
        this.notificationType = PushNotificationType.getType(typeKey);
    }

    @NotNull
    String appId;
    @NotNull
    String title;
    @NotNull
    String message;
    @NotEmpty
    List<String> destination;
    @NotNull
    PushNotificationType notificationType;

    public String getAppId() {
        return appId;
    }

    public String getTitle() {
        return title;
    }

    public String getMessage() {
        return message;
    }

    public List<String> getDestination() {
        return destination;
    }

    public PushNotificationType getNotificationType() {
        return notificationType;
    }
}
