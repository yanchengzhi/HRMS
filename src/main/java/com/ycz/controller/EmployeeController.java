package com.ycz.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ycz.pojo.Department;
import com.ycz.pojo.Employee;
import com.ycz.service.EmployeeService;

@Controller
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
	public String countEmp() {
		int res = service.countEmp();
		System.out.println("һ����" + res + "��Ա����");
		return "index";
	}

	/**
	 * ���Ա��
	 * 
	 * @return
	 */
	@RequestMapping("addEmp")
	public String addEmp() {
		Employee emp = new Employee(2020078, "����", 1, "235235@163.com", 1005);
		int res = service.addEmp(emp);
		System.out.println("�ɹ����" + res + "����¼");
		return "index";
	}

	/**
	 * ɾ��Ա��
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("removeEmp")
	public String removeEmp(int id) {
		int res = service.removeEmp(id);
		System.out.println("�ɹ�ɾ����" + res + "����¼��");
		return "index";
	}

	/**
	 * �޸�Ա����Ϣ
	 * 
	 * @return
	 */
	@RequestMapping("modifyEmp")
	public String modifyEmp() {
		Employee emp = new Employee();
		emp.setEmpId(2020002);
		emp.setEmpName("������СŮ��");
		emp.setEmail("15432132@qq.com");
		emp.setDepId(1003);
		int res = service.modify(emp);
		System.out.println("�ɹ��޸���" + res + "����¼��");
		return "index";
	}

	/**
	 * ���������Ҽ�¼
	 * 
	 * @param name
	 * @return
	 */
	@RequestMapping("findEmpByName")
	public String findEmpByName(String name) {
		Employee emp = service.findEmpByName(name);
		System.out.println("��ѯ������£�");
		System.out.println(emp.toString());
		return "index";
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
	@RequestMapping("findEmpPaged2")
	public String findEmpPaged2(int page, int pageSize) {
		List<Employee> empList = service.findEmpPaged2(page, pageSize);
		System.out.println("ÿҳ" + pageSize + "����¼����" + page + "ҳ��¼���£�");
		System.out.println("------------------------------------------");
		for (Employee e : empList) {
			System.out.println(e.getEmpId() + "\t" + e.getEmpName() + "\t" + e.getGender() + "\t" + e.getEmail() + "\t"
					+ e.getDepId());
		}
		return "index";
	}

	/**
	 * ����ID����Ա��
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("findEmpById")
	public String findEmpById(int id) {
		Employee emp = service.findEmpById(id);
		System.out.println("��ѯ������£�");
		System.out.println(emp.toString());
		return "index";
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
			System.out.println(d.getDepName() + "���Ϊ" + d.getDepId() 
			+ "����" + d.getDepLeader() + "�쵼��һ����" + empList.size() + "��Ա������������Ϣ���£�");
			for (Employee e : empList) {
				System.out.println(e.getEmpId() + "\t" + e.getEmpName() + "\t" + e.getGender());
			}
			System.out.println("------------------------------------------");
		}
		return "index";
	}

}
