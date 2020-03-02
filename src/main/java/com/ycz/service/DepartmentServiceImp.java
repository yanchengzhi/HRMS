package com.ycz.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ycz.dao.DepartmentDao;
import com.ycz.pojo.Department;

@Service
public class DepartmentServiceImp implements DepartmentService {
	
	@Autowired
	private DepartmentDao dDao;//◊¢»Î“¿¿µ

	@Override
	public int addDepartment(Map<String, Object> map) {		
		return dDao.addDepartment(map);
	}

	@Override
	public int removeDepartment(int id) {
		return dDao.removeDepartment(id);
	}

	@Override
	public int modifyDepartment(Department dep) {
		return dDao.modifyDepartment(dep);
	}

	@Override
	public Department findDepById(int id) {
		return dDao.findDepById(id);
	}

	@Override
	public int countDep() {
		return dDao.countDep();
	}

	@Override
	public List<Department> getDepList() {
		return dDao.getDepList();
	}

}
