package com.future.base.constant.common;

/**
 * element/data status
 *
 * @author liuyunfei
 */
@SuppressWarnings("unused")
public enum Deleted {

    /**
     * NO_DELETED
     */
    NO_DELETED(0, "no_delete"),
    /**
     * DELETED
     */
    DELETED(1, "delete");


    /**
     * status
     */
    public final int status;

    /**
     * disc
     */
    public final String disc;

    Deleted(int status, String disc) {
        this.status = status;
        this.disc = disc;
    }

}
