package com.ycz.pojo;

public class Department {
	
	private int depId;
	private String depName;
	private String depLeader;
	
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
	 * ÷ÿ–¥toString∑Ω∑®
	 */
	@Override
    public String toString() {
		return "Department{" + "\n" 
                + "depId: " + depId + "," + "\n" 
				+ "depName: " + depName + "," + "\n"
                + "depLeader: " + depLeader + "\n" + "}";
	}
}
