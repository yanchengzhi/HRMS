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
	private DepartmentService service;// ע������

	/**
	 * ��Ӳ���
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
	 * ɾ������
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
	 * �޸Ĳ�����Ϣ
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

	@RequestMapping("findDepById")
	public ModelAndView findDepById(int id) {
		Department dep = service.findDepById(id);
		//ע�⣬�Ƚ���Ϊ���ж�
		if (dep != null) {
			System.out.println("����ID��" + dep.getDepId());
			System.out.println("�������ƣ�" + dep.getDepName());
			System.out.println("�����쵼��" + dep.getDepLeader());
		}else {
			System.out.println("û�в鵽�κμ�¼��");
		}
		ModelAndView mav = new ModelAndView();
		mav.addObject("dept",dep);
		mav.setViewName("queryDep");
		return mav;
	}
}
