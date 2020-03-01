package com.ycz.dao;

import java.util.List;

import com.ycz.pojo.Department;
import com.ycz.pojo.Employee;

public interface EmployeeDao {
	
	public int countEmp();//查询总的记录条数
	
	public int addEmp(Employee emp);//添加记录
	
	public int removeEmp(int id);//删除记录
	
	public int modify(Employee emp);//修改记录
	
	public Employee findEmpByName(String name);//按姓名查找单条员工记录
	
	public List<Employee> findEmpPaged(int start,int pageSize);//分页查询
	
	public List<Employee> findEmpPaged2(int page,int pageSize);//使用pagehelper插件进行分页查询
	
	public Employee findEmpById(int id);//按照员工id查找记录
	
	public List<Employee> findEmpBySex(int gender,int page,int pageSize);//按照性别查询并且分页
 
	public List<Employee> findEmpByDep(int id);//按照部门查找员工记录
	
	public List<Employee> findEmpByArgs(int gender,String email,int did);//按照指定条件查找员工记录

	public List<Department> findEmpDep();//连接查询
}
