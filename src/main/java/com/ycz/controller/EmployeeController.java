package com.ycz.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.ycz.pojo.Department;
import com.ycz.pojo.Employee;
import com.ycz.service.EmployeeService;
import com.ycz.utils.JsonMsg;

@RestController
@RequestMapping("/emp/")
public class EmployeeController {

	@Autowired
	private EmployeeService service;// ע�������ķ�������

	/**
	 * ��ѯ��Ա������
	 * 
	 * @return
	 */
	@RequestMapping("countEmp")
	public int countEmp() {
		int res = service.countEmp();
		System.out.println("һ����" + res + "��Ա����");
		return res;
	}

	/**
	 * ���Ա��
	 * 
	 * @return
	 */
	@RequestMapping(value = "addEmp", method = RequestMethod.GET)
	public JsonMsg addEmp(int id, String name, int gender, String email, int depId) {
		Employee emp = new Employee();
		emp.setEmpId(id);
		emp.setEmpName(name);
		emp.setGender(gender);
		emp.setEmail(email);
		emp.setDepId(depId);
		int res = service.addEmp(emp);
		if (res == 1) {
			return JsonMsg.success();
		} else {
			return JsonMsg.fail();
		}
	}

	/**
	 * ɾ��Ա��
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "removeEmp", method = RequestMethod.GET)
	public JsonMsg removeEmp(int id) {
		int res = service.removeEmp(id);
		if (res == 1) {
			return JsonMsg.success();
		} else {
			return JsonMsg.fail().addInfo("emp_del_error", "Ա��ɾ���쳣");
		}
	}

	/**
	 * �޸�Ա����Ϣ
	 * 
	 * @return
	 */
	@RequestMapping(value="modifyEmp",method=RequestMethod.GET)
	public JsonMsg modifyEmp(int id,String name,int gender,String email,int depId) {
		Employee emp = new Employee();
		emp.setEmpId(id);
		emp.setEmpName(name);
		emp.setGender(gender);
		emp.setEmail(email);
		emp.setDepId(depId);
		int res = service.modify(emp);
		if(res==1) {
			return JsonMsg.success();
		}else {
			return JsonMsg.fail();
		}
	}

	/**
	 * �����������Ƿ����ظ���¼
	 * 
	 * @param name
	 * @return
	 */
	@RequestMapping(value = "checkEmpExist", method = RequestMethod.GET)
	public JsonMsg checkEmpExist(String name) {
		// ������������������ʽ������֤
		String regName = "(^[a-zA-Z0-9_-]{3,16}$)|(^[\\u2E80-\\u9FFF]{2,5})";
		if (!name.matches(regName)) {// ��֤�����Ƿ�ƥ��
			return JsonMsg.fail().addInfo("name_reg_error", "��������Ϊ2-5λ���Ļ�6-16λӢ�ĺ��������");
		}
		Employee emp = service.findEmpByName(name);
		if (emp != null) {
			return JsonMsg.fail().addInfo("name_reg_error", "�û����ظ���");
		} else {
			return JsonMsg.success();
		}
	}

	/**
	 * ��ҳ��ѯ �����������ȱ�ݵģ�������������󣬴Ӷ�������¼��ʼ����鷳��������������ʵ�ַ�ҳ
	 * 
	 * @param start    �Ӷ�������¼��ʼ
	 * @param pageSize ÿҳ����
	 * @return
	 */
	@RequestMapping("findEmpPaged")
	public String findEmpPaged(int start, int pageSize) {
		List<Employee> empList = service.findEmpPaged(start, pageSize);
		System.out.println("ÿҳ" + pageSize + "����¼����" + (start / pageSize + 1) + "ҳ��¼���£�");
		System.out.println("------------------------------------------");
		for (Employee e : empList) {
			System.out.println(e.getEmpId() + "\t" + e.getEmpName() + "\t" + e.getGender() + "\t" + e.getEmail() + "\t"
					+ e.getDepId());
		}
		return "index";
	}

	/**
	 * ʹ��pagehelper��ҳ������з�ҳ �Ƽ�ʹ������
	 * 
	 * @param page
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value = "findEmpPaged2", method = RequestMethod.GET)
	public ModelAndView findEmpPaged2(Integer page) {
		int pageSize = 10;
		ModelAndView mav = new ModelAndView();
		List<Employee> empList = service.findEmpPaged2(page, pageSize);
		// ��ȡ�ܵļ�¼��
		int totalItems = service.countEmp();
		// ͳ����ҳ��
		int pageSum = (totalItems % pageSize == 0) ? (totalItems / pageSize) : (int) (totalItems / pageSize) + 1;
		mav.addObject("page", page);
		mav.addObject("empList", empList);
		mav.addObject("totalItems", totalItems);
		mav.addObject("pageSum", pageSum);
		mav.setViewName("employeePage");
		return mav;
	}

	/**
	 * ��ӻ�ɾ���¼�¼�����²�ѯҳ��
	 * ��Ϊ�ٽ�ֵ����ҳ�����ӻ���ٵ����
	 * @return
	 */
	@RequestMapping(value="getPageSum",method=RequestMethod.GET)
	public JsonMsg getPageSum() {
		int totalItems = service.countEmp();
		int pageSum = (totalItems % 10 == 0) ? (totalItems / 10) : (totalItems / 10) + 1;
	    return JsonMsg.success().addInfo("pageSum", pageSum);
	}

	/**
	 * ����ID����Ա��
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value="findEmpById",method=RequestMethod.GET)
	public JsonMsg findEmpById(int id) {
		Employee emp = service.findEmpById(id);
        if(emp!=null) {
        	return JsonMsg.success().addInfo("emp", emp);
        }else {
        	return JsonMsg.fail();
        }
	}

	/**
	 * �����Ա����Ա�����ҷ�ҳ
	 * 
	 * @param gender   �Ա�0��ʾŮ��1��ʾ��
	 * @param page     �ڼ�ҳ
	 * @param pageSize ÿҳ��¼����
	 * @return
	 */
	@RequestMapping("findEmpBySex")
	public String findEmpBySex(int gender, int page, int pageSize) {
		List<Employee> empList = service.findEmpBySex(gender, page, pageSize);
		if (gender == 0) {
			System.out.println("���ҵ�����Ů��ְ����¼���£�");
			System.out.println("ÿҳ" + pageSize + "����¼����" + page + "ҳ��¼���£�");
			for (Employee e : empList) {
				System.out.println(e.getEmpId() + "\t" + e.getEmpName() + "\t" + e.getGender() + "\t" + e.getEmail()
						+ "\t" + e.getDepId());
			}
		} else {
			System.out.println("���ҵ���������ְ����¼���£�");
			System.out.println("ÿҳ" + pageSize + "����¼����" + page + "ҳ��¼���£�");
			for (Employee e : empList) {
				System.out.println(e.getEmpId() + "\t" + e.getEmpName() + "\t" + e.getGender() + "\t" + e.getEmail()
						+ "\t" + e.getDepId());
			}
		}
		return "index";
	}

	/**
	 * ���ղ��Ų�������Ա��
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("findEmpByDep")
	public String findEmpByDep(int id) {
		List<Employee> empList = service.findEmpByDep(id);
		switch (id) {
		case 1001:
			System.out.println("�з���һ����" + empList.size() + "��Ա������¼���£�");
			break;
		case 1002:
			System.out.println("UI��һ����" + empList.size() + "��Ա������¼���£�");
			break;
		case 1003:
			System.out.println("�г���һ����" + empList.size() + "��Ա������¼���£�");
			break;
		case 1004:
			System.out.println("���۲�һ����" + empList.size() + "��Ա������¼���£�");
			break;
		case 1005:
			System.out.println("���߲�һ����" + empList.size() + "��Ա������¼���£�");
			break;
		case 1006:
			System.out.println("�⽻��һ����" + empList.size() + "��Ա������¼���£�");
			break;
		case 1007:
			System.out.println("������һ����" + empList.size() + "��Ա������¼���£�");
			break;
		}
		for (Employee e : empList) {
			System.out.println(e.getEmpId() + "\t" + e.getEmpName() + "\t" + e.getGender() + "\t" + e.getEmail() + "\t"
					+ e.getDepId());
		}
		return "index";
	}

	/**
	 * ����ָ���������Ҽ�¼
	 * 
	 * @param gender �Ա�
	 * @param email  �����ʽ
	 * @param did    ����ID
	 * @return
	 */
	@RequestMapping("findEmpByArgs")
	public String findEmpByArgs(int gender, int did) {
		List<Employee> empList = service.findEmpByArgs(gender, "%163@qq.com", did);
		System.out.println("���������ļ�¼���£�");
		for (Employee e : empList) {
			System.out.println(e.getEmpId() + "\t" + e.getEmpName() + "\t" + e.getGender() + "\t" + e.getEmail() + "\t"
					+ e.getDepId());
		}
		return "index";
	}

	/**
	 * ���������ѯ
	 * 
	 * @return
	 */
	@RequestMapping("findEmpDep")
	public String findEmpDep() {
		List<Department> depList = service.findEmpDep();
		for (Department d : depList) {
			List<Employee> empList = d.getEmpList();
			System.out.println(d.getDepName() + "���Ϊ" + d.getDepId() + "����" + d.getDepLeader() + "�쵼��һ����"
					+ empList.size() + "��Ա������������Ϣ���£�");
			for (Employee e : empList) {
				System.out.println(e.getEmpId() + "\t" + e.getEmpName() + "\t" + e.getGender());
			}
			System.out.println("------------------------------------------");
		}
		return "index";
	}

}
