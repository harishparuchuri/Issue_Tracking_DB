package dao;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import utility.ConnectionManager;

public class IssueOperation {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	int issueid=0;
	int depempid=0;
	public int viewissues(String type) throws Exception
	{

		System.out.println(type);
		String Fetch_results="";
		if(type=="pending")
		{
			Fetch_results="SELECT ISSUE_ID,ISSUE_SUMMARY,ISSUE_DESCRIPTION,IDENTIFIED_BY_CU_ID,RELATED_TO_CD_ID,	PRIORITY,STATUS FROM ISSUES where STATUS='pending'";
		}
		else if(type=="working")
		{
			Fetch_results="SELECT ISSUE_ID,ISSUE_SUMMARY,ISSUE_DESCRIPTION,IDENTIFIED_BY_CU_ID,RELATED_TO_CD_ID,	PRIORITY,STATUS FROM ISSUES where STATUS='working'";
		}
		else if(type=="completed")
		{
			Fetch_results="SELECT ISSUE_ID,ISSUE_SUMMARY,ISSUE_DESCRIPTION,IDENTIFIED_BY_CU_ID,RELATED_TO_CD_ID,	PRIORITY,STATUS FROM ISSUES where STATUS='completed'";
		}
		else if(type=="all")
		{
			Fetch_results="SELECT ISSUE_ID,ISSUE_SUMMARY,ISSUE_DESCRIPTION,IDENTIFIED_BY_CU_ID,RELATED_TO_CD_ID,PRIORITY,STATUS FROM ISSUES";
		}

		else {
			//int id=Integer.parseInt(type);
			int iid=Integer.parseInt(type);
			System.out.println("id"+type);
			String getdepid="select CDU_DEPARTMENT FROM COLLEGE_DEPARTMENT_USER WHERE CDU_ID="+iid;
			Connection con=ConnectionManager.getConnection();
			ResultSet rs=con.createStatement().executeQuery(getdepid);
			rs.next();
			int depid=rs.getInt("CDU_DEPARTMENT");
			System.out.println("geting dep "+depid);
			rs.close();
			Fetch_results="SELECT ISSUE_ID,ISSUE_SUMMARY,ISSUE_DESCRIPTION,IDENTIFIED_BY_CU_ID,RELATED_TO_CD_ID,PRIORITY,STATUS FROM ISSUES WHERE RELATED_TO_CD_ID="+depid;
			System.out.println("it is foe employee ");

		}

		Connection con=ConnectionManager.getConnection();
		ResultSet rs=con.createStatement().executeQuery(Fetch_results);

		//printing headings

		String heading1="ISSUE_ID";
		String heading2="ISSUE_SUMMARY";
		String heading3="ISSUE_DESCRIPTION";
		String heading4="IDENTIFIED_BY_CU_ID";
		String heading5="RELATED_TO_CD_ID";
		String heading6="STATUS";
		String heading7="PRIORITY";

		System.out.printf("%s    %-30s %-50s %s %s %s %10s %n",heading1,heading2,heading3,heading4,heading5,heading6,heading7);
		System.out.println("_________________________________________________________________________________________________________________________________________________________________________");
		while(rs.next())
		{
			//printing isssues


			int id=rs.getInt("ISSUE_ID");
			String summary=rs.getString("ISSUE_SUMMARY");	
			String desc=rs.getString("ISSUE_DESCRIPTION");
			int uid=rs.getInt("IDENTIFIED_BY_CU_ID");
			int depid=rs.getInt("RELATED_TO_CD_ID");	
			String pri=rs.getString("STATUS");	
			String status=rs.getString("PRIORITY");
			//System.out.println(id+"\t"+summary+"\t"+desc+"\t"+uid+"\t"+depid+"\t"+pro+"\t"+pri+"\t"+status);
			System.out.printf("  %-7d %-30s %-50s        %-20d %-10d %-13s %s %n",id,summary,desc,uid,depid,pri,status);


		}
		System.out.println("_________________________________________________________________________________________________________________________________________________________________________");
		System.out.println("\n\n\n");



		return 0;

	}

	public int updateissue(int empid, String prioritytype) throws Exception
	{
		String Fetch_results="";
		System.out.println("id"+empid);
		String getdepid="select CDU_DEPARTMENT FROM COLLEGE_DEPARTMENT_USER WHERE CDU_ID="+empid;
		Connection con=ConnectionManager.getConnection();
		ResultSet rs=con.createStatement().executeQuery(getdepid);
		rs.next();
		int depid=rs.getInt("CDU_DEPARTMENT");
		System.out.println("geting dep "+depid);
		rs.close();
		if(prioritytype=="high") {
			Fetch_results="SELECT ISSUE_ID,ISSUE_SUMMARY,ISSUE_DESCRIPTION,IDENTIFIED_BY_CU_ID,RELATED_TO_CD_ID,PRIORITY,STATUS FROM ISSUES WHERE RELATED_TO_CD_ID="+depid+"and PRIORITY='high'and status='pending'";
		}
		else if(prioritytype=="all")
		{
			Fetch_results="SELECT ISSUE_ID,ISSUE_SUMMARY,ISSUE_DESCRIPTION,IDENTIFIED_BY_CU_ID,RELATED_TO_CD_ID,PRIORITY,STATUS FROM ISSUES WHERE RELATED_TO_CD_ID="+depid+"and status='pending'";
		}
		 con=ConnectionManager.getConnection();
		rs=con.createStatement().executeQuery(Fetch_results);
		
		if(rs.next()==false)
		{
			System.out.println("\t\t\t NO ISSUES TO FIX THANK YOU");
			rs.close();
		}
		else {

		//printing headings

		String heading1="ISSUE_ID";
		String heading2="ISSUE_SUMMARY";
		String heading3="ISSUE_DESCRIPTION";
		String heading4="IDENTIFIED_BY_CU_ID";
		String heading5="RELATED_TO_CD_ID";
		String heading6="STATUS";
		String heading7="PRIORITY";

		System.out.printf("%s    %-30s %-50s %s %s %s %10s %n",heading1,heading2,heading3,heading4,heading5,heading6,heading7);
		System.out.println("_________________________________________________________________________________________________________________________________________________________________________");
		while(rs.next())
		{
			//printing isssues


			int id=rs.getInt("ISSUE_ID");
			String summary=rs.getString("ISSUE_SUMMARY");	
			String desc=rs.getString("ISSUE_DESCRIPTION");
			int uid=rs.getInt("IDENTIFIED_BY_CU_ID");
			int depid2=rs.getInt("RELATED_TO_CD_ID");	
			depempid=depid2;
			String pri=rs.getString("STATUS");	
			String status=rs.getString("PRIORITY");
			//System.out.println(id+"\t"+summary+"\t"+desc+"\t"+uid+"\t"+depid+"\t"+pro+"\t"+pri+"\t"+status);
			System.out.printf("  %-7d %-30s %-50s        %-20d %-10d %-13s %s %n",id,summary,desc,uid,depid2,pri,status);


		}
		System.out.println("_________________________________________________________________________________________________________________________________________________________________________");
		System.out.println("\n\n\n");

		do {
			System.out.println("ENTER ISSUE ID DO YOU WANK TO WORK OR ENTER 0 TO GO BACK TO PREVIOUS MENU");
			issueid=Integer.parseInt(br.readLine());
			updateissuestatus( depempid,issueid);


		}while(issueid!=0);

		}
		return 1;
	}

	public int updateissuestatus(int depid, int issueid2) throws Exception//

	{
		String query="UPDATE  issues SET STATUS='WORKING' WHERE RELATED_TO_CD_ID="+depid+"and ISSUE_ID="+issueid2;
		//String query="update table college_department_user set cdu_name='ntr' where cdu_id=1";
		Connection con=ConnectionManager.getConnection();
		PreparedStatement ps=con.prepareStatement(query);
		ps.executeUpdate();
		con.close();

		return 0;

	}



}
