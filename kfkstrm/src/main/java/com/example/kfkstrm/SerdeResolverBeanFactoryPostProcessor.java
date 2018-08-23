/*
 * Copyright (c) 2018 Tideworks Technology, Inc.
 */

package com.example.kfkstrm;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

/**
 * {@link BeanFactoryPostProcessor} instance that substitutes "keyValueSerdeResolver" bean.
 *
 * @author andrii.lukhanin (alukhani)
 * @since 0.12
 */
public class SerdeResolverBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

    @Override
    public void postProcessBeanFactory(final ConfigurableListableBeanFactory beanFactory) throws BeansException {
        beanFactory.registerAlias("genericKeyValueSerdeResolver", "keyValueSerdeResolver");
    }
}
