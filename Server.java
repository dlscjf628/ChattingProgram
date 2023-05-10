import java.io.*;
import java.io.IOException;
import java.net.*;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;


public class Server {   // ����Ŭ����
	ServerSocket ss = null;
	static ConnectedClient c; // ������ ������ ���� �����
	ArrayList<ConnectedClient> clients = new ArrayList<ConnectedClient>(); // �������ִ� ���� ���ϰ�����
	ArrayList<String> UID = new ArrayList<String>(); // �������ִ� �������̵� ������
	
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
			System.out.println("Server > ���� ���� ���� �߻�, ��������");
		}catch(IOException e) {
			System.out.println("Server > ����� ���� �߻�");
		}
	}
}