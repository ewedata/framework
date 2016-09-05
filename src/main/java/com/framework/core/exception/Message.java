package com.framework.core.exception;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.framework.core.init.DemoProperties;

/**
 * This class is for managing resource info.
 * 
 * @author CHEN XIANXI
 * @since 2016年8月25日 下午10:51:14
 * @version 1.0
 */
public class Message {

    private static final Logger LOG = LoggerFactory.getLogger(Message.class);

    private static final Locale locale = new DemoProperties().getLocale();

    /* 资源配置文件放置在src/main/resources/locale/messages_zh_CN.properties */
    private static final String DEAULT_RESOURCE_FILE = "locale.Message";

    private static Map<String, Map<String, Object>> bundleLocaleCache =
            new HashMap<String, Map<String, Object>>();

    /**
     * 根据资源代码获取资源值信息
     * 
     * @param code
     * @return value
     */
    public static String getMessage(String code) {
        return getMessage(DEAULT_RESOURCE_FILE, code, locale);
    }

    /**
     * 根据locale，信息资源文件取得信息
     * 
     * @param resource 信息资源文件，如: exampleMessage
     * @param key 信息key
     * @param locale 如：zh_CN等对应的Locale
     * @return 信息值
     */
    public static String getMessage(String resource, String key, Locale locale) {
        if (resource == null || resource.length() <= 0) return "";
        if (key == null || key.length() <= 0) return "";

        ResourceBundle bundle = getResourceBundle(resource, locale);
        if (bundle == null) return "";

        String value = null;
        try {
            value = bundle.getString(key);
        } catch (Exception e) {}
        return value == null ? "" : value.trim();
    }

    /**
     * 取得<tt>ResourceBundle</tt>
     */
    public static ResourceBundle getResourceBundle(String resource, Locale locale) {
        Map<String, Object> bundleMap = getResourceBundleMap(resource, locale);
        if (bundleMap == null) {
            return null;
        }
        return (ResourceBundle) bundleMap.get("_RESOURCE_BUNDLE_");
    }

    /**
     * 返回以Map形式保存的resources
     */
    public static Map<String, Object> getResourceBundleMap(String resource, Locale locale) {
        if (locale == null) {
            throw new IllegalArgumentException("Locale cannot be null");
        }

        String resourceCacheKey = resource + "_" + locale.toString();
        Map<String, Object> bundleMap = bundleLocaleCache.get(resourceCacheKey);

        if (bundleMap == null) {
            ResourceBundle bundle = getBaseResourceBundle(resource, locale);
            bundleMap = resourceBundleToMap(bundle);
            if (bundleMap != null) {
                bundleLocaleCache.put(resourceCacheKey, bundleMap);
            }
        }
        return bundleMap;
    }

    protected static ResourceBundle getBaseResourceBundle(String resource, Locale locale) {
        if (resource == null || resource.length() <= 0) return null;
        if (locale == null) locale = Locale.getDefault();

        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        ResourceBundle bundle = null;
        try {
            bundle = ResourceBundle.getBundle(resource, locale, loader);
        } catch (MissingResourceException e) {
            LOG.error("Could not find resource: " + resource + " for locale " + locale.toString()
                    + ": " + e.toString());
            return null;
        }
        if (bundle == null) {
            LOG.error("Could not find resource: " + resource + " for locale " + locale.toString());
            return null;
        }

        return bundle;
    }

    protected static Map<String, Object> resourceBundleToMap(ResourceBundle bundle) {
        if (bundle == null) {
            return new HashMap<String, Object>();
        }
        Enumeration<String> keyNum = bundle.getKeys();
        Map<String, Object> resourceBundleMap = new HashMap<String, Object>();
        while (keyNum.hasMoreElements()) {
            String key = (String) keyNum.nextElement();
            Object value = bundle.getObject(key);
            resourceBundleMap.put(key, value);
        }
        resourceBundleMap.put("_RESOURCE_BUNDLE_", bundle);
        return resourceBundleMap;
    }


}
