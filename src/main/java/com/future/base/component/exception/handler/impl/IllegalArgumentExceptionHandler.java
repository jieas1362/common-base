package com.future.base.component.exception.handler.impl;

import com.future.base.component.exception.handler.inter.ExceptionHandler;
import com.future.base.component.exception.model.ExceptionInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import static com.future.base.constant.common.ResponseElement.BAD_REQUEST;
import static java.util.Optional.ofNullable;

/**
 * ill arg exp handler
 *
 * @author liuyunfei
 */
@SuppressWarnings({"unused"})
public final class IllegalArgumentExceptionHandler implements ExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(IllegalArgumentExceptionHandler.class);

    private static final String EXP_NAME = "java.lang.IllegalArgumentException";

    @Override
    public String exceptionName() {
        return EXP_NAME;
    }

    @Override
    public ExceptionInfo handle(Throwable throwable) {
        LOGGER.info("illegalArgumentExceptionHandler -> handle(Throwable throwable), throwable = {0}", throwable);
        return new ExceptionInfo(BAD_REQUEST, new String[]{ofNullable(throwable.getMessage()).filter(StringUtils::hasText).orElse(BAD_REQUEST.message)});
    }

}
