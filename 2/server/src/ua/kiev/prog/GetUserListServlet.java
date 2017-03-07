package ua.kiev.prog;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

/**
 * Created by kleba on 02.03.2017.
 */
public class GetUserListServlet extends HttpServlet {

    private UserList userList = UserList.getInstance();



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String fromStr = req.getParameter("from");
        int from = 0;
        try {
            from = Integer.parseInt(fromStr);
        } catch (Exception ex) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        String json=null;

        //Все сообщения в json!!!
        json= userList.toJSON(from);



        if(json!=null){
            OutputStream os = resp.getOutputStream();

            byte[] buf = json.getBytes(StandardCharsets.UTF_8);



            os.write(buf);
            os.flush();
            os.close();

        }


    }


}
