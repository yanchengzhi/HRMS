package com.ycz.dao;

import java.util.List;
import java.util.Map;

import com.ycz.pojo.Department;

/**
 * ������ű��¼���࣬����ѯҵ�񼴿�����
 * @author Administrator
 *
 */
public interface DepartmentDao {
	
	public int addDepartment(Map<String, Object> map);//��Ӳ��� 
	
	public int removeDepartment(int id);//ɾ������
	
	public int modifyDepartment(Department dep);//�޸Ĳ�����Ϣ
	
	public Department findDepById(int id);//ͨ��ID���Ҳ���
	
	public int countDep();//ͳ��һ����������¼
	
	public List<Department> getDepList();//��ȡ���в�����Ϣ

}
