package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Issue;
import utility.ConnectionManager;

public  class IssueDaoImpl implements IssueDaoInterface{



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



			PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL);
			preparedStatement.setInt(1,++iid);
			preparedStatement.setString(2,issue.getIssue_summary());
			preparedStatement.setString(3,issue.getISSUE_DESCRIPTION());
			preparedStatement.setInt(4,issue.getIdentified_by_cid());//login id user
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

	public int viewstatus(int userid) throws Exception
	{
		Issue issue=new Issue();

		String Fetch_results = "SELECT ISSUE_ID,ISSUE_SUMMARY,RELATED_TO_CD_ID,STATUS FROM ISSUES where IDENTIFIED_BY_CU_ID="+userid;

		Connection con=ConnectionManager.getConnection();
		ResultSet rs=con.createStatement().executeQuery(Fetch_results);

		//printing headings

		String heading1="ISSUE_ID";
		String heading2="ISSUE_SUMMARY";
		String heading3="RELATED_TO_CD_NAME";
		String heading4="STATUS";


		System.out.printf("%s    %-30s  %s %10s %n",heading1,heading2,heading3,heading4);

		System.out.println("__________________________________________________________________________________");
		while(rs.next())
		{
			//printing isssues


			int id=rs.getInt("ISSUE_ID");
			String summary=rs.getString("ISSUE_SUMMARY");	
			int depid=rs.getInt("RELATED_TO_CD_ID");	
			String status=rs.getString("STATUS");

			//geting depname
			String getdepname="select CD_NAME from college_department where CD_ID="+depid;
			ResultSet rs1=con.createStatement().executeQuery(getdepname);
			rs1.next();
			String depname=rs1.getString("CD_NAME");



			//System.out.println(id+"\t"+summary+"\t"+desc+"\t"+uid+"\t"+depid+"\t"+pro+"\t"+pri+"\t"+status);
			System.out.printf("  %-7d %-30s %18s \t%-15s  %n",id,summary,depname,status);
			rs1.close();

		}
		System.out.println("__________________________________________________________________________________");
		System.out.println("\n\n\n");

		return 1;

	}












}
