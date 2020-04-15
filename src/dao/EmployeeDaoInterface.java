package dao;

import model.Employee;

public interface EmployeeDaoInterface {

	int signUp(Employee employee) throws Exception;
	boolean loginEmployee(Employee  employee) throws Exception;
}
