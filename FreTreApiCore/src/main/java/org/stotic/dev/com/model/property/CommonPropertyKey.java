package org.stotic.dev.com.model.property;

public enum CommonPropertyKey {

    /**
     * APNSのベースURL(開発用)
     */
    APNS_DEV_CONNECT_URL("apns.dev.connect.url"),
    /**
     * APNSのベースURL(本番用)
     */
    APNS_PRD_CONNECT_URL("apns.prd.connect.url"),
    /**
     * FreTreアプリのバンドルID
     */
    APP_BUNDLE_ID("app.bundle.id"),
    /**
     * APNSのプライベートキー(.p8)のファイルパス
     */
    APNS_PRIVATE_KEY_PATH("apns.private.key.path");

    private final String key;

    CommonPropertyKey(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}
