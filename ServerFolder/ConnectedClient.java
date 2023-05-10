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
	
	//각종 Tag들
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
	
	int a = 0;// 리스트 삭제할때 필요한 정수값
	
	ConnectedClient(Socket _s, Server _ss){
		this.socket = _s;	
		this.server = _ss;
	}
	
	public void run() {
		try {
			System.out.println("Server > "+ this.socket.getInetAddress()+"에서의 접속이 연결되었습니다.");
			outStream =  this.socket.getOutputStream();
			dataOutStream = new DataOutputStream(outStream);
			inStream =  this.socket.getInputStream();
			dataInStream = new DataInputStream(inStream);
			while(true) {
				msg = dataInStream.readUTF();
				StringTokenizer stk = new StringTokenizer(msg, "//");
				String STK = stk.nextToken(); // Tag 저장용
				
				if(STK.equals(loginTag)) { // 로그인
					String id = stk.nextToken();
					String pass = stk.nextToken();
					LogInChecker LC = new LogInChecker(); // 여기서 선언하는 이유는 회원가입 후 정보를 최신화 하기 위해서
					if(LC.check(id, pass)) {
						CCT = server.c; // 어떤 유저가 나갔는지 확인 하기 위한 선언
						ar = id; // 어떤 유저가 나갔는지 확인하기 위한 선언
						dataOutStream.writeUTF("LOGIN_OK"); 
					}
					else dataOutStream.writeUTF("LOGIN_FAIL");
				}
				else if(STK.equals(loginId)) { // 아이디 중복확인
					String id = stk.nextToken();
					IDChecker IC = new IDChecker(); // 여기서 선언하는 이유는 회원가입 후 정보를 최신화 하기 위해서
					if(IC.check(id)) dataOutStream.writeUTF("ID_OK");
					else dataOutStream.writeUTF("ID_Fail");
				}
				else if(STK.equals(Member)) { // 회원가입 정보 중복 확인
					String id = stk.nextToken();
					String pw = stk.nextToken();
					String name = stk.nextToken();
					String phone = stk.nextToken();
					MembershipChecker MC = new MembershipChecker(); // 여기서 선언하는 이유는 회원가입 후 정보를 최신화 하기 위해서
					if(MC.check(id, pw, name, phone)) dataOutStream.writeUTF("Member_OK");
					else dataOutStream.writeUTF("Member_Fail");
				}
				else if(STK.equals(Save)) { // 회원가입 정보 저장
					String id = stk.nextToken();
					String pw = stk.nextToken();
					String name = stk.nextToken();
					String phone = stk.nextToken();
					Savemem SM = new Savemem(); // 여기서 선언하는 이유는 회원가입 후 정보를 최신화 하기 위해서
					if(SM.savecheck(id, pw, name, phone)) dataOutStream.writeUTF("Save_OK");
					else dataOutStream.writeUTF("Save_Fail");
				}
				else if(STK.equals(sendMsg)) { // 메세지 전송
					String id = stk.nextToken();
					String msg = stk.nextToken();
					System.out.println(msg);
					for(int i = 0 ; i < server.clients.size() ; i++) { // 접속해 있는 클라이언트에게 뿌려주는 작업
						outStream = server.clients.get(i).socket.getOutputStream();
						dataOutStream = new DataOutputStream(outStream);
						dataOutStream.writeUTF(sendMsg + "//" +  id + " > " + msg);
					}
				}
				else if(STK.equals(SendID)) { // 입장시 입장멘트와 리스트 추가
					String id = stk.nextToken();
					server.UID.add(id); // String 타입의 ArrayList인 배열에 id 저장해서 리스트에 뿌려주기 위한 작업
					
					for(int z = 0 ; z <server.clients.size(); z++) { // 입장멘트를 접속해있는 클라이언트에게 뿌려주는 작업
						outStream = server.clients.get(z).socket.getOutputStream();
						dataOutStream = new DataOutputStream(outStream);
						dataOutStream.writeUTF(Welcome + "//" + id + " 이(가) 입장했습니다.");
					}
					
					for(int i = 0 ; i < server.clients.size() ; i++) { // 리스트에 추가할 유저를 뿌려주는 작업
						for(int j = 0; j < server.UID.size();j++) {
							outStream = server.clients.get(i).socket.getOutputStream();
							dataOutStream = new DataOutputStream(outStream);
							dataOutStream.writeUTF(SendID + "//" + a + "//" + server.UID.get(j)); // a가 0일경우 클라이언트에서 리스트 초기화
							a++;
						}
						a = 0;
					}
				}
				else if(STK.equals(Log)) { // 메뉴바의 로그아웃시 퇴장멘트와 리스트 제거
					String id = stk.nextToken();
					
					for(int j = 0 ; j < server.UID.size() ; j++) { //로그아웃시 리스트와 관련된 배열에서 id를 제거하기 위한 작업
						if(server.UID.get(j).equals(id) ) {
							server.UID.remove(j);
						}
					}
					
					for(int z = 0 ; z <server.clients.size(); z++) { // 퇴장멘트를 접속해있는 클라이언트에게 뿌려주즌 작업
						outStream = server.clients.get(z).socket.getOutputStream();
						dataOutStream = new DataOutputStream(outStream);
						dataOutStream.writeUTF(bye + "//" + id + " 이(가) 퇴장하셨습니다.");
					}
					
					for(int i = 0 ; i < server.clients.size() ; i++) { // 리스트에서 제거하기 위함
						outStream = server.clients.get(i).socket.getOutputStream();
						dataOutStream = new DataOutputStream(outStream);
						dataOutStream.writeUTF(Log + "//" + id);
					}
				}
				
				else if(STK.equals(LE)) { // 로그인 창에서 종료시 ArrayList의 clients 에서 제거
					for(int j = 0 ; j < server.clients.size() ; j++) {
						if(server.clients.get(j).equals(server.c)) {
							server.clients.remove(j);
						}
					}
				}
				
			}
		}catch(IOException e) { // 종료시 실행
			try {
			for(int j = 0 ; j < server.clients.size() ; j++) { // 위에서 CCT로 선언한 값을 clients와 비교 후 같으면 삭제
				if(server.clients.get(j).equals(CCT)) {
					server.clients.remove(j);
				}
			}

			for(int j = 0 ; j < server.UID.size() ; j++) { // 퇴장한 유저의 id를 삭제
				if(server.UID.get(j).equals(ar) ) {
					server.UID.remove(j);
				}
			}
			for(int z = 0 ; z <server.clients.size(); z++) { // 퇴장멘트를 접속해있는 클라이언트에게 뿌려주는 작업
				outStream = server.clients.get(z).socket.getOutputStream();
				dataOutStream = new DataOutputStream(outStream);
				dataOutStream.writeUTF(bye + "//" + ar + " 이(가) 퇴장하셨습니다.");
			}
			
			for(int i = 0 ; i < server.clients.size() ; i++) { // 리스트 제거용
				outStream = server.clients.get(i).socket.getOutputStream();
				dataOutStream = new DataOutputStream(outStream);
				dataOutStream.writeUTF(exit + "//" + ar);
			}
			System.out.println("접속종료");
			}catch(Exception l){
				System.out.println("아몰랑");
			}
		}
	}
}