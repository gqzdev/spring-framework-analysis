package com.gqzdev.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName HelloController
 * @Description
 * @Author ganquanzhong
 * @Date2020/5/10 22:23
 * @Version
 **/

@RestController
public class HelloController {

	@RequestMapping("/hello")
	public String hello(){
		return "hello  it self create programming";
	}
}
