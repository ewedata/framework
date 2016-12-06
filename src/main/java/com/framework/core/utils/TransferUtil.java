package com.framework.core.utils;

import static org.apache.commons.beanutils.BeanUtils.setProperty;
import static org.apache.commons.lang.StringEscapeUtils.escapeSql;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.util.Assert;

import com.framework.core.annotatioin.EntryPK;
import com.framework.core.constant.Const;
import com.framework.entry.BaseEntity;
import com.framework.entry.SimpleEntry;

/**
 * this class is a tool for transferring anything.
 * 
 * @author matrix
 * @since 2016年8月17日 下午5:47:59
 */
public class TransferUtil {

    private static final Map<String, String> CAMEL_CACHE = new HashMap<String, String>();
    private static final Map<String, String> DBFORMAT_CACHE = new HashMap<String, String>();

    private TransferUtil() {}

    /**
     * 将驼峰风格字符串转化为数据库风格
     * <p>
     * Examples: <blockquote><pre>
     * userName returns USER_NAME
     * </pre></blockquote>
     * 
     * @param camelFormat 驼峰风格的字符串
     * @return 数据库风格
     */
    private static String assembleDbFormat(final String camelFormat) {

        final StringBuffer dbFormtBuffer = new StringBuffer();

        for (int i = 0; i < camelFormat.length(); i++) {
            final char c = camelFormat.charAt(i);
            if (i != 0) {
                if ((c >= 'A') && (c <= 'Z')) {
                    dbFormtBuffer.append(Const.UNDER_LINE);
                }
            }
            dbFormtBuffer.append(c);
        }

        return dbFormtBuffer.toString().toUpperCase();
    }

    /**
     * 将数据库风格转化为驼峰风格字符串
     * <p>
     * Examples: <blockquote><pre>
     * USER_NAME returns userName  
     * </pre></blockquote>
     * 
     * @param dbFormat 数据库风格
     * @return 驼峰风格的字符串
     */
    private static String assembleCamelFormat(final String dbFormat) {
        final StringBuffer camelFormatBuffer = new StringBuffer();
        for (final String word : dbFormat.split(Const.UNDER_LINE)) {
            if (word.length() == 0) {
                continue;
            }
            final String lowerWord = word.toLowerCase();
            if (camelFormatBuffer.length() == 0) {
                camelFormatBuffer.append(lowerWord);
            } else {
                camelFormatBuffer.append(lowerWord.substring(0, 1).toUpperCase());
                camelFormatBuffer.append(lowerWord.substring(1));
            }
        }
        return camelFormatBuffer.toString();
    }

    /**
     * 将字符串从数据库形式转换到驼峰形式
     * 
     * @param dbFormat 驼峰字符串
     * @return 对应的数据库字符串
     */
    public static String transferToCamelFormat(final String dbFormat) {
        Assert.notNull(dbFormat, "不能处理空字符串");

        final String result = CAMEL_CACHE.get(dbFormat);
        if (result != null) {
            return result;
        }

        final String afterTransfer = assembleCamelFormat(dbFormat);

        if (afterTransfer != null) {
            CAMEL_CACHE.put(dbFormat, afterTransfer);
        }
        return afterTransfer;
    }

    /**
     * 将驼峰风格字符串转化为数据库风格
     * 
     * @param camelFormat 驼峰风格的字符串
     * @return 数据库风格
     */
    public static String transferToDbFormat(final String camelFormat) {
        Assert.notNull(camelFormat, "不能处理空字符串");

        final String result = DBFORMAT_CACHE.get(camelFormat);
        if (result != null) {
            return result;
        }

        final String afterTransfer = assembleDbFormat(camelFormat);

        if (afterTransfer != null) {
            DBFORMAT_CACHE.put(camelFormat, afterTransfer);
        }
        return afterTransfer;
    }

    /**
     * get the primary key column from entry class
     * 
     * @param classInfo
     * @return
     */
    public static String extractPkName(final Class<? extends BaseEntity> classInfo) {

        Field[] fields = classInfo.getFields();

        for (Field field : fields) {
            if (field.isAnnotationPresent(EntryPK.class)) {
                return transferToDbFormat(field.getName());
            }
        }
        Assert.isTrue(false, "无法提取主键名");
        return null;
    }

    /**
     * change object to String.
     * 
     * @param value
     * @return String
     */
    private static String extractValueString(final Object value) {
        String valueString = null;
        // 由于继承结构，timestamp和time必须优先于date处理
        if ((value instanceof Timestamp) || (value instanceof Time)) {
            valueString = DateUtil.sdf_datetime_format.format(value);
        } else if ((value instanceof Date) || (value instanceof java.sql.Date)) {
            valueString = DateUtil.sdf_date_format.format(value);
        } else {
            valueString = String.valueOf(value);
        }
        return valueString;
    }

    /**
     * change object to db column value. <pre>
     * abc --> 'abc'
     * </pre>
     * 
     * @param value 需要转换的对象
     * @return 该对象对应的列值
     */
    public static String assembleColumnValue(final Object value) {
        if (value == null) {
            return null;
        } else if (NumericUtil.isNumeric(value.getClass())) {
            return value.toString();
        } else {
            final StringBuffer formatValue = new StringBuffer();
            formatValue.append('\'');
            final String sqlValue = escapeSql(extractValueString(value));
            formatValue.append(sqlValue);
            formatValue.append('\'');
            return formatValue.toString();
        }
    }

    /**
     * 提取属性值
     * 
     * @param entry 数据实体
     * @param field 属性
     * @return 该实体内该属性的值
     */
    private static <T extends BaseEntity> String extractColumnValue(final T entry,
            final Field field) {
        Object value = null;
        try {
            value = field.get(entry);
        } catch (final Exception e) {
            Assert.isTrue(false, "无法获取实体" + entry.getClass().getName() + "属性" + field.getName());
        }
        return assembleColumnValue(value);
    }

    /**
     * 提取列值
     * 
     * 
     * @param entry 数据实体
     * @param includePk 是否包含主键
     * @return 列值
     */
    public static List<Entry<String, String>> extractColumnValue(final BaseEntity entry,
            final boolean includePk) {
        final List<Entry<String, String>> entryList = new ArrayList<Map.Entry<String, String>>();

        for (final Field field : entry.getClass().getDeclaredFields()) {
            if (!includePk && field.isAnnotationPresent(EntryPK.class)) {
                continue;
            }

            final boolean isAccessible = field.isAccessible();
            field.setAccessible(true);

            final String fieldName = field.getName();
            Assert.hasLength(fieldName, "field must not null!");

            final String columnName = transferToDbFormat(fieldName);
            final String columnValue = extractColumnValue(entry, field);

            entryList.add(new SimpleEntry<String, String>(columnName, columnValue));

            field.setAccessible(isAccessible);
        }

        return entryList;
    }

    /**
     * @param <T> 实体类型
     * @param resultMap 用来提取数据的映射
     * @param entryClassInfo 实体信息
     * @return 组装好的数据实体
     */
    public <T extends BaseEntity> T transferToEntry(final Map<String, Object> resultMap,
            final Class<T> entryClassInfo) {
        T result;
        try {
            result = entryClassInfo.newInstance();
        } catch (final InstantiationException ex) {
            throw new RuntimeException("Cannot instant " + entryClassInfo.getName());
        } catch (final IllegalAccessException ex) {
            throw new RuntimeException("Cannot instant " + entryClassInfo.getName()
                    + "maybe the constructor is private");
        }

        for (final Entry<String, Object> entry : resultMap.entrySet()) {
            final String name = transferToCamelFormat(entry.getKey());
            try {
                setProperty(result, name, entry.getValue());
            } catch (final IllegalAccessException ex) {
                throw new RuntimeException(
                        "illegal access " + entryClassInfo.getSimpleName() + " property:" + name);
            } catch (final InvocationTargetException ex) {
                throw new RuntimeException("invoke " + entryClassInfo.getSimpleName() + " property:"
                        + name + "set method error");
            } catch (final IllegalArgumentException ex) {
                ex.printStackTrace();
                throw new RuntimeException("invoke " + entryClassInfo.getSimpleName() + " property:"
                        + name + "set method error" + ex.getMessage());
            }
        }
        return result;
    }


    /**
     * Get table name from entry class
     * 
     * @param classInfo entry class
     * @return table name
     */
    public static String extractTableName(final Class<? extends BaseEntity> classInfo) {
        String simpleName = classInfo.getSimpleName();
        final String className = simpleName.endsWith(Const.ENTRY)
                ? simpleName.replaceAll(Const.ENTRY, "")
                : simpleName;
        return transferToDbFormat(className);
    }

}
