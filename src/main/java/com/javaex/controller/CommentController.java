package com.javaex.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.service.CommentService;
import com.javaex.vo.CommentVo;
import com.javaex.vo.UserVo;

@Controller
@RequestMapping(value = "/comment", method = { RequestMethod.GET, RequestMethod.POST })
public class CommentController {

	@Autowired
	private CommentService commentService;

	@RequestMapping(value = "/list", method = { RequestMethod.GET, RequestMethod.POST })
	public String list(Model model) {
		System.out.println("[CommentController.list()]");

		model.addAttribute("commentList", commentService.list());

		return "/comment/list";
	}

	@RequestMapping(value = "/read", method = { RequestMethod.GET, RequestMethod.POST })
	public String read(@RequestParam("no") int no, Model model) {
		System.out.println("[CommentController.read()]");
		
		CommentVo commentVo = commentService.read(no);
		System.out.println("read"+commentVo);
		model.addAttribute("commentVo", commentVo);

		return "/comment/read";
	}

	@RequestMapping(value = "/writeForm", method = { RequestMethod.GET, RequestMethod.POST })
	public String writeForm(HttpSession session) {
		System.out.println("[CommentController.writeForm()]");

		UserVo authUser = (UserVo) session.getAttribute("authUser");
		if (authUser != null) {
			return "/comment/writeForm";
		} else {
			return "redirect:/";
		}

	}

	@RequestMapping(value = "/write", method = { RequestMethod.GET, RequestMethod.POST })
	public String write(@ModelAttribute CommentVo commentVo) {
		System.out.println("[CommentController.write()]");

		commentService.write(commentVo);

		return "redirect:/comment/list";
	}

	@RequestMapping(value = "/modifyForm", method = { RequestMethod.GET, RequestMethod.POST })
	public String modifyForm() {
		System.out.println("[CommentController.modifyForm()]");

		return "/comment/modifyForm";
	}

	@RequestMapping(value = "/modify", method = { RequestMethod.GET, RequestMethod.POST })
	public String modify() {
		System.out.println("[CommentController.modify()]");

		return "redirect:/comment/list";
	}

	@RequestMapping(value = "/delete", method = { RequestMethod.GET, RequestMethod.POST })
	public String delete(@RequestParam("no") int no) {
		System.out.println("[CommentController.delete()]");

		System.out.println(no);
		commentService.delete(no);

		return "redirect:/comment/list";
	}

	@RequestMapping(value = "/commentForm", method = { RequestMethod.GET, RequestMethod.POST })
	public String commentForm(HttpSession session, Model model,
							  @ModelAttribute CommentVo momComment) {//부모글 객체(그룹id,오더id,depth)
		System.out.println("[CommentController.commentForm()]");

		UserVo authUser = (UserVo) session.getAttribute("authUser");
		System.out.println(momComment);
		if (authUser != null) {
			model.addAttribute("momComment", momComment);
			return "/comment/commentForm";
		} else {
			return "redirect:/";
		}
	}
	
	@RequestMapping(value = "/commentWrite", method = { RequestMethod.GET, RequestMethod.POST })
	public String commentWrite(@ModelAttribute CommentVo commentVo) {
		System.out.println("[CommentController.commentWrite()]");
		
		commentService.commentWrite(commentVo);
		
		return "redirect:/comment/list";
	}
}
