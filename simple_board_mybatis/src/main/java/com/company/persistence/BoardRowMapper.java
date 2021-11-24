package com.company.persistence;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.company.domain.BoardDTO;

public class BoardRowMapper implements RowMapper<BoardDTO/*객체타입 넣어줘야한다*/>{

	@Override
	public BoardDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		BoardDTO dto = new BoardDTO();
		
		dto.setBno(rs.getInt("bno"));
		dto.setTitle(rs.getString("title"));
		dto.setContent(rs.getString("content"));
		dto.setWriter(rs.getString("writer"));
		dto.setRegdate(rs.getDate("regdate"));
		dto.setUpdatedate(rs.getDate("updatedate"));
		return dto;
	}

}
