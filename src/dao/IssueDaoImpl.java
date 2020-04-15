package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Issue;
import utility.ConnectionManager;

public class IssueDaoImpl implements IssueDaoInterface{
	
	

	public int Addissue(Issue issue) {
		
String INSERT_USERS_SQL = "INSERT INTO ISSUES(ISSUE_ID,ISSUE_SUMMARY,ISSUE_DESCRIPTION,IDENTIFIED_BY_CU_ID,RELATED_TO_CD_ID,ISSUE_CREATED_DATE,PRIORITY)VALUES(?,?,?,?,?,?,?)";
		
		String count="select count(*) AS rowcount from ISSUES";
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
			int iid = rs.getInt("rowcount") ;
			rs.close();
			
			System.out.println("id insert"+iid);
			System.out.println("user input"+issue.getIssue_summary());
			PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL);
			preparedStatement.setInt(1,++iid);
			preparedStatement.setString(2,issue.getIssue_summary());
			preparedStatement.setString(3,issue.getISSUE_DESCRIPTION());
			preparedStatement.setInt(4,issue.getIdentified_by_cid());//login id user
			//preparedStatement.setInt(4,1);
			preparedStatement.setInt(5,issue.getRelated_cd_id());
			preparedStatement.setString(6,issue.getPostedOn());
			preparedStatement.setString(7,issue.getPriority());
			
			
			
			
			System.out.println("YOUR ISSUE IS SUBMITED WITH ISSUE ID "+iid+"  sucessfully\n\n");

			result = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e);
		}
		return result;
	}

		
		
		
		


}
