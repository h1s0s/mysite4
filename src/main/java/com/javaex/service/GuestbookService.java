package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.GuestbookDao;
import com.javaex.vo.GuestbookVo;

@Service
public class GuestbookService {

	@Autowired
	private GuestbookDao guestbookDao;

	public List<GuestbookVo> getList() {
		System.out.println("[guestbookService.getList()]");
		return guestbookDao.getList();
	}

	public void guestbookInsert(GuestbookVo guestbookVo) {
		System.out.println("[guestbookService.guestbookInsert()]");
		guestbookDao.guestbookInsert(guestbookVo);
	}

	public void guestbookDelete(int no, String password) {
		System.out.println("[guestbookService.guestbookInsert()]");
		guestbookDao.guestbookDelete(no, password);
	}
	
	public GuestbookVo addGuestResultVo(GuestbookVo guestbookVo) {//name,pw,content만 있음
		System.out.println("[guestbookService.addGuestResultVo()]");
		
		//저장하기
		int count = guestbookDao.insertSelectKey(guestbookVo);//여기서 selectKey의 no 추가됨.중
		int no = guestbookVo.getNo();
		return guestbookDao.selectGuest(no);
	}
}
