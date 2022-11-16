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
import validation.LoginGroup;
import validation.RegisterGroup;

@Data
public class User implements UserDetails{
	private Integer id;
	@NotBlank(groups={LoginGroup.class})
	@Pattern(regexp="[\\w\\d]{4,24}", message="IDは半角英数、アンダーバーで8～24文字です。",groups={RegisterGroup.class})
	private String loginId;
	@NotBlank(groups={LoginGroup.class})
	@Pattern(regexp="(?=.*[\\w])(?=.*[\\d])(?=.*[!-/:-@\\[-`{-~])[!-~]{8,24}",groups={RegisterGroup.class})
	private String loginPass;
	@Size(min=1, max=45,groups={RegisterGroup.class})
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
