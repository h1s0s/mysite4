package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.service.GuestbookService;
import com.javaex.vo.GuestbookVo;

@Controller
@RequestMapping(value = "/guest", method = { RequestMethod.GET, RequestMethod.POST })
public class GuestbookController {

	@Autowired
	private GuestbookService guestbookService;


	@RequestMapping(value="/addList", method= {RequestMethod.GET,RequestMethod.POST})
	public String addList(Model model) {
		System.out.println("GuestbookController.addList()");
		
		List<GuestbookVo> guestbookList = guestbookService.getList();
		model.addAttribute("guestbookList", guestbookList);
		
		return "addList";
	}

	@RequestMapping(value = "/add", method = { RequestMethod.GET, RequestMethod.POST })
	public String add(@ModelAttribute GuestbookVo guestbookVo) {
		System.out.println("[GuestbookController.add()");

		guestbookService.guestbookInsert(guestbookVo);

		return "redirect:/guest/addList";
	}

	@RequestMapping(value = "/deleteForm", method = { RequestMethod.GET, RequestMethod.POST })
	public String deleteForm(@RequestParam("no") int no) {
		System.out.println("[GuestbookController.deleteForm()");

		return "/guestbook/deleteForm";
	}

	@RequestMapping(value = "/delete", method = { RequestMethod.GET, RequestMethod.POST })
	public String delete(@RequestParam("no") int no, @RequestParam("password") String password) {
		System.out.println("[GuestbookController.delete()");

		guestbookService.guestbookDelete(no, password);

		return "redirect:/guest/addList";
	}

}
