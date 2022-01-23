package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.BoardVo;

@Repository
public class BoardDao {

	@Autowired
	private SqlSession sqlSession;
	
	public List<BoardVo> getList() {
		System.out.println("[BoardDao.getList()");
		
		return sqlSession.selectList("board.getList");
	}

//	//1개 게시글 가져오기(read)
//	public BoardVo getBoard(int num) {
//		BoardVo boardVo = null;
//		int userNo = 0;
//		getConnection();
//		try {
//			String query = "";
//			query += " select   us.name name, ";
//			query += "          bo.no no, ";
//			query += "          bo.title title, ";
//			query += "          bo.content content, ";
//			query += "          bo.hit hit, ";
//			query += "          to_char(bo.reg_date, 'YY-MM-DD HH:MI') reg_date, ";
//			query += "          bo.user_no user_no";
//			query += " from     board bo, users us ";
//			query += " where    bo.user_no = us.no ";
//			query += " and		bo.no = ? ";
//
//			// 쿼리
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
//				String title = rs.getString("title");
//				String content = rs.getString("content");
//				int hit = rs.getInt("hit");
//				String regDate = rs.getString("reg_date");
//				userNo = rs.getInt("user_no");
//				String name = rs.getString("name");
//				
//				boardVo = new BoardVo(no, title, content, hit, regDate, userNo, name);
//			}
//			
//		} catch (SQLException e) {
//			System.out.println("error:" + e);
//		}
//
//		close();
//		return boardVo;
//	}
//	
//	//조회수 올리기
//	public void hitPlus(int num) {
//		getConnection();
//		try {
//			// SQL문 준비
//			String query = "";
//			query += " update  board ";
//			query += " set     hit = hit+1 ";
//			query += " where   no = ? ";
//
//			// 쿼리
//			pstmt = conn.prepareStatement(query);
//			
//			// 바인딩
//			pstmt.setInt(1, num);
//			
//			// 실행
//			int count = pstmt.executeUpdate();
//			
//			// 결과
//			System.out.println("["+count+"건 실행되었습니다.(Board)]");
//
//		} catch (SQLException e) {
//			System.out.println("error:" + e);
//		}
//
//		close();
//	}
//	public void boardInsert(BoardVo boardVo) {
//
//		getConnection();
//
//		try {
//
//			// SQL문 준비
//			String query = "";
//			query += " insert into board ";
//			query += " values (seq_board_no.nextval, ?, ?, 0, sysdate, ?) ";
//
//			// 쿼리
//			pstmt = conn.prepareStatement(query);
//
//			// 바인딩
//			pstmt.setString(1, boardVo.getTitle());
//			pstmt.setString(2, boardVo.getContent());
//			pstmt.setInt(3, boardVo.getUserNo());
//
//			// 실행
//			int count = pstmt.executeUpdate();
//
//			// 결과처리
//			System.out.println("[" + count + "건 추가되었습니다.(BoardDao)]");
//
//		} catch (SQLException e) {
//			System.out.println("error:" + e);
//		}
//		close();
//	}
//	public void boardDelete(int num) {
//		getConnection();
//
//		try {
//
//			// SQL문 준비
//			String query = "";
//			query += " delete board ";
//			query += " where no = ? ";
//
//			// 쿼리
//			pstmt = conn.prepareStatement(query);
//
//			// 바인딩
//			pstmt.setInt(1, num);
//
//			// 실행
//			int count = pstmt.executeUpdate();
//
//			// 결과처리
//			System.out.println("[" + count + "건 삭제되었습니다.(BoardDao)]");
//
//		} catch (SQLException e) {
//			System.out.println("error:" + e);
//		}
//		close();
//	}
//
//	public void boardUpdate(BoardVo boardVo) {
//		getConnection();
//
//		try {
//
//			// SQL문 준비
//			String query = "";
//			query += " update board ";
//			query += " set    title = ?, ";
//			query += " 		  content = ? ";
//			query += " where  no = ? ";
//
//			// 쿼리
//			pstmt = conn.prepareStatement(query);
//
//			// 바인딩
//			pstmt.setString(1, boardVo.getTitle());
//			pstmt.setString(2, boardVo.getContent());
//			pstmt.setInt(3, boardVo.getNo());
//
//			// 실행
//			int count = pstmt.executeUpdate();
//
//			// 결과처리
//			System.out.println("[" + count + "건 수정되었습니다.(BoardDao)]");
//
//		} catch (SQLException e) {
//			System.out.println("error:" + e);
//		}
//		close();
//		}
}

