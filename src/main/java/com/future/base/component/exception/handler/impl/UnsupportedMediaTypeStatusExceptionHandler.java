package com.future.base.component.exception.handler.impl;

import com.future.base.component.exception.handler.inter.ExceptionHandler;
import com.future.base.component.exception.model.ExceptionInfo;
import com.future.base.constant.common.Symbol;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.server.UnsupportedMediaTypeStatusException;

import static com.future.base.constant.common.ResponseElement.UNSUPPORTED_MEDIA_TYPE;
import static com.future.base.util.base.CommonFunctions.PATH_SEPARATOR;
import static java.util.Optional.ofNullable;

/**
 * un support exp handler
 *
 * @author liuyunfei
 */
@SuppressWarnings({"unused"})
public final class UnsupportedMediaTypeStatusExceptionHandler implements ExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(UnsupportedMediaTypeStatusExceptionHandler.class);

    private static final String EXP_NAME = "org.springframework.web.server.UnsupportedMediaTypeStatusException";

    @Override
    public String exceptionName() {
        return EXP_NAME;
    }

    @Override
    public ExceptionInfo handle(Throwable throwable) {
        LOGGER.info("unsupportedMediaTypeStatusExceptionHandler -> handle(Throwable throwable), throwable = {0}", throwable);
        return new ExceptionInfo(UNSUPPORTED_MEDIA_TYPE, new String[]{"not support media type -> " +
                ofNullable(((UnsupportedMediaTypeStatusException) throwable).getContentType())
                        .map(c -> c.getType() + PATH_SEPARATOR + c.getSubtype())
                        .orElse(Symbol.UNKNOWN.identity)});
    }
}
