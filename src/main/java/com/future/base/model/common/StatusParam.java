package com.future.base.model.common;

import java.io.Serializable;

/**
 * status param
 *
 * @author liuyunfei
 */
@SuppressWarnings({"unused"})
public final class StatusParam implements Serializable {

    private static final long serialVersionUID = 3631693809321831634L;

    /**
     * id
     */
    private Integer status;

    public StatusParam() {
    }

    public StatusParam(Integer status) {
        this.status = status;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "StatusParam{" +
                "status=" + status +
                '}';
    }

}
