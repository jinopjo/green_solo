package screen;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import sql.MemberDAO;
import sql.MemberVo;


public class Login extends WindowAdapter {
	static ArrayList<MemberVo> list;
	static MemberDAO dao = new MemberDAO();
	static TextField tf_id, tf_pwd, tf_msg;
	
	public static void employee() {
		Frame f = new Frame("직원");
		Button b = new Button("확인");
		
		f.setSize(400,600);
		f.setLayout(null);
		f.setVisible(true);
		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		
		f.getSize();
		Dimension dimen = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension dimen2 = f.getSize();
		
		int x = (dimen.width - dimen2.width) / 2;
		int y = (dimen.height - dimen2.height) / 2;
		f.setLocation(x, y);
		
		tf_msg = new TextField();
		tf_msg.setSize(150,20);
		tf_msg.setLocation(100, 250);
		tf_msg.setEditable(false);
		
		tf_id = new TextField();
		tf_id.setSize(150, 20);
		tf_id.setLocation(100, 200);
		
		Label l_id = new Label("ID", Label.RIGHT);
		l_id.setSize(20,20);
		l_id.setLocation(80, 200);
		
		tf_pwd = new TextField();
		tf_pwd.setSize(150, 20);
		tf_pwd.setLocation(100, 225);
		tf_pwd.setEchoChar('*');
		
		Label l_pwd = new Label("비밀번호", Label.RIGHT);
		l_pwd.setSize(55,20);
		l_pwd.setLocation(45, 225);
		
		b.setSize(45, 45);
		b.setLocation(260, 198);
		
		Button b_main = new Button("Main");
		b_main.setSize(350, 100);
		b_main.setLocation(25,40);
		b_main.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.main(null);
				f.dispose();
			}
		});
		
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				tf_msg.setText("");
				if (tf_id.getText().equals("")) {
					tf_msg.setText("ID를 입력하세요.");
				} else if (tf_pwd.getText().equals("")) {
					tf_msg.setText("Password를 입력하세요.");
				}
				
				list = dao.list(tf_id.getText());
				
				for(int i=0;i<list.size();i++) {
					MemberVo data = (MemberVo) list.get(i);
					String id = data.getId();
					String pwd = data.getPwd();
					System.out.println(id + " : " + pwd); 
					
					if (id.equals(tf_id.getText())
							&& pwd.equals(tf_pwd.getText())) {
						tf_msg.setText("로그인 성공");
						Employee.start();
						f.dispose();
					} else {
						tf_msg.setText("로그인 실패");
					}
				}
			}
		});
		
		f.add(tf_id);
		f.add(l_id);
		f.add(tf_pwd);
		f.add(l_pwd);
		f.add(b);
		f.add(b_main);
		f.add(tf_msg);
	}
		
	
	public static void employer() {
		Frame f = new Frame("매니저");
		Button b = new Button("확인");
		
		f.setSize(400,600);
		f.setLayout(null);
		f.setVisible(true);
		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		
		f.getSize();
		Dimension dimen = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension dimen2 = f.getSize();
		
		int x = (dimen.width - dimen2.width) / 2;
		int y = (dimen.height - dimen2.height) / 2;
		f.setLocation(x, y);
		
		tf_msg = new TextField();
		tf_msg.setSize(150,20);
		tf_msg.setLocation(100, 250);
		tf_msg.setEditable(false);
		
		tf_id = new TextField();
		tf_id.setSize(150, 20);
		tf_id.setLocation(100, 200);
		
		Label l_id = new Label("ID", Label.RIGHT);
		l_id.setSize(20,20);
		l_id.setLocation(80, 200);
		
		tf_pwd = new TextField();
		tf_pwd.setSize(150, 20);
		tf_pwd.setLocation(100, 225);
		tf_pwd.setEchoChar('*');
		
		Label l_pwd = new Label("비밀번호", Label.RIGHT);
		l_pwd.setSize(55,20);
		l_pwd.setLocation(45, 225);
		
		b.setSize(45, 45);
		b.setLocation(260, 198);
		
		Button b_main = new Button("Main");
		b_main.setSize(350, 100);
		b_main.setLocation(25,40);
		b_main.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.main(null);
				f.dispose();
			}
		});
		
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				tf_msg.setText("");
				if (tf_id.getText().equals("")) {
					tf_msg.setText("ID를 입력하세요.");
				} else if (tf_pwd.getText().equals("")) {
					tf_msg.setText("Password를 입력하세요.");
				}
				
				list = dao.list_admin(tf_id.getText());
				
				for(int i=0;i<list.size();i++) {
					MemberVo data = (MemberVo) list.get(i);
					String id = data.getId();
					String pwd = data.getPwd();
					System.out.println(id + " : " + pwd); 
					
					if (id.equals(tf_id.getText())
							&& pwd.equals(tf_pwd.getText())) {
						tf_msg.setText("로그인 성공");
						Manager.start();
						f.dispose();
					} else {
						tf_msg.setText("로그인 실패");
					}
				}
			}
		});
		
		f.add(tf_id);
		f.add(l_id);
		f.add(tf_pwd);
		f.add(l_pwd);
		f.add(b);
		f.add(b_main);
		f.add(tf_msg);
	}

	
}
