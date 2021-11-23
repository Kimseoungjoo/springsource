package com.company.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.company.domain.BookDTO;

import static com.company.persistence.JdbcUtil.*;

@Repository
// 결국은 component랑 같은 개념이야
// 근데 얘는 database와 연결된 객체야 ~ dataBase component 라고 생각해
public class BookDAO {
	
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	
	//INSERT
	public boolean insert(BookDTO dto) {
		
		String sql = "insert into bookTBL values(?,?,?,?)";
	
		int result = jdbcTemplate.update(sql,dto.getCode(),dto.getTitle(),
				dto.getWriter(),dto.getPrice());

		return result>0 ? true : false;
	}
	//DELETE
	public boolean delete(String code) {
		
			String sql = "delete from bookTBL where code =?";
			
			int result = jdbcTemplate.update(sql,code);
		return result > 0 ? true : false;
	}
	//UPDATE
	public boolean update(String code, int price) {
		String sql = "update bookTBL set price=? where code=?";

			int result = jdbcTemplate.update(sql,price,code);
			
		return result > 0 ? true : false;
	}
	//list
	public List<BookDTO> list() {
		
			String sql = "select * from bookTBL";
			
			return jdbcTemplate.query(sql, new BookRowMapper());
		
	}
	// search/select 구문
	public List<BookDTO> search(String criteria,String keyword){
		
		String sql = "select * from bookTBL where "+criteria+"=?";
		
		return jdbcTemplate.query(sql, new BookRowMapper(), keyword);
	}
	
}
