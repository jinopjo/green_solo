package screen;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.text.*;

import sql.MemberDAO;
import sql.MemberVo;

public class Employee extends Login {

	static ArrayList<MemberVo> list, list_service, list_io, list_inout, list_up, list_memo, list_upmemo;
	static MemberDAO dao = new MemberDAO();
	
	static Calendar cal_in, cal_out;
	static SimpleDateFormat df = new SimpleDateFormat("MM-dd, HH:mm");
	static String d_in, d_out;
	
	static String code, dutyhours, io;
	static TextField tf_io = new TextField();
	static TextField tf_tot = new TextField();
	static int in_h, in_m, out_h, out_m, diff_h, diff_m, tot_h, tot_m;
	
	
	static int[] htot = new int[9999];
	static int[] mtot = new int[9999];
	
	public static void start() {
		Frame f = new Frame("Á÷¿ø");
		Button b_main = new Button("Main");
		Button b_in, b_out;
		Button b_memo;
		
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
		
		b_memo = new Button("¸Þ¸ð");
		b_memo.setSize(30, 25);
		b_memo.setLocation(270, 152);
		b_memo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				plus_memo();
			}
		});
		
		b_in = new Button("Ãâ±Ù");
		b_in.setSize(100,100);
		b_in.setLocation(50,450);
		b_in.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getActionCommand().equals("Ãâ±Ù")) {
					String io_b = " ";
					list_inout = dao.list_servicetime(code);
					
					for(int i=0;i<list_inout.size();i++) {
						MemberVo data = (MemberVo) list_inout.get(i);
						code = data.getCode();
						String name = data.getName();
						dutyhours = data.getDutyhours();
						io_b = data.getInout();
						
//						System.out.println(io_b.charAt(13));
					}
					
					if((io_b.equals(" ")) || (io_b.charAt(13) == 'o')) { // »õ·Îµé¾î¿Ô°Å³ª Åð±ÙÀÌ ÂïÇôÀÖÀ¸¸é Ãâ±Ù¹öÆ°Å¬¸¯°¡´É
						cal_in = Calendar.getInstance();
						Date date = new Date();
						cal_in.setTime(date);
					
						d_in = df.format(cal_in.getTime());
					
						System.out.println(d_in);
					
						tf_io.setText("Ãâ±Ù " + d_in);
						
						list_io = dao.update_inout(code, d_in + " in");
						
						for(int i=0;i<list_io.size();i++) {
							MemberVo data = (MemberVo) list_io.get(i);
							code = data.getCode();
							String name = data.getName();
							dutyhours = data.getDutyhours();
							io = data.getInout();
						}
						
//						in_h = Integer.parseInt(d_in.substring(7, 9));
//						in_m = Integer.parseInt(d_in.substring(10));
//						
//						System.out.println(in_h + " : " +  in_m);
					}
				}
			}
		});
		
		b_out = new Button("Åð±Ù");
		b_out.setSize(100,100);
		b_out.setLocation(250,450);
		b_out.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String io_b = "";
				if(e.getActionCommand().equals("Åð±Ù")) {
					
					list_inout = dao.list_servicetime(code);
					
					for(int i=0;i<list_inout.size();i++) {
						MemberVo data = (MemberVo) list_inout.get(i);
						code = data.getCode();
						String name = data.getName();
						dutyhours = data.getDutyhours();
						io_b = data.getInout();
						
//						System.out.println(io_b.charAt(13));
					}
					
					in_h = Integer.parseInt(io_b.substring(7, 9));	// Ãâ±Ù ½Ã/ºÐ ÀúÀå
					in_m = Integer.parseInt(io_b.substring(10, 12));
					
					System.out.println(in_h + " : " +  in_m);
					
					String arr[] = tf_tot.getText().split(":");
					
					if(io_b.charAt(13) == 'i') {
						cal_out = Calendar.getInstance();			
					
						Date date = new Date();
						cal_out.setTime(date);
					
						d_out = df.format(cal_out.getTime());
					
						System.out.println(d_out);
						tf_io.setText("Åð±Ù " + d_out);
						
						list_io = dao.update_inout(code, d_out + " out");
						
						for(int i=0;i<list_io.size();i++) {
							MemberVo data = (MemberVo) list_io.get(i);
							code = data.getCode();
							String name = data.getName();
							dutyhours = data.getDutyhours();
							io = data.getInout();
						}
						
						out_h = Integer.parseInt(d_out.substring(7, 9));
						out_m = Integer.parseInt(d_out.substring(10));
						
						diff_h = out_h - in_h;
						diff_m = out_m - in_m;
						if(diff_m < 0) {
							diff_h--;
							diff_m += 60;
						}
						
						tot_h += diff_h;
						tot_m += diff_m;
						
						if(tot_m >= 60) {
							tot_m = 0;
							tot_h++;
						}
						
						htot[Integer.parseInt(code)-1] = tot_h + Integer.parseInt(arr[0]);
						mtot[Integer.parseInt(code)-1] = tot_m + Integer.parseInt(arr[1]);
						
						if(mtot[Integer.parseInt(code)-1] >= 60) {
							mtot[Integer.parseInt(code)-1] -= 60;
							htot[Integer.parseInt(code)-1]++;
						}
						
						if(htot[Integer.parseInt(code)-1] < 0) {
							htot[Integer.parseInt(code)-1] += 24;
						}
						
						if(mtot[Integer.parseInt(code)-1] < 10) {
							list_up = dao.update_servicetime(code, htot[Integer.parseInt(code)-1] + ":0" + mtot[Integer.parseInt(code)-1]);
						}
						else {
							list_up = dao.update_servicetime(code, htot[Integer.parseInt(code)-1] + ":" + mtot[Integer.parseInt(code)-1]);
						}
						
						for(int i=0;i<list_up.size();i++) {
							MemberVo data = (MemberVo) list_up.get(i);
							code = data.getCode();
							String name = data.getName();
							dutyhours = data.getDutyhours();
							io_b = data.getInout();
							
						}
						
						tot_h = 0;
						tot_m = 0;
						
					}
				}
			}
		});
		
		list = dao.list(tf_id.getText(), tf_pwd.getText());
		
		code = "";
		for(int i=0;i<list.size();i++) {
			MemberVo data = (MemberVo) list.get(i);
			code = data.getCode();
			String id = data.getId();
			String pwd = data.getPwd();
			System.out.println(id + " : " + pwd + " : " + code);
		}
		
		list = dao.list_employee(code);
		
		String name = "";
		String position = "";
		String time = "";
		for(int i=0;i<list.size();i++) {
			MemberVo data = (MemberVo) list.get(i);
			code = data.getCode();
			name = data.getName();
			position = data.getPosition();
			time = data.getTime();
			String memo = data.getMemo();
			
			System.out.println(code + ", " + name + ", " + position + ", " + time);
		}
		
		list_service = dao.list_servicetime(code);
		
		String dutyhours = "";
		String inout = "";
		for(int i=0;i<list.size();i++) {
			MemberVo data = (MemberVo) list_service.get(i);
			code = data.getCode();
			name = data.getName();
			dutyhours = data.getDutyhours();
			inout = data.getInout();

			System.out.println(code + " : " + name + " : " + dutyhours);
		}
		
		Label l_name = new Label();
		l_name.setSize(150,50);
		l_name.setText("" + name + "´Ô");
		l_name.setLocation(138,140);
		l_name.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 30));
		
		Label l_posi = new Label();
		l_posi.setSize(150,50);
		l_posi.setText("" + position);
		l_posi.setLocation(168, 175);
		l_posi.setFont(new Font("¸¼Àº °íµñ", 10, 20));

		Label l_tot = new Label();
		l_tot.setSize(150,50);
		l_tot.setText("ÀÌ¹ø´Þ ÃÑ ±Ù¹«½Ã°£");
		l_tot.setLocation(145, 210);
		
		tf_tot = new TextField();
		tf_tot.setSize(115, 50);
		tf_tot.setLocation(145, 260);
		tf_tot.setFont(new Font("¸¼Àº °íµñ", 10, 35));
		tf_tot.setEditable(false);
		tf_tot.setBackground(Color.white);
		tf_tot.setText("" + dutyhours);
		
//		Label l_pay = new Label();
//		l_pay.setSize(150,50);
//		l_pay.setText("±Ù¹« Å¸ÀÓ");
//		l_pay.setLocation(165, 310);
		

		
		tf_io = new TextField();
		tf_io.setSize(250, 40);
		tf_io.setLocation(75, 360);
		tf_io.setFont(new Font("¸¼Àº °íµñ", 10, 28));
		tf_io.setEditable(false);
		tf_io.setBackground(Color.white);
		
		
//		if(inout.equals("")) {
//			tf_io.setText("");
//		}
//		else if(inout.charAt(13) == 'i') {
//			tf_io.setText("Ãâ±Ù " + inout.substring(0, 13));
//		}
//		else if(inout.charAt(13) == 'o') {
//			tf_io.setText("Åð±Ù " + inout.substring(0, 13));
//		}
		
		f.add(b_main);
		f.add(b_memo);
		f.add(b_in);
		f.add(b_out);
		f.add(l_name);
		f.add(l_posi);
		f.add(l_tot);
		f.add(tf_tot);
		f.add(tf_io);
		

	}
	
//	static public void diffTime() {
//		long diffHour = cal_out.get(Calendar.HOUR_OF_DAY) - cal_in.get(Calendar.HOUR_OF_DAY);
//		long diffMinute = cal_out.get(Calendar.MINUTE) - cal_in.get(Calendar.MINUTE);
////		long diffSecond = cal_out.get(Calendar.SECOND) - cal_in.get(Calendar.SECOND);
//		
//		
//		System.out.println("½Ã : " + diffHour);
//		System.out.println("ºÐ : " + diffMinute);
////		System.out.println("ÃÊ : " + diffSecond);
//	}
	
	public static void plus_memo() {
		Frame f_memo = new Frame("¸Þ¸ð");
		f_memo.setSize(400,300);
		f_memo.setLayout(null);
		f_memo.setVisible(true);
		f_memo.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				f_memo.dispose();
			}
		});
		
		f_memo.getSize();
		Dimension dimen = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension dimen2 = f_memo.getSize();
		
		int x = (dimen.width - dimen2.width) / 2;
		int y = (dimen.height - dimen2.height) / 2;
		f_memo.setLocation(x, y);
		
		TextArea ta = new TextArea();
		ta.setSize(350,200);
		ta.setLocation(25,40);
		
		Button b_save = new Button("ÀúÀå");
		b_save.setSize(40, 30);
		b_save.setLocation(150, 250);
		b_save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String str = ta.getText();
				
				list_upmemo = dao.update_memo(code, str);
				
				for(int i=0;i<list_upmemo.size();i++) {
					MemberVo data = (MemberVo) list_upmemo.get(i);
					code = data.getCode();
					String name = data.getName();
					String position = data.getPosition();
					String time = data.getTime();
					String memo = data.getMemo();
					
					System.out.println(memo);
				}
			}
		});
		
		Button b_exit = new Button("´Ý±â");
		b_exit.setSize(40, 30);
		b_exit.setLocation(220, 250);
		b_exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				f_memo.dispose();
			}
		});
		
		list_memo = dao.list_employee(code);
		
		String memo = "";
		for(int i=0;i<list_memo.size();i++) {
			MemberVo data = (MemberVo) list_memo.get(i);
			code = data.getCode();
			String name = data.getName();
			String position = data.getPosition();
			String time = data.getTime();
			memo = data.getMemo();
			
			System.out.println(memo);
		}
		
		ta.setText(memo);
		
		f_memo.add(ta);
		f_memo.add(b_save);
		f_memo.add(b_exit);
		
	}
}
