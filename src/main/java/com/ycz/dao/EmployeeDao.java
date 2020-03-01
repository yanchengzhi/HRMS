package com.ycz.dao;

import java.util.List;

import com.ycz.pojo.Department;
import com.ycz.pojo.Employee;

public interface EmployeeDao {
	
	public int countEmp();//��ѯ�ܵļ�¼����
	
	public int addEmp(Employee emp);//��Ӽ�¼
	
	public int removeEmp(int id);//ɾ����¼
	
	public int modify(Employee emp);//�޸ļ�¼
	
	public Employee findEmpByName(String name);//���������ҵ���Ա����¼
	
	public List<Employee> findEmpPaged(int start,int pageSize);//��ҳ��ѯ
	
	public List<Employee> findEmpPaged2(int page,int pageSize);//ʹ��pagehelper������з�ҳ��ѯ
	
	public Employee findEmpById(int id);//����Ա��id���Ҽ�¼
	
	public List<Employee> findEmpBySex(int gender,int page,int pageSize);//�����Ա��ѯ���ҷ�ҳ
 
	public List<Employee> findEmpByDep(int id);//���ղ��Ų���Ա����¼
	
	public List<Employee> findEmpByArgs(int gender,String email,int did);//����ָ����������Ա����¼

	public List<Department> findEmpDep();//���Ӳ�ѯ
}
