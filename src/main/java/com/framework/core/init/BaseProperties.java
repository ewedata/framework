package com.framework.core.init;

import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 属性文件加载类
 * 
 * @author matrix
 *
 */
public class BaseProperties {

    private static final Logger LOG = LoggerFactory.getLogger(BaseProperties.class);

    private Properties props = null;

    /**
     * 加载文件
     * 
     * @param filename 文件名
     */
    public synchronized void loads(String filename) {
        if (props == null) {
            InputStream is = BaseProperties.class.getResourceAsStream(filename);
            props = new Properties();
            try {
                props.load(is);
            } catch (Exception e) {
                LOG.error("Cannot load the properties from " + filename + ".");
            }
        }
    }

    /**
     * 根据key获取值
     * 
     * @param key
     * @return 值
     */
    public String getProperty(String key) {
        String value;

        try {
            value = props.getProperty(key);
        } catch (Exception e) {
            value = "";
        }

        if (value == null || value.length() == 0) {
            value = "";
        }

        return value;
    }

    /**
     * 构造属性文件对象
     */
    public Properties getProps() {
        return props;
    }
}
