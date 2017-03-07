package ua.kiev.prog;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.SocketHandler;

import javax.servlet.http.*;

public class GetListServlet extends HttpServlet {
	
	private MessageList msgList = MessageList.getInstance();
	private UserList userList = UserList.getInstance();


	static{


	}

    @Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String fromStr = req.getParameter("from");
		String Name = req.getParameter("name");
		String room = req.getParameter("room");



		int from = 0;
		try {
			from = Integer.parseInt(fromStr);
		} catch (Exception ex) {
			resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
		}

		String json=null;

		//Все сообщения в json!!!
		json= msgList.toJSON(from,Name,room);



		if(json!=null){
			OutputStream os = resp.getOutputStream();

			byte[] buf2 = json.getBytes(StandardCharsets.UTF_8);


			os.write(buf2);
			os.flush();
			os.close();

		}


	}


	}








