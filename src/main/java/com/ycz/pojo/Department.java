package com.ycz.pojo;

import java.util.ArrayList;
import java.util.List;

public class Department {
	
	private int depId;
	private String depName;
	private String depLeader;
	
	private List<Employee> empList = new ArrayList<Employee>();//注入依赖
	
	public List<Employee> getEmpList() {
		return empList;
	}
	public void setEmpList(List<Employee> empList) {
		this.empList = empList;
	}
	public int getDepId() {
		return depId;
	}
	public void setDepId(int depId) {
		this.depId = depId;
	}
	public String getDepName() {
		return depName;
	}
	public void setDepName(String depName) {
		this.depName = depName;
	}
	public String getDepLeader() {
		return depLeader;
	}
	public void setDepLeader(String depLeader) {
		this.depLeader = depLeader;
	}
	
	public Department() {
		
	}
	
	public Department(int depId,String depName,String depLeader) {
		this.depId = depId;
		this.depName = depName;
		this.depLeader = depLeader;
	}
	
	/**
	 * 重写toString方法
	 */
	@Override
    public String toString() {
		return "Department{" + "\n" 
                + "depId: " + depId + "," + "\n" 
				+ "depName: " + depName + "," + "\n"
                + "depLeader: " + depLeader + "\n" + "}";
	}
}
