package org.stotic.dev.com.logic;

import jakarta.enterprise.context.RequestScoped;
import org.stotic.dev.com.dto.ApiResultDto;

@RequestScoped
public class SendNotificationLogic implements ApiLogic {

    @Override
    public <Input, Output extends ApiResultDto> Output process(Input input) throws Exception {
        return null;
    }
}
