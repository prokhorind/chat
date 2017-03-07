package com.company;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

/**
 * Created by kleba on 02.03.2017.
 */
public class GetUsers {
    private final Gson gson;
    private int n;


    public GetUsers() {
        gson = new GsonBuilder().create();
    }


    public void getUsersList() {
        try {

                URL url = new URL(Utils.getURL() + "/getU?from=" + n);
                HttpURLConnection http = (HttpURLConnection) url.openConnection();
                //получили json сообщение с сервера
                InputStream is = http.getInputStream();


                try {

                    byte[] buf = requestBodyToArray(is);


                    String strBuf = new String(buf, StandardCharsets.UTF_8);


                    JsonUsers list = gson.fromJson(strBuf, JsonUsers.class);
                    if (list != null) {
                        for (User m : list.getList()) {
                            System.out.println(m);

                        }
                    }
                } finally {
                    is.close();
                }



        } catch (Exception ex) {
            ex.printStackTrace();
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
