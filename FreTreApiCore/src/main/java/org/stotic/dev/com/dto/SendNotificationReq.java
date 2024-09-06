package org.stotic.dev.com.dto;

import jakarta.validation.constraints.NotNull;

import java.util.List;

public class SendNotificationReq {

    // 送信元の名前
    @NotNull
    private String srcName;
    // 宛先のtoken
    @NotNull
    private List<String> destinations;
    // Apnsペイロードのjson
    @NotNull
    private String payload;

    public String getSrcName() {
        return srcName;
    }

    public List<String> getDestinations() {
        return destinations;
    }

    public String getPayload() {
        return payload;
    }
}
