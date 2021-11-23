package com.company.persistence;

import static com.company.persistence.JdbcUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.company.domain.BoardDTO;

@Repository
public class BoardDAO {
	// 삽입
	public boolean insert(BoardDTO insertDto) {
		PreparedStatement pstmt = null;
		Connection con = null;
		boolean insertFlag = false;
		
		try {
			
			con=getConnection();
			String sql = "insert into spring_board";
					sql += "(bno,title,content,writer)";
					sql += " values(seq_board.nextval,?,?,?)";
					
					pstmt= con.prepareStatement(sql);
					
					pstmt.setString(1, insertDto.getTitle());
					pstmt.setString(2, insertDto.getContent());
					pstmt.setString(3, insertDto.getWriter());
					
					int result = pstmt.executeUpdate();
				
					if(result>0) {
						insertFlag = true;
						commit(con);
					}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				close(pstmt);
				close(con);
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return insertFlag;
	}
	// 선택
	public BoardDTO getRow(int bno) {
		PreparedStatement pstmt = null;;
		Connection con = null;
		ResultSet rs = null;
		BoardDTO dto = null;
		try {
			con = getConnection();
			String sql = "select * from spring_board where bno=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, bno);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				dto = new BoardDTO();
				
				dto.setBno(rs.getInt("bno"));
				dto.setTitle(rs.getString("title"));
				dto.setContent(rs.getString("content"));
				dto.setWriter(rs.getString("writer"));
				dto.setRegdate(rs.getDate("regdate"));
				dto.setUpdatedate(rs.getDate("updatedate"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
				close(rs);
				close(pstmt);
				close(con);
		}
		return dto;
	}
	// 삭제
	public boolean delete(int bno) {
		PreparedStatement pstmt = null;
		Connection con =null;
		boolean deleteFlag = false;
		
		try {
			con = getConnection();
			
			String sql = "delete from spring_board where bno=?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, bno);
			
			int result = pstmt.executeUpdate();
			
			if(result>0) {
				commit(con);
				deleteFlag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
				close(pstmt);
				close(con);
		}
		return deleteFlag;
	}
	// 수정 
	public boolean update(BoardDTO updateDto) {
		PreparedStatement pstmt = null;
		boolean updateFlag = false;
		Connection con = null;
		try {
			con = getConnection();
			// 수정할 목록
			// title, content, updatedate
			String sql = "update spring_board set title=?,content=?,updatedate=sysdate where bno=?";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1,updateDto.getTitle());
			pstmt.setString(2,updateDto.getContent());
			pstmt.setInt(3, updateDto.getBno());
			
			int result = pstmt.executeUpdate();
			
			if(result>0) {
				commit(con);
				updateFlag = true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
				close(pstmt);
				close(con);
		}
		return updateFlag;
	}
	// 전체조회
	public List<BoardDTO> listAll(){
		List<BoardDTO> list  = new ArrayList<BoardDTO>();
		PreparedStatement pstmt = null;
		Connection con = null;
		ResultSet rs = null;
		
		try {
			con = getConnection();
			
			String sql = "select * from spring_board";
			
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				BoardDTO dto = new BoardDTO();
				dto.setBno(rs.getInt("bno"));
				dto.setTitle(rs.getString("title"));
				dto.setContent(rs.getString("content"));
				dto.setWriter(rs.getString("writer"));
				dto.setRegdate(rs.getDate("regdate"));
				dto.setUpdatedate(rs.getDate("updatedate"));
				
				list.add(dto);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
				close(rs);
				close(pstmt);
				close(con);
		}
		return list;
	}
	
}
