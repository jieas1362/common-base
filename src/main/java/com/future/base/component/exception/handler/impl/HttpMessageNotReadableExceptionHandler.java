package com.future.base.component.exception.handler.impl;

import com.future.base.component.exception.handler.inter.ExceptionHandler;
import com.future.base.component.exception.model.ExceptionInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.future.base.constant.common.ResponseElement.EMPTY_PARAM;

/**
 * null param exp handler
 *
 * @author liuyunfei
 */
@SuppressWarnings({"unused"})
public class HttpMessageNotReadableExceptionHandler implements ExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(HttpMessageNotReadableExceptionHandler.class);

    private static final String EXP_NAME = "org.springframework.http.converter.HttpMessageNotReadableException";

    private static final ExceptionInfo EXP_HANDLE_INFO = new ExceptionInfo(EMPTY_PARAM);

    @Override
    public String exceptionName() {
        return EXP_NAME;
    }

    @Override
    public ExceptionInfo handle(Throwable throwable) {
        LOGGER.info("httpMessageNotReadableExceptionHandler -> handle(Throwable throwable), throwable = {0}", throwable);
        return EXP_HANDLE_INFO;
    }

}
