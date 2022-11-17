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
public final class ExceptionResponse implements Serializable {

    private static final long serialVersionUID = -1568322065726586876L;

    /**
     * business code
     */
    private final Integer code;

    /**
     * response message
     */
    private final String message;

    public ExceptionResponse() {
        this.code = INTERNAL_SERVER_ERROR.code;
        this.message = INTERNAL_SERVER_ERROR.message;
    }

    public ExceptionResponse(Integer code, String message) {
        if (isNotNull(code) && isNotNull(message)) {
            this.code = code;
            this.message = message;
        } else {
            throw new RuntimeException("status or code or message can't be null");
        }
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "ExceptionResponse{" +
                ", code=" + code +
                ", message='" + message + '\'' +
                '}';
    }

}
