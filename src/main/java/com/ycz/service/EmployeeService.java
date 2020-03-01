package com.ycz.service;

import java.util.List;

import com.ycz.pojo.Department;
import com.ycz.pojo.Employee;

public interface EmployeeService {
	
	public int countEmp();
	
	public int addEmp(Employee emp);
	
	public int removeEmp(int id);
	
	public int modify(Employee emp);
	
	public Employee findEmpByName(String name);
	
	public List<Employee> findEmpPaged(int start, int pageSize);
	
	public List<Employee> findEmpPaged2(int page, int pageSize);
	
	public Employee findEmpById(int id);
	
	public List<Employee> findEmpBySex(int gender, int page, int pageSize);
	
	public List<Employee> findEmpByDep(int id);
	
	public List<Employee> findEmpByArgs(int gender,String email,int did);
	
	public List<Department> findEmpDep();

}
