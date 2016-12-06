package com.framework.example.tmp;

import java.io.File;
import java.io.FileInputStream;

public class TmpDemo {

    public static void main(String[] args) throws Exception {


        File f = new File("e:\\in.html");

        FileInputStream is = new FileInputStream(f);
        
        System.out.println(is.read());


    }
}


