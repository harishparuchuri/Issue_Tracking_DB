package model;

public class Employee {
	
	//private int eid;
	private String ename;
	private String email;
	private String erole;
	private int edepartment;
	private String epassword;

	public Employee() {
		// TODO Auto-generated constructor stub
	}

	public Employee( String ename, String email, String erole, int edepartment, String epassword) {
		super();
		
		this.ename = ename;
		this.email = email;
		this.erole = erole;
		this.edepartment = edepartment;
		this.epassword = epassword;
	}

//	public int getEid() {
//		return eid;
//	}
//
//	public void setEid(int eid) {
//		this.eid = eid;
//	}

	public String getEname() {
		return ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getErole() {
		return erole;
	}

	public void setErole(String erole) {
		this.erole = erole;
	}

	public int getEdepartment() {
		return edepartment;
	}

	public void setEdepartment(int edepartment) {
		this.edepartment = edepartment;
	}

	public String getEpassword() {
		return epassword;
	}

	public void setEpassword(String epassword) {
		this.epassword = epassword;
	}

	
	

}
