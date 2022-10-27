package com.example.app.domain;

import java.util.Collection;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;

@Data
public class User implements UserDetails{
	
	private Integer id;
	@NotBlank
	@Pattern(regexp="[a-zA-Z0-9_-]{8,24}", message="IDは半角英数、アンダーバー、ハイフンで8～24文字です。")
	private String loginId;
	@NotBlank
	@Pattern(regexp="(?=.*[a-zA-Z])(?=.*[0-9])[a-zA-Z0-9!?\\\"#$%\\&'()=^~|\\@`[{;+:*]},<.>/?_-]{8,24}")
	private String loginPass;
	@NotBlank
	@Size(min=1, max=45)
	private String name;
	private List<String> roles;
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return roles.stream()
				.map(role -> new SimpleGrantedAuthority(role))
				.toList();
	}
	@Override
	public String getUsername() {
		return loginId;
	}
	@Override
	public String getPassword() {
		return loginPass;
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
