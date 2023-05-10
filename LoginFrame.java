import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;


public class LoginFrame extends JFrame {
	JPanel panel = new JPanel(new FlowLayout()); // 레이아웃 선언 
	JButton enter = new JButton("Login"); // Button enter 선언 
	JButton cancel = new JButton("exit"); // 종료용
	JButton Team = new JButton("회원가입");
	JTextField typeId = new JTextField(); // id 받은곳  선언
	JPasswordField typePassword = new JPasswordField(); // password 받은곳 선언 받으면 ** < 처럼 나옴
	JLabel id = new JLabel("I   D"); // 라벨 type id
	JLabel password = new JLabel("Password"); // 라벨 type password
	MyConnector connector;
	Operator mainOperator = null;
	//Membership MBS = null;
	
	public LoginFrame(Operator _o) {
		mainOperator = _o;
		connector = _o.connector;
		MyActionListener ml = new MyActionListener();
		setTitle("채팅프로그램 로그인");
		
		id.setPreferredSize(new Dimension(70, 30));
		typeId.setPreferredSize(new Dimension(300, 30));
		password.setPreferredSize(new Dimension(70, 30));
		typePassword.setPreferredSize(new Dimension(300, 30));
		enter.setPreferredSize(new Dimension(120, 30));
		Team.setPreferredSize(new Dimension(120,30));
		cancel.setPreferredSize(new Dimension(120, 30));
		
		panel.add(id); //  ID 추가 
		panel.add(typeId); // 입력된 ID 추가 
		panel.add(password); // PASSWORD 추가 
		panel.add(typePassword); // 입력된 PASSWORD 추가 
		panel.add(enter);
		panel.add(Team);
		panel.add(cancel);
		setContentPane(panel);
		enter.addActionListener(ml); // Login 버튼에 이벤트 리스너 추가 
		Team.addActionListener(ml);
		cancel.addActionListener(ml); // Cancel 버튼에 이벤트 리스너 추가

		setResizable(false);
		setSize(400, 150);
		
		//로그인 창을 화면 중앙에 배치시키기...
  	    Dimension frameSize = this.getSize();   //프레임 사이즈를 가져오기
 	    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);

		setVisible(true);
	}
	
	class MyActionListener implements ActionListener  {
		  //이벤트를 발생시킨 컴포넌트(소스)
		public void actionPerformed(ActionEvent e) {
			JButton b =  (JButton)e.getSource();
			if (b.getText().equals("Login")) {	// 로그인버튼을 누르면...
				//Password 컴포넌트에서 문자열 읽어오기 1
				String ID = "";
				String pw = "";
				for(int i=0; i<typePassword.getPassword().length; i++) {
					pw = pw+ typePassword.getPassword()[i];
				} // 비밀번호 따오기
				ID = typeId.getText(); // ID 따오기
				System.out.println(typeId.getText()+ "//" + pw);
				if(ID.length() != 0 && pw.length() != 0) { // 길이가 0이 아니고 
					if(connector.sendLoginInformation(typeId.getText(), pw)) { // 로그인에 성공했다면
						mainOperator.mf.setVisible(true); // 메인프레임 띄우고
						connector.MGL.start(); // 서버로부터 메세지를 받기위해 항상 대기 시켜놓고
						connector.sendmge(typeId.getText()); // ID를 보내 list에 추가
						dispose();
					}else { // 로그인 실패면 실패 프레임 띄우기
						Fail f = new Fail(3);
					}
				}
				else { // 길이가 0이면 실패 프래임 띄우기
					Fail f = new Fail(3);
				}
								
				
			}else if (b.getText().equals("exit")) { //종료시 시스템 종료
				connector.Loginexit();
				System.exit(0);
			}
			else if (b.getText().equals("회원가입")) {
				new Membership(); // 회원가입 창 열기
			}
		}
	}
	
	
}