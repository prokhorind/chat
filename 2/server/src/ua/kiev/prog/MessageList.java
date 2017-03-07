package ua.kiev.prog;

import java.io.OutputStream;
import java.util.LinkedList;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class MessageList {
	private static final MessageList msgList = new MessageList();
    private static final int LIMIT = 100;

    private final Gson gson;
	private final List<ua.kiev.prog.Message> list = new LinkedList<ua.kiev.prog.Message>();
	
	public static MessageList getInstance() {
		return msgList;
	}
  
  	private MessageList() {
		gson = new GsonBuilder().create();
	}
	
	public synchronized void add(ua.kiev.prog.Message m) {
	   /* if (list.size() + 1 == LIMIT) {
	        list.remove(0);
        }
        */
		list.add(m);
	}
 	public String	getFrom(int i){
		return list.get(i).getFrom();
	}
	public OutputStream getOs(int i) {
		return list.get(i).getOs();
	}

	public synchronized void get(int i) {

		list.get(i);
	}
	public synchronized String getTo(int i) {

		return 	list.get(i).getTo();
	}


	public synchronized int size() {

	return	list.size();
	}
	
	public synchronized String toJSON(int n,String name,String room) {
		if (n == list.size()) return null;

		return gson.toJson(new JsonMessages(list, n,name,room));
	}
}
