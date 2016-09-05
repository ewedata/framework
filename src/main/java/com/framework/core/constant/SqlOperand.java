/**
 * 
 */
package com.framework.core.constant;

/**
 * @author matrix
 * @since 2016年8月19日 下午5:02:00
 */
public enum SqlOperand {
    /**
     * 
     */
    EQUALS {

        @Override
        public String toString() {
            return "=";
        }

    },
    /**
     * 
     */
    IN,
    /**
     * 
     */
    IS;
}
