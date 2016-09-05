package com.framework.core.utils;

import java.util.Properties;

import org.junit.Test;

import com.framework.core.utils.PropertiesUtil;

public class PropertiesUtilTest {

    @Test
    public void testGetFileProperties() {
        String filename = "/config/demo.properties";
        Properties fileProperties = PropertiesUtil.getFileProperties(filename);
        System.out.println(fileProperties.getProperty("redis.ip"));
    }

}
