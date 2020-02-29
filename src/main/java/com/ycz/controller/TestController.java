package com.ycz.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/test/")
public class TestController {
	
	@RequestMapping(value="index",method=RequestMethod.GET)
	public String index() {
		System.out.println("一个简单的小测试...");
		return "main";
	}

}
