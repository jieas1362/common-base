package com.future.base.component.exception.model;

import com.future.base.constant.common.ResponseElement;
import com.future.base.model.exps.ProException;

import java.util.Arrays;

import static com.future.base.constant.common.ResponseElement.INTERNAL_SERVER_ERROR;
import static com.future.base.util.base.ProChecker.isNotNull;

/**
 * http status code and result
 *
 * @author liuyunfei
 */
public final class ExceptionInfo {

    private final Integer status;

    private final Integer code;

    private final String[] replacements;

    public ExceptionInfo() {
        this.status = INTERNAL_SERVER_ERROR.code;
        this.code = INTERNAL_SERVER_ERROR.code;
        this.replacements = null;
    }

    public ExceptionInfo(ProException proException) {
        if (isNotNull(proException)) {
            this.status = proException.getStatus();
            this.code = proException.getCode();
            this.replacements = proException.getReplacements();
        } else {
            this.status = INTERNAL_SERVER_ERROR.code;
            this.code = INTERNAL_SERVER_ERROR.code;
            this.replacements = null;
        }
    }

    public ExceptionInfo(ProException proException, String[] replacements) {
        if (isNotNull(proException)) {
            this.status = proException.getStatus();
            this.code = proException.getCode();
        } else {
            this.status = INTERNAL_SERVER_ERROR.code;
            this.code = INTERNAL_SERVER_ERROR.code;
        }
        this.replacements = replacements;
    }

    public ExceptionInfo(ResponseElement responseElement) {
        if (isNotNull(responseElement)) {
            this.status = responseElement.status;
            this.code = responseElement.code;
        } else {
            this.status = INTERNAL_SERVER_ERROR.code;
            this.code = INTERNAL_SERVER_ERROR.code;
        }

        this.replacements = null;
    }

    public ExceptionInfo(ResponseElement responseElement, String[] replacements) {
        if (isNotNull(responseElement)) {
            this.status = responseElement.status;
            this.code = responseElement.code;
        } else {
            this.status = INTERNAL_SERVER_ERROR.code;
            this.code = INTERNAL_SERVER_ERROR.code;
        }

        this.replacements = replacements;
    }

    public ExceptionInfo(Integer status, Integer code) {
        this.status = isNotNull(status) ? status : INTERNAL_SERVER_ERROR.code;
        this.code = isNotNull(code) ? code : INTERNAL_SERVER_ERROR.code;
        this.replacements = null;
    }

    public ExceptionInfo(Integer status, Integer code, String[] replacements) {
        this.status = isNotNull(status) ? status : INTERNAL_SERVER_ERROR.code;
        this.code = isNotNull(code) ? code : INTERNAL_SERVER_ERROR.code;
        this.replacements = replacements;
    }

    public Integer getStatus() {
        return status;
    }

    public Integer getCode() {
        return code;
    }

    public String[] getReplacements() {
        return replacements;
    }

    @Override
    public String toString() {
        return "ExceptionHandleInfo{" +
                "status=" + status +
                ", code=" + code +
                ", replacements=" + Arrays.toString(replacements) +
                '}';
    }

}
