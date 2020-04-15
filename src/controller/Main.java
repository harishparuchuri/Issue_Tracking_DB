package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;

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
		User user=new User();
		UserDaoImpl userdao=new UserDaoImpl();
		Employee employee=new Employee();
		EmployeeDaoImpl employeedao= new EmployeeDaoImpl();
		AdminLogin admin=new AdminLogin();
		Issue issue=new Issue();
		IssueDaoImpl issuedao=new IssueDaoImpl();
		Department department=new Department();
		DepartmentDaoImpl departmentdao=new DepartmentDaoImpl();

		//Date 
		String pattern = "dd-MM-yyyy";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		String date = simpleDateFormat.format(new Date());
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int select;
		int userid;//for user specific operations
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
						adminmenu();
						select=Integer.parseInt(br.readLine()); 
						switch(select)
						{
						case 1:

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
							System.out.println("add user");


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
							System.out.println("Add Department");


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
							System.out.println("view issue");
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
						//userdao.loginUser(user)==true
						if(employeedao.loginEmployee(employee)==true)
						{
							eflag=true;
							System.out.println("Login successfully\n\n");
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
							break;
						case 2:
							System.out.println("fix issue");
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
						{
							uflag=true;
							System.out.println("Login successfully\n\n");
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

}


