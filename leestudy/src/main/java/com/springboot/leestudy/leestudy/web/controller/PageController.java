package com.springboot.leestudy.leestudy.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {
	
	@GetMapping("/auth/login") // 로그인
	public String signin() {
		return "auth/login";
	}

}
