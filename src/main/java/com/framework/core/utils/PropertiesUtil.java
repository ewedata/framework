package com.framework.core.utils;

import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The class loads properties file.
 * 
 * @author matrix
 * @since 2016年8月17日 上午9:56:04
 */
public class PropertiesUtil {

    private static final Logger LOG = LoggerFactory.getLogger(PropertiesUtil.class);

    /**
     * Get Properties from special filename
     * 
     * @param filename
     * @return
     */
    public static Properties getFileProperties(String filename) {

        Properties prop = new Properties();

        InputStream is = PropertiesUtil.class.getResourceAsStream(filename);

        try {
            prop.load(is);
        } catch (Exception e) {
            LOG.error("load properties file error: " + filename);
            e.printStackTrace();
        }

        return prop;
    }

}
