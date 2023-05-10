import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

class IDChecker { // �ߺ�Ȯ���� ���� ����
	ArrayList<UserID> userInfor = new ArrayList<UserID>(); //�ؽ�Ʈ���Ͽ��� �о�� id���� �����ϴ� �迭
	File dataFile = new File("UserID.txt"); // userid�� �����ϴ� �ؽ�Ʈ����
	String readData; // readData �б� 
	StringTokenizer st; // �����ڸ� �����Ѵ�
	IDChecker(){
		try {
			BufferedReader br = new BufferedReader(new FileReader(dataFile)); // ���� ���� ���� 
			while ((readData = br.readLine()) != null) { // ������ �б�
				UserID user = new UserID(readData);
				userInfor.add(user);
			}
			br.close(); // ���� �ݱ�
		} catch (IOException error) {
			System.out.println(error.getMessage());
		}
	}
	
	boolean check(String _id){ // �ߺ�Ȯ���� �ϴ� �뵵, userInfor�迭�� ���� _id ���� ������ false, ������ true ����
		boolean flag = true;
		for(int i = 0; i<userInfor.size(); i++) {
			if(userInfor.get(i).Id.equals(_id)) {
				flag = false;
			}
		}
		return flag;
	}
}
