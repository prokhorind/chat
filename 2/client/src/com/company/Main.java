package com.company;

import javax.jws.soap.SOAPBinding;
import java.io.IOException;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		try {
			System.out.println("Enter your login: ");
			String login = scanner.nextLine();
			User user = new User(login);

			user.send(Utils.getURL() + "/addU");
			Thread th = new Thread(new GetThread(login,user.getRoom()));
			th.setDaemon(true);
			th.start();

			GetUsers gut= new GetUsers();

            System.out.println("Enter your message: ");
			while (true) {
				Message m;
					String text = scanner.nextLine();
				if(text.equals("/list")) {
					gut.getUsersList();
				}
				else if(text.equals("/cr")){
					System.out.println("Введите название комнаты");
					String roomName=scanner.nextLine();
					th.interrupt();
					user.setRoom(roomName);
					Thread t2=	new Thread(new GetThread(login,user.getRoom()));
					t2.setDaemon(true);
						t2.start();
					Utils.exit(login);
					Utils.changeRoom(login,roomName);
				}

				else if( text.equals("/exit")){

					System.out.println(Utils.exit(login));
					break;
				}




				else{
					//отделить текст сообщения от адресата
					String[] messagesplit = text.split(":", 2);


					if (text.isEmpty()) break;
					if (messagesplit.length == 1) {
						m = new Message(login, text,user.getRoom());
					} else {
						m = new Message(login, messagesplit[0], text,user.getRoom());
					}


					//метод вывода кода ошибки и отправки сообщения
					int res = m.send(Utils.getURL() + "/add");

					if (res != 200) { // 200 OK
						System.out.println("HTTP error occured: " + res);
						return;
					}
					//	User.send(Utils.getURL()+"/addu");



				}

			}


		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			scanner.close();
		}
	}
}
