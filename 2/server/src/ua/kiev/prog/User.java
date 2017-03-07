package ua.kiev.prog;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

/**
 * Created by kleba on 02.03.2017.
 */
public class User {



    private String name;
    private String room;
    private boolean status;


    User(String name,boolean status ){
        this.name=name;
        this.status=status;
        this.room="public";

    }

    User(String name,String room ){
        this.name=name;
        this.status=true;
        this.room=room;

    }

    User(String name){
        this.name= name;
        this.status= true;
        this.room="public";
    }
    User(String name,boolean status,String room){
        this.name=name;
        this.status=status;
        this.room=room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getRoom() {
        return room;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    @Override
    public String toString() {
        return new StringBuilder().append("Name:").append(name).append(",room:").append(room).toString();
    }


    public String getName() {
        return name;
    }

    public boolean isStatus() {
        return status;
    }


    public String toJSON() {
        Gson gson = new GsonBuilder().create();
        return gson.toJson(this);
    }

    public static User fromJSON(String s) {
        Gson gson = new GsonBuilder().create();
        return gson.fromJson(s, User.class);
    }

    public int send(String url) throws IOException {
        URL obj = new URL(url);
        HttpURLConnection conn = (HttpURLConnection) obj.openConnection();

        conn.setRequestMethod("POST");
        conn.setDoOutput(true);

        OutputStream os = conn.getOutputStream();
        try {
            //отправка в поток
            String json = toJSON();
            os.write(json.getBytes(StandardCharsets.UTF_8));
            //  return conn.getResponseCode();
        } finally {
            os.close();
        }




        return conn.getResponseCode();
    }


}


