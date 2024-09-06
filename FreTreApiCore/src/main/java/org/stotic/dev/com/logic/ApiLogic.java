package org.stotic.dev.com.logic;

import org.stotic.dev.com.dto.ApiResultDto;

public interface ApiLogic {

    <Input, Output extends ApiResultDto> Output process(Input input) throws Exception;
}
