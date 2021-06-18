package sql;

public class MemberVo {
	String id;
	String pwd;
	String code;
	String name;
	String position;
	String time;
	String dutyhours;
	String inout;
	String memo;
	
	MemberVo() {
		
	}
	
	MemberVo(String id, String pwd) {
		this.id = id;
		this.pwd = pwd;
	}
	
	public String getId() {
		return id;
	}
	public String getPwd() {
		return pwd;
	}

	
	MemberVo(String code, String name, String position, String time, String memo) {
		this.code = code;
		this.name = name;
		this.position = position;
		this.time = time;
		this.memo = memo;
	}
	
	public String getCode() {
		return code;
	}
	public String getName() {
		return name;
	}
	public String getPosition() {
		return position;
	}
	public String getTime() {
		return time;
	}
	public String getMemo() {
		return memo;
	}
	
	MemberVo(String code, String id, String pwd) {
		this.code = code;
		this.id = id;
		this.pwd = pwd;
	}
	
	MemberVo(String code, String name, String dutyhours, String inout) {
		this.code = code;
		this.name = name;
		this.dutyhours = dutyhours;
		this.inout = inout;
	}
	public String getDutyhours() {
		return dutyhours;
	}
	
	public String getInout() {
		return inout;
	}
	
}
