package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.User;
import utility.ConnectionManager;

public class UserDaoImpl implements UserDaoInterface {
	
	String returnuser;
	String returnpass;
	
	public int signUp(User user) throws Exception {
		String INSERT_USERS_SQL = "INSERT INTO COLLEGE_USER(CU_ID,CU_NAME,P_NUMBER,CU_PASSWORD)VALUES(?,?,?,?)";
		
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
			int uid = rs.getInt("rowcount") ;
			rs.close();
			PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL);
			preparedStatement.setInt(1,++uid);
			preparedStatement.setString(2,user.getUname());
			preparedStatement.setString(3,user.getUnumber());
			preparedStatement.setString(4,user.getUpassword());
			
			
			System.out.println("User "+user.getUname()+" Added sucessfully\n\n");

			result = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e);
		}
		return result;
	}

	public boolean loginUser(User user) throws Exception {
		boolean status = false;
		try{
			Connection connection = null;
			try {
				connection = ConnectionManager.getConnection();
			} catch (Exception e) {

				e.printStackTrace();
			}
			
					PreparedStatement preparedStatement = connection.prepareStatement("select * from COLLEGE_USER where CU_NAME = ? and CU_PASSWORD = ? ");

					returnuser=user.getUname();
					returnpass=	user.getUpassword();	
			preparedStatement.setString(1,user.getUname());
			preparedStatement.setString(2, user.getUpassword());

			//System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			status = rs.next();
			
			

		} catch (SQLException e) {

			System.out.println(e);
		}
		return status;
	}
	int loginid;
	public int returnid(){
		
		

		try{
			Connection connection = null;
			try {
				connection = ConnectionManager.getConnection();
			} catch (Exception e) {

				e.printStackTrace();
			}
			PreparedStatement preparedStatement = connection.prepareStatement("select CU_ID as userid from COLLEGE_USER  where CU_NAME = ? and CU_PASSWORD = ?");
		
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
