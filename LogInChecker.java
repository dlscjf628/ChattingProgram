import java.io.*;
import java.io.IOException;
import java.net.*;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.StringTokenizer;

class LogInChecker{ // �α����� �ϱ����� ������
	ArrayList<User> userInfor = new ArrayList<User>(); //�ؽ�Ʈ���Ͽ��� �о�� id�� pw�� �����ϱ����� ���� ArrayList
	File dataFile = new File("users.txt"); // ���ԵǾ��ִ� id�� pw�� ����Ǿ��ִ� �ؽ�Ʈ����
	String readData; // readData �б� 
	StringTokenizer st; // �����ڸ� �����Ѵ�
	LogInChecker(){
		try {
			BufferedReader br = new BufferedReader(new FileReader(dataFile)); // ���� ���� ���� 
			while ((readData = br.readLine()) != null) {
				// ������ �д´�.
				st = new StringTokenizer(readData, "//"); // �����ڴ� //
				String userId= st.nextToken(); // ù��° �޾��ֱ� 
				String userPassword = st.nextToken(); //  ������ �� �ι�°
				User user = new User(userId, userPassword);
				userInfor.add(user);
			}
			br.close(); // ���� �ݱ�
		} catch (IOException error) {
			System.out.println(error.getMessage());
		}
	}

	boolean check(String _id, String _pass){ // �α����Ϸ��� id�� pw�� �޾� userInfor�� ����Ǿ������� true�� ������ false�� ������
		boolean flag = false;
		for(int i = 0; i<userInfor.size(); i++) {
			if(userInfor.get(i).id.equals(_id) && userInfor.get(i).pass.equals(_pass)) {
				flag = true;
			}
		}
		return flag;
	}
}