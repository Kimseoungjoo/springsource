package com.company.domain;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SpUser {
	private String userid;
	private String email;
	private boolean enabled;
	private String password;
	
	List<SpUserAuthority> authList = new ArrayList<SpUserAuthority>();
}
