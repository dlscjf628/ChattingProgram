import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;

public class Fail extends JFrame{ // ���� ���� �������� ���� ���� ����
	JPanel FJ = new JPanel(new FlowLayout());
	JLabel fail = new JLabel("ȸ�����Կ� �����߽��ϴ�.");
	JLabel re = new JLabel("�ٽ� �����ϼ���.");
	JButton fB = new JButton("Ȯ��");
	JLabel msg = new JLabel("�޼����� �Է��ϼ���.");
	JLabel in = new JLabel("��� ������ �Է��ϼ���.");
	JLabel at = new JLabel("�ߺ��� ���̵� �Դϴ�.");
	JLabel fa = new JLabel("���̵�� ��й�ȣ�� Ȯ���ϼ���.");
	public Fail(int a) {
		if (a == 0) { // ȸ������ ����
			setTitle("ȸ������ ����");
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
		else if (a == 1) { // ��� ������ �Էµ��� �ʾ�����
			setTitle("ȸ������ ����");
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
		else if (a == 2) { // ���̵� �ߺ��϶�
			setTitle("ID �ߺ�");
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
		else if (a == 3) { // �α��� ����
			setTitle("�α��� ����");
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
		else if (a == 4) { // �޼��� �Է�
			setTitle("�޼����Է�");
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
			if(b.getText().equals("Ȯ��")) {
				dispose();
			}
		}
	}
}
