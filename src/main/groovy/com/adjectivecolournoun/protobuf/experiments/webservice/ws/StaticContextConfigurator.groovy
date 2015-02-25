package com.adjectivecolournoun.protobuf.experiments.webservice.ws

import org.springframework.beans.BeansException
import org.springframework.context.ApplicationContext
import org.springframework.context.ApplicationContextAware
import org.springframework.core.annotation.AnnotationUtils
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service
import org.springframework.util.ClassUtils
import org.springframework.util.ObjectUtils

import javax.websocket.server.ServerEndpointConfig
import java.util.concurrent.ConcurrentHashMap

@Service
class StaticContextConfigurator extends ServerEndpointConfig.Configurator implements ApplicationContextAware {

    private static ApplicationContext CONTEXT

    private static final String NO_VALUE = ObjectUtils.identityToString(new Object());

    private static final Map<String, Map<Class<?>, String>> cache =
            new ConcurrentHashMap<String, Map<Class<?>, String>>().withDefault { new ConcurrentHashMap<Class<?>, String>() }

    @Override
    def <T> T getEndpointInstance(Class<T> endpointClass) throws InstantiationException {
        String beanName = ClassUtils.getShortNameAsProperty(endpointClass);
        if (CONTEXT.containsBean(beanName)) {
            T endpoint = CONTEXT.getBean(beanName, endpointClass);
            return endpoint
        }

        Component annot = AnnotationUtils.findAnnotation(endpointClass, Component);
        if ((annot != null) && CONTEXT.containsBean(annot.value())) {
            T endpoint = CONTEXT.getBean(annot.value(), endpointClass);
            return endpoint
        }

        beanName = getBeanNameByType(endpointClass);
        if (beanName != null) {
            return (T) CONTEXT.getBean(beanName);
        }

        CONTEXT.autowireCapableBeanFactory.createBean(endpointClass)
    }

    private static String getBeanNameByType(Class<?> endpointClass) {
        String wacId = CONTEXT.id

        def beanNamesByType = cache[wacId]

        if (!beanNamesByType.containsKey(endpointClass)) {
            String[] names = CONTEXT.getBeanNamesForType(endpointClass)
            if (names.length == 1) {
                beanNamesByType[endpointClass] = names.first()
            } else {
                beanNamesByType[endpointClass] = NO_VALUE
                if (names.length > 1) {
                    throw new IllegalStateException("Found multiple @ServerEndpoint's of type [ $endpointClass.name ]: bean names $names")
                }
            }
        }

        String beanName = beanNamesByType[endpointClass]
        NO_VALUE == beanName ? null : beanName
    }

    @Override
    void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        CONTEXT = applicationContext
    }
}
