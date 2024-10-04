package org.stotic.dev.com.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;

import java.util.HashMap;
import java.util.Map;

public class PushNotificationApnsRequest {

    // ヘッダー情報
    private Headers headers;

    private Aps aps;

    public PushNotificationApnsRequest(Headers headers, Aps aps) {
        this.headers = headers;
        this.aps = aps;
    }

    public PushNotificationApnsRequest(Headers headers, String aps) throws JsonProcessingException {
        this.headers = headers;
        this.aps = new ObjectMapper().readValue(aps, new TypeReference<>() {});
    }

    public Map<String, String> getHeaders() {
        Map<String, String> headersMap = new HashMap<>();

        headersMap.put("path", headers.path);
        headersMap.put("authorization", headers.authorization);
        headersMap.put("apns-push-type", headers.pushType.getKey());

        if (headers.apnsId != null) {
            headersMap.put("apns-id", headers.apnsId);
        }

        if (headers.apnsPriority != null) {
            headersMap.put("apns-priority", headers.apnsPriority.getValue());
        }

        if (headers.apnsTopic != null) {
            headersMap.put("apns-topic", headers.apnsTopic);
        }

        if (headers.apnsCollapseId != null) {
            headersMap.put("apns-collapse-id", headers.apnsCollapseId);
        }

        return headersMap;
    }

    public String getPayloadJsonString() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        return mapper.writeValueAsString(aps);
    }

    protected static class Headers {

        public Headers(@NotNull String path,
                       @NotNull String authorization,
                       @NotNull PushNotificationType pushType) {
            this.path = path;
            this.authorization = authorization;
            this.pushType = pushType;
        }

        // デバイストークンへのパス
        @NotNull
        private String path;
        // 通知を送信することを許可する暗号化されたトークン
        @NotNull
        private String authorization;
        // 通知するペイロードの種別
        @NotNull
        private PushNotificationType pushType;
        // 通知の一意のID(オプション)
        private String apnsId;
        // 通知が無効になる日付(オプション)
        private String apnsExpiration;
        // 通知の優先順位(オプション)
        private PushNotificationPriority apnsPriority;
        // 通知のトピック(バンドルID/アプリID)(オプション)
        private String apnsTopic;
        // 複数の通知をユーザーへの1つの通知にマージするために使用する識別子(オプション)
        private String apnsCollapseId;

        public void setApnsId(String apnsId) {
            this.apnsId = apnsId;
        }

        public Headers setApnsExpiration(String apnsExpiration) {
            this.apnsExpiration = apnsExpiration;
            return this;
        }

        public Headers setApnsPriority(PushNotificationPriority apnsPriority) {
            this.apnsPriority = apnsPriority;
            return this;
        }

        public Headers setApnsTopic(String apnsTopic) {
            this.apnsTopic = apnsTopic;
            return this;
        }

        public Headers setApnsCollapseId(String apnsCollapseId) {
            this.apnsCollapseId = apnsCollapseId;
            return this;
        }
    }

    protected static class Aps {

        private Payload aps;

        public Aps(Payload aps) {
            this.aps = aps;
        }

        public Payload getAps() {
            return aps;
        }

        @JsonInclude(JsonInclude.Include.NON_NULL)
        protected static class Payload {

            public Payload(Alert alert) {
                this.alert = alert;
            }

            // 通知を表示するための情報
            private Alert alert;
            // アプリのアイコンのバッジに表示される番号
            // 現在のバッジがある場合は削除するように0を指定します。
            private Integer badge;
            // サウンドの詳細設定
            @JsonProperty("sound")
            private Sound soundDetailConf;
            // 関連する通知をグループ化するためのアプリ固有の識別子
            @JsonProperty("thread-id")
            private String threadId;
            // 通知のタイプ
            // この文字列は、起動時に登録するオブジェクトの に対応している必要があります
            // アプリ側は通知に含まれるactionIdentifierとしてこの値を見て通知を区別できる
            private String category;
            // バックグラウンド通知フラグ
            // バックグラウンド通知する時は1を、そうでなければ設定しない
            @JsonProperty("content-available")
            private Integer contentAvailable;
            // 通知サービス アプリ拡張機能フラグ
            // 値が1の場合、システムは通知を配信前に通知をアプリ拡張機能に渡します
            @JsonProperty("mutable-content")
            private Integer mutableContent;
            // ユーザが通知をタップした時にアプリが表示する画面の識別子
            // このキーの値は、通知ペイロードから作成されたオブジェクトに設定されます
            @JsonProperty("target-content-id")
            private String targetContentId;
            // 通知の重要度と配信タイミング
            // 文字列値“passive”,“active”,“time-sensitive”,"critical"
            // https://developer.apple.com/documentation/usernotifications/unnotificationinterruptionlevel
            @JsonProperty("interruption-level")
            private String interruptionLevel;
            // 関連性スコアは、システムがアプリからの通知を並べ替えるために使用する数値
            // 最も高いスコアが通知の概要に表示される
            @JsonProperty("relevance-score")
            private Integer relevanceScore;
            // システムが現在のフォーカスに通知を表示するかどうかを決定するために評価する基準
            @JsonProperty("filter-criteria")
            private String filterCriteria;

            public Alert getAlert() {
                return alert;
            }

            public Integer getBadge() {
                return badge;
            }

            public Sound getSoundDetailConf() {
                return soundDetailConf;
            }

            public String getThreadId() {
                return threadId;
            }

            public String getCategory() {
                return category;
            }

            public Integer getContentAvailable() {
                return contentAvailable;
            }

            public Integer getMutableContent() {
                return mutableContent;
            }

            public String getTargetContentId() {
                return targetContentId;
            }

            public String getInterruptionLevel() {
                return interruptionLevel;
            }

            public Integer getRelevanceScore() {
                return relevanceScore;
            }

            public String getFilterCriteria() {
                return filterCriteria;
            }

            public Payload setBadge(Integer badge) {
                this.badge = badge;
                return this;
            }

            public Payload setSoundDetailConf(Sound soundDetailConf) {
                this.soundDetailConf = soundDetailConf;
                return this;
            }

            public Payload setThreadId(String threadId) {
                this.threadId = threadId;
                return this;
            }

            public Payload setCategory(String category) {
                this.category = category;
                return this;
            }

            public Payload setContentAvailable(Integer contentAvailable) {
                this.contentAvailable = contentAvailable;
                return this;
            }

            public Payload setMutableContent(Integer mutableContent) {
                this.mutableContent = mutableContent;
                return this;
            }

            public Payload setTargetContentId(String targetContentId) {
                this.targetContentId = targetContentId;
                return this;
            }

            public Payload setInterruptionLevel(String interruptionLevel) {
                this.interruptionLevel = interruptionLevel;
                return this;
            }

            public Payload setRelevanceScore(Integer relevanceScore) {
                this.relevanceScore = relevanceScore;
                return this;
            }

            public Payload setFilterCriteria(String filterCriteria) {
                this.filterCriteria = filterCriteria;
                return this;
            }

            @JsonInclude(JsonInclude.Include.NON_NULL)
            protected static class Alert {

                // タイトル
                private String title;
                // 通知の目的を説明する追加情報
                @JsonProperty("subtitle")
                private String subTitle;
                // 通知のメッセージ内容
                private String body;
                // 表示する起動イメージファイルの名前
                @JsonProperty("launch-image")
                private String launchImage;
                // ローカライズされたタイトル文字列のキー
                // アプリ側でタイトルを指定する場合はtitleキーの代わりにこちらを指定する
                @JsonProperty("title-loc-key")
                private String titleLocKey;
                // タイトル文字列内の変数の置換値を含む文字列の配列
                @JsonProperty("title-loc-args")
                private String[] titleLocArgs;
                // ローカライズされたサブタイトルの文字列のキー
                @JsonProperty("subtitle-loc-key")
                private String subtitleLocKey;
                @JsonProperty("subtitle-loc-args")
                private String[] subtitleLocArgs;
                @JsonProperty("loc-key")
                private String locKey;
                @JsonProperty("loc-args")
                private String[] locArgs;

                public Alert(@Nullable String title, @Nullable String body) {
                    this.title = title;
                    this.body = body;
                }

                public String getTitle() {
                    return title;
                }

                public String getSubTitle() {
                    return subTitle;
                }

                public String getBody() {
                    return body;
                }

                public String getTitleLocKey() {
                    return titleLocKey;
                }

                public String[] getTitleLocArgs() {
                    return titleLocArgs;
                }

                public String getSubtitleLocKey() {
                    return subtitleLocKey;
                }

                public String[] getSubtitleLocArgs() {
                    return subtitleLocArgs;
                }

                public String getLocKey() {
                    return locKey;
                }

                public String[] getLocArgs() {
                    return locArgs;
                }

                public String getLaunchImage() {
                    return launchImage;
                }

                public Alert setSubTitle(String subTitle) {
                    this.subTitle = subTitle;
                    return this;
                }

                public Alert setLaunchImage(String launchImage) {
                    this.launchImage = launchImage;
                    return this;
                }

                public Alert setTitleLocKey(String titleLocKey) {
                    this.titleLocKey = titleLocKey;
                    return this;
                }

                public Alert setTitleLocArgs(String[] titleLocArgs) {
                    this.titleLocArgs = titleLocArgs;
                    return this;
                }

                public Alert setSubtitleLocKey(String subtitleLocKey) {
                    this.subtitleLocKey = subtitleLocKey;
                    return this;
                }

                public Alert setSubtitleLocArgs(String[] subtitleLocArgs) {
                    this.subtitleLocArgs = subtitleLocArgs;
                    return this;
                }

                public Alert setLocKey(String locKey) {
                    this.locKey = locKey;
                    return this;
                }

                public Alert setLocArgs(String[] locArgs) {
                    this.locArgs = locArgs;
                    return this;
                }
            }

            @JsonInclude(JsonInclude.Include.NON_NULL)
            protected static class Sound {
                // 重大なアラートフラグ
                // 重大なアラートを有効にするには1に設定します
                private Integer critical;
                // サウンドファイルの名前
                // Library/Soundsアプリのメインバンドル内またはアプリのコンテナディレクトリのフォルダ内のファイルを指定
                private String name;
                // 重大なアラートの音量
                // これを0(無音)から1(最大音量)の間の値に設定します。
                private Integer volume;

                protected Sound() {}

                protected Sound(Integer critical, String name, Integer volume) {
                    this.critical = critical;
                    this.name = name;
                    this.volume = volume;
                }

                public Integer getCritical() {
                    return critical;
                }

                public String getName() {
                    return name;
                }

                public Integer getVolume() {
                    return volume;
                }
            }
        }
    }
}
