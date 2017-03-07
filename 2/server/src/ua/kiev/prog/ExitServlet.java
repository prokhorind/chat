package ua.kiev.prog;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by kleba on 04.03.2017.
 */
public class ExitServlet extends HttpServlet {

    private UserList userList = UserList.getInstance();


    static {


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        DataInputStream dis= new DataInputStream(req.getInputStream() );

        //String Name = req.getParameter("name");
        String Name = dis.readUTF();

            for(int i=0;i<userList.size();i++){
                if(Name.equals(userList.getName(i))){
                 //   userList.setStatus(i,false);
                        userList.delete(i);
                    break;
                }
            }


    }
}
