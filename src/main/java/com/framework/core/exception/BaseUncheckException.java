package com.framework.core.exception;

/**
 * base unchecked exception
 * 
 * @author CHEN XIANXI
 * @since 2016年8月25日 下午10:36:42
 * @version 1.0
 */
public class BaseUncheckException extends RuntimeException {

    private static final long serialVersionUID = -2121599154084035677L;

    /**
     * 错误代码，用于唯一标识错误类型。 必须在common/Message.properties中定义。
     */
    private String code;

    /**
     * 配置文件中，错误代码所表示的错误信息
     */
    private String msg;

    /**
     * 传递给变量的错误值
     */
    private Object[] values;

    public BaseUncheckException() {
        super();
    }

    public BaseUncheckException(String code) {
        super();
        this.code = code;

    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object[] getValues() {
        return values;
    }

    public void setValues(Object[] values) {
        this.values = values;
    }

}
