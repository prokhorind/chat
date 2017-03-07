package ua.kiev.prog;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.DataInputStream;
import java.io.IOException;

/**
 * Created by kleba on 04.03.2017.
 */
public class ChangeRoomServlet extends HttpServlet {

    private UserList userList = UserList.getInstance();




    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        DataInputStream dis= new DataInputStream(req.getInputStream() );
        int counter=0;
        //String Name = req.getParameter("name");
        String Name = dis.readUTF();
        String room= dis.readUTF();

  //      if(userList.size()==0){
            userList.add(new User(Name,room));
    //    }

     /*   for(int i=0;i<userList.size();i++){
            if(Name.equals(userList.getName(i))){
               userList.setRoom(room,i);
                counter++;
            }
            if((counter+1)==userList.size()){
                userList.add(new User(Name,room));
            }
        }
*/

    }
}
