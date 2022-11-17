package com.future.base.model.exps;

import com.future.base.constant.common.ResponseElement;

import java.util.Arrays;

import static com.future.base.constant.common.ResponseElement.INTERNAL_SERVER_ERROR;
import static com.future.base.util.base.ProChecker.isNull;

/**
 * global business exception
 *
 * @author liuyunfei
 */
@SuppressWarnings("unused")
public final class ProException extends RuntimeException {

    private static final long serialVersionUID = -5482951655656407756L;

    /**
     * exp for can't process
     */
    private static final ResponseElement ELEMENT = INTERNAL_SERVER_ERROR;

    /**
     * http status
     */
    private Integer status;

    /**
     * business code
     */
    private Integer code;

    /**
     * message
     */
    private String message;

    /**
     * replacements
     */
    private String[] replacements;

    public ProException() {
        this.status = INTERNAL_SERVER_ERROR.status;
        this.code = INTERNAL_SERVER_ERROR.code;
        this.message = INTERNAL_SERVER_ERROR.message;
        this.replacements = null;
    }

    public ProException(ResponseElement responseElement) {
        this.status = responseElement.status;
        this.code = responseElement.code;
        this.message = responseElement.message;
        this.replacements = null;
    }

    public ProException(ResponseElement responseElement, String[] replacements) {
        this.status = responseElement.status;
        this.code = responseElement.code;
        this.message = responseElement.message;
        this.replacements = replacements;
    }


    public ProException(Integer status) {
        this.status = status;
        this.code = status;
        this.message = null;
        this.replacements = null;
    }

    public ProException(Integer status, Integer code) {
        this.status = status;
        this.code = code;
        this.message = null;
        this.replacements = null;
    }

    public ProException(Integer status, Integer code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
        this.replacements = null;
    }

    public ProException(Integer status, Integer code, String message, String[] replacements) {
        this.status = status;
        this.code = code;
        this.message = message;
        this.replacements = replacements;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String[] getReplacements() {
        return replacements;
    }

    public void setReplacements(String[] replacements) {
        this.replacements = replacements;
    }

    @Override
    public String toString() {
        return "ProException{" +
                "status=" + status +
                ", code=" + code +
                ", message='" + message + '\'' +
                (isNull(replacements) ? "" : ", replacements=" + Arrays.toString(replacements)) +
                '}';
    }
}
