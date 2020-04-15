package model;

public class Department {
	
	private int department_id;
	private String department_name;
	private String department_number;
	
	public Department() {
		// TODO Auto-generated constructor stub
	}

	public Department(int department_id, String department_name, String department_number) {
		super();
		this.department_id = department_id;
		this.department_name = department_name;
		this.department_number = department_number;
	}

	public int getDepartment_id() {
		return department_id;
	}

	public void setDepartment_id(int department_id) {
		this.department_id = department_id;
	}
	public String getDepartment_name() {
		return department_name;
	}

	public void setDepartment_name(String department_name) {
		this.department_name = department_name;
	}

	public String getDepartment_number() {
		return department_number;
	}

	public void setDepartment_number(String department_number) {
		this.department_number = department_number;
	}
	
	

	

}
