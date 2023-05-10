import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;

public class Membership extends JFrame { //회원가입 프레임
	Operator mainOperator = new Operator();
	MyConnector connector = new MyConnector(mainOperator);
	JPanel jp = new JPanel(new FlowLayout());
	
	JLabel id = new JLabel("아이디");
	JTextField typeId = new JTextField("");
	
	JLabel pw = new JLabel("비밀번호");
	JPasswordField tpw = new JPasswordField();
	
	JLabel cpw = new JLabel("비밀번호재확인");
	JPasswordField ctpw = new JPasswordField();
	
	JLabel n = new JLabel("이름");
	JTextField name = new JTextField();
	
	JLabel num = new JLabel("연락처");
	JTextField pn = new JTextField();
	
	JButton art = new JButton("중복확인");
	JButton completion = new JButton("확인");
	JButton cancel = new JButton("취소");
	
	public Membership() {
		setTitle("회원가입");
		MyActionListener MS = new MyActionListener();
		//사이즈 조정
		id.setPreferredSize(new Dimension(90, 30));
		typeId.setPreferredSize(new Dimension(200,30));
		art.setPreferredSize(new Dimension(100,30));
		pw.setPreferredSize(new Dimension(90,30));
		tpw.setPreferredSize(new Dimension(300,30));		
		cpw.setPreferredSize(new Dimension(90,30));
		ctpw.setPreferredSize(new Dimension(300,30));		
		n.setPreferredSize(new Dimension(90,30));
		name.setPreferredSize(new Dimension(300,30));		
		num.setPreferredSize(new Dimension(90,30));
		pn.setPreferredSize(new Dimension(300,30));		
		completion.setPreferredSize(new Dimension(200,30));
		cancel.setPreferredSize(new Dimension(200,30));
		//JPanel에 추가
		jp.add(id);
		jp.add(typeId);
		jp.add(art);
		jp.add(pw);
		jp.add(tpw);
		jp.add(cpw);
		jp.add(ctpw);
		jp.add(n);
		jp.add(name);
		jp.add(num);
		jp.add(pn);
		jp.add(completion);
		jp.add(cancel);
		
		setContentPane(jp);
		art.addActionListener(MS);
		completion.addActionListener(MS);
		cancel.addActionListener(MS);
		setResizable(false);
		setSize(450,270);
		
		 Dimension frameSize = this.getSize();
	 	 Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	     this.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
	     setVisible(true);
	}
	class MyActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JButton b = (JButton)e.getSource();
			if(b.getText().equals("중복확인")) { // 중복확인시 아이디 보내서 true값을 받으면 성공 프레임 띄움 false 값이면 실패 프레임
				String ID = typeId.getText();
				if(connector.sendLoginID(ID)) {
					Congratulation cg = new Congratulation(1);
				}
				else {
					Fail f = new Fail(2);
				}
			}
			else if (b.getText().equals("확인")) { // 회원가입 진행시 
				String PW = "";
				String CPW = "";
				String id = typeId.getText();
				
				for(int i=0; i<tpw.getPassword().length; i++) {
					PW = PW + tpw.getPassword()[i]; //비밀번호 받고
				}
				
				for(int i=0; i<ctpw.getPassword().length; i++) {
					CPW = CPW + ctpw.getPassword()[i]; //비밀번호 재입력 받아서
				}
				
				if(PW.length() != 0 && CPW.length() != 0) { // 비밀번호와 재입력이 같으면
					if(PW.equals(CPW) && connector.sendLoginID(id)) { //중복확인 결과와 비밀번호와 재입력이 같으면 
						String nm = name.getText();
						String Phone = pn.getText();
						if(connector.sendMember(id, PW, nm, Phone)) { // 회원가입 진행해서 중복이 없으면
							if(connector.SaveMember(id, PW, nm, Phone)) { // 저장하고 저장이 성공하면 
								Congratulation Cgl = new Congratulation(0); //성공
								dispose();
							}
						}
						else {
							Fail f = new Fail(0); // 실패
							dispose();
						}
					}
					else { //중복확인 결과와 비밀번호와 재입력이 다르면 실패
						Fail f = new Fail(0); 
						dispose();
					}
				}
				else { // 비밀번호와 재입력이 다르면 실패
					Fail f = new Fail(1);
				}
			}
			else if (b.getText().equals("취소")) { //회원가입 취소
				dispose(); 
			}
		}
	}
}
