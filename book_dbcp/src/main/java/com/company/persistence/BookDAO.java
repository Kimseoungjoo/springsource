package com.company.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.company.domain.BookDTO;

import static com.company.persistence.JdbcUtil.*;

@Repository
// 결국은 component랑 같은 개념이야
// 근데 얘는 database와 연결된 객체야 ~ dataBase component 라고 생각해
public class BookDAO {
	
	
	@Autowired
	private DataSource ds;
	
	
	//INSERT
	public boolean insert(BookDTO dto) {
		
		String sql = "insert into bookTBL values(?,?,?,?)";
		boolean insertFlag = false;

		PreparedStatement pstmt = null;
		Connection con = null;
		try {
			con = ds.getConnection(); // 이 작업을 계속 해준다는건 속도에 문제가 있을 수 있다. 
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, dto.getCode());
			pstmt.setString(2, dto.getTitle());
			pstmt.setString(3, dto.getWriter());
			pstmt.setInt(4, dto.getPrice());
			
			int result = pstmt.executeUpdate();
			
			if(result>0) { 
				insertFlag =true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				close(pstmt);
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return insertFlag;
	}
	//DELETE
	public boolean delete(String code) {
		boolean deleteFlag = false;
		PreparedStatement pstmt = null;
		Connection con = null;
		try {
			con = ds.getConnection();
			String sql = "delete from bookTBL where code =?";
			
			pstmt =  con.prepareStatement(sql);
			pstmt.setString(1, code);
			
			int result = pstmt.executeUpdate();
			
			if(result>0) {
				deleteFlag = true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				close(pstmt);
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return deleteFlag;
	}
	//UPDATE
	public boolean update(String code, int price) {
		boolean updateFlag = false;
		String sql = "update bookTBL set price=? where code=?";
		PreparedStatement pstmt = null;
		Connection con = null;
		try {
			con = ds.getConnection();
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, price);
			pstmt.setString(2, code);
			int result = pstmt.executeUpdate();
			
			if(result>0) {
				updateFlag = true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				close(pstmt);
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return updateFlag;
	}
	//list
	public List<BookDTO> list() {
		boolean updateFlag = false;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<BookDTO> bookList = new ArrayList<BookDTO>();
		Connection con = null;

		try {
			con = ds.getConnection();
			String sql = "select * from bookTBL";
			
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				
				BookDTO dto = new BookDTO();
				
				dto.setCode(rs.getString("code"));
				dto.setTitle(rs.getString("title"));
				dto.setWriter(rs.getString("writer"));
				dto.setPrice(rs.getInt("price"));
				
				bookList.add(dto);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				close(pstmt);
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return bookList;
	}
	// search/select 구문
	public List<BookDTO> search(String criteria,String keyword){
		List<BookDTO> list = new ArrayList<BookDTO>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		
		Connection con = null;
		
		try {
			con = ds.getConnection();
			String sql = "select * from bookTBL where "+criteria+"=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, keyword);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				BookDTO dto = new BookDTO();
				dto.setCode(rs.getString("code"));
				dto.setTitle(rs.getString("title"));
				dto.setWriter(rs.getString("writer"));
				dto.setPrice(rs.getInt("price"));
				
				list.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return list;
	}
	
}
