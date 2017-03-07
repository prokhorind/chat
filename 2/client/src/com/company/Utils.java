package com.company;

import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Utils {
    private static final String URL = "http://127.0.0.1";
    private static final int PORT = 8080;

    public static String getURL() {
        return URL + ":" + PORT;
    }

    public static int exit(String name) throws IOException {
        java.net.URL url = new URL(Utils.getURL() + "/exit");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();



            conn.setRequestMethod("POST");
            conn.setDoOutput(true);


            try ( OutputStream os = conn.getOutputStream();){
                DataOutput dos = new DataOutputStream(os);
                dos.writeUTF(name);

            }
            return conn.getResponseCode();




    }
        public static int changeRoom(String login,String room) throws IOException {
            java.net.URL url = new URL(Utils.getURL() + "/cr");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();



            conn.setRequestMethod("POST");
            conn.setDoOutput(true);


            try ( OutputStream os = conn.getOutputStream();){
                DataOutput dos = new DataOutputStream(os);
                dos.writeUTF(login);
                dos.writeUTF(room);

            }


            return conn.getResponseCode();
            //Thread th = new Thread(new GetThread(login));
        }
}
