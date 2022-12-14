package com.future.base.constant.common;

/**
 * sort type
 *
 * @author liuyunfei
 */
public enum SortType {

    /**
     * DESC
     */
    DESC("DESC"),
    /**
     * ASC
     */
    ASC("ASC");

    /**
     * type identity
     */
    public final String identity;

    SortType(String identity) {
        this.identity = identity;
    }

}
