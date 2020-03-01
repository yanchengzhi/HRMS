package com.ycz.service;

import java.util.Map;

import com.ycz.pojo.Department;

public interface DepartmentService {
	
	public int addDepartment(Map<String, Object> map);
	
	public int removeDepartment(int id);
	
	public int modifyDepartment(Department dep);
	
	public Department findDepById(int id);
	
	public int countDep();

}
