import java.io.*;
import java.io.IOException;
import java.net.*;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;


public class Server {   // 메인클래스
	ServerSocket ss = null;
	static ConnectedClient c; // 접속한 유저의 소켓 저장용
	ArrayList<ConnectedClient> clients = new ArrayList<ConnectedClient>(); // 접속해있는 유저 소켓관리용
	ArrayList<String> UID = new ArrayList<String>(); // 접속해있는 유저아이디 관리용
	
	public static void main(String[] args) {
		Server server = new Server();
		try {
			server.ss = new ServerSocket(60000);
			System.out.println("Server > Server Socket is Created...");
			while(true) {
				Socket socket = server.ss.accept();			
				c = new ConnectedClient(socket, server);	
				server.clients.add(c);
				c.start();
			}
		}catch(SocketException e) {
			System.out.println("Server > 소켓 관련 예외 발생, 서버종료");
		}catch(IOException e) {
			System.out.println("Server > 입출력 예외 발생");
		}
	}
}