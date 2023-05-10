import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;


public class LoginFrame extends JFrame {
	JPanel panel = new JPanel(new FlowLayout()); // ���̾ƿ� ���� 
	JButton enter = new JButton("Login"); // Button enter ���� 
	JButton cancel = new JButton("exit"); // �����
	JButton Team = new JButton("ȸ������");
	JTextField typeId = new JTextField(); // id ������  ����
	JPasswordField typePassword = new JPasswordField(); // password ������ ���� ������ ** < ó�� ����
	JLabel id = new JLabel("I   D"); // �� type id
	JLabel password = new JLabel("Password"); // �� type password
	MyConnector connector;
	Operator mainOperator = null;
	//Membership MBS = null;
	
	public LoginFrame(Operator _o) {
		mainOperator = _o;
		connector = _o.connector;
		MyActionListener ml = new MyActionListener();
		setTitle("ä�����α׷� �α���");
		
		id.setPreferredSize(new Dimension(70, 30));
		typeId.setPreferredSize(new Dimension(300, 30));
		password.setPreferredSize(new Dimension(70, 30));
		typePassword.setPreferredSize(new Dimension(300, 30));
		enter.setPreferredSize(new Dimension(120, 30));
		Team.setPreferredSize(new Dimension(120,30));
		cancel.setPreferredSize(new Dimension(120, 30));
		
		panel.add(id); //  ID �߰� 
		panel.add(typeId); // �Էµ� ID �߰� 
		panel.add(password); // PASSWORD �߰� 
		panel.add(typePassword); // �Էµ� PASSWORD �߰� 
		panel.add(enter);
		panel.add(Team);
		panel.add(cancel);
		setContentPane(panel);
		enter.addActionListener(ml); // Login ��ư�� �̺�Ʈ ������ �߰� 
		Team.addActionListener(ml);
		cancel.addActionListener(ml); // Cancel ��ư�� �̺�Ʈ ������ �߰�

		setResizable(false);
		setSize(400, 150);
		
		//�α��� â�� ȭ�� �߾ӿ� ��ġ��Ű��...
  	    Dimension frameSize = this.getSize();   //������ ����� ��������
 	    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);

		setVisible(true);
	}
	
	class MyActionListener implements ActionListener  {
		  //�̺�Ʈ�� �߻���Ų ������Ʈ(�ҽ�)
		public void actionPerformed(ActionEvent e) {
			JButton b =  (JButton)e.getSource();
			if (b.getText().equals("Login")) {	// �α��ι�ư�� ������...
				//Password ������Ʈ���� ���ڿ� �о���� 1
				String ID = "";
				String pw = "";
				for(int i=0; i<typePassword.getPassword().length; i++) {
					pw = pw+ typePassword.getPassword()[i];
				} // ��й�ȣ ������
				ID = typeId.getText(); // ID ������
				System.out.println(typeId.getText()+ "//" + pw);
				if(ID.length() != 0 && pw.length() != 0) { // ���̰� 0�� �ƴϰ� 
					if(connector.sendLoginInformation(typeId.getText(), pw)) { // �α��ο� �����ߴٸ�
						mainOperator.mf.setVisible(true); // ���������� ����
						connector.MGL.start(); // �����κ��� �޼����� �ޱ����� �׻� ��� ���ѳ���
						connector.sendmge(typeId.getText()); // ID�� ���� list�� �߰�
						dispose();
					}else { // �α��� ���и� ���� ������ ����
						Fail f = new Fail(3);
					}
				}
				else { // ���̰� 0�̸� ���� ������ ����
					Fail f = new Fail(3);
				}
								
				
			}else if (b.getText().equals("exit")) { //����� �ý��� ����
				connector.Loginexit();
				System.exit(0);
			}
			else if (b.getText().equals("ȸ������")) {
				new Membership(); // ȸ������ â ����
			}
		}
	}
	
	
}