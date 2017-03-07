package ua.kiev.prog;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class JsonMessages {
    private final List<Message> list;
    private final List<Message> privatelist;
    private final UserList  userList;


    public JsonMessages(List<Message> sourceList, int fromIndex,String name,String room) {

        int counter=0;

        int numbercounter=0;
        this.list = new ArrayList<>();
        this.privatelist = new ArrayList<>();
        this.userList = UserList.getInstance();



        for (int i = 0; i < sourceList.size(); i++) {

                if ((sourceList.get(i).getTo().equals("null"))) {


                    if ((sourceList.get(i).getRoom().equalsIgnoreCase(userList.findRoom(name)))&&room.equals(sourceList.get(i).getRoom())) {

                        privatelist.add(sourceList.get(i));

                    }
                }
                for (int j = 0; j < userList.size(); j++) {

                    if ((((name.equalsIgnoreCase(sourceList.get(i).getTo())) || name.equalsIgnoreCase(sourceList.get(i).getFrom()))
                            && (name.equalsIgnoreCase(userList.getName(j)))) && (!sourceList.get(i).getTo().equalsIgnoreCase("null"))) {

                         if ((sourceList.get(i).getRoom().equalsIgnoreCase(userList.findRoom(sourceList.get(i).getFrom())))
                                 && (sourceList.get(i).getRoom().equalsIgnoreCase(userList.findRoom(sourceList.get(i).getTo())))
                                 &&(room.equals(sourceList.get(i).getRoom()))) {

                            privatelist.add(sourceList.get(i));
                        }


                    }

                }
        }
        for(int i=fromIndex;i<privatelist.size();i++){
            list.add(privatelist.get(i));
        }
   }
    }

