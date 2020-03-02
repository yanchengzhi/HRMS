package com.ycz.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.ycz.pojo.Department;
import com.ycz.service.DepartmentService;
import com.ycz.utils.JsonMsg;

@RestController
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
	@RequestMapping(value = "addDepartment", method = RequestMethod.PUT)
	public JsonMsg addDepartment(int id, String name, String leader) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("depId", id);
		map.put("depName", name);
		map.put("depLeader", leader);
		int res = service.addDepartment(map);
		if (res != 1) {
			return JsonMsg.fail().addInfo("add_dept_error", "����쳣��");
		}
		return JsonMsg.success();
	}

	/**
	 * ɾ������
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "removeDepartment", method = RequestMethod.GET)
	public JsonMsg removeDepartment(int id) {
		int res = service.removeDepartment(id);
		if (res == 1) {
			return JsonMsg.success();
		} else {
			return JsonMsg.fail().addInfo("del_dept_error", "ɾ���쳣");
		}
	}

	/**
	 * �޸Ĳ�����Ϣ
	 * 
	 * @return
	 */
	@RequestMapping(value = "modifyDepartment", method = RequestMethod.GET)
	public JsonMsg modifyDepartment(int id, String name, String lead) {
		Department dep = new Department();
		dep.setDepId(id);
		dep.setDepName(name);
		dep.setDepLeader(lead);
		int res = service.modifyDepartment(dep);
		if (res == 1) {
			return JsonMsg.success();
		} else {
			return JsonMsg.fail().addInfo("update_dept_error", "���Ÿ���ʧ��");
		}
	}

	/**
	 * ͨ��ID���Ҽ�¼��
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "findDepById", method = RequestMethod.GET)
	public JsonMsg findDepById(int id) {
		Department dep = service.findDepById(id);
		// ע�⣬�Ƚ���Ϊ���ж�
		if (dep != null) {
			System.out.println("����ID��" + dep.getDepId());
			System.out.println("�������ƣ�" + dep.getDepName());
			System.out.println("�����쵼��" + dep.getDepLeader());
			return JsonMsg.success().addInfo("dep", dep);
		} else {
			System.out.println("û�в鵽�κμ�¼��");
			return JsonMsg.fail().addInfo("get_dept_error", "�޲�����Ϣ");
		}
	}

	/**
	 * ͳ�Ʊ����ܹ��ļ�¼����
	 * 
	 * @return
	 */
	@RequestMapping("countDep")
	public ModelAndView countDep() {
		ModelAndView mav = new ModelAndView();
		int res = service.countDep();
		System.out.println("һ����" + res + "����¼��");
		mav.setViewName("main");
		return mav;
	}

	/**
	 * ��ȡ���в�����Ϣ
	 * 
	 * @return
	 */
	@RequestMapping("getDepList")
	public ModelAndView getDepList() {
		ModelAndView mav = new ModelAndView();
		List<Department> depList = service.getDepList();
		for (Department d : depList) {
			System.out.println(d.toString());
		}
		int totalItems = service.countDep();
		mav.addObject("totalItems", totalItems);
		mav.addObject("depList", depList);
		mav.setViewName("departmentPage");
		return mav;
	}
}
