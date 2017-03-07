package ua.kiev.prog;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by kleba on 02.03.2017.
 */
public class UserList {
    private static final UserList userList= new UserList();
    private final Gson gson;
    private final List<User> list = new LinkedList<User>();

    public static UserList getInstance() {
        return userList;
    }
    private UserList() {
        gson = new GsonBuilder().create();
    }

    public synchronized void add(ua.kiev.prog.User m) {

        list.add(m);
    }


    public synchronized void get(int i) {

        list.get(i);
    }
    public synchronized String getName(int i) {

        return 	list.get(i).getName();
    }

    public synchronized String toJSON(int n) {

        if (n == list.size()) return null;

        return gson.toJson(new JsonUsers(list, n));
    }
     public void setStatus(int i,boolean status){
         list.get(i).setStatus(status);

     }
     public String getRoom(int i){
        return list.get(i).getRoom();
     }
 public void setRoom(String room,int i){
     list.get(i).setRoom(room);

 }
    public void delete(int i){
        list.remove(i);
    }

    public String findRoom(String name){
        String room="noroom";
        for(int i=0;i<list.size();i++){
            if (name.equals(list.get(i).getName())) {
                room = list.get(i).getRoom();
                break;
            }
        }
        return room;
    }
    public String findPerson(String name){
        String person="nope";
        for(int i=0;i<list.size();i++){
            if (name.equals(list.get(i).getName())) {
                person = list.get(i).getRoom();
                break;
            }
        }
        return person;

    }


    public synchronized int size() {

        return	list.size();
    }


}
