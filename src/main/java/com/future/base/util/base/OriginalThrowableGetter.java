package com.future.base.util.base;

import static com.future.base.util.base.ProChecker.isNotNull;
import static com.future.base.util.base.ProChecker.isNull;

/**
 * original throwable getter
 *
 * @author liuyunfei
 */
@SuppressWarnings({"AliControlFlowStatementWithoutBraces", "JavaDoc"})
public final class OriginalThrowableGetter {

    /**
     * get original throwable
     *
     * @param throwable
     * @return
     */
    public static Throwable getOriginalThrowable(Throwable throwable) {
        if (isNull(throwable))
            throw new RuntimeException("throwable can't be null");

        Throwable original = throwable;
        Throwable cause;
        while (isNotNull(cause = original.getCause()))
            original = cause;

        return original;
    }

}
