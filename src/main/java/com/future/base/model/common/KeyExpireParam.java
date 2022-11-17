package com.future.base.model.common;

import com.future.base.model.exps.ProException;

import java.io.Serializable;
import java.time.temporal.ChronoUnit;

import static com.future.base.constant.common.ResponseElement.BAD_REQUEST;
import static com.future.base.util.base.ProChecker.*;

/**
 * redis key expire info
 *
 * @author liuyunfei
 */
@SuppressWarnings({"AliControlFlowStatementWithoutBraces", "unused"})
public final class KeyExpireParam implements Serializable {

    private static final long serialVersionUID = 713277204676056312L;

    /**
     * redis key
     */
    private String key;

    /**
     * expire time
     */
    private Long expire;

    /**
     * time unit
     */
    private ChronoUnit unit;

    public KeyExpireParam() {
    }

    public KeyExpireParam(String key, Long expire, ChronoUnit unit) {
        if (isBlank(key))
            throw new ProException(BAD_REQUEST);
        if (isInvalidIdentity(expire))
            throw new ProException(BAD_REQUEST);
        if (isNull(unit))
            throw new ProException(BAD_REQUEST);

        this.key = key;
        this.expire = expire;
        this.unit = unit;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        if (isBlank(key))
            throw new ProException(BAD_REQUEST);
        this.key = key;
    }

    public Long getExpire() {
        return expire;
    }

    public void setExpire(Long expire) {
        if (isInvalidIdentity(expire))
            throw new ProException(BAD_REQUEST);
        this.expire = expire;
    }

    public ChronoUnit getUnit() {
        return unit;
    }

    public void setUnit(ChronoUnit unit) {
        if (isNull(unit))
            throw new ProException(BAD_REQUEST);
        this.unit = unit;
    }

    @Override
    public String toString() {
        return "KeyExpireParam{" +
                "key='" + key + '\'' +
                ", expire=" + expire +
                ", unit=" + unit +
                '}';
    }
    
}
