package com.company.handler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class CustomLoginSuccessHandler implements AuthenticationSuccessHandler{

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication/* => 인증 성공시 각종 정보를 가지고 있는 변수*/) throws IOException, ServletException {
		log.info("Login success");
		
		// 로그인에 성공시 ROLE 이 ADMIN 이라면 ADMIN-PAGE 로 이동

		
		// 로그인에 성공시 ROLE 이 USER 이라면 USER-PAGE 로 이동
		
		List<String> roleNames = new ArrayList<String>();
		authentication.getAuthorities().forEach(authority ->{
			roleNames.add(authority.getAuthority());
		});
		
		log.info("roleNames : " +roleNames);
		
		if(roleNames.contains("ROLE_ADMIN")){
			response.sendRedirect("/admin-page");
			return;
		}
		if(roleNames.contains("ROLE_USER")){
			response.sendRedirect("/user-page");
			return;
		}
		response.sendRedirect("/");
		
		
	}

}
