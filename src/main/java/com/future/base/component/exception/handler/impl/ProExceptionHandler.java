package com.future.base.component.exception.handler.impl;

import com.future.base.component.exception.handler.inter.ExceptionHandler;
import com.future.base.component.exception.model.ExceptionInfo;
import com.future.base.model.exps.ProException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * pro exp handler
 *
 * @author liuyunfei
 */
@SuppressWarnings({"unused"})
public final class ProExceptionHandler implements ExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProExceptionHandler.class);

    private static final String EXP_NAME = "com.future.base.model.exps.ProException";

    @Override
    public String exceptionName() {
        return EXP_NAME;
    }

    @Override
    public ExceptionInfo handle(Throwable throwable) {
        LOGGER.info("proExceptionHandler -> handle(Throwable throwable), throwable = {0}", throwable);
        ProException ex = (ProException) throwable;

        return new ExceptionInfo(ex);
    }
}
