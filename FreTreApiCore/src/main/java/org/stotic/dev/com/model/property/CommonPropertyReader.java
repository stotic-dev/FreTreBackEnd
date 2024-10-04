package org.stotic.dev.com.model.property;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import org.stotic.dev.com.logger.ApiLogger;
import org.stotic.dev.com.utilities.PropertyUtility;

@ApplicationScoped
public class CommonPropertyReader {

    private PropertyUtility property;

    private static final String PROPERTY_FILE_PATH = "src/main/resources/common.property";

    public CommonPropertyReader() {
        property = new PropertyUtility(PROPERTY_FILE_PATH);
        ApiLogger.log.debug("Complete initialize CommonPropertyReader: " + property.toString());
    }

    public String getValue(CommonPropertyKey key) {
        return property.getProperty(key.getKey(), "");
    }
}

