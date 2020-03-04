package com.ycz.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ycz.utils.JsonMsg;

/**
 * ��¼������
 * 
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/hrms/")
public class LoginController {
	
	@Autowired
	private HttpServletRequest request;//�Զ�ע��������
	

	/**
	 * ��¼ʱ��ת����¼ҳ��
	 */
	@RequestMapping(value = "login", method = RequestMethod.GET)
	public String login() {
		return "login";
	}

	/**
	 * �Ե�¼ҳ����û�����������м򵥺�̨У��
	 * @throws IOException 
	 * @throws ServletException 
	 */
	@ResponseBody
	@RequestMapping(value = "doLogin", method = RequestMethod.POST)
	public JsonMsg doLogin(String username,String password){
		// ��ȡ�û��������Ϣ
		 username = request.getParameter("username");
		 password = request.getParameter("password");
		if (username!=null && !username.equals("") && username.equals("admin") &&
				password!=null && !password.equals("") && password.equals("admin")) {	
		 System.out.println(username + "----" + password);
			return JsonMsg.success();
		} else {
			return JsonMsg.fail();
		}
	}

	/**
	 * ������ҳ��
	 * 
	 * @return
	 */
	@RequestMapping(value = "main", method = RequestMethod.GET)
	public String main() {
		return "main";
	}

	/**
	 * �˳���¼������ҳ��������¼ҳ��
	 */
	@RequestMapping(value = "logout", method = RequestMethod.GET)
	public String logout() {
		return "login";
	}
}
