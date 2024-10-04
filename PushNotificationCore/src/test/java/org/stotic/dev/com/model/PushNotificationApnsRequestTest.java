package org.stotic.dev.com.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Map;

public class PushNotificationApnsRequestTest {

    PushNotificationApnsRequest.Headers testHeader;
    PushNotificationApnsRequest.Aps testAps;

    private String testPath = "/3/device/00fc13adff785122b4ad28809a3420982341241421348097878e577c991de8f0";
    private String testAuth = "bearer eyAia2lkIjogIjhZTDNHM1JSWDciIH0.eyAiaXNzIjogIkM4Nk5WOUpYM0QiLCAiaWF0I\n" +
            "\t\t jogIjE0NTkxNDM1ODA2NTAiIH0.MEYCIQDzqyahmH1rz1s-LFNkylXEa2lZ_aOCX4daxxTZkVEGzwIhALvkClnx5m5eAT6\n" +
            "\t\t Lxw7LZtEQcH6JENhJTMArwLf3sXwi";
    private PushNotificationType testType = PushNotificationType.ALERT;

    private String testTitle = "Test Title";
    private String testBody = "Test Message";

    @Test
    public void testCreateHeader() throws Exception {

        PushNotificationApnsRequest.Headers headers = new PushNotificationApnsRequest.Headers(testPath,testAuth,testType);
        PushNotificationApnsRequest.Aps aps = new PushNotificationApnsRequest.Aps(new PushNotificationApnsRequest.Aps.Payload(new PushNotificationApnsRequest.Aps.Payload.Alert(testTitle,testBody)));
        PushNotificationApnsRequest request = new PushNotificationApnsRequest(headers,aps);

        Map<String, String> resultHeader = request.getHeaders();

        Assertions.assertEquals(3, resultHeader.size());
        Assertions.assertEquals(testPath, resultHeader.get("path"));
        Assertions.assertEquals(testAuth, resultHeader.get("authorization"));
        Assertions.assertEquals(testType.getKey(), resultHeader.get("apns-push-type"));

        String payload = request.getPayloadJsonString();

        String expectedPayload = """
                {
                  "aps" : {
                    "alert" : {
                      "title" : "Test Title",
                      "body" : "Test Message"
                    }
                  }
                }""";
        Assertions.assertEquals(expectedPayload, payload);
    }
}
