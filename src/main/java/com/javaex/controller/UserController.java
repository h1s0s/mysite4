package com.javaex.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.javaex.service.UserService;
import com.javaex.vo.UserVo;

@Controller
@RequestMapping(value = "/user", method = { RequestMethod.GET, RequestMethod.POST })
public class UserController {

	@Autowired
	private UserService userService;

	// 로그인폼
	@RequestMapping(value = "/loginForm", method = { RequestMethod.GET, RequestMethod.POST })
	public String loginForm() {
		System.out.println("[UserComtroller.loginForm()]");
		return "/user/loginForm";
	}

	// 로그인(세션 활용)
	@RequestMapping(value = "/login", method = { RequestMethod.GET, RequestMethod.POST })
	public String login(@ModelAttribute UserVo userVo, HttpSession session) {
		System.out.println("[UserComtroller.loginin()]");

		UserVo authUser = userService.login(userVo);

		if (authUser != null) {
			System.out.println("[로그인성공]");
			session.setAttribute("authUser", authUser);
			return "redirect:/";
		} else {
			System.out.println("[로그인실패]");
			return "redirect:/user/loginForm?result=fail";
		}
	}
	@RequestMapping(value = "/logout", method = { RequestMethod.GET, RequestMethod.POST })
	public String logout(HttpSession session) {
		System.out.println("[UserComtroller.logout()]");

		//세션의 정보 삭제
		session.removeAttribute("authUser");
		session.invalidate();//초기화
		
		return "redirect:/";
	}
	
	//
	@RequestMapping(value = "/joinForm", method = { RequestMethod.GET, RequestMethod.POST })
	public String joinForm() {
		System.out.println("[UserComtroller.joinForm()]");

		return "/user/joinForm";
	}

	@RequestMapping(value = "/join", method = { RequestMethod.GET, RequestMethod.POST })
	public String join(@ModelAttribute UserVo userVo) {
		System.out.println("[UserComtroller.join()]");
		userService.join(userVo);
		return "joinOk";
	}

	@RequestMapping(value = "/modifyForm", method = { RequestMethod.GET, RequestMethod.POST })
	public String modifyForm(HttpSession session, Model model) {
		System.out.println("[UserComtroller.modifyForm()]");
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		UserVo userVo = userService.modifyForm(authUser.getNo());
		model.addAttribute("userVo", userVo);
		return "/user/modifyForm";
	}

	@RequestMapping(value = "/modify", method = { RequestMethod.GET, RequestMethod.POST })
	public String modify(@ModelAttribute UserVo userVo) {
		System.out.println("[UserComtroller.modify()]");
		userService.modify(userVo);
		return "redirect:/";
	}
	


}
