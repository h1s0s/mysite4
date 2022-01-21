package com.javaex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.javaex.dao.UserDao;
import com.javaex.vo.UserVo;


@Controller
public class UserController {
    
	@Autowired
	UserDao userDao;
	
	//로그인폼
	@RequestMapping(value="/user/loginForm", method= {RequestMethod.GET, RequestMethod.POST})
	public String loginForm() {
		System.out.println("[UserComtroller.loginForm()]");
		return "/user/loginForm";
	}
	//로그인
	@RequestMapping(value="/user/login", method= {RequestMethod.GET, RequestMethod.POST})
	public String loginin(@ModelAttribute UserVo userVo) {
		System.out.println("[UserComtroller.loginin()]");
		
		UserVo authUser = userDao.getUser(userVo);
		return "/user/joinOk";
	}
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		System.out.println("/user");
//		String act = request.getParameter("action");
//		
//		if("joinForm".equals(act)) {
//			WebUtil.forward(request, response, "/WEB-INF/views/user/joinForm.jsp");
//		
//		} else if("join".equals(act)) {
//			String id = request.getParameter("id");
//			String password = request.getParameter("password");
//			String name = request.getParameter("name");
//			String gender = request.getParameter("gender");
//			
//			UserVo userVo = new UserVo(id, password, name, gender);
//			UserDao userDao = new UserDao();
//			
//			userDao.insert(userVo);
//			
//			WebUtil.forward(request, response, "/WEB-INF/views/user/joinOk.jsp");
//		
//		} else if("loginForm".equals(act)) {
//			WebUtil.forward(request, response, "/WEB-INF/views/user/loginForm.jsp");
//		
//		} else if("login".equals(act)) {
//			String id = request.getParameter("id");
//			String password = request.getParameter("password");
//			
//			UserDao userDao = new UserDao();
//			UserVo authVo = userDao.getUser(id, password);//맞는 사용자
//			//System.out.println(authVo.toString());
//			
//			// 세션의 어트리뷰트의 authUser이라는
//			// 공간에 로그인정보를 담고 관리한다.
//			// 로그인 되어 있지 않으면 null, 로그인 되어 있으면 값이 있음
//			// 모든 코드마다 이 값을 읽게 만들어주기
//			//System.out.println(userDao.insert(authVo)) +"건이 실행되었습니다.");
//			if(authVo == null) {
//				System.out.println("로그인 실패");
//				WebUtil.redirect(request, response, "/mysite/user?action=loginForm&result=fail");
//				
//			} else {
//				System.out.println("로그인 성공");
//				HttpSession session = request.getSession(); //지금 세션 값을 줘
//				session.setAttribute("authUser", authVo);//호출할 이름, 넣을 변수
//				
//				WebUtil.redirect(request, response, "/mysite/main");
//			}
//		} else if("logout".equals(act)){
//			System.out.println("로그아웃");
//			
//			HttpSession session = request.getSession();
//			session.removeAttribute("authUser");
//			session.invalidate();
//			
//			WebUtil.redirect(request, response, "/mysite/main");
//		} else if ("modifyForm".equals(act)){
//			HttpSession session = request.getSession();
//			UserVo authUser = (UserVo)session.getAttribute("authUser");
//			
//			int no = authUser.getNo();
//			//세션에 넘버를 변수에 저장
//			
//			UserDao userDao = new UserDao();
//			UserVo userVo = userDao.getUser(no);
//			
//			request.setAttribute("userVo", userVo);
//			WebUtil.forward(request, response, "/WEB-INF/views/user/modifyForm.jsp");
//		} else if("modify".equals(act)) { 
//			int no = Integer.parseInt(request.getParameter("no"));
//			String id = request.getParameter("id");
//			String password = request.getParameter("password");
//			String name = request.getParameter("name");
//			String gender = request.getParameter("gender");
//			
//			UserVo userVo = new UserVo(no, id, password, name, gender);
//			UserDao userDao = new UserDao();
//			
//			System.out.println(userVo);
//			
//			userDao.Update(userVo);
//			UserVo authVo = new UserVo();
//			authVo.setNo(userVo.getNo());
//			authVo.setName(userVo.getName());
//			
//			HttpSession session = request.getSession(); //지금 세션 값을 줘
//			session.setAttribute("authUser", authVo);//호출할 이름, 넣을 변수
//			System.out.println(authVo);
//			
//			WebUtil.redirect(request, response, "/mysite/main");
//		} else {		
//			System.out.println("파라미터 없음");
//		}
//	}
}
