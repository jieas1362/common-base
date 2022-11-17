package com.future.base.model.common;

import com.future.base.inter.Asserter;
import com.future.base.model.exps.ProException;
import com.future.base.util.base.ProChecker;

import java.io.Serializable;

import static com.future.base.constant.common.ProCommonThreshold.*;
import static com.future.base.constant.common.ResponseElement.*;
import static com.future.base.util.base.ProChecker.isNull;
import static java.util.Optional.ofNullable;

/**
 * page model request params
 *
 * @author liuyunfei
 */
@SuppressWarnings({"unused", "AliControlFlowStatementWithoutBraces"})
public final class PageModelRequest<T> implements Asserter, Serializable {

    private static final long serialVersionUID = -59225648928098772L;

    private static final long DEFAULT_PAGE = PAGE.value, DEFAULT_ROWS = ROWS.value;
    private static final long MAX_ROWS_PER_REQ = MAX_ROWS.value;

    /**
     * current page
     */
    private Long page;

    /**
     * nums per page
     */
    private Long rows;

    /**
     * differentiated parameter package
     */
    private T condition;

    public PageModelRequest() {
    }

    public PageModelRequest(Long page, Long rows, T condition) {
        if (isNull(page) || page < 1L)
            throw new ProException(BAD_REQUEST.status, BAD_REQUEST.code, "page can't be less than 1, max rows per page can't be greater than " + MAX_ROWS.value);
        if (isNull(rows) || rows < 1L || rows > MAX_ROWS_PER_REQ)
            throw new ProException(BAD_REQUEST.status, BAD_REQUEST.code, "page can't be less than 1, max rows per page can't be greater than " + MAX_ROWS.value);

        this.page = page;
        this.rows = rows;
        this.condition = condition;
    }

    public Long getPage() {
        return ofNullable(page).orElse(DEFAULT_PAGE);
    }

    public Long getRows() {
        return ofNullable(rows).orElse(DEFAULT_ROWS);
    }

    public Long getLimit() {
        return (getPage() - 1L) * getRows();
    }

    public void setPage(Long page) {
        if (isNull(page) || page < 1L)
            throw new ProException(BAD_REQUEST.status, BAD_REQUEST.code, "page can't be less than 1, max rows per page can't be greater than " + MAX_ROWS.value);

        this.page = page;
    }

    public void setRows(Long rows) {
        if (isNull(rows) || rows < 1L || rows > MAX_ROWS_PER_REQ)
            throw new ProException(BAD_REQUEST.status, BAD_REQUEST.code, "page can't be less than 1, max rows per page can't be greater than " + MAX_ROWS.value);

        this.rows = rows;
    }

    public T getCondition() {
        return condition;
    }

    public void setCondition(T condition) {
        this.condition = condition;
    }

    @Override
    public void asserts() {
        if (!ProChecker.isGreaterThanZero(page))
            throw new ProException(INVALID_PARAM);
        if (rows < 0L)
            throw new ProException(INVALID_PARAM);
        if (rows > (int) MAX_SERVICE_SELECT.value)
            throw new ProException(PAYLOAD_TOO_LARGE);
    }

    @Override
    public String toString() {
        return "PageModelDTO{" +
                "page=" + page +
                ", rows=" + rows +
                ", param=" + condition +
                '}';
    }

}
