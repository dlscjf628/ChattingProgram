import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;

public class Fail extends JFrame{ // 여러 실패 프레임을 띄우기 위해 선언
	JPanel FJ = new JPanel(new FlowLayout());
	JLabel fail = new JLabel("회원가입에 실패했습니다.");
	JLabel re = new JLabel("다시 진행하세요.");
	JButton fB = new JButton("확인");
	JLabel msg = new JLabel("메세지를 입력하세요.");
	JLabel in = new JLabel("모든 정보를 입력하세요.");
	JLabel at = new JLabel("중복된 아이디 입니다.");
	JLabel fa = new JLabel("아이디와 비밀번호를 확인하세요.");
	public Fail(int a) {
		if (a == 0) { // 회원가입 실패
			setTitle("회원가입 실패");
			MyActionListener fails = new MyActionListener();
			fail.setPreferredSize(new Dimension(250,70));
			re.setPreferredSize(new Dimension(250,70));
			fB.setPreferredSize(new Dimension(250,50));
			FJ.add(fail);
			FJ.add(re);
			FJ.add(fB);
			setContentPane(FJ);
			setResizable(false);
			fB.addActionListener(fails);
			setSize(320,260);
			Dimension frameSize = this.getSize();
		 	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		    this.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
		    setVisible(true);
		}
		else if (a == 1) { // 모든 정보가 입력되지 않았을때
			setTitle("회원가입 실패");
			MyActionListener fails = new MyActionListener();
			in.setPreferredSize(new Dimension(250,70));
			re.setPreferredSize(new Dimension(250,70));
			fB.setPreferredSize(new Dimension(250,50));
			FJ.add(in);
			FJ.add(re);
			FJ.add(fB);
			setContentPane(FJ);
			setResizable(false);
			fB.addActionListener(fails);
			setSize(320,260);
			Dimension frameSize = this.getSize();
		 	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		    this.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
		    setVisible(true);
		}
		else if (a == 2) { // 아이디가 중복일때
			setTitle("ID 중복");
			MyActionListener fails = new MyActionListener();
			at.setPreferredSize(new Dimension(250,70));
			re.setPreferredSize(new Dimension(250,70));
			fB.setPreferredSize(new Dimension(250,50));
			FJ.add(at);
			FJ.add(re);
			FJ.add(fB);
			setContentPane(FJ);
			setResizable(false);
			fB.addActionListener(fails);
			setSize(320,260);
			Dimension frameSize = this.getSize();
		 	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		    this.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
		    setVisible(true);
		}
		else if (a == 3) { // 로그인 실패
			setTitle("로그인 실패");
			MyActionListener fails = new MyActionListener();
			fa.setPreferredSize(new Dimension(300,70));
			fB.setPreferredSize(new Dimension(300,50));
			FJ.add(fa);
			FJ.add(fB);
			setContentPane(FJ);
			setResizable(false);
			fB.addActionListener(fails);
			setSize(350,200);
			Dimension frameSize = this.getSize();
		 	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		    this.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
		    setVisible(true);
		}
		else if (a == 4) { // 메세지 입력
			setTitle("메세지입력");
			MyActionListener fails = new MyActionListener();
			msg.setPreferredSize(new Dimension(300,70));
			fB.setPreferredSize(new Dimension(300,50));
			FJ.add(msg);
			FJ.add(fB);
			setContentPane(FJ);
			setResizable(false);
			fB.addActionListener(fails);
			setSize(350,200);
			Dimension frameSize = this.getSize();
		 	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		    this.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
		    setVisible(true);
		}
	}
	class MyActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JButton b = (JButton)e.getSource();
			if(b.getText().equals("확인")) {
				dispose();
			}
		}
	}
}
