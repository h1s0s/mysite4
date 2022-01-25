package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.CommentVo;

@Repository
public class CommentDao {

	@Autowired
	private SqlSession sqlSession;

	public List<CommentVo> getList() {
		System.out.println("[CommentDao.getList()]");
		return sqlSession.selectList("comment.getList");
	}
	
	public CommentVo getComment(int no) {
		System.out.println("[CommentDao.getComment()]");
		return sqlSession.selectOne("comment.getComment", no);
	}
	public void hitPlus(int no) {
		System.out.println("[CommentDao.hitPlus()]");
		sqlSession.update("comment.hitPlus", no);
	}
	//게시글 작성
	public int Insert(CommentVo commentVo) {
		System.out.println("[CommentDao.insert()]");
		int count = sqlSession.insert("comment.insert", commentVo);
		System.out.println("[" + count + "건이 등록되었습니다.(commentDao)]");
		return count;
	}
	//답글 작성
	public int commentInsert(CommentVo commentVo) {
		System.out.println("[CommentDao.commentInsert()]");
		int count = sqlSession.insert("comment.commentinsert", commentVo);
		System.out.println("[" + count + "건이 등록되었습니다.(commentDao)]");
		return count;
	}
	//orderNo +1
	public int orderNoPlus(CommentVo commentVo) {
		System.out.println("[CommentDao.orderNoPlus()]");
		int count = sqlSession.update("comment.orderNoPlus", commentVo);
		System.out.println("["+count+"건이 처리되었습니다.(commentDao)]");
		return count;
	}
	
	public int commentDelete(int no) {
		System.out.println("[CommentDao.delete()]");
		int count = sqlSession.delete("comment.delete", no);
		System.out.println("[" + count + "건이 삭제되었습니다.(commentDao)]");
		return count;
	}

}
