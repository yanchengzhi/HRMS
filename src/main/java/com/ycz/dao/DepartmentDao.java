package com.ycz.dao;

import java.util.Map;

import com.ycz.pojo.Department;

public interface DepartmentDao {
	
	public int addDepartment(Map<String, Object> map);//��Ӳ��� 
	
	public int removeDepartment(int id);//ɾ������
	
	public int modifyDepartment(Department dep);//�޸Ĳ�����Ϣ

}
