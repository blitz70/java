package center.wiz.java3.project;

import java.awt.List;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;
import java.util.Iterator;

public class ChatThread extends Thread {

	private int id = 0;
	private HashMap<Integer, Socket> sockets = null;
	private Socket socket = null;
	private List list = null;
	private PrintWriter writer = null;
	private BufferedReader reader = null;
	private String data;

	public ChatThread(int id, HashMap<Integer, Socket> sockets, List list) {
		super();
		this.id = id;
		this.sockets = sockets;
		this.list = list;
	}

	@Override
	public void run() {
		try {
			list.add("Client(" + id + ") connected.");
			reader = new BufferedReader(new InputStreamReader(sockets.get(id).getInputStream()));
			while(true) {
				try {
					data = reader.readLine();
					list.add("(" + id + ") :" + data);
					Iterator<Integer> iterator = sockets.keySet().iterator();
					while (iterator.hasNext()) {
						socket = sockets.get(iterator.next());
						writer = new PrintWriter(socket.getOutputStream(), true);
						writer.println(data);
					}
				} catch (Exception e) {
					list.add("Client(" + id + ")  disconnected.");
					break;
				}
			}
			if(writer != null) writer.close(); 
			if(reader != null) reader.close(); 
			if(socket != null) socket.close(); 
			
		} catch (Exception e) {
		}
	}

}
