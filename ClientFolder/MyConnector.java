import java.io.*;
import java.net.*;
import java.util.Vector;
import javax.swing.DefaultListModel;
import java.util.StringTokenizer;
import java.util.ArrayList;

public class MyConnector {
	Socket mySocket = null;
	OutputStream outStream = null;
	DataOutputStream dataOutStream = null;
	InputStream inStream = null;
	DataInputStream dataInStream=null;
	MessageListener MGL;
	Operator mainOperator;
	MyConnector connector;
	
	String msgO; // �������� ���� �޼��� �б�
	
	//���� Tag��
	final String loginTag = "LOGIN";
	final String queryTag = "QUERY";
	final String loginId = "ID";
	final String Member = "member";
	final String Save ="save";
	final String SME = "SendMessage";
	final String SID = "SendID";
	final String Wel= "welcome";
	final String Log = "LogOut";
	final String bye = "bye";
	final String exit = "exit";
	final String LE = "Loginexit";
	
	public MyConnector(Operator _o){
		try {
			mainOperator = _o;
			connector = _o.connector;
			mySocket = new Socket("localhost", 60000);
			MGL = new MessageListener(mySocket);
			System.out.println("CLIENT LOG> ������ ����Ǿ����ϴ�.");
			outStream = mySocket.getOutputStream();
			dataOutStream = new DataOutputStream(outStream);
			inStream = mySocket.getInputStream();
			dataInStream =  new DataInputStream(inStream);
			Thread.sleep(100);
		}catch(Exception e) {
			
		}
	}
	
	boolean sendLoginInformation(String uid, String upass){
		String msg = null; // �������� ���� �޼��� �ޱ��
		try {
			dataOutStream.writeUTF(loginTag+ "//" + uid + "//" + upass); // �α��� ���� ������
			msg = dataInStream.readUTF();
		}catch(Exception e) {
			System.out.println("�α��ο���");
		}
		if(msg.equals("LOGIN_OK")) return true; //msg�� OK�̸� true
		else return false; // �ƴϸ� false
	}
	
	boolean sendLoginID(String eid) {
		String msg = null; // �������� ���� �޼��� �ޱ��
		try {
			dataOutStream.writeUTF(loginId + "//" + eid); // �ߺ�Ȯ�� ��
			msg = dataInStream.readUTF();
		}
		catch(Exception e) {
			System.out.println("ID�ߺ�Ȯ�ο���");
		}
		if(msg.equals("ID_OK")) return true; //OK�̸� true
		else return false; // �ƴϸ� false
	}
	
	boolean sendMember(String uid, String upw, String una , String upn) {
		String msg = null; // �������� ���� �޼��� �ޱ��
		try {
			dataOutStream.writeUTF(Member + "//" + uid + "//" + upw + "//" + una + "//" + upn); // ȸ������ �ߺ�Ȯ�ο�
			msg = dataInStream.readUTF();
		}catch(Exception e) {
			System.out.println("ȸ�����Ե��������ۿ���");
		}
		if(msg.equals("Member_OK")) return true; //OK�̸� true
		else return false; // �ƴϸ� false
	}
	
	boolean SaveMember(String uid, String upw, String una, String upn) {
		String msg = null; // �������� ���� �޼��� �ޱ��
		try {
			dataOutStream.writeUTF(Save + "//" + uid + "//" + upw + "//" + una + "//" + upn); // ȸ������ �����
			msg = dataInStream.readUTF();
		}catch(Exception e) {
			System.out.println("ȸ������ ���ۿ���");
		}
		if(msg.equals("Save_OK")) return true; // Ok�̸� true
		else return false; // �ƴϸ� false
	}
	
	void sendmge (String id, String M) {
		try {
			dataOutStream.writeUTF(SME + "//" + id + "//" + M); // �޼��� ���ۿ�
		}catch(Exception e) {
			System.out.println("�޼������ۿ���");
		}
	}
	
	void sendmge (String id) {
		try {
			dataOutStream.writeUTF(SID + "//" + id); // ����Ʈ �����ϱ� ���� id ��
		}catch(Exception e) {
			System.out.println("ID���ۿ���");
		}
	}
	
	void Loginexit() {
		try {
			dataOutStream.writeUTF(LE); //�α���â������ ����
		}catch(Exception e) {
			System.out.println("�α���â ���� ����");
		}
	}
	
	void sendID (String id) { // �α׾ƿ����� ����
		try {
			dataOutStream.writeUTF(Log + "//" + id);
		}catch(Exception e) {
			System.out.println("�α׾ƿ� ID ���� ����");
		}
	}
		
	public class MessageListener extends Thread {
		Socket socket;
		MessageListener(Socket _s) {
			this.socket = _s;
		}
		
		public void run() {
			try {
				while(true) {
					msgO = dataInStream.readUTF(); // �޼����ް�
					StringTokenizer stk = new StringTokenizer(msgO, "//");
					String STK = stk.nextToken(); // �ױ������ϰ�
					String qtk = stk.nextToken(); // �޼��� ����
					if (STK.equals(SME)) { // �޼��� ����
						mainOperator.mf.ch.append(qtk + "\n"); // TextArea�� �߰�
					}
					
					else if (STK.equals(Wel)) { // �����
						mainOperator.mf.ch.append(qtk + "\n"); // TextArea�� �߰�
					}
					
					else if (STK.equals(SID)) {
						String m = stk.nextToken(); // �������� ���� �������� String���� ����
						System.out.println(qtk);
						if(qtk.equals("0")) { // 0 �̸� list���� �ʱ�ȭ �� �߰�
							mainOperator.mf.model.removeAllElements();
							mainOperator.mf.model.addElement(m);
						}
						else {
							mainOperator.mf.model.addElement(m); // �ƴϸ� �׳� �߰�
						}
					}
					
					else if (STK.equals(bye)) { // �����Ʈ TextArea�� �߰�
						mainOperator.mf.ch.append(qtk + "\n");
					}
					
					else if (STK.equals(Log)) { // �α� �ƿ��� list���� ����
						for(int i = 0 ; i < mainOperator.mf.model.getSize(); i++) {
							if(mainOperator.mf.model.get(i).equals(qtk)) {
								mainOperator.mf.model.remove(i);
							}
						}
					}
					
					else if (STK.equals(exit)) { // ����� list���� ����
						for(int i = 0 ; i < mainOperator.mf.model.getSize(); i++) {
							if(mainOperator.mf.model.get(i).equals(qtk)) {
								mainOperator.mf.model.remove(i);
							}
						}
					}
				}
			}catch(Exception e) {
				System.out.println("�޼��� �ޱ� ����");	
			}
		}
	}
}
