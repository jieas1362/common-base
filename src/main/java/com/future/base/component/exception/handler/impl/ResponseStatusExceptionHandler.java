package com.future.base.component.exception.handler.impl;

import com.future.base.component.exception.handler.inter.ExceptionHandler;
import com.future.base.component.exception.model.ExceptionInfo;
import com.future.base.constant.common.ResponseElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.server.ResponseStatusException;


/**
 * resp status exp handler
 *
 * @author liuyunfei
 */
@SuppressWarnings({"unused"})
public final class ResponseStatusExceptionHandler implements ExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(ResponseStatusExceptionHandler.class);

    private static final String EXP_NAME = "org.springframework.web.server.ResponseStatusException";

    @Override
    public String exceptionName() {
        return EXP_NAME;
    }

    @Override
    public ExceptionInfo handle(Throwable throwable) {
        LOGGER.info("responseStatusExceptionHandler -> handle(Throwable throwable), throwable = {0}", throwable);
        return new ExceptionInfo(ResponseElement.get(((ResponseStatusException) throwable).getStatus().value()));
    }
}
