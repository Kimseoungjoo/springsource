package com.company.security;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/root-context.xml",
	"file:src/main/webapp/WEB-INF/spring/security-context.xml"})
public class MemberTests {
	
	@Autowired
	private DataSource ds;
	
	@Test // 테스트 메소드 - @Test 어노테이션 사용 / public , 파라미터 없고, viod 타입
	public void test() {
		String sql = "insert into spring_member_auth(userid,auth) values(?,?)";
		
		for(int i=0;i<100;i++) {
			Connection con = null;
			PreparedStatement pstmt =null;
			
			try {
				con = ds.getConnection();
				pstmt = con.prepareStatement(sql);
				
				if(i<80) {
					pstmt.setString(1, "user"+i); //user0 ~ user79
					pstmt.setString(2, "ROLE_MEMBER"); // 
				}else if(i<90) {
					pstmt.setString(1, "manager"+i); // manger80 ~ 89
					pstmt.setString(2, "ROLE_MANAGER");    // 
				}else{
					pstmt.setString(1, "admin"+i); 	 // admin90 ~ admin99
					pstmt.setString(2, "ROLE_ADMIN");
				}
				pstmt.executeUpdate();
			}
				
			 catch (Exception e) {
				e.printStackTrace();
			}finally {
				if(pstmt!=null) {
					try {
						pstmt.close();
					} catch (Exception e2) {
						e2.printStackTrace();
					}
				}
				if(con!=null) {
					try {
						con.close();
					} catch (Exception e2) {
						e2.printStackTrace();
					}
				}
			}
		}
	}
}
