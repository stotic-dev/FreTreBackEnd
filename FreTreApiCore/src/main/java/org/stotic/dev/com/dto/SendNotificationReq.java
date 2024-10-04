package org.stotic.dev.com.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import jakarta.validation.constraints.*;

import java.util.List;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.PUBLIC_ONLY)
public class SendNotificationReq extends ApiRequestDto {

    // 送信元の名前
    @NotBlank(message = "Not found srcName") private String srcName;
    // 宛先のtoken
    @NotNull(message = "Not found srcName") private List<String> destinations;
    // Apnsペイロードのjson
    @NotBlank(message = "Not found srcName") private String payload;
    // 開発向けのリクエストかどうか
    private Boolean developFlg = true;
    // 通知の優先度
    @Min(value = 1, message = "No allow less than 1") @Max(value = 10, message = "No allow 10 over") private Integer notificationPriority;
    // 通知の有効期限(UNIXエポック)
    @NotEmpty(message = "No allow empty string") private String notificationExpiration;
    // 複数通知をマージするための識別子
    @NotEmpty(message = "No allow empty string") private String collapseId;

    public SendNotificationReq(@NotBlank String srcName, @NotBlank List<String> destinations, @NotBlank String payload, Boolean developFlg, @Min(value = 1) @Max(value = 10) Integer notificationPriority, @NotEmpty String notificationExpiration, @NotEmpty @Max(value = 64) String collapseId) {
        this.srcName = srcName;
        this.destinations = destinations;
        this.payload = payload;
        this.developFlg = developFlg;
        this.notificationPriority = notificationPriority;
        this.notificationExpiration = notificationExpiration;
        this.collapseId = collapseId;
    }

    public SendNotificationReq() {
        srcName = null;
        destinations = null;
        payload = null;
        developFlg = null;
        notificationPriority = null;
        notificationExpiration = null;
        collapseId = null;
    }

    public String getSrcName() {
        return srcName;
    }

    public List<String> getDestinations() {
        return destinations;
    }

    public String getPayload() {
        return payload;
    }

    public Boolean getDevelopFlg() {
        return developFlg;
    }

    public Integer getNotificationPriority() {
        return notificationPriority;
    }

    public String getNotificationExpiration() {
        return notificationExpiration;
    }

    public String getCollapseId() {
        return collapseId;
    }

    public void setSrcName(String srcName) {
        this.srcName = srcName;
    }

    public void setDestinations(List<String> destinations) {
        this.destinations = destinations;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    public void setDevelopFlg(Boolean developFlg) {
        this.developFlg = developFlg;
    }

    public void setNotificationPriority(Integer notificationPriority) {
        this.notificationPriority = notificationPriority;
    }

    public void setNotificationExpiration(String notificationExpiration) {
        this.notificationExpiration = notificationExpiration;
    }

    public void setCollapseId(String collapseId) {
        this.collapseId = collapseId;
    }
}
