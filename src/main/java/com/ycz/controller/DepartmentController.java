package com.ycz.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ycz.pojo.Department;
import com.ycz.service.DepartmentService;

@Controller
@RequestMapping("/dept/")
public class DepartmentController {

	@Autowired
	private DepartmentService service;// 注入依赖

	/**
	 * 添加部门
	 * 
	 * @param id
	 * @param name
	 * @param leader
	 * @return
	 */
	@RequestMapping("addDepartment")
	public String addDepartment(Integer id, String name, String leader) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("depId", id);
		map.put("depName", name);
		map.put("depLeader", leader);
		service.addDepartment(map);
		return "index";
	}

	/**
	 * 删除部门
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("removeDepartment")
	public String removeDepartment(int id) {
		service.removeDepartment(id);
		return "index";
	}

	/**
	 * 修改部门信息
	 * 
	 * @return
	 */
	@RequestMapping("modifyDepartment")
	public String modifyDepartment() {
		Department dep = new Department();
		dep.setDepId(1007);
		dep.setDepName("安保部-new");
		dep.setDepLeader("啦啦啦");
		service.modifyDepartment(dep);
		return "index";
	}

	@RequestMapping("findDepById")
	public ModelAndView findDepById(int id) {
		Department dep = service.findDepById(id);
		//注意，先进行为空判断
		if (dep != null) {
			System.out.println("部门ID：" + dep.getDepId());
			System.out.println("部门名称：" + dep.getDepName());
			System.out.println("部门领导：" + dep.getDepLeader());
		}else {
			System.out.println("没有查到任何记录！");
		}
		ModelAndView mav = new ModelAndView();
		mav.addObject("dept",dep);
		mav.setViewName("queryDep");
		return mav;
	}
}
