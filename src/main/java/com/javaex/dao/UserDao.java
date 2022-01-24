package com.javaex.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.UserVo;

@Repository
public class UserDao {

	@Autowired
	private SqlSession sqlSession;
	
	// 회원정보 1명 가져오기(로그인용)
	public UserVo selectUser(UserVo userVo) {
		System.out.println("[UserDao.getUser()]");
		return sqlSession.selectOne("user.selectUser", userVo);
	}
//	// 저장 메소드(회원가입)
//	public int insert(UserVo userVo) {
//		int count = 0;
//		getConnection();
//
//		try {
//			// SQL문 준비
//			// 문자열
//			String query = "";
//			query += " insert into users ";
//			query += " values (seq_users_no.nextval, ?, ?, ?, ?) ";
//
//			// 문자열을 쿼리문으로
//			pstmt = conn.prepareStatement(query);
//
//			// 바인딩
//			pstmt.setString(1, userVo.getId());
//			pstmt.setString(2, userVo.getPassword());
//			pstmt.setString(3, userVo.getName());
//			pstmt.setString(4, userVo.getGender());
//
//			// 실행
//			count = pstmt.executeUpdate();
//
//			// 결과처리
//			System.out.println("[" + count + " 건이 등록되었습니다(UserDao)]");
//
//		} catch (SQLException e) {
//			System.out.println("error:" + e);
//		}
//		close();
//
//		return count;
//	}
//

//
//	// 회원정보 가져오기(수정용)
//	public UserVo getUser(int num) {
//		UserVo userVo = null;
//		getConnection();
//
//		try {
//			// 문자열
//			String query = "";
//			query += " select  no, ";
//			query += "         id,";
//			query += "         password,";
//			query += "         name,";
//			query += "         gender ";
//			query += " from    users ";
//			query += " where   no=? ";
//
//			// 문자열을 쿼리문으로
//			pstmt = conn.prepareStatement(query);
//
//			// 바인딩
//			pstmt.setInt(1, num);
//
//			// 실행
//			rs = pstmt.executeQuery();
//
//			// 결과처리
//			while (rs.next()) {
//				int no = rs.getInt("no");
//				String id = rs.getString("id");
//				String password = rs.getString("password");
//				String name = rs.getString("name");
//				String gender = rs.getString("gender");
//
//				userVo = new UserVo(no, id, password, name, gender);
//			}
//		} catch (SQLException e) {
//			System.out.println("error:" + e);
//		}
//		close();
//		return userVo;
//	}
//
//	public int Update(UserVo userVo) {
//		int count = 0;
//		getConnection();
//
//		try {
//			// SQL문 준비
//			// 문자열
//			String query = "";
//			query += " update users ";
//			query += " set    id = ?, ";
//			query += "        password = ?, ";
//			query += "        name = ?, ";
//			query += "        gender = ? ";
//			query += " where  no = ? ";
//
//			// 문자열을 쿼리문으로
//			pstmt = conn.prepareStatement(query);
//
//			// 바인딩
//			pstmt.setString(1, userVo.getId());
//			pstmt.setString(2, userVo.getPassword());
//			pstmt.setString(3, userVo.getName());
//			pstmt.setString(4, userVo.getGender());
//			pstmt.setInt(5, userVo.getNo());
//
//			// 실행
//			count = pstmt.executeUpdate();
//
//			// 결과처리
//			System.out.println("[" + count + " 건이 수정되었습니다(UserDao)]");
//
//		} catch (SQLException e) {
//			System.out.println("error:" + e);
//		}
//		close();
//
//		return count;
//	}
}
