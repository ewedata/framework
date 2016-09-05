package com.framework.core.init;

import java.util.Locale;

/**
 * load properties file
 * 
 * @author CHEN XIANXI
 * @since 2016年8月25日 下午10:44:52
 * @version 1.0
 */
public class DemoProperties extends BaseProperties {

    // TODO 将属性文件加载到内存中，即将加载后的资源文件放到该类的静态属性中


    private static final String PROP_FILE = "/config/demo.properties";

    public DemoProperties() {
        loads(PROP_FILE);
    }

    public Locale getLocale() {

        Locale locale;

        String setting = getProperty("locale");

        if (setting == "") {

            locale = Locale.getDefault();

        } else {
            try {
                locale = new Locale(setting);
            } catch (Exception e) {
                locale = Locale.getDefault();
            }
        }

        return locale;
    }

}
