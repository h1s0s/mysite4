package com.javaex.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/guestbook")
public class ApiGuestbookController {

	@RequestMapping("/addList")
	public String addList() {
		System.out.println("[ApiGuestbookController.addList()]");
		return "aGuestbook/addList";
	}
	@RequestMapping("/list")
	public String list() {
		System.out.println("[ApiGuestbookController.list()]");
		return "";
	}
}
