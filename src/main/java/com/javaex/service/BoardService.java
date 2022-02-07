package com.javaex.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.BoardDao;
import com.javaex.vo.BoardVo;

@Service
public class BoardService {

	@Autowired
	private BoardDao boardDao;

	public List<BoardVo> getList() {
		System.out.println("[BoardService.getList()]");
		return boardDao.getList();
	}
	public Map<String, Object> getBoardList2(int crtPage) {
		System.out.println("[BoardService.getBoardList2()]");
		
		//리스트 가져오기
		//페이지당 글 개수
		int listCnt = 10;
		//삼학연산자
		crtPage = (crtPage>0) ? crtPage : (crtPage=1);
		//0보다 크면 crtPage 그대로,
		//0보다 작으면 1	
		//시작글 번호
		int startRnum = ((crtPage-1)*listCnt) + 1;
		//끝글 번호
		int endRnum = (startRnum + listCnt) - 1;
		
		List<BoardVo> boardList = boardDao.getBoardList2(startRnum, endRnum);
		
		//페이징 버튼
		//전체 글 개수
		int totalCnt = boardDao.selectTotal();
		System.out.println("totalCnt="+totalCnt);
		
		//페이지당 버튼 갯수
		int pageBtnCount = 5;
		
		//마지막 버튼 번호
		int endPageBtnNo = (int)( Math.ceil(crtPage/(double)pageBtnCount ) )*pageBtnCount;
		
		//시작 버튼 번호
		int startPageBtnNo = endPageBtnNo - (pageBtnCount-1);
		
		//다음 화살표 유무
		boolean next = false;
		if(endPageBtnNo*listCnt < totalCnt) {
			next = true;
		}
		
		//이전 화살표 유무
		boolean prev = false;
		if(startPageBtnNo != 1) {
			prev = true;
		}

		Map<String, Object> pMap = new HashMap<String, Object>();
		pMap.put("prev", prev);
		pMap.put("startPageBtnNo", startPageBtnNo);
		pMap.put("endPageBtnNo", endPageBtnNo);
		pMap.put("next", next);
		pMap.put("boardList", boardList);
		
		return pMap;
	}

	public BoardVo getBoard(int num) {
		System.out.println("[BoardService.getBoard()]");
		boardDao.hitPlus(num);
		return boardDao.getBoard(num);
	}

	public void boardInsert(BoardVo boardVo) {
		System.out.println("[BoardService.boardInsert()]");
		
		//페이징 데이타 추가 123개
		for(int i=1; i<=123; i++) {
			boardVo.setTitle(i + "번째 글 제목입니다.");
			boardVo.setContent(i + "번째 글 내용입니다.");
			boardVo.setUserNo(1);
			boardDao.boardInsert(boardVo);
		}
	}

	public void boardDelete(int num) {
		System.out.println("[BoardService.boardDelete()]");
		boardDao.boardDelete(num);
	}
	
	public void boardUpdate(BoardVo boardVo) {
		System.out.println("[BoardService.boardUpdate()]");
		boardDao.boardUpdate(boardVo);
	}
}
