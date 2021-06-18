package screen;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import sql.MemberVo;
import sql.MemberDAO;

public class Main extends WindowAdapter {

	ArrayList<MemberVo> list;
	MemberDAO dao = new MemberDAO();
	
	public void start() {
		Frame f_main = new Frame("Main");
		Button b_main, b_yee, b_yer;
		
		f_main.setSize(400,600);
		f_main.setLayout(null);
		f_main.setVisible(true);
		f_main.addWindowListener(this);
		
		f_main.getSize();
		Dimension dimen = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension dimen2 = f_main.getSize();
		
		int x = (dimen.width - dimen2.width) / 2;
		int y = (dimen.height - dimen2.height) / 2;
		f_main.setLocation(x, y);
		
		b_main = new Button("Main");
		b_main.setSize(350, 100);
		b_main.setLocation(25,40);
		
		b_yee = new Button("직원 로그인");
		b_yee.setSize(150,150);
		b_yee.setLocation(30,400);
		b_yee.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login.employee();
				f_main.dispose();
			}
		});
		
		b_yer = new Button("매니저 로그인");
		b_yer.setSize(150,150);
		b_yer.setLocation(230,400);
		b_yer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login.employer();
				f_main.dispose();
			}
		});
		
		f_main.add(b_main);
		f_main.add(b_yee);
		f_main.add(b_yer);
	}
	
	public static void main(String[] args) {
		Main s = new Main();
		s.start();
	}
	
	public void windowClosing(WindowEvent e) {
		System.exit(0);
	}

}
 