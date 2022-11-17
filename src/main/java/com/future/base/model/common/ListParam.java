package com.future.base.model.common;

import java.io.Serializable;
import java.util.List;

/**
 * list data param
 *
 * @author liuyunfei
 */
@SuppressWarnings({"unused"})
public final class ListParam<T> implements Serializable {

    private static final long serialVersionUID = -1390300952756018262L;

    private List<T> data;

    public ListParam() {
    }

    public ListParam(List<T> data) {
        this.data = data;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ListParam{" +
                "data=" + data +
                '}';
    }

}
