package com.javaex.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.service.GuestbookService;
import com.javaex.vo.GuestbookVo;

@Controller
@RequestMapping("/api/guestbook")
public class ApiGuestbookController {

	@Autowired
	private GuestbookService guestbookService;

	@RequestMapping("/addList")
	public String addList() {
		System.out.println("[ApiGuestbookController.addList()]");
		return "aGuestbook/addList";
	}

	@ResponseBody
	@RequestMapping("/list")
	public List<GuestbookVo> list() {
		System.out.println("[ApiGuestbookController.list()]");
		List<GuestbookVo> guestbookList = guestbookService.getList();
		System.out.println(guestbookList);
		// js로 응답할때 문자열로 번역해서 보내야함
		// 이건 스프링이알아서 해줌
		// 그냥리턴에담고, 어노테이션에 @ResponseBody를 붙여
		// 응답문서의 Body에 문자 형태로 붙어서 가면 json이 해석해줌
		return guestbookList;
	}

	@ResponseBody
	@RequestMapping("/write")
	public GuestbookVo write(@ModelAttribute GuestbookVo guestbookVo) {
		System.out.println("[ApiGuestbookController.write()]");
		System.out.println(guestbookVo);
		
		GuestbookVo gVo = guestbookService.addGuestResultVo(guestbookVo);
		System.out.println(gVo);
		
		return gVo;
	}
}
