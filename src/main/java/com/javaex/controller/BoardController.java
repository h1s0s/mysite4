package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.javaex.dao.BoardDao;
import com.javaex.vo.BoardVo;

@Controller
@RequestMapping(value="/board", method={RequestMethod.GET, RequestMethod.POST})
public class BoardController {
	
	@Autowired
	private BoardDao boardDao;
	
	@RequestMapping(value="/list", method={RequestMethod.GET, RequestMethod.POST})
	public String list(Model model) {
		System.out.println("[BoardController.list()");
		
		List<BoardVo> boardList = boardDao.getList();
		
		model.addAttribute("boardList", boardList);
		
		return "/board/list";
	}
//	protected void doGet(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		System.out.println("/board");
//		String act = request.getParameter("action");
//
//		if ("list".equals(act)) {// 게시글 리스트
//			System.out.println("action > list");
//
//			List<BoardVo> boardList = new BoardDao().getList();
//
//			request.setAttribute("boardList", boardList);
//
//			WebUtil.forward(request, response, "/WEB-INF/views/board/list.jsp");
//		} else if ("writeForm".equals(act)) {// 게시글 쓰기 폼
//			System.out.println("action > writeForm");
//			
//			HttpSession session = request.getSession();
//			UserVo authUser = (UserVo)session.getAttribute("authUser");
//			if(authUser != null) {
//				WebUtil.forward(request, response, "/WEB-INF/views/board/writeForm.jsp");
//			} else {
//				WebUtil.redirect(request, response, "/mysite/main");
//			}
//		} else if ("write".equals(act)) {// 게시글 쓰기
//			System.out.println("action > write");
//
//			HttpSession session = request.getSession();
//
//			UserVo authUser = (UserVo) session.getAttribute("authUser");
//
//			String title = request.getParameter("title");
//			String content = request.getParameter("content");
//			int userNo = authUser.getNo();
//
//			BoardVo boardVo = new BoardVo(title, content, userNo);
//			new BoardDao().boardInsert(boardVo);
//
//			WebUtil.redirect(request, response, "/mysite/board?action=list");
//		} else if ("read".equals(act)) {// 게시글 읽기 폼
//			System.out.println("action > read");
//
//			int no = Integer.parseInt(request.getParameter("no"));
//			
//			new BoardDao().hitPlus(no);
//			BoardVo boardVo = new BoardDao().getBoard(no);
//
//			request.setAttribute("boardVo", boardVo);
//
//			WebUtil.forward(request, response, "/WEB-INF/views/board/read.jsp");
//		} else if ("delete".equals(act)) {// 게시글 삭제
//			System.out.println("action > delete");
//
//			int num = Integer.parseInt(request.getParameter("no"));
//
//			new BoardDao().boardDelete(num);
//
//			WebUtil.redirect(request, response, "/mysite/board?action=list");
//		} else if ("modifyForm".equals(act)) {// 게시글 수정 폼
//			System.out.println("action > modifyForm");
//
//			int no = Integer.parseInt(request.getParameter("no"));
//
//			BoardVo boardVo = new BoardDao().getBoard(no);
//			request.setAttribute("boardVo", boardVo);
//
//			WebUtil.forward(request, response, "/WEB-INF/views/board/modifyForm.jsp");
//		} else if ("modify".equals(act)) {// 게시글 수정
//			System.out.println("action > modify");
//
//			int no = Integer.parseInt(request.getParameter("no"));
//			String title = request.getParameter("title");
//			String content = request.getParameter("content");
//
//			BoardVo boardVo = new BoardVo(no, title, content);
//			new BoardDao().boardUpdate(boardVo);
//
//			WebUtil.redirect(request, response, "/mysite/board?action=list");
//		} else {
//			System.out.println("파라미터 없음");
//		}
//	}

}
