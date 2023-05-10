import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class MembershipChecker { // 회원가입을 하기 위함
	ArrayList<MemberShipS> userInfor = new ArrayList<MemberShipS>(); // 텍스트파일에서 읽어온 값들을 저장하는 ArrayList
	File dataFile = new File("membership.txt"); // 회원정보가 저장되어있는 텍스트 파일
	String readData; // readData 읽기 
	StringTokenizer st; // 구분자를 선언한다
	MembershipChecker(){
		try {
			BufferedReader br = new BufferedReader(new FileReader(dataFile)); // 파일 리더 선언 
			while ((readData = br.readLine()) != null) {
				// 끝까지 읽는다.
				st = new StringTokenizer(readData, "//"); // 구분자는 //
				String userId= st.nextToken(); // 첫번째 받아주기 
				String userPassword = st.nextToken(); //  구분자 후 두번째
				String userName = st.nextToken(); // 구분자 후 세번째
				String userPhone = st.nextToken(); // 구분자 후 네번째
				MemberShipS mem = new MemberShipS(userId, userPassword, userName, userPhone);
				userInfor.add(mem);
			}
			br.close(); // 버퍼 닫기
		} catch (IOException error) {
			System.out.println(error.getMessage());
		}
	}
	boolean check(String _id, String _pass, String _name, String _Phone){ // 가입하려는 회원정보가 userInfor에 없으면 true를 있으면 false를 리턴
		boolean flag = true; 
		for(int i = 0; i<userInfor.size(); i++) {
			if(userInfor.get(i).ID.equals(_id) && userInfor.get(i).PW.equals(_pass) && userInfor.get(i).name.equals(_name) && userInfor.get(i).PN.equals(_Phone)) {
				flag = false;
			}
		}
		return flag;
	}
}
