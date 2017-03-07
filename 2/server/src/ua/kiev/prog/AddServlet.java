package ua.kiev.prog;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddServlet extends HttpServlet {

	private MessageList msgList = MessageList.getInstance();
    private UserList userList=UserList.getInstance();
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

		byte[] buf = requestBodyToArray(req);


        //message в json
        String bufStr = new String(buf, StandardCharsets.UTF_8);


        Message msg = Message.fromJSON(bufStr);
/*
        User u = new User(msg.getFrom());
        if(userList.size()==0){
            userList.add(u);
        }
        else {
            for (int i = 0; i < userList.size(); i++) {
                if (u.getName().equals(userList.getName(i))) {
                    break;
                }
                if(i==(userList.size()-1))
                userList.add(u);
            }
        }
*/
        //отделить текст сообщения от адресата
        String[] message= msg.getText().split(":",2);

        if(message.length==2) {
          String   to = message[0];
            msg.setText(message[1]);
             msg.setTo(to);
        }
        else{
            msg.setTo("null");
        }

		if (msg != null) {

            for(int i=0;i<userList.size();i++) {

                if(((!userList.findPerson(msg.getTo()).equals("nope"))||(msg.getTo().equals("null")))) {
                    if((userList.findRoom(msg.getTo()).equals(userList.findRoom(msg.getFrom())))||msg.getTo().equals("null")) {
                        msgList.add(msg);
                        break;
                    }
                }
            }
        }
		else
			resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);

    }

	private byte[] requestBodyToArray(HttpServletRequest req) throws IOException {
        InputStream is = req.getInputStream();
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
