package com.company.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface PersonMapper {
	
	//입력
//	@Insert("insert into person(id,name) values(#{id},#{name})")
//	public int insertPerson(@Param("id") String  id, @Param("name") String name);
//	
//	@Select("select name from person where id=#{id}")
//	public String selectPerson(@Param("id") String id);
//	
//	@Update("update person set name=#{name} where id=#{id}")
//	public int updatePerson(@Param("id") String  id, @Param("name") String name);

	// 인터페이스 + XML 조합 : 메소드 명과 xml 아이디가 일치해야함************
	public int insertPerson(@Param("id") String  id, @Param("name") String name);
	public String selectPerson(String id);
	public int updatePerson(@Param("id") String  id, @Param("name") String name);
	public int deletePerson(String id);
}
