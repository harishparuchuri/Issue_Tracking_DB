package dao;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import utility.ConnectionManager;

public class IssueOperation {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	int issueid=0;
	int depempid=0;

	//view issues and update issue status to working
	public int viewissues(String type) throws Exception
	{

		int depid=0;
		int empoperation=-10;
		String query = null;
		if(type=="pending")
		{
			query="select ISSUE_ID,ISSUE_SUMMARY,ISSUE_DESCRIPTION,IDENTIFIED_BY_CU_ID,RELATED_TO_CD_ID,PRIORITY,STATUS from issues where status='"+type+"'";
		}
		else if(type=="working")
		{
			query="select ISSUE_ID,ISSUE_SUMMARY,ISSUE_DESCRIPTION,IDENTIFIED_BY_CU_ID,RELATED_TO_CD_ID,PRIORITY,STATUS from issues where status='working'";

		}
		else if(type=="COMPLETED")
		{
			query="select ISSUE_ID,ISSUE_SUMMARY,ISSUE_DESCRIPTION,IDENTIFIED_BY_CU_ID,RELATED_TO_CD_ID,PRIORITY,STATUS from issues where status='COMPLETED'";


		}
		else if(type=="all")
		{
			query="select ISSUE_ID,ISSUE_SUMMARY,ISSUE_DESCRIPTION,IDENTIFIED_BY_CU_ID,RELATED_TO_CD_ID,PRIORITY,STATUS from issues";


		}
		else
		{

			//employee specific issues;
			empoperation=1;
			int empId=Integer.parseInt(type);
			String getdepid="select CDU_DEPARTMENT FROM COLLEGE_DEPARTMENT_USER WHERE CDU_ID="+empId;
			Connection con=ConnectionManager.getConnection();
			ResultSet rs=con.createStatement().executeQuery(getdepid);
			rs.next();
			depid=rs.getInt("CDU_DEPARTMENT");

			query="SELECT ISSUE_ID,ISSUE_SUMMARY,ISSUE_DESCRIPTION,IDENTIFIED_BY_CU_ID,RELATED_TO_CD_ID,PRIORITY,STATUS FROM ISSUES WHERE RELATED_TO_CD_ID="+depid+" and STATUS='pending'";
		}

		Connection con=ConnectionManager.getConnection();
		ResultSet rs=con.createStatement().executeQuery(query);
		if(rs.next()==false)
		{
			System.out.println("________________________________________________________");
			System.out.println("\t\tNO ISSSUES TO WORK THANK YOU");
			System.out.println("________________________________________________________");
		}
		else {
			String heading1="ISSUE_ID";
			String heading2="ISSUE_SUMMARY";
			String heading3="ISSUE_DESCRIPTION";
			String heading4="IDENTIFIED_BY_CU_ID";
			String heading5="RELATED_TO_CD_ID";
			String heading6="PRIORITY";
			String heading7="STATUS";

			System.out.printf("%s    %-30s %-50s %s %s %s %10s %n",heading1,heading2,heading3,heading4,heading5,heading6,heading7);
			System.out.println("_________________________________________________________________________________________________________________________________________________________________________");

			int did=0;
			while(rs.next())
			{

				int id=rs.getInt("ISSUE_ID");
				String summary=rs.getString("ISSUE_SUMMARY");
				String des=rs.getString("ISSUE_DESCRIPTION");
				int uid=rs.getInt("IDENTIFIED_BY_CU_ID");
				did=rs.getInt("RELATED_TO_CD_ID");
				String pri=rs.getString("PRIORITY");
				String status=rs.getString("STATUS");


				System.out.printf("  %-7d %-30s %-50s        %-20d %-10d %-13s %s %n",id,summary,des,uid,did,pri,status);


			}
			System.out.println("_________________________________________________________________________________________________________________________________________________________________________");

			if(empoperation==1) {

				do {
					System.out.println("\n\nENTER ISSUE ID DO YOU WANT WORK |OR|  ENTER |0| GO BACK TO PREVIOUS MENU ");
					issueid=Integer.parseInt(br.readLine());
					if(issueid!=0)
					{
						updateissuestatus(did,issueid);
					}


				}while(issueid!=0);	
			}
		}	return depid;

	}

	public int updateissuestatus(int depid, int issueid2) throws Exception//

	{

		String query="UPDATE  issues SET STATUS='working' WHERE RELATED_TO_CD_ID="+depid+"and ISSUE_ID="+issueid2;

		Connection con=ConnectionManager.getConnection();
		PreparedStatement ps=con.prepareStatement(query);
		ps.executeUpdate();
		con.close();
		System.out.println("_____________________________________________________________________");
		System.out.println("\nISSUE STATUS UPDATED TO WORKING THANK YOU\n");
		System.out.println("_____________________________________________________________________");
		return 0;

	}
	//resolve issue set status complete and send sms
	public int resolveissue(int empid) throws Exception
	{



		String getdepid="select CDU_DEPARTMENT FROM COLLEGE_DEPARTMENT_USER WHERE CDU_ID="+empid;
		Connection con=ConnectionManager.getConnection();
		ResultSet rs=con.createStatement().executeQuery(getdepid);
		rs.next();
		int depid=rs.getInt("CDU_DEPARTMENT");

		rs.close();

		String	Fetch_results="SELECT ISSUE_ID,ISSUE_SUMMARY,ISSUE_DESCRIPTION,IDENTIFIED_BY_CU_ID,RELATED_TO_CD_ID,PRIORITY,STATUS FROM ISSUES WHERE RELATED_TO_CD_ID="+depid+"and status='working'";

		con=ConnectionManager.getConnection();
		rs=con.createStatement().executeQuery(Fetch_results);

		if(rs.next()==false)
		{
			System.out.println("NO ISSUES TO RESOLVE THANK YOU\n\n");
		}
		else {

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

				String pri=rs.getString("STATUS");	
				String status=rs.getString("PRIORITY");

				System.out.printf("  %-7d %-30s %-50s        %-20d %-10d %-13s %s %n",id,summary,desc,uid,depid,pri,status);


			}
			System.out.println("_________________________________________________________________________________________________________________________________________________________________________");





			do {
				System.out.println("ENTER ISSUE ID DO YOU WANK TO RESOLVE |OR| ENTER |0| TO GO BACK TO PREVIOUS MENU");
				issueid=Integer.parseInt(br.readLine());
				if(issueid!=0)
				{
					resolveissuestatus( depid,issueid);
				}



			}while(issueid!=0);

		}

		return 1;


	}
	//update resolve status of the issue
	public int resolveissuestatus(int depid, int issueid2) throws Exception//

	{
		System.out.println("Please enter Issue Resolution summary");
		String rsum=br.readLine();

		String getnumber="SELECT COLLEGE_USER.P_NUMBER FROM COLLEGE_USER LEFT JOIN ISSUES ON issues.IDENTIFIED_BY_CU_ID= college_user.CU_ID";
		Connection con=ConnectionManager.getConnection();
		ResultSet rs=con.createStatement().executeQuery(getnumber);
		rs.next();
		String num=rs.getString("P_NUMBER");
		sendsms(num,rsum);




		String query="UPDATE  issues SET STATUS='COMPLETED',RESOLUTION_SUMMARY='"+rsum+"' WHERE RELATED_TO_CD_ID="+depid+"and ISSUE_ID="+issueid2;

		con=ConnectionManager.getConnection();
		PreparedStatement ps=con.prepareStatement(query);
		ps.executeUpdate();
		con.close();
		System.out.println("____________________________________________________________________________________");
		System.out.println("\n\n ISSUE RESOLVED AND USER GET MESSAGE --> "+rsum+"\n");
		System.out.println("____________________________________________________________________________________");
		return 0;

	}

	//Sending sms using api
	public String sendsms(String num, String rsum)
	{
		try {
			// Construct data
			String apiKey = "apikey=" + "FoNPUZXpwmw-mH9frWDoSc8bCGGiVc4e0zBNtBNo4U";
			String message = "&message=" +rsum;
			String sender = "&sender=" + "TXTLCL";
			String numbers = "&numbers=" + num;

			// Send data
			HttpURLConnection conn = (HttpURLConnection) new URL("https://api.textlocal.in/send/?").openConnection();
			String data = apiKey + numbers + message + sender;
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Length", Integer.toString(data.length()));
			conn.getOutputStream().write(data.getBytes("UTF-8"));
			final BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			final StringBuffer stringBuffer = new StringBuffer();
			String line;
			while ((line = rd.readLine()) != null) {
				stringBuffer.append(line);

			}
			rd.close();

			return stringBuffer.toString();
		} catch (Exception e) {
			System.out.println("Error SMS "+e);
			return "Error "+e;
		}
	}



}
