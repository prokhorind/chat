package com.company;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class GetThread implements Runnable {
    private final Gson gson;
    private int n;
    private int k;
    String name;
    String room;




    public GetThread() {

        gson = new GsonBuilder().create();
    }

    GetThread(String name){
        this.name=name;
        gson = new GsonBuilder().create();
    }
    GetThread(String name,String room){
        this.name=name;
        this.room=room;
        gson = new GsonBuilder().create();
    }

    @Override
    public void run() {
        try {
            while ( ! Thread.interrupted()) {
                URL url = new URL(Utils.getURL() + "/get?from=" + n+"&name="+name+"&room="+room);
                HttpURLConnection http = (HttpURLConnection) url.openConnection();
                //получили json сообщение с сервера
                InputStream is = http.getInputStream();


                try {

                    byte[] buf = requestBodyToArray(is);


                    String strBuf = new String(buf, StandardCharsets.UTF_8);


                    JsonMessages list = gson.fromJson(strBuf, JsonMessages.class);
                    if (list != null) {
                        for (Message m : list.getList()) {
                            System.out.println(m);
                            n++;
                        }
                    }
                } finally {
                    is.close();
                }

                Thread.sleep(500);
            }
        } catch (Exception ex) {
           // ex.printStackTrace();
        }
    }

    private byte[] requestBodyToArray(InputStream is) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] buf = new byte[10240];
        int r;

        do {
            r = is.read(buf);
            if (r > 0) bos.write(buf, 0, r);
        } while (r != -1);

        return bos.toByteArray();
    }


}
