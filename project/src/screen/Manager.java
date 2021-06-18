package screen;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.text.*;

import sql.MemberDAO;
import sql.MemberVo;

public class Manager extends Employee {
	static ArrayList<MemberVo> list, list2, list_em, list_log, list_ser, list_choice, list_pt, list_uppt;
	static MemberDAO dao = new MemberDAO();
	static int pay_m = 8800, pay_l = 10000, pay_t = 12000;
	static int allow_m = 4400, allow_l = 5000, allow_t = 6000;
	static String code2;
	static int cd2 = 1;
	
	static DecimalFormat df_pay = new DecimalFormat("###,###,###");
	
	public static void start() {
		Frame f = new Frame("매니저");
		Button b_main = new Button("Main");
		Button b_plus = new Button("직원 추가");
		Button b_mana = new Button("직원 수정");
		Label l_position, l_time, l_servicetime, l_pay;
		TextField tf_position, tf_time, tf_servicetime, tf_pay;
		
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
		
		b_main.setSize(350, 100);
		b_main.setLocation(25,40);
		b_main.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.main(null);
				f.dispose();
			}
		});
		
		b_mana.setSize(350, 50);
		b_mana.setLocation(25, 440);
		b_mana.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mana_employee();
			}
		});
		
		b_plus.setSize(350, 50);
		b_plus.setLocation(25,500);
		b_plus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				plus_employee();
			}
		});
		
		Choice names = new Choice();
		names.add("선택없음");
		int cd = 1;
		String tmp = "";
		String code = "";
		String name = "";
		String position = "";
		String time = "";
		String memo = "";
		while(true) {
			if(cd<10) tmp = "000" + cd;
			else if(cd<100) tmp = "00" + cd;
			else if(cd<1000) tmp = "0" + cd;
			else tmp = "" + cd;
			
			list = dao.list_employee(tmp);
			
			for(int i=0;i<list.size();i++) {
				MemberVo data = (MemberVo) list.get(i);
				code = data.getCode();
				name = data.getName();
				position = data.getPosition();
				time = data.getTime();
				memo = data.getMemo();
				
				names.add(name);
				
				System.out.println(code + ", " + name + ", " + position + ", " + time);
				
			}
			
			if(Integer.parseInt(code) < Integer.parseInt(tmp)) break;
			cd++;
			
			cd2 = cd;
		}
		
		names.setSize(100, 50);
		names.setLocation(60, 170);

		l_position = new Label("직급", Label.RIGHT);
		l_position.setSize(80, 20);
		l_position.setLocation(60, 230);
		
		
		l_time = new Label("근무타임", Label.RIGHT);
		l_time.setSize(80, 20);
		l_time.setLocation(60, 280);
		
		l_servicetime = new Label("총 근무시간", Label.RIGHT);
		l_servicetime.setSize(80, 20);
		l_servicetime.setLocation(60, 330);
		
		l_pay = new Label("실시간 월급", Label.RIGHT);
		l_pay.setSize(80, 20);
		l_pay.setLocation(60, 380);
		
		tf_position = new TextField();
		tf_position.setSize(100, 20);
		tf_position.setLocation(150, 230);
		tf_position.setEditable(false);
		
		tf_time = new TextField();
		tf_time.setSize(100, 20);
		tf_time.setLocation(150, 280);
		tf_time.setEditable(false);
		
		tf_servicetime = new TextField();
		tf_servicetime.setSize(100, 20);
		tf_servicetime.setLocation(150, 330);
		tf_servicetime.setEditable(false);
		
		tf_pay = new TextField();
		tf_pay.setSize(100, 20);
		tf_pay.setLocation(150, 380);
		tf_pay.setEditable(false);
		
		names.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				
				list_choice = dao.list_emp(names.getSelectedItem());
				
//				String code2 = "";
				String name2 = "";
				String position2 = "";
				String time2 = "";
				for(int i=0;i<list_choice.size();i++) {
					MemberVo data = (MemberVo) list_choice.get(i);
					code2 = data.getCode();
					name2 = data.getName();
					position2 = data.getPosition();
					time2 = data.getTime();
				}
				
				list_choice = dao.list_ser(names.getSelectedItem());
				
				String dutyhours = "";
				for(int i=0;i<list_choice.size();i++) {
					MemberVo data = (MemberVo) list_choice.get(i);
					code2 = data.getCode();
					name2 = data.getName();
					dutyhours = data.getDutyhours();
				}
				
				tf_position.setText(position2);
				tf_time.setText("" + time2);
				tf_servicetime.setText(dutyhours);
				String arr[] = tf_servicetime.getText().split(":");
				
				int pay = 0;
				if(tf_position.getText().equals("mate")) {
					pay = pay_m * Integer.parseInt(arr[0]) + (pay_m / 60) * Integer.parseInt(arr[1]);
				}
				else if(tf_position.getText().equals("leader")) {
					pay = pay_l * Integer.parseInt(arr[0]) + (pay_l / 60) * Integer.parseInt(arr[1]);
				}
				else if(tf_position.getText().equals("trainer")) {
					pay = pay_t * Integer.parseInt(arr[0]) + (pay_t / 60) * Integer.parseInt(arr[1]);
				}
				
//				int hst = Integer.parseInt(tf_time.getText().substring(0, 2)); // 초과수당을 위한 하루 근무 시간 계산
//				int mst = Integer.parseInt(tf_time.getText().substring(3, 5));
//				int het = Integer.parseInt(tf_time.getText().substring(6, 8));
//				int met = Integer.parseInt(tf_time.getText().substring(9));
//				int allowtime;
//				
//				if(met - het > 0) {
//					allowtime = het - hst + 1;
//				}
//				else allowtime = het - hst;
//				
//				if(diff_h > allowtime) {
//					if(tf_position.getText().equals("mate") || (met - mst) >= 0) {
//						pay += (diff_h - allowtime) * allow_m + (diff_m - (met - mst)) * (allow_m / 60);
//					}
//					else if(tf_position.getText().equals("mate") || (met - mst) < 0) {
//						pay += (diff_h - allowtime) * allow_m + (diff_m - (met - mst)) * (allow_m / 60);
//					}
//				}
				
				String spay = df_pay.format(pay);
				tf_pay.setText(spay + "원");
			}
		});
		

		        
		f.add(b_main);
		f.add(b_mana);
		f.add(b_plus);
		f.add(names);
		f.add(l_position);
		f.add(l_time);
		f.add(l_servicetime);
		f.add(l_pay);
		f.add(tf_position);
		f.add(tf_time);
		f.add(tf_servicetime);
		f.add(tf_pay);
	}
	
	public static void plus_employee() {
		Frame f_plus = new Frame("직원 추가");
		f_plus.setSize(400,300);
		f_plus.setLayout(null);
		f_plus.setVisible(true);
		f_plus.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				f_plus.dispose();
			}
		});
		
		f_plus.getSize();
		Dimension dimen = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension dimen2 = f_plus.getSize();
		
		int x = (dimen.width - dimen2.width) / 2;
		int y = (dimen.height - dimen2.height) / 2;
		f_plus.setLocation(x, y);
		
		Label l_name = new Label("이름", Label.RIGHT);
		Label l_time = new Label("타임", Label.RIGHT);
		Label l_id = new Label("ID", Label.RIGHT);
		Label l_pwd = new Label("비밀번호", Label.RIGHT);
		Label l_pwd2 = new Label("재입력", Label.RIGHT);
		
		TextField tf_name = new TextField();
		TextField tf_time = new TextField();
		TextField tf_id = new TextField();
		TextField tf_pwd = new TextField();
		TextField tf_pwd2 = new TextField();
		TextField tf_msg = new TextField();
		TextField tf_check = new TextField();
		
		Button b_new = new Button("생성");
		Button b_id = new Button("중복확인");
		Button b_clear = new Button("초기화");
		
		l_name.setSize(40, 20);
		l_name.setLocation(40, 60);
		tf_name.setSize(60, 20);
		tf_name.setLocation(90, 60);
		
		l_time.setSize(40, 20);
		l_time.setLocation(180, 60);
		tf_time.setSize(120, 20);
		tf_time.setLocation(230, 60);
		
		l_id.setSize(40, 20);
		l_id.setLocation(40, 100);
		tf_id.setSize(150, 20);
		tf_id.setLocation(90, 100);
		
		l_pwd.setSize(50, 20);
		l_pwd.setLocation(30, 130);
		tf_pwd.setSize(150, 20);
		tf_pwd.setLocation(90, 130);
		tf_pwd.setEchoChar('*');
		
		l_pwd2.setSize(40, 20);
		l_pwd2.setLocation(40, 160);
		tf_pwd2.setSize(150, 20);
		tf_pwd2.setLocation(90, 160);
		tf_pwd2.setEchoChar('*');
		
		tf_msg.setSize(150, 20);
		tf_msg.setLocation(90, 190);
		tf_msg.setEditable(false);
		
		tf_check.setSize(30, 20);
		tf_check.setLocation(330, 100);
		tf_check.setEditable(false);
		
		b_id.setSize(70, 20);
		b_id.setLocation(250, 100);
		b_id.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				list = dao.list(tf_id.getText());
				
				tf_check.setText("");
				String id="";
				for(int i=0;i<list.size();i++) {
					MemberVo data = (MemberVo) list.get(i);
					id = data.getId();
					String pwd = data.getPwd();
					
					System.out.println(id + " : " + pwd);
					
				}
				
				if(tf_id.getText().equals(id)) {
					tf_check.setText("불가");
				}
				else  {
					tf_check.setText("가능");
				}
			}
		});
		
		b_new.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getActionCommand().equals("생성")) {
					if((!tf_name.getText().equals("")) && (!tf_time.getText().equals("")) && (tf_check.getText().equals("가능")) &&
							(!tf_id.getText().equals("")) && (!tf_pwd.getText().equals("")) && tf_pwd2.getText().equals(tf_pwd.getText())) {
						
						list = dao.list_employee(code);
						
						for(int i=0;i<list.size();i++) {
							MemberVo data = (MemberVo) list.get(i);
							code = data.getCode();
							String name = data.getName();
							String position = data.getPosition();
							String time = data.getTime();
							String memo = data.getMemo();
							
//							System.out.println(code + ", " + name + ", " + position + ", " + time);
						}
						
						
						System.out.println(cd2);
						
						String tmp = "";
						if(cd2<10) tmp = "000" + cd2;
						else if(cd2<100) tmp = "00" + cd2;
						else if(cd2<1000) tmp = "0" + cd2;
						else tmp = "" + cd2;
						
						list_em = dao.insert_employee(tmp, tf_name.getText(), "mate", tf_time.getText()); // 직원 테이블에 저장
						
						for(int i=0;i<list_em.size();i++) {
							MemberVo data = (MemberVo) list_em.get(i);
							String code = data.getCode();
							String name = data.getName();
							String position = data.getPosition();
							String time = data.getTime();
							String memo = data.getMemo();
							
							System.out.println(code + ", " + name + ", " + position + ", " + time);
						}
						
						list_log = dao.insert_login(tmp, tf_id.getText(), tf_pwd.getText()); // 직원 로그인 테이블에 저장
						
						for(int i=0;i<list_log.size();i++) {
							MemberVo data = (MemberVo) list_log.get(i);
							String code = data.getCode();
							String id = data.getId();
							String pwd = data.getPwd();
							
							System.out.println(code + ", " + id + ", " + pwd);
						}
						
						list_ser = dao.insert_servicetime(tmp, tf_name.getText()); // 근무시간에 저장
						
						for(int i=0;i<list_log.size();i++) {
							MemberVo data = (MemberVo) list_log.get(i);
							String code = data.getCode();
							String name = data.getId();
							
							System.out.println(code + ", " + name);
						}
						
						cd2++;
					}
					else if(!tf_pwd.getText().equals(tf_pwd2.getText())) {
						tf_msg.setText("비밀번호가 일치하지 않습니다.");
					}
					else if((tf_name.getText().equals("")) || (tf_id.getText().equals("")) || (tf_time.getText().equals("")) ||
							(tf_pwd.getText().equals("")) || tf_pwd2.getText().equals("")) {
						tf_msg.setText("빈칸을 채워주세요");
					}
				}
			}
		});
		
//		b_clear.setSize(50, 50);
//		b_clear.setLocation(null);
		
		b_new.setSize(60, 60);
		b_new.setLocation(250, 130);
		
		f_plus.add(l_name);
		f_plus.add(tf_name);
		f_plus.add(l_time);
		f_plus.add(tf_time);
		f_plus.add(l_id);
		f_plus.add(tf_id);
		f_plus.add(l_pwd);
		f_plus.add(tf_pwd);
		f_plus.add(l_pwd2);
		f_plus.add(tf_pwd2);
		f_plus.add(tf_msg);
		f_plus.add(b_new);
//		f_plus.add(b_clear);
		f_plus.add(b_id);
		f_plus.add(tf_msg);
		f_plus.add(tf_check);
	}

	public static void mana_employee() {
		Frame f_mana = new Frame("직원 수정");
		f_mana.setSize(250,200);
		f_mana.setLayout(null);
		f_mana.setVisible(true);
		f_mana.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				f_mana.dispose();
			}
		});
		
		f_mana.getSize();
		Dimension dimen = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension dimen2 = f_mana.getSize();
		
		int x = (dimen.width - dimen2.width) / 2;
		int y = (dimen.height - dimen2.height) / 2;
		f_mana.setLocation(x, y);
		
		TextField tf_name, tf_posi, tf_time;
	
		list_pt = dao.list_employee(code2);
		
		String posi = "";
		String time = "";
		String name = "";
		
		for(int i=0;i<list_pt.size();i++) {
			MemberVo data = (MemberVo) list_pt.get(i);
			
			code2 = data.getCode();
			name = data.getName();
			posi = data.getPosition();
			time = data.getTime();
			String memo = data.getMemo();
		}
		
		Label l_name = new Label("이름");
		l_name.setSize(40, 20);
		l_name.setLocation(50, 50);
		
		Label l_posi = new Label("직급");
		l_posi.setSize(40, 20);
		l_posi.setLocation(50, 80);
		
		Label l_time = new Label("근무타임");
		l_time.setSize(60, 20);
		l_time.setLocation(30, 110);
		
		tf_name = new TextField();
		tf_name.setSize(80, 20);
		tf_name.setLocation(90, 50);
		tf_name.setText(name);
		tf_name.setEditable(false);
		tf_name.setBackground(Color.white);
		
		tf_posi = new TextField();
		tf_posi.setSize(80, 20);
		tf_posi.setLocation(90, 80);
		tf_posi.setText(posi);
		
		tf_time = new TextField();
		tf_time.setSize(80, 20);
		tf_time.setLocation(90, 110);
		tf_time.setText(time);
		
		Button b = new Button("수정");
		b.setSize(40, 30);
		b.setLocation(80, 140);
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				list_uppt = dao.update_employee(code2, tf_posi.getText(), tf_time.getText());
				
				for(int i=0;i<list_pt.size();i++) {
					MemberVo data = (MemberVo) list_pt.get(i);
					
					code2 = data.getCode();
					String name = data.getName();
					String posi = data.getPosition();
					String time = data.getTime();
					String memo = data.getMemo();
				}
				
			}
		});
		
		Button b2 = new Button("닫기");
		b2.setSize(40, 30);
		b2.setLocation(130, 140);
		b2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				f_mana.dispose();
			}
		});
		
		
		f_mana.add(l_name);
		f_mana.add(l_posi);
		f_mana.add(l_time);
		f_mana.add(tf_time);
		f_mana.add(tf_posi);
		f_mana.add(tf_name);
		f_mana.add(b);
		f_mana.add(b2);
		
	}
}
