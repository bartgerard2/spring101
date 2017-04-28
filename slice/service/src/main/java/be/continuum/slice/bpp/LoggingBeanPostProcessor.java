package be.continuum.slice.bpp;

import lombok.extern.java.Log;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * LoggingBeanPostProcessor
 *
 * @author bartgerard
 * @version v0.0.1
 */
@Component
@Log
public class LoggingBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(
            Object bean,
            String beanName
    ) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(
            Object bean,
            String beanName
    ) throws BeansException {
        // Log the names and types of all non inner-beans created
        if (!beanName.contains("inner bean")) {
            log.info("NEW " + bean.getClass()
                                  .getSimpleName() + " -> " + beanName);
        }
        
        return bean;
    }

}
