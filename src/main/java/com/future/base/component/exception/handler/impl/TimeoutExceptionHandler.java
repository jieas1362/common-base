package com.future.base.component.exception.handler.impl;

import com.future.base.component.exception.handler.inter.ExceptionHandler;
import com.future.base.component.exception.model.ExceptionInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.future.base.constant.common.ResponseElement.GATEWAY_TIMEOUT;

/**
 * timeout exp handler
 *
 * @author liuyunfei
 */
@SuppressWarnings({"unused"})
public final class TimeoutExceptionHandler implements ExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(TimeoutExceptionHandler.class);

    private static final String EXP_NAME = "org.springframework.cloud.gateway.support.TimeoutException";

    private static final ExceptionInfo EXP_HANDLE_INFO = new ExceptionInfo(GATEWAY_TIMEOUT);

    @Override
    public String exceptionName() {
        return EXP_NAME;
    }

    @Override
    public ExceptionInfo handle(Throwable throwable) {
        LOGGER.info("timeoutExceptionHandler -> handle(Throwable throwable), throwable = {0}", throwable);
        return EXP_HANDLE_INFO;
    }
}
