package com.future.base.component.exception.handler.impl;

import com.future.base.component.exception.handler.inter.ExceptionHandler;
import com.future.base.component.exception.model.ExceptionInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.future.base.constant.common.ResponseElement.REQUEST_TIMEOUT;

/**
 * connection exp handler
 *
 * @author liuyunfei
 */
@SuppressWarnings({"unused"})
public final class ConnectExceptionHandler implements ExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConnectExceptionHandler.class);

    private static final String EXP_NAME = "java.net.ConnectException";

    private static final ExceptionInfo EXP_HANDLE_INFO = new ExceptionInfo(REQUEST_TIMEOUT);

    @Override
    public String exceptionName() {
        return EXP_NAME;
    }

    @Override
    public ExceptionInfo handle(Throwable throwable) {
        LOGGER.info("connectExceptionHandler -> handle(Throwable throwable), throwable = {0}", throwable);
        return EXP_HANDLE_INFO;
    }
}
