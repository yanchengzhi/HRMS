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
	private EmployeeService service;// 注入依赖的服务层组件

	/**
	 * 查询总员工人数
	 * 
	 * @return
	 */
	@RequestMapping("countEmp")
	public int countEmp() {
		int res = service.countEmp();
		System.out.println("一共有" + res + "名员工！");
		return res;
	}

	/**
	 * 添加员工
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
	 * 删除员工
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
			return JsonMsg.fail().addInfo("emp_del_error", "员工删除异常");
		}
	}

	/**
	 * 修改员工信息
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
	 * 按姓名查找是否有重复记录
	 * 
	 * @param name
	 * @return
	 */
	@RequestMapping(value = "checkEmpExist", method = RequestMethod.GET)
	public JsonMsg checkEmpExist(String name) {
		// 对输入的姓名和邮箱格式进行验证
		String regName = "(^[a-zA-Z0-9_-]{3,16}$)|(^[\\u2E80-\\u9FFF]{2,5})";
		if (!name.matches(regName)) {// 验证姓名是否匹配
			return JsonMsg.fail().addInfo("name_reg_error", "输入姓名为2-5位中文或6-16位英文和数字组合");
		}
		Employee emp = service.findEmpByName(name);
		if (emp != null) {
			return JsonMsg.fail().addInfo("name_reg_error", "用户名重复！");
		} else {
			return JsonMsg.success();
		}
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
	@RequestMapping(value = "findEmpPaged2", method = RequestMethod.GET)
	public ModelAndView findEmpPaged2(Integer page) {
		int pageSize = 10;
		ModelAndView mav = new ModelAndView();
		List<Employee> empList = service.findEmpPaged2(page, pageSize);
		// 获取总的记录数
		int totalItems = service.countEmp();
		// 统计总页数
		int pageSum = (totalItems % pageSize == 0) ? (totalItems / pageSize) : (int) (totalItems / pageSize) + 1;
		mav.addObject("page", page);
		mav.addObject("empList", empList);
		mav.addObject("totalItems", totalItems);
		mav.addObject("pageSum", pageSum);
		mav.setViewName("employeePage");
		return mav;
	}

	/**
	 * 添加或删除新记录后，重新查询页数
	 * 因为临界值存在页数增加或减少的情况
	 * @return
	 */
	@RequestMapping(value="getPageSum",method=RequestMethod.GET)
	public JsonMsg getPageSum() {
		int totalItems = service.countEmp();
		int pageSum = (totalItems % 10 == 0) ? (totalItems / 10) : (totalItems / 10) + 1;
	    return JsonMsg.success().addInfo("pageSum", pageSum);
	}

	/**
	 * 按照ID查找员工
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
			System.out.println(d.getDepName() + "编号为" + d.getDepId() + "，由" + d.getDepLeader() + "领导，一共有"
					+ empList.size() + "名员工，所有人信息如下：");
			for (Employee e : empList) {
				System.out.println(e.getEmpId() + "\t" + e.getEmpName() + "\t" + e.getGender());
			}
			System.out.println("------------------------------------------");
		}
		return "index";
	}

}
