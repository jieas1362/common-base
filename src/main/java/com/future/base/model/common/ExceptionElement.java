package com.future.base.model.common;

import java.io.Serializable;

import static com.future.base.constant.common.ResponseElement.INTERNAL_SERVER_ERROR;
import static com.future.base.util.base.ProChecker.isNotNull;

/**
 * exp response result info
 *
 * @author liuyunfei
 */
@SuppressWarnings("unused")
public final class ExceptionElement implements Serializable {

    private static final long serialVersionUID = -1568322065726586876L;

    private final Integer status;

    /**
     * resp
     */
    private final ExceptionResponse response;

    public ExceptionElement() {
        this.status = INTERNAL_SERVER_ERROR.status;
        this.response = new ExceptionResponse(INTERNAL_SERVER_ERROR.code, INTERNAL_SERVER_ERROR.message);
    }

    public ExceptionElement(Integer status, Integer code, String message) {
        if (isNotNull(status) && isNotNull(code) && isNotNull(message)) {
            this.status = status;
            this.response = new ExceptionResponse(code, message);
        } else {
            throw new RuntimeException("status or code or message can't be null");
        }
    }

    public Integer getStatus() {
        return status;
    }

    public ExceptionResponse getResponse() {
        return response;
    }

    @Override
    public String toString() {
        return "ExceptionElement{" +
                "status=" + status +
                ", response=" + response +
                '}';
    }
}
