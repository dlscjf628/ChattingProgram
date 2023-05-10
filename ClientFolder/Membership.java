import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;

public class Membership extends JFrame { //ȸ������ ������
	Operator mainOperator = new Operator();
	MyConnector connector = new MyConnector(mainOperator);
	JPanel jp = new JPanel(new FlowLayout());
	
	JLabel id = new JLabel("���̵�");
	JTextField typeId = new JTextField("");
	
	JLabel pw = new JLabel("��й�ȣ");
	JPasswordField tpw = new JPasswordField();
	
	JLabel cpw = new JLabel("��й�ȣ��Ȯ��");
	JPasswordField ctpw = new JPasswordField();
	
	JLabel n = new JLabel("�̸�");
	JTextField name = new JTextField();
	
	JLabel num = new JLabel("����ó");
	JTextField pn = new JTextField();
	
	JButton art = new JButton("�ߺ�Ȯ��");
	JButton completion = new JButton("Ȯ��");
	JButton cancel = new JButton("���");
	
	public Membership() {
		setTitle("ȸ������");
		MyActionListener MS = new MyActionListener();
		//������ ����
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
		//JPanel�� �߰�
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
			if(b.getText().equals("�ߺ�Ȯ��")) { // �ߺ�Ȯ�ν� ���̵� ������ true���� ������ ���� ������ ��� false ���̸� ���� ������
				String ID = typeId.getText();
				if(connector.sendLoginID(ID)) {
					Congratulation cg = new Congratulation(1);
				}
				else {
					Fail f = new Fail(2);
				}
			}
			else if (b.getText().equals("Ȯ��")) { // ȸ������ ����� 
				String PW = "";
				String CPW = "";
				String id = typeId.getText();
				
				for(int i=0; i<tpw.getPassword().length; i++) {
					PW = PW + tpw.getPassword()[i]; //��й�ȣ �ް�
				}
				
				for(int i=0; i<ctpw.getPassword().length; i++) {
					CPW = CPW + ctpw.getPassword()[i]; //��й�ȣ ���Է� �޾Ƽ�
				}
				
				if(PW.length() != 0 && CPW.length() != 0) { // ��й�ȣ�� ���Է��� ������
					if(PW.equals(CPW) && connector.sendLoginID(id)) { //�ߺ�Ȯ�� ����� ��й�ȣ�� ���Է��� ������ 
						String nm = name.getText();
						String Phone = pn.getText();
						if(connector.sendMember(id, PW, nm, Phone)) { // ȸ������ �����ؼ� �ߺ��� ������
							if(connector.SaveMember(id, PW, nm, Phone)) { // �����ϰ� ������ �����ϸ� 
								Congratulation Cgl = new Congratulation(0); //����
								dispose();
							}
						}
						else {
							Fail f = new Fail(0); // ����
							dispose();
						}
					}
					else { //�ߺ�Ȯ�� ����� ��й�ȣ�� ���Է��� �ٸ��� ����
						Fail f = new Fail(0); 
						dispose();
					}
				}
				else { // ��й�ȣ�� ���Է��� �ٸ��� ����
					Fail f = new Fail(1);
				}
			}
			else if (b.getText().equals("���")) { //ȸ������ ���
				dispose(); 
			}
		}
	}
}
