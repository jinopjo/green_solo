package sql;

import java.sql.*;
import java.util.*;

public class MemberDAO {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521/xe";
	String user = "green";
	String password = "green1234";
	
	private Connection con;
	private Statement stmt;
	private ResultSet rs;
	
	public ArrayList<MemberVo> list(String name) { // �α��ν� ���̵� ���
		ArrayList<MemberVo> list = new ArrayList<MemberVo>();
		try {
			connDB();
			
			String query = "SELECT * FROM LOGIN";
			if(name != null) {
				query += " where id='" + name.toLowerCase() + "'";
			}
			System.out.println("SQL : " + query);
			
			rs = stmt.executeQuery(query);
			rs.last();
			System.out.println("rs.getRow : " + rs.getRow());
			
			if(rs.getRow() == 0) {
				System.out.println("0 row selected...");
			} else {
				System.out.println(rs.getRow() + " rows selected...");
				rs.previous();
				while(rs.next()) {
					String id = rs.getString("id");
					String password = rs.getString("pwd");
					
					MemberVo data = new MemberVo(id, password);
					list.add(data);
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public ArrayList<MemberVo> list(String name, String pwd) { // �α��� �� �α��� �� ���̵��� �ڵ�
		ArrayList<MemberVo> list = new ArrayList<MemberVo>();
		try {
			connDB();
			
			String query = "SELECT * FROM LOGIN";
			if(name != null) {
				query += " where id='" + name.toLowerCase() + "'";
			}
			System.out.println("SQL : " + query);
			
			rs = stmt.executeQuery(query);
			rs.last();
			System.out.println("rs.getRow : " + rs.getRow());
			
			if(rs.getRow() == 0) {
				System.out.println("0 row selected...");
			} else {
				System.out.println(rs.getRow() + " rows selected...");
				rs.previous();
				while(rs.next()) {
					String code = rs.getString("code");
					String id = rs.getString("id");
					String password = rs.getString("pwd");
					
					MemberVo data = new MemberVo(code, id, password);
					list.add(data);
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public ArrayList<MemberVo> list_employee(String cd) { // �ڵ尪���� ���� ����ȹ��
		ArrayList<MemberVo> list = new ArrayList<MemberVo>();
		try {
			connDB();
			
			String query = "SELECT * FROM employee";
			if(cd != null) {
				query += " where code='" + cd.toLowerCase() + "'";
			}
			System.out.println("SQL : " + query);
			
			rs = stmt.executeQuery(query);
			rs.last();
			System.out.println("rs.getRow : " + rs.getRow());
			
			if(rs.getRow() == 0) {
				System.out.println("0 row selected...");
			} else {
				System.out.println(rs.getRow() + " rows selected...");
				rs.previous();
				while(rs.next()) {
					String code = rs.getString("code");
					String name = rs.getString("name");
					String position = rs.getString("position");
					String time = rs.getString("time");
					String memo = rs.getString("memo");
					
					MemberVo data = new MemberVo(code, name, position, time, memo);
					list.add(data);
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public ArrayList<MemberVo> list_emp(String n) { // �̸����� ���� ����ȹ��
		ArrayList<MemberVo> list = new ArrayList<MemberVo>();
		try {
			connDB();
			
			String query = "SELECT * FROM employee";
			if(n != null) {
				query += " where name='" + n.toLowerCase() + "'";
			}
			System.out.println("SQL : " + query);
			
			rs = stmt.executeQuery(query);
			rs.last();
			System.out.println("rs.getRow : " + rs.getRow());
			
			if(rs.getRow() == 0) {
				System.out.println("0 row selected...");
			} else {
				System.out.println(rs.getRow() + " rows selected...");
				rs.previous();
				while(rs.next()) {
					String code = rs.getString("code");
					String name = rs.getString("name");
					String position = rs.getString("position");
					String time = rs.getString("time");
					String memo = rs.getString("memo");
					
					MemberVo data = new MemberVo(code, name, position, time, memo);
					list.add(data);
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public ArrayList<MemberVo> list_admin(String name) { // ������ �α��� ( �Ŵ��� )
		ArrayList<MemberVo> list = new ArrayList<MemberVo>();
		try {
			connDB();
			
			String query = "SELECT * FROM ADMIN";
			if(name != null) {
				query += " where id='" + name.toLowerCase() + "'";
			}
			System.out.println("SQL : " + query);
			
			rs = stmt.executeQuery(query);
			rs.last();
			System.out.println("rs.getRow : " + rs.getRow());
			
			if(rs.getRow() == 0) {
				System.out.println("0 row selected...");
			} else {
				System.out.println(rs.getRow() + " rows selected...");
				rs.previous();
				while(rs.next()) {
					String id = rs.getString("id");
					String password = rs.getString("pwd");
					
					MemberVo data = new MemberVo(id, password);
					list.add(data);
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public ArrayList<MemberVo> list_servicetime(String cd) { // �ڵ�� �ٹ��ð� ��ȸ
		ArrayList<MemberVo> list = new ArrayList<MemberVo>();
		try {
			connDB();
			
			String query = "SELECT * FROM SERVICETIME";
			if(cd != null) {
				query += " where code='" + cd.toLowerCase() + "'";
			}
			System.out.println("SQL : " + query);
			
			rs = stmt.executeQuery(query);
			rs.last();
			System.out.println("rs.getRow : " + rs.getRow());
			
			if(rs.getRow() == 0) {
				System.out.println("0 row selected...");
			} else {
				System.out.println(rs.getRow() + " rows selected...");
				rs.previous();
				while(rs.next()) {
					String code = rs.getString("code");
					String name = rs.getString("name");
					String dutyhours = rs.getString("dutyhours");
					String inout = rs.getString("inout");
					
					MemberVo data = new MemberVo(code, name, dutyhours, inout);
					list.add(data);
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public ArrayList<MemberVo> list_ser(String n) { // �̸����� �ٹ��ð� ��ȸ
		ArrayList<MemberVo> list = new ArrayList<MemberVo>();
		try {
			connDB();
			
			String query = "SELECT * FROM SERVICETIME";
			if(n != null) {
				query += " where name='" + n.toLowerCase() + "'";
			}
			System.out.println("SQL : " + query);
			
			rs = stmt.executeQuery(query);
			rs.last();
			System.out.println("rs.getRow : " + rs.getRow());
			
			if(rs.getRow() == 0) {
				System.out.println("0 row selected...");
			} else {
				System.out.println(rs.getRow() + " rows selected...");
				rs.previous();
				while(rs.next()) {
					String code = rs.getString("code");
					String name = rs.getString("name");
					String dutyhours = rs.getString("dutyhours");
					String inout = rs.getString("inout");
					
					MemberVo data = new MemberVo(code, name, dutyhours, inout);
					list.add(data);
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public ArrayList<MemberVo> insert_employee(String c, String n, String p, String t) { // ���� �߰�
		ArrayList<MemberVo> list = new ArrayList<MemberVo>();
		try {
			connDB();
			
			String query = "INSERT INTO EMPLOYEE VALUES(";
			if(c != null ) {
				query += "'" + c.toLowerCase() + "','" + n.toLowerCase() +  "','" + p.toLowerCase() + "','" + t.toLowerCase() + "', '')";
			}
			System.out.println("SQL : " + query);
			
			rs = stmt.executeQuery(query);
//			rs.last();
//			System.out.println("rs.getRow : " + rs.getRow());
//			
//			if(rs.getRow() == 0) {
//				System.out.println("0 row selected...");
//			} else {
//				System.out.println(rs.getRow() + " rows selected...");
//				rs.previous();
//				while(rs.next()) {
//					String code = rs.getString("code");
//					String name = rs.getString("name");
//					String position = rs.getString("position");
//					String time = rs.getString("time");
//					
//					MemberVo data = new MemberVo(code, name, position, time);
//					list.add(data);
//				}
//			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public ArrayList<MemberVo> insert_login(String c, String i, String p) { // ���� ���̵� �߰�
		ArrayList<MemberVo> list = new ArrayList<MemberVo>();
		try {
			connDB();
			
			String query = "INSERT INTO LOGIN VALUES (";
			if(c != null) {
				query += "'" + c.toLowerCase() + "','" + i.toLowerCase() + "','" + p.toLowerCase() + "')";
			}
			System.out.println("SQL : " + query);
			
			rs = stmt.executeQuery(query);
//			rs.last();
//			System.out.println("rs.getRow : " + rs.getRow());
//			
//			if(rs.getRow() == 0) {
//				System.out.println("0 row selected...");
//			} else {
//				System.out.println(rs.getRow() + " rows selected...");
//				rs.previous();
//				while(rs.next()) {
//					String code = rs.getString("code");
//					String id = rs.getString("id");
//					String password = rs.getString("pwd");
//					
//					MemberVo data = new MemberVo(code, id, password);
//					list.add(data);
//				}
//			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public ArrayList<MemberVo> insert_servicetime(String c, String n) { // ���� �ٹ��ð� �߰�
		ArrayList<MemberVo> list = new ArrayList<MemberVo>();
		try {
			connDB();
			
			String query = "INSERT INTO SERVICETIME VALUES (";
			if(c != null) {
				query += "'" + c.toLowerCase() + "','" + n.toLowerCase() + "','0:0',' ')";
			}
			System.out.println("SQL : " + query);
			
			rs = stmt.executeQuery(query);
//			rs.last();
//			System.out.println("rs.getRow : " + rs.getRow());
//			
//			if(rs.getRow() == 0) {
//				System.out.println("0 row selected...");
//			} else {
//				System.out.println(rs.getRow() + " rows selected...");
//				rs.previous();
//				while(rs.next()) {
//					String code = rs.getString("code");
//					String name = rs.getString("name");
//					String dutyhours = rs.getString("dutyhours");
//					String inout = rs.getString("inout");
//					
//					MemberVo data = new MemberVo(code, name, dutyhours, inout);
//					list.add(data);
//				}
//			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public ArrayList<MemberVo> update_servicetime(String cd, String ut) { // �ڵ�� �ٹ��ð� ������Ʈ
		ArrayList<MemberVo> list = new ArrayList<MemberVo>();
		try {
			connDB();
			
			String query = "UPDATE SERVICETIME SET DUTYHOURS = '";
			if(cd != null) {
				query += ut.toLowerCase() + "' where code='" + cd.toLowerCase() + "'";
			}
			System.out.println("SQL : " + query);
			
			rs = stmt.executeQuery(query);
//			rs.last();
//			System.out.println("rs.getRow : " + rs.getRow());
//			
//			if(rs.getRow() == 0) {
//				System.out.println("0 row selected...");
//			} else {
//				System.out.println(rs.getRow() + " rows selected...");
//				rs.previous();
//				while(rs.next()) {
//					String code = rs.getString("code");
//					String name = rs.getString("name");
//					String dutyhours = rs.getString("dutyhours");
//					String inout = rs.getString("inout");
//					
//					MemberVo data = new MemberVo(code, name, dutyhours, inout);
//					list.add(data);
//				}
//			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public ArrayList<MemberVo> update_inout(String cd, String io) { // ���� ��/��� �ð� ����
		ArrayList<MemberVo> list = new ArrayList<MemberVo>();
		try {
			connDB();
			
			String query = "UPDATE SERVICETIME SET INOUT = '";
			if(cd != null) {
				query += io.toLowerCase() + "' where code='" + cd.toLowerCase() + "'";
			}
			System.out.println("SQL : " + query);
			
			rs = stmt.executeQuery(query);
//			rs.last();
//			System.out.println("rs.getRow : " + rs.getRow());
//			
//			if(rs.getRow() == 0) {
//				System.out.println("0 row selected...");
//			} else {
//				System.out.println(rs.getRow() + " rows selected...");
//				rs.previous();
//				while(rs.next()) {
//					String code = rs.getString("code");
//					String name = rs.getString("name");
//					String dutyhours = rs.getString("dutyhours");
//					String inout = rs.getString("inout");
//					
//					MemberVo data = new MemberVo(code, name, dutyhours, inout);
//					list.add(data);
//				}
//			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public ArrayList<MemberVo> update_memo(String c, String m) { // �ڵ�� �ٹ��ð� ������Ʈ
		ArrayList<MemberVo> list = new ArrayList<MemberVo>();
		try {
			connDB();
			
			String query = "UPDATE EMPLOYEE SET MEMO = '";
			if(c != null) {
				query += m + "' where code='" + c.toLowerCase() + "'";
			}
			System.out.println("SQL : " + query);
			
			rs = stmt.executeQuery(query);
//			rs.last();
//			System.out.println("rs.getRow : " + rs.getRow());
//			
//			if(rs.getRow() == 0) {
//				System.out.println("0 row selected...");
//			} else {
//				System.out.println(rs.getRow() + " rows selected...");
//				rs.previous();
//				while(rs.next()) {
//					String code = rs.getString("code");
//					String name = rs.getString("name");
//					String dutyhours = rs.getString("dutyhours");
//					String inout = rs.getString("inout");
//					
//					MemberVo data = new MemberVo(code, name, dutyhours, inout);
//					list.add(data);
//				}
//			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public ArrayList<MemberVo> update_employee(String c, String p, String t) { // ���� ��/��� �ð� ����
		ArrayList<MemberVo> list = new ArrayList<MemberVo>();
		try {
			connDB();
			
			String query = "UPDATE EMPLOYEE SET POSITION = '";
			if(c != null) {
				query += p.toLowerCase() + "', time = '" + t + "' where code='" + c.toLowerCase() + "'";
			}
			System.out.println("SQL : " + query);
			
			rs = stmt.executeQuery(query);
//			rs.last();
//			System.out.println("rs.getRow : " + rs.getRow());
//			
//			if(rs.getRow() == 0) {
//				System.out.println("0 row selected...");
//			} else {
//				System.out.println(rs.getRow() + " rows selected...");
//				rs.previous();
//				while(rs.next()) {
//					String code = rs.getString("code");
//					String name = rs.getString("name");
//					String dutyhours = rs.getString("dutyhours");
//					String inout = rs.getString("inout");
//					
//					MemberVo data = new MemberVo(code, name, dutyhours, inout);
//					list.add(data);
//				}
//			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public void connDB() {
		try {
			Class.forName(driver);
			System.out.println("jdbc driver loading success.");
			con = DriverManager.getConnection(url, user, password);
			System.out.println("oracle connection success.");
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			System.out.println("statement create success.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
