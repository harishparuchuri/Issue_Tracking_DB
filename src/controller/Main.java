package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

import dao.IssueOperation;
import dao.AdminLogin;
import dao.DepartmentDaoImpl;
import dao.EmployeeDaoImpl;
import dao.IssueDaoImpl;
import dao.UserDaoImpl;
import model.Department;
import model.Employee;
import model.Issue;
import model.User;

public class Main {


	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		//Objects
		
		DateFormat df = new SimpleDateFormat("dd-MM-yy");
	       Date dateobj = new Date();
	       System.out.println("date "+df.format(dateobj));
		
		
		
		
		User user=new User();
		UserDaoImpl userdao=new UserDaoImpl();
		Employee employee=new Employee();
		EmployeeDaoImpl employeedao= new EmployeeDaoImpl();
		AdminLogin admin=new AdminLogin();
		Issue issue=new Issue();
		IssueDaoImpl issuedao=new IssueDaoImpl();
		Department department=new Department();
		DepartmentDaoImpl departmentdao=new DepartmentDaoImpl();
		IssueOperation issuetask=new IssueOperation();

		//Date 
		String pattern = "dd-MM-yyyy";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		String date = simpleDateFormat.format(new Date());
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int select;
		int select1;
		int choice;
		int userid=0;//for user specific operations
		int Empid=0;//for employee specific operations
		Boolean flag=false;
		Boolean eflag=false;
		Boolean uflag=false;

		do {
			mainmenu();

			select=Integer.parseInt(br.readLine()); 
			switch(select)
			{
			case 1://admin menu display 
				do {
					if(flag!=true)
					{
						if(admin.AdminLogin()==true)//Admin Login
						//if(true)//disable login
						{
							flag=true;
							System.out.println("Login successfully\n\n");
						}
						else
						{
							System.out.println("Please Enter Correct username or password");
						}

					}	
					if(flag==true)
					{
						adminmenu();//Admin menu display
						select=Integer.parseInt(br.readLine()); 
						switch(select)
						{
						case 1:
							//Adding Employee to DataBase
							System.out.println("add employee");
							System.out.println("Enter Employee Namge");
							String ename=br.readLine();
							System.out.println("Enter Employee Email");
							String email=br.readLine();
							System.out.println("Enter Employee Role");
							String role=br.readLine();
							System.out.println("Enter Employee Department id");
							int departmentId=Integer.parseInt(br.readLine());
							System.out.println("Enter Employee Password");
							String epassword=br.readLine();

							employee.setEname(ename);
							employee.setEmail(email);
							employee.setErole(role);
							employee.setEdepartment(departmentId);
							employee.setEpassword(epassword);

							employeedao.signUp(employee);


							break;
						case 2:
							//Adding User to DataBase
							System.out.println("Enter User Name");
							String name=br.readLine();
							System.out.println("Enter User Phone Number");
							String pnumber=br.readLine();
							System.out.println("Enter User Password");
							String password=br.readLine();

							user.setUname(name);
							user.setUnumber(pnumber);
							user.setUpassword(password);
							userdao.signUp(user);




							break;
						case 3:
							//Adding Department To DataBase
							System.out.println("ENTER  DEPARTMENT NAME");
							String dname=br.readLine();
							System.out.println("ENTER  DEPARTMENT NUMBER");
							String dnumber=br.readLine();

							department.setDepartment_name(dname);
							department.setDepartment_number(dnumber);
							int status=departmentdao.AddDepartment(department);
							if(status==1)
							{
								System.out.println("\n________________________________________________________________________________________________");
								System.out.println("|\t\t\tDepartment "+dname+" Added Sucessfully With Department ID "+department.getDepartment_id()+"\t\t|");
								System.out.println("-------------------------------------------------------------------------------------------------\n");
							}


							break;
						case 4:
							//Check issues
							
							do{
								issuetype();
								select1=Integer.parseInt(br.readLine());
								switch(select1) {


								case 1:
									issuetask. viewissues("pending");
									break;
								case 2:
									issuetask. viewissues("working");
									break;
								case 3:issuetask. viewissues("completed");
								break;
								case 4:issuetask. viewissues("all");	
								break;
								default:break;
								}


							}
							while(select1!=0);


							break;	

						default: break;

						}


					}

				}
				while (select !=0);
				break;
			case 2:
				
				System.out.println("Enter the user name");
				String name=br.readLine();

				System.out.println("Enter password");
				String pass=br.readLine();
				employee.setEname(name);
				employee.setEpassword(pass);
				

				do {
					if(eflag!=true)
					{
						
						if(employeedao.loginEmployee(employee)==true)
						//if(true)//disable login
						{
							eflag=true;
							Empid=employeedao.returnid();
							System.out.println("Login successfully with "+Empid+"\n\n");
							
						}
						else
						{
							System.out.println("Please Enter Correct username or password");
							break;
						}

					}	
					if(eflag==true)
					{

						employeemenu();
						select=Integer.parseInt(br.readLine()); 
						switch(select)
						{
						case 1:
							System.out.println("view issue");
							String eid=Integer.toString(Empid);
							issuetask. viewissues(eid);	
							break;
						case 2:
							System.out.println("fix issue");
							do{
								fixissue();
								select1=Integer.parseInt(br.readLine());
								switch(select1) {


								case 1:
									issuetask.updateissue(Empid,"high");
									
									
									break;
								case 2:
									issuetask. updateissue(Empid,"all");
									break;
								case 3:
									
									String date1 = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
									issuetask.setResolvedOn(date1);
									issuetask.resolveissue(Empid,date);
									
									
									
								
								default:break;
								}

								
							}
							while(select1!=0);


							break;	
							
						case 0:System.out.println("Back to main menu");
						eflag=false;
						break;
						default:
							break;
						}
					}
				}
				while (select !=0);
				break;





			case 3:
				
				System.out.println("Enter the user name");
				String ename=br.readLine();
				System.out.println("Enter password");
				String epass=br.readLine();
				user.setUname(ename);
				user.setUpassword(epass);

 					
				do {
					if(uflag!=true)
					
					{
						if(userdao.loginUser(user)==true)//user login
						//if(true) //disable login
						{
							uflag=true;
							System.out.println("Login successfully\n\n");
							userid=userdao.returnid();
							
							
						}
						else
						{
							System.out.println("Please Enter Correct username or password");
							break;
						}

					}	
					if(uflag==true)
					{

						usermenu();
						select=Integer.parseInt(br.readLine()); 
						switch(select)
						{
						case 1:
							int id=userdao.returnid();
							System.out.println("Add issue for user "+id);

							System.out.println("ENTER ISSUE_SUMMARY");
							String summary=br.readLine();

							System.out.println("ENTER ISSUE_DESCRIPTION");
							String description=br.readLine();
							System.out.println("ENTER CONSERT DEPARTMENT ID");
							int departmentid=Integer.parseInt(br.readLine());
							//System.out.println("ISSUE_CREATED_DATE");//print not required
							String Idate=date;
							System.out.println("PRIORITY");
							String priority=br.readLine();



							issue.setIssue_summary(summary);
							issue.setISSUE_DESCRIPTION(description);
							issue.setRelated_cd_id(departmentid);
							issue.setPostedOn(Idate);
							issue.setPriority(priority);
							issue.setIdentified_by_cid(userdao.returnid());
							issuedao.Addissue(issue);
							

							break;
						case 2:
							System.out.println("Check Issue");
							System.out.println(userid);
							issuedao.viewstatus(userid);
							break;
						case 0:System.out.println("Back to main menu");
						uflag=false;
						break;
						default:
							break;
						}

					}
				}

				while (select !=0);
				break;

			default:
				break;



			}
		}
		while (select !=4);
		System.out.println("Program Closed Thank You");



	}



	static void mainmenu()
	{
		System.out.println("1. Admin ");
		System.out.println("2. Employee");
		System.out.println("3. User");
		System.out.println("4. Exit");
		System.out.println("------------------------------------------------------------------------------------------------------------");
		System.out.println("Select any option");
	}

	static void adminmenu()
	{
		System.out.println("1. Add Employee");
		System.out.println("2. Add User");
		System.out.println("3. Add Department");
		System.out.println("4. View Issues");
		System.out.println("0.LOGOUT AND  BACK TO MAIN MENU");
		System.out.println("------------------------------------------------------------------------------------------------------------");
		System.out.println("Select any option");
	}
	static void employeemenu()
	{
		System.out.println("1. View Issues");
		System.out.println("2. Fix Issue");
		System.out.println("0.LOGOUT AND  BACK TO MAIN MENU");
		System.out.println("------------------------------------------------------------------------------------------------------------");
		System.out.println("Select any option");
	}

	static void usermenu()
	{
		System.out.println("1. Add Issue");
		System.out.println("2. Check Issue Status");
		System.out.println("0.LOGOUT AND  BACK TO MAIN MENU");
		System.out.println("------------------------------------------------------------------------------------------------------------");
		System.out.println("Select any option");
	}

	static void issuetype()
	{
		System.out.println("Select Which type of Issues Do You Want View");
		System.out.println("1. Pending Issues");
		System.out.println("2. Working Issues");
		System.out.println("3. Resolved Issues");
		System.out.println("4. All Issues");
		System.out.println("0. BACK TO Admin MENU");
	}
	static void fixissue()
	{
		System.out.println("Select Which type of Issues Do You Want Fix OR RESOLVE ");
		System.out.println("1. HIGH PRIORITY");
		System.out.println("2. ALL TYPE OF ISSUES");
		System.out.println("3. ISSUE TO COMPLETE");
		System.out.println("0. BACK TO EMPLOYEE MENU");
	}

}


