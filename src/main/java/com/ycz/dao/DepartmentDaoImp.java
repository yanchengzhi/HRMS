package com.ycz.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ycz.pojo.Department;

@Component
public class DepartmentDaoImp implements DepartmentDao {

	@Autowired
	private SqlSessionFactoryBean ssfb;// 注入依赖

	@Override
	public int addDepartment(Map<String, Object> map) {
		int res = 0;
		try {
			SqlSession ss = ssfb.getObject().openSession();
			res = ss.insert("depts.addDepartment", map);
			System.out.println("成功添加" + res + "条记录！");
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
			res = ss.delete("depts.removeDepartment", id);
			System.out.println("成功删除" + res + "条记录！");
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
			res = ss.update("depts.modifyDepartment", dep);
			System.out.println("成功修改" + res + "条记录！");
			ss.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public Department findDepById(int id) {
		SqlSession ss = null;
		try {
			ss = ssfb.getObject().openSession();
		} catch (Exception e) {
			e.printStackTrace();
		}
		Department dep = ss.selectOne("depts.findDepById", id);
		ss.close();
		return dep;
	}

	@Override
	public int countDep() {
		int res = 0;
		try {
			SqlSession ss = ssfb.getObject().openSession();
			res = (Integer)ss.selectOne("depts.countDep");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

}
