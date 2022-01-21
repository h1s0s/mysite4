package com.javaex.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {

	@RequestMapping(value="/", method={RequestMethod.GET, RequestMethod.POST})
	public String main() {
		System.out.println("[MainController.main()]");
		
		return "/main/index";
	}
//	protected void doGet(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		System.out.println("/main");
//		
//		// 포워드, 리다이렉트
//		//1. 포워드 : 포워드는 파일위치를 지정해준다 <-> 리다이렉트는 url을 입력해 준다
//		//2.포워드는 만들어진 jsp를 사용자에게 보여줌.
//		//3. 리다이렉트는 만들어진 사이트로 보냄
//		WebUtil.forward(request, response, "/WEB-INF/views/main/index.jsp");
//	}


}
