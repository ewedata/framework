package com.framework.core.constant;

/**
 * This Class contain the constant variable.
 * 
 * @author matrix
 *
 */
public class Const {

    /**
     * operating systems specific Carriage Return
     */
    public static final String CR = System.getProperty("line.separator");

    /**
     * vertical line
     */
    public static final String VERTICAL_LINE = "\\|";

    /**
     * under line
     */
    public static final String UNDER_LINE = "_";

    /**
     * the class end up with entry.
     */
    public static final String ENTRY = "Entry";

    /**
     * email pattern
     */
    public static final String EMAIL_PATTERN = "^(\\w)+(\\.\\w+)*@(\\w)+((\\.\\w+)+)$";

    /**
     * mobile patter
     */
    public static final String MOBILE_PATTERN = "^(1[3|4|5|7|8])[0-9]{9}$";

}
