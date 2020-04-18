package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.Department;
import utility.ConnectionManager;

public class DepartmentDaoImpl implements DepartmentDaoInterface {

	@Override
	public int AddDepartment(Department department) throws Exception {

		String INSERT_DEPARTMENT_QUERY="INSERT INTO COLLEGE_DEPARTMENT(CD_ID,CD_NAME,CD_NUMBER)VALUES(?,?,?)";
		String count="SELECT  COUNT(*) AS ROWCOUNT FROM COLLEGE_DEPARTMENT";
		int result = 0;
		Connection con=ConnectionManager.getConnection();
		ResultSet rs=con.createStatement().executeQuery(count);
		rs.next();
		int cdid=rs.getInt("ROWCOUNT");
		rs.close();
		department.setDepartment_id(cdid);
		PreparedStatement ps=con.prepareStatement(INSERT_DEPARTMENT_QUERY);
		ps.setInt(1,++cdid);
		ps.setString(2,department.getDepartment_name());
		ps.setString(3,department.getDepartment_number());
		result=ps.executeUpdate();



		return result;
	}

}
