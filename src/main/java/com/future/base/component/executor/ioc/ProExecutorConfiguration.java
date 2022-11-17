package com.future.base.component.executor.ioc;

import com.future.base.component.executor.api.conf.ExecutorConf;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;

import java.util.concurrent.ExecutorService;

import static com.future.base.component.executor.api.generator.ProExecutorGenerator.generateExecutorService;

/**
 * global executor configuration
 *
 * @author liuyunfei
 */
@ConditionalOnBean(value = {ExecutorConf.class})
@AutoConfiguration
public class ProExecutorConfiguration {

    @Bean
    ExecutorService executorService(ExecutorConf executorConf) {
        return generateExecutorService(executorConf);
    }

}
