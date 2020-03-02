package com.ycz.dao;

import java.util.List;
import java.util.Map;

import com.ycz.pojo.Department;

/**
 * 这个部门表记录不多，简答查询业务即可满足
 * @author Administrator
 *
 */
public interface DepartmentDao {
	
	public int addDepartment(Map<String, Object> map);//添加部门 
	
	public int removeDepartment(int id);//删除部门
	
	public int modifyDepartment(Department dep);//修改部门信息
	
	public Department findDepById(int id);//通过ID查找部门
	
	public int countDep();//统计一共多少条记录
	
	public List<Department> getDepList();//获取所有部门信息

}
