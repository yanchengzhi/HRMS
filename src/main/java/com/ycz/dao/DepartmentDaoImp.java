package com.ycz.dao;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ycz.pojo.Department;

@Component
public class DepartmentDaoImp implements DepartmentDao {

	@Autowired
	private SqlSessionFactoryBean ssfb;// ע������

	@Override
	public int addDepartment(Map<String, Object> map) {
		int res = 0;
		try {
			SqlSession ss = ssfb.getObject().openSession();
			res = ss.insert("depts.addDepartment",map);
			System.out.println("�ɹ����" + res + "����¼��");
			ss.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public int removeDepartment(int id) {
		int res = 0;
		try {
			SqlSession ss = ssfb.getObject().openSession();
			res = ss.delete("depts.removeDepartment",id);
			System.out.println("�ɹ�ɾ��" + res + "����¼��");
			ss.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public int modifyDepartment(Department dep) {
		int res = 0;
		try {
			SqlSession ss = ssfb.getObject().openSession();
			res = ss.update("depts.modifyDepartment",dep);
			System.out.println("�ɹ��޸�" + res + "����¼��");
			ss.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

}
