import java.io.*;
import java.io.IOException;
import java.net.*;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.StringTokenizer;

class LogInChecker{ // 로그인을 하기위해 개발함
	ArrayList<User> userInfor = new ArrayList<User>(); //텍스트파일에서 읽어온 id와 pw를 저장하기위해 만든 ArrayList
	File dataFile = new File("users.txt"); // 가입되어있는 id와 pw가 저장되어있는 텍스트파일
	String readData; // readData 읽기 
	StringTokenizer st; // 구분자를 선언한다
	LogInChecker(){
		try {
			BufferedReader br = new BufferedReader(new FileReader(dataFile)); // 파일 리더 선언 
			while ((readData = br.readLine()) != null) {
				// 끝까지 읽는다.
				st = new StringTokenizer(readData, "//"); // 구분자는 //
				String userId= st.nextToken(); // 첫번째 받아주기 
				String userPassword = st.nextToken(); //  구분자 후 두번째
				User user = new User(userId, userPassword);
				userInfor.add(user);
			}
			br.close(); // 버퍼 닫기
		} catch (IOException error) {
			System.out.println(error.getMessage());
		}
	}

	boolean check(String _id, String _pass){ // 로그인하려는 id와 pw를 받아 userInfor에 저장되어잇으면 true를 없으면 false를 리턴함
		boolean flag = false;
		for(int i = 0; i<userInfor.size(); i++) {
			if(userInfor.get(i).id.equals(_id) && userInfor.get(i).pass.equals(_pass)) {
				flag = true;
			}
		}
		return flag;
	}
}