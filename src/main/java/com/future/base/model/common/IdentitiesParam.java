package com.future.base.model.common;

import java.io.Serializable;
import java.util.List;

/**
 * ids param
 *
 * @author liuyunfei
 */
@SuppressWarnings({"unused"})
public final class IdentitiesParam implements Serializable {

    private static final long serialVersionUID = -7710914687734474892L;

    /**
     * ids
     */
    private List<Long> ids;

    public IdentitiesParam() {
    }

    public IdentitiesParam(List<Long> ids) {
        this.ids = ids;
    }

    public List<Long> getIds() {
        return this.ids;
    }

    public void setIds(List<Long> ids) {
        this.ids = ids;
    }

    @Override
    public String toString() {
        return "IdentitiesParam{" +
                "ids=" + ids +
                '}';
    }

}
