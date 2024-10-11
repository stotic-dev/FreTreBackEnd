package org.stotic.dev.com.model.property;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import org.stotic.dev.com.exception.SystemException;
import org.stotic.dev.com.logger.ApiLogger;
import org.stotic.dev.com.utilities.PropertyUtility;
import org.stotic.dev.com.utilities.SystemVariableUtility;

@ApplicationScoped
public class CommonPropertyReader {

    private PropertyUtility property;

//    private static final String PROPERTY_FILE_PATH = "/Users/satoutaichi/work/server/FreTreBackEnd/FreTreApiCore/src/main/resources/common.property";

    public CommonPropertyReader() throws SystemException {
        property = new PropertyUtility(SystemVariableUtility.getValue(SystemPropertyKey.COMMON_PROPERTY_PATH));
        ApiLogger.log.debug("Complete initialize CommonPropertyReader: " + property.toString());
    }

    public String getValue(CommonPropertyKey key) {
        return property.getProperty(key.getKey(), "");
    }
}

