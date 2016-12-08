package com.framework.controller.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

    public static void main(String[] args) {
        Socket s;
        try {
            s = new Socket("127.0.0.1", 8080);

            PrintWriter pw = new PrintWriter(s.getOutputStream());
            pw.println("i am client.");
            pw.flush();

            BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));

            String line = br.readLine();

            System.out.println("server return:" + line);

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
