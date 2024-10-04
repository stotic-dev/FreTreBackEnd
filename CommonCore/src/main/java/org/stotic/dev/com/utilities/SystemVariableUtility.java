package org.stotic.dev.com.utilities;

import org.stotic.dev.com.exception.SystemException;
import org.stotic.dev.com.logger.ApiLogger;

public class SystemVariableUtility {

    public static String getValue(SystemVariableKeys key) throws SystemException {
        String value = System.getProperty(key.getKey());
        if (value == null) {
            throw new SystemException(String.format("Failed read system property(key=%s)", key.getKey()));
        }
        return value;
    }

    public static String getValue(SystemVariableKeys key, String defaultValue) {
        return System.getProperty(key.getKey(), defaultValue);
    }
}
