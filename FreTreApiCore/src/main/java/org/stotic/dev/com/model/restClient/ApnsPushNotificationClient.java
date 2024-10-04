package org.stotic.dev.com.model.restClient;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.RequestScoped;
import org.stotic.dev.com.client.ApiResponse;
import org.stotic.dev.com.client.HttpClient;
import org.stotic.dev.com.exception.SystemException;
import org.stotic.dev.com.logger.ApiLogger;
import org.stotic.dev.com.model.PushNotificationApnsResponseData;
import org.stotic.dev.com.model.restClient.service.ApnsPushNotificationHttpService;

import java.io.IOException;

@ApplicationScoped
public class ApnsPushNotificationClient {

    private HttpClient client;

    public ApnsPushNotificationClient() {
        ApiLogger.log.debug("[In]");
        client = new HttpClient();
        ApiLogger.log.info("Complete initialize ApnsPushNotificationClient");
    }

    public ApiResponse<PushNotificationApnsResponseData> execute(ApnsPushNotificationHttpService service) throws SystemException {
        try {
            return client.post(service, PushNotificationApnsResponseData.class);
        }
        catch(IOException e) {
            throw new SystemException(String.format("Failed apns request(error=%s)", e.toString()));
        }
    }
}
