package com.example.app.domain;

import java.util.Collection;
import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;

@Data
public class User implements UserDetails{
	
	private Integer id;
	@NotBlank
	@Min(4)
	private String loginId;
	@NotBlank
	@Min(8)
	private String loginPass;
	@NotBlank
	private String name;
	@NotBlank
	private List<String> roles;
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return roles.stream()
				.map(role -> new SimpleGrantedAuthority(role))
				.toList();
	}
	@Override
	public String getPassword() {
		// TODO 自動生成されたメソッド・スタブ
		return loginPass;
	}
	@Override
	public String getUsername() {
		// TODO 自動生成されたメソッド・スタブ
		return loginId;
	}
	@Override
	public boolean isAccountNonExpired() {
		// TODO 自動生成されたメソッド・スタブ
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		// TODO 自動生成されたメソッド・スタブ
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO 自動生成されたメソッド・スタブ
		return true;
	}
	@Override
	public boolean isEnabled() {
		// TODO 自動生成されたメソッド・スタブ
		return true;
	}
}
