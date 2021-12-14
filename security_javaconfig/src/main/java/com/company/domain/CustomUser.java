package com.company.domain;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import lombok.Getter;

@Getter
public class CustomUser extends User {
	
	private SpUser spUser;

	public CustomUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
	}

	public CustomUser(SpUser spUser) {
		super(spUser.getUserid(),spUser.getPassword(), spUser.getAuthorities().stream()/*중간작업*/
				.map/*통해 각각의 정보를 추출*/(auth -> new SimpleGrantedAuthority(auth.getAuthority()))
				.collect/*각각 추출한 정보를 하나의 리스트로 만들어주는 작업*/(Collectors.toList()));
		
		this.spUser = spUser;
	}

}
