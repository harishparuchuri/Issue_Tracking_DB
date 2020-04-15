package model;

public class User {
	//private int uid;
	private String uname;
	private String unumber;
	private String upassword;
	
	public User() {
		// TODO Auto-generated constructor stub
	}
	
	public User(String uname, String unumber, String upassword) {
		super();
		
		this.uname = uname;
		this.unumber = unumber;
		this.upassword = upassword;
	}

	

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getUnumber() {
		return unumber;
	}

	public void setUnumber(String unumber) {
		this.unumber = unumber;
	}

	public String getUpassword() {
		return upassword;
	}

	public void setUpassword(String upassword) {
		this.upassword = upassword;
	}

	
	

}
