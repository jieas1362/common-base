package com.future.base.component.message.ioc;

import com.future.base.component.message.api.conf.MessageConf;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.annotation.Order;
import org.springframework.lang.NonNull;

import static com.future.base.component.message.api.loader.MessageLoader.load;
import static org.springframework.core.Ordered.LOWEST_PRECEDENCE;

/**
 * messages loader configuration
 *
 * @author liuyunfei
 */
@Order(LOWEST_PRECEDENCE)
public class ProMessageLoadConfiguration implements ApplicationListener<ContextRefreshedEvent> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProMessageLoadConfiguration.class);

    @Override
    public void onApplicationEvent(@NonNull ContextRefreshedEvent contextRefreshedEvent) {
        ApplicationContext applicationContext = contextRefreshedEvent.getApplicationContext();

        MessageConf messageConf;
        try {
            messageConf = applicationContext.getBean(MessageConf.class);
            LOGGER.info("ProMessageLoadConfiguration onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent), messageConf = {}", messageConf);
        } catch (BeansException e) {
            LOGGER.error("applicationContext.getBean(MessageConf.class), e = {0}", e);
            throw new RuntimeException("applicationContext.getBean(MessageConf.class) failed");
        }

        load(messageConf);
    }

}
