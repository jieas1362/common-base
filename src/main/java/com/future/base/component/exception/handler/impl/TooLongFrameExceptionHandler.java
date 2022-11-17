package com.future.base.component.exception.handler.impl;

import com.future.base.component.exception.handler.inter.ExceptionHandler;
import com.future.base.component.exception.model.ExceptionInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.future.base.constant.common.ResponseElement.PAYLOAD_TOO_LARGE;

/**
 * frame exp handler for netty
 *
 * @author liuyunfei
 */
@SuppressWarnings({"unused"})
public final class TooLongFrameExceptionHandler implements ExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(TooLongFrameExceptionHandler.class);

    private static final String EXP_NAME = "io.netty.handler.codec.TooLongFrameException";

    private static final ExceptionInfo EXP_HANDLE_INFO = new ExceptionInfo(PAYLOAD_TOO_LARGE);

    @Override
    public String exceptionName() {
        return EXP_NAME;
    }

    @Override
    public ExceptionInfo handle(Throwable throwable) {
        LOGGER.info("tooLongFrameExceptionHandler -> handle(Throwable throwable), throwable = {0}", throwable);
        return EXP_HANDLE_INFO;
    }

}
