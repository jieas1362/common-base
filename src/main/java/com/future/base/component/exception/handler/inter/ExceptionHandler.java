package com.future.base.component.exception.handler.inter;

import com.future.base.component.exception.model.ExceptionInfo;

/**
 * exp handler interface
 *
 * @author liuyunfei
 */
@SuppressWarnings({"unused", "JavaDoc"})
public interface ExceptionHandler {

    /**
     * exp name
     *
     * @return
     */
    String exceptionName();

    /**
     * handle exp
     *
     * @param throwable
     * @return
     */
    ExceptionInfo handle(Throwable throwable);

}
