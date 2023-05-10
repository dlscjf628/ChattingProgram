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
	
	String msgO; // 서버에서 보낸 메세지 읽기
	
	//각종 Tag들
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
			System.out.println("CLIENT LOG> 서버로 연결되었습니다.");
			outStream = mySocket.getOutputStream();
			dataOutStream = new DataOutputStream(outStream);
			inStream = mySocket.getInputStream();
			dataInStream =  new DataInputStream(inStream);
			Thread.sleep(100);
		}catch(Exception e) {
			
		}
	}
	
	boolean sendLoginInformation(String uid, String upass){
		String msg = null; // 서버에서 보낸 메세지 받기용
		try {
			dataOutStream.writeUTF(loginTag+ "//" + uid + "//" + upass); // 로그인 정보 보내기
			msg = dataInStream.readUTF();
		}catch(Exception e) {
			System.out.println("로그인오류");
		}
		if(msg.equals("LOGIN_OK")) return true; //msg가 OK이면 true
		else return false; // 아니면 false
	}
	
	boolean sendLoginID(String eid) {
		String msg = null; // 서버에서 보낸 메세지 받기용
		try {
			dataOutStream.writeUTF(loginId + "//" + eid); // 중복확인 용
			msg = dataInStream.readUTF();
		}
		catch(Exception e) {
			System.out.println("ID중복확인오류");
		}
		if(msg.equals("ID_OK")) return true; //OK이면 true
		else return false; // 아니면 false
	}
	
	boolean sendMember(String uid, String upw, String una , String upn) {
		String msg = null; // 서버에서 보낸 메세지 받기용
		try {
			dataOutStream.writeUTF(Member + "//" + uid + "//" + upw + "//" + una + "//" + upn); // 회원가입 중복확인용
			msg = dataInStream.readUTF();
		}catch(Exception e) {
			System.out.println("회원가입데이터전송오류");
		}
		if(msg.equals("Member_OK")) return true; //OK이면 true
		else return false; // 아니면 false
	}
	
	boolean SaveMember(String uid, String upw, String una, String upn) {
		String msg = null; // 서버에서 보낸 메세지 받기용
		try {
			dataOutStream.writeUTF(Save + "//" + uid + "//" + upw + "//" + una + "//" + upn); // 회원가입 저장용
			msg = dataInStream.readUTF();
		}catch(Exception e) {
			System.out.println("회원가입 전송오류");
		}
		if(msg.equals("Save_OK")) return true; // Ok이면 true
		else return false; // 아니면 false
	}
	
	void sendmge (String id, String M) {
		try {
			dataOutStream.writeUTF(SME + "//" + id + "//" + M); // 메세지 전송용
		}catch(Exception e) {
			System.out.println("메세지전송오류");
		}
	}
	
	void sendmge (String id) {
		try {
			dataOutStream.writeUTF(SID + "//" + id); // 리스트 관리하기 위한 id 용
		}catch(Exception e) {
			System.out.println("ID전송오류");
		}
	}
	
	void Loginexit() {
		try {
			dataOutStream.writeUTF(LE); //로그인창에서의 종료
		}catch(Exception e) {
			System.out.println("로그인창 종료 오류");
		}
	}
	
	void sendID (String id) { // 로그아웃으로 종료
		try {
			dataOutStream.writeUTF(Log + "//" + id);
		}catch(Exception e) {
			System.out.println("로그아웃 ID 전송 오류");
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
					msgO = dataInStream.readUTF(); // 메세지받고
					StringTokenizer stk = new StringTokenizer(msgO, "//");
					String STK = stk.nextToken(); // 테그저장하고
					String qtk = stk.nextToken(); // 메세지 저장
					if (STK.equals(SME)) { // 메세지 전송
						mainOperator.mf.ch.append(qtk + "\n"); // TextArea에 추가
					}
					
					else if (STK.equals(Wel)) { // 입장시
						mainOperator.mf.ch.append(qtk + "\n"); // TextArea에 추가
					}
					
					else if (STK.equals(SID)) {
						String m = stk.nextToken(); // 서버에서 보낸 정수값을 String으로 받음
						System.out.println(qtk);
						if(qtk.equals("0")) { // 0 이면 list값을 초기화 후 추가
							mainOperator.mf.model.removeAllElements();
							mainOperator.mf.model.addElement(m);
						}
						else {
							mainOperator.mf.model.addElement(m); // 아니면 그냥 추가
						}
					}
					
					else if (STK.equals(bye)) { // 퇴장멘트 TextArea에 추가
						mainOperator.mf.ch.append(qtk + "\n");
					}
					
					else if (STK.equals(Log)) { // 로그 아웃시 list에서 삭제
						for(int i = 0 ; i < mainOperator.mf.model.getSize(); i++) {
							if(mainOperator.mf.model.get(i).equals(qtk)) {
								mainOperator.mf.model.remove(i);
							}
						}
					}
					
					else if (STK.equals(exit)) { // 종료시 list에서 삭제
						for(int i = 0 ; i < mainOperator.mf.model.getSize(); i++) {
							if(mainOperator.mf.model.get(i).equals(qtk)) {
								mainOperator.mf.model.remove(i);
							}
						}
					}
				}
			}catch(Exception e) {
				System.out.println("메세지 받기 오류");	
			}
		}
	}
}
