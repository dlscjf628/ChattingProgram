import java.io.*;
import java.io.IOException;
import java.net.*;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Savemem { // ȸ�������� �����ϱ� ���� ����
	File file = new File("membership.txt"); //ȸ�������� �����ϱ�����
	File filse = new File("UserID.txt"); // id�� �����ϱ�����
	File filses = new File("users.txt"); // id�� pw�� �����ϱ�����
	int Savemem(String _id, String _pw, String _name, String _phone) {
		
		String MBS =_id + "//" + _pw + "//" + _name + "//" + _phone; // ȸ�������� ����
		String UI = _id;
		String U = _id + "//" + _pw;
		System.out.println(MBS);
		try {
			BufferedWriter buffer = new BufferedWriter(new FileWriter(file, true)); // ���� ����
			BufferedWriter buffers = new BufferedWriter(new FileWriter(filse, true));
			BufferedWriter bufferse = new BufferedWriter(new FileWriter(filses, true));
			
			buffer.write(MBS); //���ۿ� ���� 
			buffer.flush(); // ����ϰ�
			buffer.newLine(); // ���ο� �� �����
			buffer.close(); // ���� �ݱ�
			
			buffers.write(UI);
			buffers.flush();
			buffers.newLine();
			buffers.close();
			
			bufferse.write(U);
			bufferse.flush();
			bufferse.newLine();
			bufferse.close();
			
		}catch (Exception e){
			System.out.println("���� �Է¿���");
			return 1;
		}
		return 0;
	}
	
	boolean savecheck(String _id, String _pw, String _name, String _phone) {
		int S = Savemem(_id, _pw, _name, _phone); // ������ �Ҷ� ������ 0�� return ���� 
		if (S == 0) return true; // �����ϸ� true����
		else return false; // �����ϸ� false���� return��
	}
 }
