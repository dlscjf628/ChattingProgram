import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

class ConnectedClient extends Thread{
	Socket socket;
	OutputStream outStream;    	DataOutputStream dataOutStream;
	InputStream inStream;       	DataInputStream dataInStream;
	String msg;
	String uName;
	Server server;
	String ar;
	ConnectedClient CCT;
	
	//���� Tag��
	final String loginTag = "LOGIN";
	final String queryTag = "QUERY";
	final String Member = "member";
	final String loginId = "ID";
	final String Save = "save";
	final String sendMsg = "SendMessage";
	final String SendID = "SendID";
	final String Welcome = "welcome";
	final String bye = "bye";
	final String Log = "LogOut";
	final String exit = "exit";
	final String LE = "Loginexit";
	
	int a = 0;// ����Ʈ �����Ҷ� �ʿ��� ������
	
	ConnectedClient(Socket _s, Server _ss){
		this.socket = _s;	
		this.server = _ss;
	}
	
	public void run() {
		try {
			System.out.println("Server > "+ this.socket.getInetAddress()+"������ ������ ����Ǿ����ϴ�.");
			outStream =  this.socket.getOutputStream();
			dataOutStream = new DataOutputStream(outStream);
			inStream =  this.socket.getInputStream();
			dataInStream = new DataInputStream(inStream);
			while(true) {
				msg = dataInStream.readUTF();
				StringTokenizer stk = new StringTokenizer(msg, "//");
				String STK = stk.nextToken(); // Tag �����
				
				if(STK.equals(loginTag)) { // �α���
					String id = stk.nextToken();
					String pass = stk.nextToken();
					LogInChecker LC = new LogInChecker(); // ���⼭ �����ϴ� ������ ȸ������ �� ������ �ֽ�ȭ �ϱ� ���ؼ�
					if(LC.check(id, pass)) {
						CCT = server.c; // � ������ �������� Ȯ�� �ϱ� ���� ����
						ar = id; // � ������ �������� Ȯ���ϱ� ���� ����
						dataOutStream.writeUTF("LOGIN_OK"); 
					}
					else dataOutStream.writeUTF("LOGIN_FAIL");
				}
				else if(STK.equals(loginId)) { // ���̵� �ߺ�Ȯ��
					String id = stk.nextToken();
					IDChecker IC = new IDChecker(); // ���⼭ �����ϴ� ������ ȸ������ �� ������ �ֽ�ȭ �ϱ� ���ؼ�
					if(IC.check(id)) dataOutStream.writeUTF("ID_OK");
					else dataOutStream.writeUTF("ID_Fail");
				}
				else if(STK.equals(Member)) { // ȸ������ ���� �ߺ� Ȯ��
					String id = stk.nextToken();
					String pw = stk.nextToken();
					String name = stk.nextToken();
					String phone = stk.nextToken();
					MembershipChecker MC = new MembershipChecker(); // ���⼭ �����ϴ� ������ ȸ������ �� ������ �ֽ�ȭ �ϱ� ���ؼ�
					if(MC.check(id, pw, name, phone)) dataOutStream.writeUTF("Member_OK");
					else dataOutStream.writeUTF("Member_Fail");
				}
				else if(STK.equals(Save)) { // ȸ������ ���� ����
					String id = stk.nextToken();
					String pw = stk.nextToken();
					String name = stk.nextToken();
					String phone = stk.nextToken();
					Savemem SM = new Savemem(); // ���⼭ �����ϴ� ������ ȸ������ �� ������ �ֽ�ȭ �ϱ� ���ؼ�
					if(SM.savecheck(id, pw, name, phone)) dataOutStream.writeUTF("Save_OK");
					else dataOutStream.writeUTF("Save_Fail");
				}
				else if(STK.equals(sendMsg)) { // �޼��� ����
					String id = stk.nextToken();
					String msg = stk.nextToken();
					System.out.println(msg);
					for(int i = 0 ; i < server.clients.size() ; i++) { // ������ �ִ� Ŭ���̾�Ʈ���� �ѷ��ִ� �۾�
						outStream = server.clients.get(i).socket.getOutputStream();
						dataOutStream = new DataOutputStream(outStream);
						dataOutStream.writeUTF(sendMsg + "//" +  id + " > " + msg);
					}
				}
				else if(STK.equals(SendID)) { // ����� �����Ʈ�� ����Ʈ �߰�
					String id = stk.nextToken();
					server.UID.add(id); // String Ÿ���� ArrayList�� �迭�� id �����ؼ� ����Ʈ�� �ѷ��ֱ� ���� �۾�
					
					for(int z = 0 ; z <server.clients.size(); z++) { // �����Ʈ�� �������ִ� Ŭ���̾�Ʈ���� �ѷ��ִ� �۾�
						outStream = server.clients.get(z).socket.getOutputStream();
						dataOutStream = new DataOutputStream(outStream);
						dataOutStream.writeUTF(Welcome + "//" + id + " ��(��) �����߽��ϴ�.");
					}
					
					for(int i = 0 ; i < server.clients.size() ; i++) { // ����Ʈ�� �߰��� ������ �ѷ��ִ� �۾�
						for(int j = 0; j < server.UID.size();j++) {
							outStream = server.clients.get(i).socket.getOutputStream();
							dataOutStream = new DataOutputStream(outStream);
							dataOutStream.writeUTF(SendID + "//" + a + "//" + server.UID.get(j)); // a�� 0�ϰ�� Ŭ���̾�Ʈ���� ����Ʈ �ʱ�ȭ
							a++;
						}
						a = 0;
					}
				}
				else if(STK.equals(Log)) { // �޴����� �α׾ƿ��� �����Ʈ�� ����Ʈ ����
					String id = stk.nextToken();
					
					for(int j = 0 ; j < server.UID.size() ; j++) { //�α׾ƿ��� ����Ʈ�� ���õ� �迭���� id�� �����ϱ� ���� �۾�
						if(server.UID.get(j).equals(id) ) {
							server.UID.remove(j);
						}
					}
					
					for(int z = 0 ; z <server.clients.size(); z++) { // �����Ʈ�� �������ִ� Ŭ���̾�Ʈ���� �ѷ����� �۾�
						outStream = server.clients.get(z).socket.getOutputStream();
						dataOutStream = new DataOutputStream(outStream);
						dataOutStream.writeUTF(bye + "//" + id + " ��(��) �����ϼ̽��ϴ�.");
					}
					
					for(int i = 0 ; i < server.clients.size() ; i++) { // ����Ʈ���� �����ϱ� ����
						outStream = server.clients.get(i).socket.getOutputStream();
						dataOutStream = new DataOutputStream(outStream);
						dataOutStream.writeUTF(Log + "//" + id);
					}
				}
				
				else if(STK.equals(LE)) { // �α��� â���� ����� ArrayList�� clients ���� ����
					for(int j = 0 ; j < server.clients.size() ; j++) {
						if(server.clients.get(j).equals(server.c)) {
							server.clients.remove(j);
						}
					}
				}
				
			}
		}catch(IOException e) { // ����� ����
			try {
			for(int j = 0 ; j < server.clients.size() ; j++) { // ������ CCT�� ������ ���� clients�� �� �� ������ ����
				if(server.clients.get(j).equals(CCT)) {
					server.clients.remove(j);
				}
			}

			for(int j = 0 ; j < server.UID.size() ; j++) { // ������ ������ id�� ����
				if(server.UID.get(j).equals(ar) ) {
					server.UID.remove(j);
				}
			}
			for(int z = 0 ; z <server.clients.size(); z++) { // �����Ʈ�� �������ִ� Ŭ���̾�Ʈ���� �ѷ��ִ� �۾�
				outStream = server.clients.get(z).socket.getOutputStream();
				dataOutStream = new DataOutputStream(outStream);
				dataOutStream.writeUTF(bye + "//" + ar + " ��(��) �����ϼ̽��ϴ�.");
			}
			
			for(int i = 0 ; i < server.clients.size() ; i++) { // ����Ʈ ���ſ�
				outStream = server.clients.get(i).socket.getOutputStream();
				dataOutStream = new DataOutputStream(outStream);
				dataOutStream.writeUTF(exit + "//" + ar);
			}
			System.out.println("��������");
			}catch(Exception l){
				System.out.println("�Ƹ���");
			}
		}
	}
}