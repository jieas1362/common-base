package com.future.base.component.exception.handler.impl;

import com.future.base.component.exception.handler.inter.ExceptionHandler;
import com.future.base.component.exception.model.ExceptionInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.future.base.constant.common.ResponseElement.BAD_REQUEST;

/**
 * invalid format exp handler
 *
 * @author liuyunfei
 */
@SuppressWarnings({"unused"})
public class InvalidFormatExceptionHandler implements ExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(InvalidFormatExceptionHandler.class);

    private static final String EXP_NAME = "com.fasterxml.jackson.databind.exc.InvalidFormatException";

    private static final ExceptionInfo EXP_HANDLE_INFO = new ExceptionInfo(BAD_REQUEST);

    @Override
    public String exceptionName() {
        return EXP_NAME;
    }

    @Override
    public ExceptionInfo handle(Throwable throwable) {
        LOGGER.info("invalidFormatExceptionHandler -> handle(Throwable throwable), throwable = {0}", throwable);
        return EXP_HANDLE_INFO;
    }

}
