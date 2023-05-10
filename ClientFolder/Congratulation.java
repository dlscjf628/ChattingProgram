import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;

public class Congratulation extends JFrame { // 성공용 프레임들 
	JPanel JC = new JPanel(new FlowLayout());
	JLabel Cg = new JLabel("회원가입에 성공하셨습니다.");
	JLabel ar = new JLabel("사용 가능한 ID 입니다.");
	JButton com = new JButton("확인");

	public Congratulation(int a) {
		if (a == 0) { // 회원가입 성공
			setTitle("회원가입 성공");
			MyActionListener CGLT = new MyActionListener();
			Cg.setPreferredSize(new Dimension(250, 70));
			com.setPreferredSize(new Dimension(250, 50));
			JC.add(Cg);
			JC.add(com);
			setContentPane(JC);
			com.addActionListener(CGLT);
			setResizable(false);
			setSize(320, 200);
			Dimension frameSize = this.getSize();
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			this.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
			setVisible(true);
		}
		else if(a == 1) { // 아이디 중복이 없을때
			setTitle("ID 중복");
			MyActionListener CGLT = new MyActionListener();
			ar.setPreferredSize(new Dimension(250, 70));
			com.setPreferredSize(new Dimension(250, 50));
			JC.add(ar);
			JC.add(com);
			setContentPane(JC);
			com.addActionListener(CGLT);
			setResizable(false);
			setSize(320, 200);
			Dimension frameSize = this.getSize();
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			this.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
			setVisible(true);
		}
	}

	class MyActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JButton b = (JButton) e.getSource();
			if (b.getText().equals("확인")) {
				dispose();
			}
		}
	}
}
