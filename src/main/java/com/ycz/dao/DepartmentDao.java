package com.ycz.dao;

import java.util.Map;

import com.ycz.pojo.Department;

public interface DepartmentDao {
	
	public int addDepartment(Map<String, Object> map);//添加部门 
	
	public int removeDepartment(int id);//删除部门
	
	public int modifyDepartment(Department dep);//修改部门信息

}
