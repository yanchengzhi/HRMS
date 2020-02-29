package com.ycz.pojo;

public class Employee {
	
	private Integer empId;
	private String empName;
	private Integer gender;
	private String email;
	private Integer depId;
	
	private Department department;//注入依赖
	
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	public Integer getId() {
		return empId;
	}
	public void setId(Integer id) {
		this.empId = id;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public Integer getGender() {
		return gender;
	}
	public void setGender(Integer gender) {
		this.gender = gender;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Integer getDepId() {
		return depId;
	}
	public void setDepId(Integer depId) {
		this.depId = depId;
	}
	
	public Employee() {
		
	}
	
	public Employee(Integer id,String empName,Integer gender,String email,Integer depId) {
		this.empId = id;
		this.empName = empName;
		this.gender = gender;
		this.email = email;
		this.depId = depId;
	}
	
	/**
	 * 重写toString方法
	 */
	@Override
	public String toString() {  		
	   return "Employee{" + "\n"
	           + "empId: " + empId + "," + "\n"
			   + "empName: " + empName + "," + "\n"
	           + "gender: " + gender + "," + "\n"
			   + "email: " + email + "," + "\n"
	           + "depId: " + depId + "\n" + "}";
	}
	
	

}
