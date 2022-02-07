package com.javaex.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.BoardVo;

@Repository
public class BoardDao {

	@Autowired
	private SqlSession sqlSession;
	
	public List<BoardVo> getList() {
		System.out.println("[BoardDao.getList()]");
		
		return sqlSession.selectList("board.getList");
	}
	public List<BoardVo> getBoardList2(int startRnum, int endRnum) {
		System.out.println("[BoardDao.getBoardList2()]");
		System.out.println(startRnum + "," + endRnum);
		
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("startRnum", startRnum);
		map.put("endRnum", endRnum);
		
		List<BoardVo> boardList = sqlSession.selectList("board.boardList2", map);
		
		return boardList;
	}
	
	public int selectTotal() {
		System.out.println("[BoardDao.selectTotal()]");
		
		return sqlSession.selectOne("board.totalCnt");
	}
	
	//1개 게시글 가져오기(read)
	public BoardVo getBoard(int num) {
		System.out.println("[BoardDao.getBoard()]");
		return sqlSession.selectOne("board.getBoard", num);
	}
	
	//조회수 올리기
	public void hitPlus(int num) {
		System.out.println("[BoardDao.hitPlus()]");
		sqlSession.update("board.hitPlus", num);
	}
	
	//게시물 등록
	public void boardInsert(BoardVo boardVo) {
		System.out.println("[BoardDao.boardInsert()]");
		int count = sqlSession.insert("board.boardInsert", boardVo);
		System.out.println("["+count+"건이 등록되었습니다]");
	}
	//게시물 삭제
	public void boardDelete(int num) {
		System.out.println("[BoardDao.boardDelete()]");
		int count = sqlSession.delete("board.boardDelete", num);
		System.out.println("["+count+"건이 삭제되었습니다]");
	}
	//게시물 수정
	public void boardUpdate(BoardVo boardVo) {
		System.out.println("[BoardDao.boardUpdate()]");
		System.out.println(boardVo);
		int count = sqlSession.update("board.boardUpdate", boardVo);
		System.out.println("["+count+"건이 수정되었습니다]");
	}
}

