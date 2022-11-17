package com.future.base.component.exception.handler.impl;

import com.future.base.component.exception.handler.inter.ExceptionHandler;
import com.future.base.component.exception.model.ExceptionInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.future.base.constant.common.ResponseElement.BAD_REQUEST;

/**
 * input exp handler
 *
 * @author liuyunfei
 */
@SuppressWarnings({"unused"})
public final class ServerWebInputExceptionHandler implements ExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(ServerWebInputExceptionHandler.class);

    private static final String EXP_NAME = "org.springframework.web.server.ServerWebInputException";

    private static final ExceptionInfo EXP_HANDLE_INFO = new ExceptionInfo(BAD_REQUEST);

    @Override
    public String exceptionName() {
        return EXP_NAME;
    }

    @Override
    public ExceptionInfo handle(Throwable throwable) {
        LOGGER.info("serverWebInputExceptionHandler -> handle(Throwable throwable), throwable = {0}", throwable);
        return EXP_HANDLE_INFO;
    }

}
