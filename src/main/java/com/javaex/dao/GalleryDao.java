package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.GalleryVo;

@Repository
public class GalleryDao {

	@Autowired
	private SqlSession sqlSession;
	
	public List<GalleryVo> getList() {
		System.out.println("[GalleryDao.getList()]");
		
		List<GalleryVo> galleryList = sqlSession.selectList("getList");
		System.out.println(galleryList);
		return galleryList;
	}
	
	public int save(GalleryVo galleryVo) {
		System.out.println("[GalleryDao.save()]");
		
		int count = sqlSession.insert("insert", galleryVo);
		System.out.println("["+count+"건이 등록되었습니다.(GalleryDao)");
		
		return count;
	}
}
