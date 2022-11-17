package com.future.base.component.common;

import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ResourceLoader;
import org.springframework.lang.NonNull;

import java.util.Set;

/**
 * Scanner for customized configuration
 *
 * @author liuyunfei
 */
@SuppressWarnings("unused")
public final class ProBeanDefinitionScanner extends ClassPathBeanDefinitionScanner {

    public ProBeanDefinitionScanner(BeanDefinitionRegistry registry) {
        super(registry);
    }

    public ProBeanDefinitionScanner(BeanDefinitionRegistry registry, boolean useDefaultFilters) {
        super(registry, useDefaultFilters);
    }

    public ProBeanDefinitionScanner(BeanDefinitionRegistry registry, boolean useDefaultFilters, Environment environment) {
        super(registry, useDefaultFilters, environment);
    }

    public ProBeanDefinitionScanner(BeanDefinitionRegistry registry, boolean useDefaultFilters, Environment environment, ResourceLoader resourceLoader) {
        super(registry, useDefaultFilters, environment, resourceLoader);
    }

    @Override
    @NonNull
    public Set<BeanDefinitionHolder> doScan(@NonNull String... basePackages) {
        return super.doScan(basePackages);
    }

}
