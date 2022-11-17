package com.future.base.model.common;

import java.io.Serializable;
import java.util.List;

/**
 * keys param
 *
 * @author liuyunfei
 */
@SuppressWarnings({"unused"})
public final class ElementKeysParam implements Serializable {

    private static final long serialVersionUID = 2734920052156382753L;

    /**
     * keys
     */
    private List<String> keys;

    public ElementKeysParam() {
    }

    public ElementKeysParam(List<String> keys) {
        this.keys = keys;
    }

    public List<String> getKeys() {
        return keys;
    }

    public void setKeys(List<String> keys) {
        this.keys = keys;
    }

    @Override
    public String toString() {
        return "ElementKeysParam{" +
                "keys=" + keys +
                '}';
    }
}
