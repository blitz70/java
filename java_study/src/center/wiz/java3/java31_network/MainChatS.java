package center.wiz.java3.java31_network;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class MainChatS {

	ServerSocket sSocket = null;
	Socket socket = null;
	PrintWriter writer = null;
	BufferedReader reader = null;
	String data;
	
	public MainChatS() {
		try {
			sSocket = new ServerSocket(2000);
			
			while(true) {
				socket = sSocket.accept();
				System.out.println(socket.toString() + " Client 요청.");
				
				writer = new PrintWriter(socket.getOutputStream(), true);
				reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				
				while((data = reader.readLine()) != null) {
					writer.write(data);
					System.out.println("Input : " + data);
				}
				if(writer != null) writer.close(); 
				if(reader != null) reader.close(); 
				if(socket != null) socket.close(); 
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		new MainChatS();
	}

}
