package org.stotic.dev.com.logic;

import org.stotic.dev.com.api.exception.ApiServiceException;
import org.stotic.dev.com.exception.SystemException;
import org.stotic.dev.com.dto.ApiResultDto;

public interface ApiLogic<Input, Output extends ApiResultDto> {

    Output process(Input input) throws ApiServiceException, SystemException;
}
