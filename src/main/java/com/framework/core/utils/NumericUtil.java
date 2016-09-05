package com.framework.core.utils;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class is for checking Numeric type
 * 
 * @author matrix
 * @since 2016年8月18日 上午10:24:34
 */
public class NumericUtil {

    private static final Set<Class<?>> NUMERIC_CLASS = new HashSet<Class<?>>();

    static {
        NUMERIC_CLASS.add(Integer.class);
        NUMERIC_CLASS.add(Short.class);
        NUMERIC_CLASS.add(Long.class);
        NUMERIC_CLASS.add(Float.class);
        NUMERIC_CLASS.add(Double.class);
        NUMERIC_CLASS.add(Byte.class);
        NUMERIC_CLASS.add(BigDecimal.class);

        NUMERIC_CLASS.add(Integer.TYPE);
        NUMERIC_CLASS.add(Short.TYPE);
        NUMERIC_CLASS.add(Long.TYPE);
        NUMERIC_CLASS.add(Float.TYPE);
        NUMERIC_CLASS.add(Double.TYPE);
        NUMERIC_CLASS.add(Byte.TYPE);
    }

    /**
     * Check the object, return true if it is a number
     * 
     * @param clazz type
     * @return {@code true} if the clazz is numeric type,else return {@code false}
     */
    public static boolean isNumeric(final Class<?> clazz) {
        return NUMERIC_CLASS.contains(clazz);
    }

    /**
     * whether a string is a numeric
     * 
     * @param str
     * @return <code>true</code> is a numeric
     */
    public static boolean isNumeric(final String str) {
        Pattern p = Pattern.compile("^[-+]?\\d+(\\.\\d+)?$");
        Matcher m = p.matcher(str);

        if (m.matches())
            return true;
        else
            return false;
    }

}
