package com.future.base.component.exception.handler.impl;

import com.future.base.component.exception.handler.inter.ExceptionHandler;
import com.future.base.component.exception.model.ExceptionInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.future.base.constant.common.ResponseElement.REQUEST_TIMEOUT;

/**
 * resilience4j exp handler
 *
 * @author liuyunfei
 */
@SuppressWarnings({"unused", "SpellCheckingInspection"})
public final class CallNotPermittedExceptionHandler implements ExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(CallNotPermittedExceptionHandler.class);

    private static final String EXP_NAME = "io.github.resilience4j.circuitbreaker.CallNotPermittedException";

    private static final ExceptionInfo EXP_HANDLE_INFO = new ExceptionInfo(REQUEST_TIMEOUT);

    @Override
    public String exceptionName() {
        return EXP_NAME;
    }

    @Override
    public ExceptionInfo handle(Throwable throwable) {
        LOGGER.info("callNotPermittedExceptionHandler -> handle(Throwable throwable), throwable = {0}", throwable);
        return EXP_HANDLE_INFO;
    }

}
