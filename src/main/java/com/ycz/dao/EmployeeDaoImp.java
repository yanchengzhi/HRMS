package com.ycz.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.pagehelper.PageHelper;
import com.ycz.pojo.Department;
import com.ycz.pojo.Employee;

@Component
public class EmployeeDaoImp implements EmployeeDao {

	@Autowired
	private SqlSessionFactoryBean ssfb;// ◊¢»Î“¿¿µ

	@Override
	public int countEmp() {
		int res = 0;
		try {
			SqlSession ss = ssfb.getObject().openSession();
			res = ss.selectOne("emps.countEmp");
			ss.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public int addEmp(Employee emp) {
		int res = 0;
		try {
			SqlSession ss = ssfb.getObject().openSession();
			res = ss.insert("emps.addEmp", emp);
			ss.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public int removeEmp(int id) {
		int res = 0;
		try {
			SqlSession ss = ssfb.getObject().openSession();
			res = ss.delete("emps.removeEmp", id);
			ss.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public int modify(Employee emp) {
		int res = 0;
		try {
			SqlSession ss = ssfb.getObject().openSession();
			res = ss.update("emps.modifyEmp", emp);
			ss.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public Employee findEmpByName(String name) {
		Employee emp = null;
		try {
			SqlSession ss = ssfb.getObject().openSession();
			emp = ss.selectOne("emps.findEmpByName", name);
			ss.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return emp;
	}

	@Override
	public List<Employee> findEmpPaged(int start, int pageSize) {
		List<Employee> empList = new ArrayList<Employee>();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("start", start);
		map.put("pageSize", pageSize);
		try {
			SqlSession ss = ssfb.getObject().openSession();
			empList = ss.selectList("emps.findEmpPaged", map);
			ss.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return empList;
	}

	@Override
	public List<Employee> findEmpPaged2(int page, int pageSize) {
		PageHelper.startPage(page, pageSize);
		List<Employee> empList = new ArrayList<Employee>();
		try {
			SqlSession ss = ssfb.getObject().openSession();
			empList = ss.selectList("emps.findEmpPaged2");
			ss.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return empList;
	}

	@Override
	public Employee findEmpById(int id) {
		Employee emp = null;
		try {
			SqlSession ss = ssfb.getObject().openSession();
			emp = ss.selectOne("emps.findEmpById", id);
			ss.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return emp;
	}

	@Override
	public List<Employee> findEmpBySex(int gender, int page, int pageSize) {
		PageHelper.startPage(page, pageSize);
		List<Employee> empList = new ArrayList<Employee>();
		try {
			SqlSession ss = ssfb.getObject().openSession();
			empList = ss.selectList("emps.findEmpBySex", gender);
			ss.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return empList;
	}

	@Override
	public List<Employee> findEmpByDep(int id) {
		List<Employee> empList = new ArrayList<Employee>();
		try {
			SqlSession ss = ssfb.getObject().openSession();
			empList = ss.selectList("emps.findEmpByDep", id);
			ss.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return empList;
	}

	@Override
	public List<Employee> findEmpByArgs(int gender, String email, int did) {
		List<Employee> empList = new ArrayList<Employee>();
		Map <String,Object> map = new HashMap<String,Object>();
		email = "%@163.com";
		map.put("gender", gender);
		map.put("email", email);
		map.put("depId", did);
		try {
			SqlSession ss = ssfb.getObject().openSession();
			empList = ss.selectList("emps.findEmpByArgs", map);
			ss.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return empList;
	}

	@Override
	public List<Department> findEmpDep() {
		List<Department> depList = new ArrayList<Department>();
		try {
			SqlSession ss = ssfb.getObject().openSession();
			depList = ss.selectList("emps.findEmpDep");
			ss.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return depList;
	}

}
