import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;

public class Congratulation extends JFrame { // ������ �����ӵ� 
	JPanel JC = new JPanel(new FlowLayout());
	JLabel Cg = new JLabel("ȸ�����Կ� �����ϼ̽��ϴ�.");
	JLabel ar = new JLabel("��� ������ ID �Դϴ�.");
	JButton com = new JButton("Ȯ��");

	public Congratulation(int a) {
		if (a == 0) { // ȸ������ ����
			setTitle("ȸ������ ����");
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
		else if(a == 1) { // ���̵� �ߺ��� ������
			setTitle("ID �ߺ�");
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
			if (b.getText().equals("Ȯ��")) {
				dispose();
			}
		}
	}
}
