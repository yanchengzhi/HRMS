package com.ycz.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * �Է�����Ϣ���з�װ
 * @author Administrator
 *
 */
public final class JsonMsg {
	
	private Integer code;
	private String msg;
	private Map<String, Object> extendInfo = new HashMap<>();
	
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Map<String, Object> getExtendInfo() {
		return extendInfo;
	}
	public void setExtendInfo(Map<String, Object> extendInfo) {
		this.extendInfo = extendInfo;
	}
	
	//�����ɹ������
	public static JsonMsg success() {
		JsonMsg res = new JsonMsg();
		res.setCode(100);
		res.setMsg("�����ɹ���");
		return res;
	}
	
	//����ʧ�ܵ����
	public static JsonMsg fail() {
		JsonMsg res = new JsonMsg();
		res.setCode(200);
		res.setMsg("����ʧ�ܣ�");
		return res;
	}
	
    public JsonMsg addInfo(String key, Object obj){
        this.extendInfo.put(key, obj);
        return this;
    }
}
