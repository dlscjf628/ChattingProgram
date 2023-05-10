import java.io.*;
import java.io.IOException;
import java.net.*;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Savemem { // 회원정보를 저장하기 위한 선언
	File file = new File("membership.txt"); //회원정보를 저장하기위함
	File filse = new File("UserID.txt"); // id를 저장하기위함
	File filses = new File("users.txt"); // id와 pw를 저장하기위함
	int Savemem(String _id, String _pw, String _name, String _phone) {
		
		String MBS =_id + "//" + _pw + "//" + _name + "//" + _phone; // 회원정보를 저장
		String UI = _id;
		String U = _id + "//" + _pw;
		System.out.println(MBS);
		try {
			BufferedWriter buffer = new BufferedWriter(new FileWriter(file, true)); // 버퍼 열기
			BufferedWriter buffers = new BufferedWriter(new FileWriter(filse, true));
			BufferedWriter bufferse = new BufferedWriter(new FileWriter(filses, true));
			
			buffer.write(MBS); //버퍼에 쓰고 
			buffer.flush(); // 출력하고
			buffer.newLine(); // 새로운 줄 만들고
			buffer.close(); // 버퍼 닫기
			
			buffers.write(UI);
			buffers.flush();
			buffers.newLine();
			buffers.close();
			
			bufferse.write(U);
			bufferse.flush();
			bufferse.newLine();
			bufferse.close();
			
		}catch (Exception e){
			System.out.println("파일 입력오류");
			return 1;
		}
		return 0;
	}
	
	boolean savecheck(String _id, String _pw, String _name, String _phone) {
		int S = Savemem(_id, _pw, _name, _phone); // 저장을 할때 성공시 0을 return 받음 
		if (S == 0) return true; // 성공하면 true값을
		else return false; // 실패하면 false값을 return함
	}
 }
