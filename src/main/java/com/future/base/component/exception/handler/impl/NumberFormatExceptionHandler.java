package com.future.base.component.exception.handler.impl;

import com.future.base.component.exception.handler.inter.ExceptionHandler;
import com.future.base.component.exception.model.ExceptionInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.future.base.constant.common.ResponseElement.INVALID_PARAM;

/**
 * invalid param exp handler
 *
 * @author liuyunfei
 */
@SuppressWarnings({"unused"})
public class NumberFormatExceptionHandler implements ExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(NumberFormatExceptionHandler.class);

    private static final String EXP_NAME = "java.lang.NumberFormatException";

    private static final ExceptionInfo EXP_HANDLE_INFO = new ExceptionInfo(INVALID_PARAM);

    @Override
    public String exceptionName() {
        return EXP_NAME;
    }

    @Override
    public ExceptionInfo handle(Throwable throwable) {
        LOGGER.info("numberFormatExceptionHandler -> handle(Throwable throwable), throwable = {0}", throwable);
        return EXP_HANDLE_INFO;
    }

}
