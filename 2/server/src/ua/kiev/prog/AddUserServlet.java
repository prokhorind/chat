package ua.kiev.prog;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

/**
 * Created by kleba on 02.03.2017.
 */
public class AddUserServlet extends HttpServlet {
    public static String from;


    private UserList userList=UserList.getInstance();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        byte[] buf = requestBodyToArray(req);


        //message в json
        String bufStr = new String(buf, StandardCharsets.UTF_8);


        User u = User.fromJSON(bufStr);
        System.out.println("USer="+u.getName());

        if (u != null) {
            userList.add(u);

          /*  if(userList.size()==0){
                userList.add(u);
            }
            else {
                for (int i = 0; i < userList.size(); i++) {
                    if (u.getName().equals(userList.getName(i))) {
                        break;
                    }
                    else if(i==(userList.size()-1))
                        userList.add(u);
                }
            }
*/
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
