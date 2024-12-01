package com.app.security;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.app.entities.UserEntity;
import com.app.entities.UserRole;

public class CustomUserDetails implements UserDetails {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private UserEntity user;
	

	public CustomUserDetails(UserEntity user) {
		super();
		this.user = user;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		//return List.of(new SimpleGrantedAuthority(user.getRole().name()));
		 Set<UserRole> roles = user.getRoles();
	        return roles.stream()
	                .map(role -> new SimpleGrantedAuthority(role.name()))
	                .collect(Collectors.toList());
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return user.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
