package com.framework.core.common;

public class HttpStatus {

    private int code;

    private String content;

    public HttpStatus() {}

    public HttpStatus(int code, String content) {
        super();
        this.code = code;
        this.content = content;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
