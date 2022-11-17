package com.future.base.util.base;

import com.future.base.constant.common.Symbol;
import com.future.base.model.exps.ProException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cglib.beans.BeanCopier;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static com.future.base.constant.common.ResponseElement.BAD_REQUEST;
import static com.future.base.util.base.ProChecker.isNull;

/**
 * bean copier base on cglib
 *
 * @author liuyunfei
 */
@SuppressWarnings({"JavaDoc", "rawtypes", "AliControlFlowStatementWithoutBraces", "unused"})
public final class ProBeanCopier {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProBeanCopier.class);

    private static final String PAR_CONCATENATION = Symbol.PAR_CONCATENATION.identity;

    private static final Map<String, BeanCopier> COPIERS_HOLDER = new ConcurrentHashMap<>();

    /**
     * generate key
     *
     * @param source
     * @param target
     * @param useConverter
     * @return
     */
    private static String generateKey(Class source, Class target, boolean useConverter) {
        if (isNull(source) || isNull(target))
            throw new ProException(BAD_REQUEST);

        return source.getName() + PAR_CONCATENATION + target.getName() + PAR_CONCATENATION + useConverter;
    }

    /**
     * create copier
     *
     * @param source
     * @param target
     * @param useConverter
     * @return
     */
    public static BeanCopier create(Class source, Class target, boolean useConverter) {
        String key = generateKey(source, target, useConverter);

        BeanCopier beanCopier = COPIERS_HOLDER.get(key);
        if (isNull(beanCopier))
            synchronized (ProBeanCopier.class) {
                beanCopier = COPIERS_HOLDER.get(key);
                if (isNull(beanCopier)) {
                    BeanCopier copier = BeanCopier.create(source, target, useConverter);
                    COPIERS_HOLDER.put(key, copier);
                    beanCopier = copier;
                    LOGGER.info("BeanCopier created, key = {}", key);
                }
            }

        return beanCopier;
    }

}
