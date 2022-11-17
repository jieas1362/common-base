package com.future.base.component.exception.handler.impl;

import com.future.base.component.exception.handler.inter.ExceptionHandler;
import com.future.base.component.exception.model.ExceptionInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import static com.future.base.constant.common.ResponseElement.BAD_REQUEST;
import static java.util.Optional.of;
import static org.springframework.util.CollectionUtils.isEmpty;

/**
 * invalid arg exp handler
 *
 * @author liuyunfei
 */
@SuppressWarnings({"unused"})
public final class MethodArgumentNotValidExceptionHandler implements ExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(MethodArgumentNotValidExceptionHandler.class);

    private static final String EXP_NAME = "org.springframework.web.bind.MethodArgumentNotValidException";

    @Override
    public String exceptionName() {
        return EXP_NAME;
    }

    @Override
    public ExceptionInfo handle(Throwable throwable) {
        LOGGER.info("methodArgumentNotValidExceptionHandler -> handle(Throwable throwable), throwable = {0}", throwable);
        MethodArgumentNotValidException ex = (MethodArgumentNotValidException) throwable;
        return new ExceptionInfo(BAD_REQUEST, new String[]{of(ex.getBindingResult())
                .map(BindingResult::getAllErrors)
                .filter(l -> !isEmpty(l))
                .map(l -> l.get(0))
                .map(ObjectError::getDefaultMessage)
                .orElse(BAD_REQUEST.message)});
    }
}
