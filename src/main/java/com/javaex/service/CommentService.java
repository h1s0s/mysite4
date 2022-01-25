package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.CommentDao;
import com.javaex.vo.CommentVo;

@Service
public class CommentService {

	@Autowired
	private CommentDao commentDao;
	
	public List<CommentVo> list(){
		System.out.println("[CommentService.list()]");
		return commentDao.getList();
	}
	
	public CommentVo read(int no){
		System.out.println("[CommentService.read()]");
		commentDao.hitPlus(no);
		return commentDao.getComment(no);
	}
	
	public void write(CommentVo commentVo){
		System.out.println("[CommentService.write()]");
		commentDao.Insert(commentVo);
	}
	
	public void delete(int no){
		System.out.println("[CommentService.delete()]");
		commentDao.commentDelete(no);
	}
	
	public void commentWrite(CommentVo commentVo){
		System.out.println("[CommentService.commentWrite()]");
		commentDao.commentInsert(commentVo);
		commentDao.orderNoPlus(commentVo);
	}
}
