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
 * 登录控制器
 * 
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/hrms/")
public class LoginController {
	
	@Autowired
	private HttpServletRequest request;//自动注入依赖了
	

	/**
	 * 登录时跳转到登录页面
	 */
	@RequestMapping(value = "login", method = RequestMethod.GET)
	public String login() {
		return "login";
	}

	/**
	 * 对登录页面的用户名和密码进行简单后台校验
	 * @throws IOException 
	 * @throws ServletException 
	 */
	@ResponseBody
	@RequestMapping(value = "doLogin", method = RequestMethod.POST)
	public JsonMsg doLogin(String username,String password){
		// 获取用户输入的信息
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
	 * 跳到主页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "main", method = RequestMethod.GET)
	public String main() {
		return "main";
	}

	/**
	 * 退出登录，从主页面跳到登录页面
	 */
	@RequestMapping(value = "logout", method = RequestMethod.GET)
	public String logout() {
		return "login";
	}
}
