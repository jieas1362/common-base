package com.future.base.component.exception.handler.impl;

import com.future.base.component.exception.handler.inter.ExceptionHandler;
import com.future.base.component.exception.model.ExceptionInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.future.base.constant.common.ResponseElement.BAD_REQUEST;

/**
 * another json parse exp handler
 *
 * @author liuyunfei
 */
@SuppressWarnings({"unused"})
public final class MalformedJsonExceptionHandler implements ExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(MalformedJsonExceptionHandler.class);

    private static final String EXP_NAME = "com.google.gson.stream.MalformedJsonException";

    private static final ExceptionInfo EXP_HANDLE_INFO = new ExceptionInfo(BAD_REQUEST);

    @Override
    public String exceptionName() {
        return EXP_NAME;
    }

    @Override
    public ExceptionInfo handle(Throwable throwable) {
        LOGGER.info("malformedJsonExceptionHandler -> handle(Throwable throwable), throwable = {0}", throwable);
        return EXP_HANDLE_INFO;
    }

}
