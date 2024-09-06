package org.stotic.dev.com.client;

import java.net.URL;
import java.util.Map;

public interface ApiServiceInterface {

    public String getApiUrl();

    public Map<String, String> getHttpHeaders();

    public String getBody();
}
