package com.ycz.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ycz.dao.EmployeeDao;
import com.ycz.pojo.Department;
import com.ycz.pojo.Employee;

@Service
public class EmployeeServiceImp implements EmployeeService {
	
	@Autowired
	private EmployeeDao eDao;//注入依赖的数据处理层组件

	@Override
	public int countEmp() {
		return eDao.countEmp();
	}

	@Override
	public int addEmp(Employee emp) {
		return eDao.addEmp(emp);
	}

	@Override
	public int removeEmp(int id) {
		return eDao.removeEmp(id);
	}

	@Override
	public int modify(Employee emp) {
		return eDao.modify(emp);
	}

	@Override
	public Employee findEmpByName(String name) {
		return eDao.findEmpByName(name);
	}

	@Override
	public List<Employee> findEmpPaged(int start, int pageSize) {
		return eDao.findEmpPaged(start, pageSize);
	}

	@Override
	public List<Employee> findEmpPaged2(int page, int pageSize) {
		return eDao.findEmpPaged2(page, pageSize);
	}

	@Override
	public Employee findEmpById(int id) {
		return eDao.findEmpById(id);
	}

	@Override
	public List<Employee> findEmpBySex(int gender, int page, int pageSize) {
		return eDao.findEmpBySex(gender, page, pageSize);
	}

	@Override
	public List<Employee> findEmpByDep(int id) {
		return eDao.findEmpByDep(id);
	}

	@Override
	public List<Employee> findEmpByArgs(int gender,String email,int did) {
		return eDao.findEmpByArgs(gender,email,did);
	}

	@Override
	public List<Department> findEmpDep() {
		return eDao.findEmpDep();
	}
	
	

}
