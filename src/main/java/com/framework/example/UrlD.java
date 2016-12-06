package com.framework.example;

import java.net.URLDecoder;

public class UrlD {

    public static void main(String[] args) {
        String result = URLDecoder.decode("%E8%8E%AB%E9%9B%B7%E4%B8%80");
        System.out.println(result);
    }

}
