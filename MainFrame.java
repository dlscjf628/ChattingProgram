import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.table.*;
import javax.swing.tree.*;


public class MainFrame extends JFrame{	 
	Operator mainOperator = null;
	MyConnector connector;
	
	MyActionListener MS = new MyActionListener(); // 버튼 이벤트
	MyMouseAdapterLogOut LO = new MyMouseAdapterLogOut(); // 마우스 이벤트
	MyMouseAdapterexit ex = new MyMouseAdapterexit(); // 마우스 이벤트
	JPanel p1 = new JPanel();
    //메뉴 제작용
	JMenuBar mb = new JMenuBar();
	JMenu homeMenu = new JMenu("Menu");
	JMenuItem LogOut = new JMenuItem("로그아웃");
	JMenuItem exitMI = new JMenuItem("종료");		 
	
	JLabel connect = new JLabel("접속인원");
	DefaultListModel model = new DefaultListModel();
	JList list= new JList(model);
	JButton send = new JButton("send");
	JTextField chat = new JTextField();
	JTextArea ch = new JTextArea();
	// 스크롤바 사용
	JScrollPane sp = new JScrollPane(ch,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	JScrollPane LP = new JScrollPane(list,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	GridBagConstraints gbc = new GridBagConstraints();
	GridBagLayout grid = new GridBagLayout(); // 좌표값을 설정하여 배치하기 위해 GridBagLayout 사용

	
	public MainFrame(Operator _o) {
		mainOperator = _o;
		connector = _o.connector;
		
		setTitle("채팅프로그램");
		p1.setLayout(grid);
		gbc.fill = GridBagConstraints.BOTH; //x,y축 다 채움
		gbc.weightx = 1.0; // x 비율
        gbc.weighty = 1.0; // y 비율
		
		ch.selectAll(); // 전체영역을 텍스트로 채우기
		ch.setEditable(false); // 직접입력 불가
		ch.setBackground(Color.DARK_GRAY); // 배경색
		ch.setFont(new Font("굴림",Font.PLAIN,15)); // 폰트
		ch.setForeground(Color.white); // 글자색
		chat.setFont(new Font("굴림",Font.PLAIN,15));
		//homeMenu 추가 
		homeMenu.addSeparator();
		homeMenu.add(LogOut);
		homeMenu.add(exitMI);
		mb.add(homeMenu);
		setJMenuBar(mb);
		
		// make(컨텐츠, x좌표, y좌표, 가로폭, 세로폭)
		make(sp, 0, 0, 2, 3);
        gbc.weightx = 0.01; // 비율 수정
        gbc.weighty = 0.01; // 비율 수정
		make(connect, 2, 0, 1, 1);
        gbc.weightx = 0.01; // 비율 수정
        gbc.weighty = 1.0; // 비율 수정
		make(LP, 2, 1, 1, 2);
		gbc.weightx = 0.05; // 비율 수정
        gbc.weighty = 0.05; // 비율 수정
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
		
		Dimension frameSize = this.getSize();   //프레임 사이즈를 가져오기
 	    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(900, 700);
	}
	class MyActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JButton b = (JButton)e.getSource();
			if(b.getText().equals("send")) { // 보내기 버튼이 눌리면
				String textmsg = chat.getText(); // 텍스트 필드 문자열을 받아와서
				if(textmsg.length() != 0 ) { // 길이가 0이 아니면 전송 후 초기화
					connector.sendmge(mainOperator.lf.typeId.getText(), textmsg);
					chat.setText("");
				}
				else {
					Fail f = new Fail(4); // 0이면 메세지 입력하라고 프레임 띄움
				}
			}
		}
	}
	class MyMouseAdapterLogOut extends MouseAdapter {
		public void mousePressed(MouseEvent e) { // 로그아웃 눌리면 ID 받아와서 list 제거하기위해 서버로 보내고 새로운 로그인창 띄움
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
			System.exit(0); // 종료시 시스템 종료시킴
		}
	}
	public void make(JComponent c, int x, int y, int w, int h) { //버튼이나 텍스트필드 등 여러가지들의 크기조절용
		gbc.gridx = x;
		gbc.gridy = y;
		gbc.gridwidth = w;
		gbc.gridheight = h;
		grid.setConstraints(c, gbc);
	}
	
}