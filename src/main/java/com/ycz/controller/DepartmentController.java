package com.ycz.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ycz.pojo.Department;
import com.ycz.service.DepartmentService;

@RestController
@RequestMapping("/dept/")
public class DepartmentController {
      
	@Autowired
	private DepartmentService service;//ע������
	
	/**
	 * ��Ӳ���
	 * @param id
	 * @param name
	 * @param leader
	 * @return
	 */
	@RequestMapping("addDepartment")
	public String addDepartment(Integer id,String name,String leader) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("depId",id);
		map.put("depName",name);
		map.put("depLeader",leader);
		service.addDepartment(map);
		return "index";
	}
	
	/**
	 * ɾ������
	 * @param id
	 * @return
	 */
	@RequestMapping("removeDepartment")
	public String removeDepartment(int id) {
		service.removeDepartment(id);
		return "index";
	}
	
	/**
	 * 
	 * @return
	 */
	@RequestMapping("modifyDepartment")
	public String modifyDepartment() {
		Department dep = new Department();
		dep.setDepId(1007);
		dep.setDepName("������-new");
		dep.setDepLeader("������");
		service.modifyDepartment(dep);
		return "index";
	}
}
