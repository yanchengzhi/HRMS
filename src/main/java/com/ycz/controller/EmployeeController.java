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
	private EmployeeService service;// 注入依赖的服务层组件

	/**
	 * 查询总员工人数
	 * 
	 * @return
	 */
	@RequestMapping("countEmp")
	public String countEmp() {
		int res = service.countEmp();
		System.out.println("一共有" + res + "名员工！");
		return "index";
	}

	/**
	 * 添加员工
	 * 
	 * @return
	 */
	@RequestMapping("addEmp")
	public String addEmp() {
		Employee emp = new Employee(2020078, "秦玉安", 1, "235235@163.com", 1005);
		int res = service.addEmp(emp);
		System.out.println("成功添加" + res + "条记录");
		return "index";
	}

	/**
	 * 删除员工
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("removeEmp")
	public String removeEmp(int id) {
		int res = service.removeEmp(id);
		System.out.println("成功删除了" + res + "条记录！");
		return "index";
	}

	/**
	 * 修改员工信息
	 * 
	 * @return
	 */
	@RequestMapping("modifyEmp")
	public String modifyEmp() {
		Employee emp = new Employee();
		emp.setEmpId(2020002);
		emp.setEmpName("何立立小女神");
		emp.setEmail("15432132@qq.com");
		emp.setDepId(1003);
		int res = service.modify(emp);
		System.out.println("成功修改了" + res + "条记录！");
		return "index";
	}

	/**
	 * 按姓名查找记录
	 * 
	 * @param name
	 * @return
	 */
	@RequestMapping("findEmpByName")
	public String findEmpByName(String name) {
		Employee emp = service.findEmpByName(name);
		System.out.println("查询结果如下：");
		System.out.println(emp.toString());
		return "index";
	}

	/**
	 * 分页查询 这个方法是有缺陷的，如果数据量过大，从多少条记录开始会很麻烦，不建议用这种实现分页
	 * 
	 * @param start    从多少条记录开始
	 * @param pageSize 每页条数
	 * @return
	 */
	@RequestMapping("findEmpPaged")
	public String findEmpPaged(int start, int pageSize) {
		List<Employee> empList = service.findEmpPaged(start, pageSize);
		System.out.println("每页" + pageSize + "条记录，第" + (start / pageSize + 1) + "页记录如下：");
		System.out.println("------------------------------------------");
		for (Employee e : empList) {
			System.out.println(e.getEmpId() + "\t" + e.getEmpName() + "\t" + e.getGender() + "\t" + e.getEmail() + "\t"
					+ e.getDepId());
		}
		return "index";
	}

	/**
	 * 使用pagehelper分页插件进行分页 推荐使用这种
	 * 
	 * @param page
	 * @param pageSize
	 * @return
	 */
	@RequestMapping("findEmpPaged2")
	public String findEmpPaged2(int page, int pageSize) {
		List<Employee> empList = service.findEmpPaged2(page, pageSize);
		System.out.println("每页" + pageSize + "条记录，第" + page + "页记录如下：");
		System.out.println("------------------------------------------");
		for (Employee e : empList) {
			System.out.println(e.getEmpId() + "\t" + e.getEmpName() + "\t" + e.getGender() + "\t" + e.getEmail() + "\t"
					+ e.getDepId());
		}
		return "index";
	}

	/**
	 * 按照ID查找员工
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("findEmpById")
	public String findEmpById(int id) {
		Employee emp = service.findEmpById(id);
		System.out.println("查询结果如下：");
		System.out.println(emp.toString());
		return "index";
	}

	/**
	 * 按照性别查找员工并且分页
	 * 
	 * @param gender   性别：0表示女，1表示男
	 * @param page     第几页
	 * @param pageSize 每页记录条数
	 * @return
	 */
	@RequestMapping("findEmpBySex")
	public String findEmpBySex(int gender, int page, int pageSize) {
		List<Employee> empList = service.findEmpBySex(gender, page, pageSize);
		if (gender == 0) {
			System.out.println("查找的所有女性职工记录如下：");
			System.out.println("每页" + pageSize + "条记录，第" + page + "页记录如下：");
			for (Employee e : empList) {
				System.out.println(e.getEmpId() + "\t" + e.getEmpName() + "\t" + e.getGender() + "\t" + e.getEmail()
						+ "\t" + e.getDepId());
			}
		} else {
			System.out.println("查找的所有男性职工记录如下：");
			System.out.println("每页" + pageSize + "条记录，第" + page + "页记录如下：");
			for (Employee e : empList) {
				System.out.println(e.getEmpId() + "\t" + e.getEmpName() + "\t" + e.getGender() + "\t" + e.getEmail()
						+ "\t" + e.getDepId());
			}
		}
		return "index";
	}

	/**
	 * 按照部门查找所有员工
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("findEmpByDep")
	public String findEmpByDep(int id) {
		List<Employee> empList = service.findEmpByDep(id);
		switch (id) {
		case 1001:
			System.out.println("研发部一共有" + empList.size() + "名员工，记录如下：");
			break;
		case 1002:
			System.out.println("UI部一共有" + empList.size() + "名员工，记录如下：");
			break;
		case 1003:
			System.out.println("市场部一共有" + empList.size() + "名员工，记录如下：");
			break;
		case 1004:
			System.out.println("销售部一共有" + empList.size() + "名员工，记录如下：");
			break;
		case 1005:
			System.out.println("决策部一共有" + empList.size() + "名员工，记录如下：");
			break;
		case 1006:
			System.out.println("外交部一共有" + empList.size() + "名员工，记录如下：");
			break;
		case 1007:
			System.out.println("安保部一共有" + empList.size() + "名员工，记录如下：");
			break;
		}
		for (Employee e : empList) {
			System.out.println(e.getEmpId() + "\t" + e.getEmpName() + "\t" + e.getGender() + "\t" + e.getEmail() + "\t"
					+ e.getDepId());
		}
		return "index";
	}

	/**
	 * 按照指定条件查找记录
	 * 
	 * @param gender 性别
	 * @param email  邮箱格式
	 * @param did    部门ID
	 * @return
	 */
	@RequestMapping("findEmpByArgs")
	public String findEmpByArgs(int gender, int did) {
		List<Employee> empList = service.findEmpByArgs(gender, "%163@qq.com", did);
		System.out.println("符合条件的记录如下：");
		for (Employee e : empList) {
			System.out.println(e.getEmpId() + "\t" + e.getEmpName() + "\t" + e.getGender() + "\t" + e.getEmail() + "\t"
					+ e.getDepId());
		}
		return "index";
	}

	/**
	 * 连接两表查询
	 * 
	 * @return
	 */
	@RequestMapping("findEmpDep")
	public String findEmpDep() {
		List<Department> depList = service.findEmpDep();
		for (Department d : depList) {
			List<Employee> empList = d.getEmpList();
			System.out.println(d.getDepName() + "编号为" + d.getDepId() 
			+ "，由" + d.getDepLeader() + "领导，一共有" + empList.size() + "名员工，所有人信息如下：");
			for (Employee e : empList) {
				System.out.println(e.getEmpId() + "\t" + e.getEmpName() + "\t" + e.getGender());
			}
			System.out.println("------------------------------------------");
		}
		return "index";
	}

}
