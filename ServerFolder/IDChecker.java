import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

class IDChecker { // 중복확인을 위해 제작
	ArrayList<UserID> userInfor = new ArrayList<UserID>(); //텍스트파일에서 읽어온 id들을 저장하는 배열
	File dataFile = new File("UserID.txt"); // userid를 저장하는 텍스트파일
	String readData; // readData 읽기 
	StringTokenizer st; // 구분자를 선언한다
	IDChecker(){
		try {
			BufferedReader br = new BufferedReader(new FileReader(dataFile)); // 파일 리더 선언 
			while ((readData = br.readLine()) != null) { // 끝까지 읽기
				UserID user = new UserID(readData);
				userInfor.add(user);
			}
			br.close(); // 버퍼 닫기
		} catch (IOException error) {
			System.out.println(error.getMessage());
		}
	}
	
	boolean check(String _id){ // 중복확인을 하는 용도, userInfor배열에 받은 _id 값이 있으면 false, 없으면 true 전송
		boolean flag = true;
		for(int i = 0; i<userInfor.size(); i++) {
			if(userInfor.get(i).Id.equals(_id)) {
				flag = false;
			}
		}
		return flag;
	}
}
