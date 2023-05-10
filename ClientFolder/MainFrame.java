import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.table.*;
import javax.swing.tree.*;


public class MainFrame extends JFrame{	 
	Operator mainOperator = null;
	MyConnector connector;
	
	MyActionListener MS = new MyActionListener(); // ��ư �̺�Ʈ
	MyMouseAdapterLogOut LO = new MyMouseAdapterLogOut(); // ���콺 �̺�Ʈ
	MyMouseAdapterexit ex = new MyMouseAdapterexit(); // ���콺 �̺�Ʈ
	JPanel p1 = new JPanel();
    //�޴� ���ۿ�
	JMenuBar mb = new JMenuBar();
	JMenu homeMenu = new JMenu("Menu");
	JMenuItem LogOut = new JMenuItem("�α׾ƿ�");
	JMenuItem exitMI = new JMenuItem("����");		 
	
	JLabel connect = new JLabel("�����ο�");
	DefaultListModel model = new DefaultListModel();
	JList list= new JList(model);
	JButton send = new JButton("send");
	JTextField chat = new JTextField();
	JTextArea ch = new JTextArea();
	// ��ũ�ѹ� ���
	JScrollPane sp = new JScrollPane(ch,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	JScrollPane LP = new JScrollPane(list,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	GridBagConstraints gbc = new GridBagConstraints();
	GridBagLayout grid = new GridBagLayout(); // ��ǥ���� �����Ͽ� ��ġ�ϱ� ���� GridBagLayout ���

	
	public MainFrame(Operator _o) {
		mainOperator = _o;
		connector = _o.connector;
		
		setTitle("ä�����α׷�");
		p1.setLayout(grid);
		gbc.fill = GridBagConstraints.BOTH; //x,y�� �� ä��
		gbc.weightx = 1.0; // x ����
        gbc.weighty = 1.0; // y ����
		
		ch.selectAll(); // ��ü������ �ؽ�Ʈ�� ä���
		ch.setEditable(false); // �����Է� �Ұ�
		ch.setBackground(Color.DARK_GRAY); // ����
		ch.setFont(new Font("����",Font.PLAIN,15)); // ��Ʈ
		ch.setForeground(Color.white); // ���ڻ�
		chat.setFont(new Font("����",Font.PLAIN,15));
		//homeMenu �߰� 
		homeMenu.addSeparator();
		homeMenu.add(LogOut);
		homeMenu.add(exitMI);
		mb.add(homeMenu);
		setJMenuBar(mb);
		
		// make(������, x��ǥ, y��ǥ, ������, ������)
		make(sp, 0, 0, 2, 3);
        gbc.weightx = 0.01; // ���� ����
        gbc.weighty = 0.01; // ���� ����
		make(connect, 2, 0, 1, 1);
        gbc.weightx = 0.01; // ���� ����
        gbc.weighty = 1.0; // ���� ����
		make(LP, 2, 1, 1, 2);
		gbc.weightx = 0.05; // ���� ����
        gbc.weighty = 0.05; // ���� ����
        make(chat, 0, 3, 1, 1);
        make(send, 2, 3, 1, 1);
		
		p1.add(sp);
		p1.add(connect);
		p1.add(LP);
		p1.add(chat);
		p1.add(send);
		
		send.addActionListener(MS);
		exitMI.addMouseListener(ex);
		LogOut.addMouseListener(LO);
		setContentPane(p1);
		setResizable(false);
		
		Dimension frameSize = this.getSize();   //������ ����� ��������
 	    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(900, 700);
	}
	class MyActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JButton b = (JButton)e.getSource();
			if(b.getText().equals("send")) { // ������ ��ư�� ������
				String textmsg = chat.getText(); // �ؽ�Ʈ �ʵ� ���ڿ��� �޾ƿͼ�
				if(textmsg.length() != 0 ) { // ���̰� 0�� �ƴϸ� ���� �� �ʱ�ȭ
					connector.sendmge(mainOperator.lf.typeId.getText(), textmsg);
					chat.setText("");
				}
				else {
					Fail f = new Fail(4); // 0�̸� �޼��� �Է��϶�� ������ ���
				}
			}
		}
	}
	class MyMouseAdapterLogOut extends MouseAdapter {
		public void mousePressed(MouseEvent e) { // �α׾ƿ� ������ ID �޾ƿͼ� list �����ϱ����� ������ ������ ���ο� �α���â ���
			connector.sendID(mainOperator.lf.typeId.getText());
			dispose();
			Operator operator = new Operator();
			operator.connector = new MyConnector(operator);
			operator.mf = new MainFrame(operator);
			operator.lf = new LoginFrame(operator);	
		}
	}
	class MyMouseAdapterexit extends MouseAdapter {
		public void mousePressed(MouseEvent e) {
			System.exit(0); // ����� �ý��� �����Ŵ
		}
	}
	public void make(JComponent c, int x, int y, int w, int h) { //��ư�̳� �ؽ�Ʈ�ʵ� �� ������������ ũ��������
		gbc.gridx = x;
		gbc.gridy = y;
		gbc.gridwidth = w;
		gbc.gridheight = h;
		grid.setConstraints(c, gbc);
	}
	
}