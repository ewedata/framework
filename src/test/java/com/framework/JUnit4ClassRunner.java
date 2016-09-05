package com.framework;

import java.io.FileNotFoundException;

import org.junit.runners.model.InitializationError;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Log4jConfigurer;

/**
 * @author matrix
 * @since 2016年8月17日 下午1:59:19
 */
public class JUnit4ClassRunner extends SpringJUnit4ClassRunner {

    static {
        try {
            Log4jConfigurer.initLogging("classpath:config/log4j.properties");
        } catch (FileNotFoundException ex) {
            System.err.println("Cannot Initialize log4j, please check file log4j.properties.");
        }
    }

    public JUnit4ClassRunner(Class<?> clazz) throws InitializationError {
        super(clazz);
    }

}
