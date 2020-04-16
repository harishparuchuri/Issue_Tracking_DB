package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Employee;

import utility.ConnectionManager;

public class EmployeeDaoImpl implements EmployeeDaoInterface {
	
	int loginid;
	String returnuser;
	String returnpass;

	public int signUp(Employee employee) throws Exception {
		String INSERT_USERS_SQL = "INSERT INTO COLLEGE_DEPARTMENT_USER(CDU_ID,CDU_NAME,CDU_EMAIL,CDU_ROLE,CDU_DEPARTMENT,CDU_PASSWORD)VALUES(?,?,?,?,?,?)";
		String count="select count(*) AS rowcount from COLLEGE_USER";
		
		int result = 0;
		try
		{
			Connection connection = null;
			try {
				connection = ConnectionManager.getConnection();
			} catch (Exception e) {

				e.printStackTrace();
			}
			ResultSet rs=connection.createStatement().executeQuery(count);
			rs.next();
			int Eid = rs.getInt("rowcount") ;
			rs.close();

			PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL);
			preparedStatement.setInt(1,++Eid);
			preparedStatement.setString(2,employee.getEname());
			preparedStatement.setString(3,employee.getEmail());
			preparedStatement.setString(4,employee.getErole());
			preparedStatement.setInt(5,employee.getEdepartment());
			preparedStatement.setString(6,employee.getEpassword());
			
			result = preparedStatement.executeUpdate();
			
			

			
			System.out.println("Employee "+employee.getEname()+" Added sucessfully\n\n");
			
		} catch (SQLException e) {
			System.out.println(e);
		}
		return result;
	}

	
	
	public boolean loginEmployee(Employee employee) throws Exception {
		boolean status = false;
		try{
			Connection connection = null;
			try {
				connection = ConnectionManager.getConnection();
			} catch (Exception e) {

				e.printStackTrace();
			}

			String getid="select CU_ID as userid from COLLEGE_USER  where CDU_NAME = ? and CDU_PASSWORD = ?";
			PreparedStatement preparedStatement = connection.prepareStatement("select * from COLLEGE_DEPARTMENT_USER where CDU_NAME = ? and CDU_PASSWORD = ? ");

			
			returnuser=employee.getEname();
			returnpass=	employee.getEpassword();
			preparedStatement.setString(1,employee.getEname());
			preparedStatement.setString(2, employee.getEpassword());

			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			status = rs.next();
			
			
			
			

		} catch (SQLException e) {

			System.out.println(e);
		}
		
		return status;
	}
	
	//get employee id
	
	public int returnid(){
		
		

		try{
			Connection connection = null;
			try {
				connection = ConnectionManager.getConnection();
			} catch (Exception e) {

				e.printStackTrace();
			}
			PreparedStatement preparedStatement = connection.prepareStatement("select CDU_ID as userid from college_department_user  where CDU_NAME = ? and CDU_PASSWORD = ?");
		
		preparedStatement.setString(1,returnuser);
		preparedStatement.setString(2,returnpass);
		ResultSet rs = preparedStatement.executeQuery();
		rs.next();
		loginid = rs.getInt("userid") ;
		
		rs.close();
		}
		
		catch (SQLException e) {

			System.out.println(e);
		}
		
		return loginid;
	}


	
	

}
