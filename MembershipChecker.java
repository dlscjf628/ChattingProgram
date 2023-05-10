import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class MembershipChecker { // ȸ�������� �ϱ� ����
	ArrayList<MemberShipS> userInfor = new ArrayList<MemberShipS>(); // �ؽ�Ʈ���Ͽ��� �о�� ������ �����ϴ� ArrayList
	File dataFile = new File("membership.txt"); // ȸ�������� ����Ǿ��ִ� �ؽ�Ʈ ����
	String readData; // readData �б� 
	StringTokenizer st; // �����ڸ� �����Ѵ�
	MembershipChecker(){
		try {
			BufferedReader br = new BufferedReader(new FileReader(dataFile)); // ���� ���� ���� 
			while ((readData = br.readLine()) != null) {
				// ������ �д´�.
				st = new StringTokenizer(readData, "//"); // �����ڴ� //
				String userId= st.nextToken(); // ù��° �޾��ֱ� 
				String userPassword = st.nextToken(); //  ������ �� �ι�°
				String userName = st.nextToken(); // ������ �� ����°
				String userPhone = st.nextToken(); // ������ �� �׹�°
				MemberShipS mem = new MemberShipS(userId, userPassword, userName, userPhone);
				userInfor.add(mem);
			}
			br.close(); // ���� �ݱ�
		} catch (IOException error) {
			System.out.println(error.getMessage());
		}
	}
	boolean check(String _id, String _pass, String _name, String _Phone){ // �����Ϸ��� ȸ�������� userInfor�� ������ true�� ������ false�� ����
		boolean flag = true; 
		for(int i = 0; i<userInfor.size(); i++) {
			if(userInfor.get(i).ID.equals(_id) && userInfor.get(i).PW.equals(_pass) && userInfor.get(i).name.equals(_name) && userInfor.get(i).PN.equals(_Phone)) {
				flag = false;
			}
		}
		return flag;
	}
}
