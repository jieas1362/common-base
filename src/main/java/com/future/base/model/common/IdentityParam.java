package com.future.base.model.common;

import java.io.Serializable;

/**
 * id param
 *
 * @author liuyunfei
 */
@SuppressWarnings({"unused"})
public final class IdentityParam implements Serializable {

    private static final long serialVersionUID = 3631693809321831634L;

    /**
     * id
     */
    private Long id;

    public IdentityParam() {
    }

    public IdentityParam(Long id) {
        this.id = id;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "IdentityParam{" +
                "id=" + id +
                '}';
    }

}
