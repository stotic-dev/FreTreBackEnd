package org.stotic.dev.com.model.property;

import org.stotic.dev.com.utilities.SystemVariableKeys;

public enum SystemPropertyKey implements SystemVariableKeys {

    /**
     * AppのTeamId
     */
    APP_TEAM_ID("teamId"),
    /**
     * Apns認証のキーID
     */
    APNS_KEY_ID("apnsKeyId"),
    /**
     * 共通プロパティファイルのパス
     */
    COMMON_PROPERTY_PATH("commonPropertyPath");

    SystemPropertyKey(String key) {
        this.key = key;
    }

    private String key;

    public String getKey() {
        return key;
    }
}
