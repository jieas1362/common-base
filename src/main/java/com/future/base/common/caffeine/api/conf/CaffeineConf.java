package com.future.base.common.caffeine.api.conf;

import com.future.base.common.caffeine.constant.ExpireStrategy;

import java.time.Duration;
import java.util.concurrent.ExecutorService;

/**
 * caffeine conf
 *
 * @author liuyunfei
 */
@SuppressWarnings("JavaDoc")
public interface CaffeineConf {

    /**
     * max size
     *
     * @return
     */
    Integer getMaximumSize();

    /**
     * expire
     *
     * @return
     */
    Duration getExpireDuration();

    /**
     * expire strategy
     *
     * @return
     */
    ExpireStrategy getExpireStrategy();

    /**
     * executor pool
     *
     * @return
     */
    ExecutorService getExecutorService();

}
