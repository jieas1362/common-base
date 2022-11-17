package com.future.base.component.exception.handler.impl;

import com.future.base.component.exception.handler.inter.ExceptionHandler;
import com.future.base.component.exception.model.ExceptionInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.future.base.constant.common.ResponseElement.NOT_FOUND;

/**
 * method not support exp handler
 *
 * @author liuyunfei
 */
@SuppressWarnings({"unused"})
public final class HttpRequestMethodNotSupportedExceptionHandler implements ExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(HttpRequestMethodNotSupportedExceptionHandler.class);

    private static final String EXP_NAME = "org.springframework.web.HttpRequestMethodNotSupportedException";

    private static final ExceptionInfo EXP_HANDLE_INFO = new ExceptionInfo(NOT_FOUND);

    @Override
    public String exceptionName() {
        return EXP_NAME;
    }

    @Override
    public ExceptionInfo handle(Throwable throwable) {
        LOGGER.info("httpRequestMethodNotSupportedExceptionHandler -> handle(Throwable throwable), throwable = {0}", throwable);
        return EXP_HANDLE_INFO;
    }
}
