package br.com.eps.pontointeligente.api.security.jwt;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;

public class JwtUser implements UserDetails {

	private static final long serialVersionUID = 1L;
	private Long id;
	private String userName;
	private String password;
	private Collection<? extends GrantedAuthority> authorities;
	

	public JwtUser(Long id, String userName, String password, Collection<? extends GrantedAuthority> authorities) {
		this.id = id;
		this.userName = userName;
		this.password = password;
		this.authorities = authorities;
	}

	public Long getId() {
		return this.id;
	}
	
	@Override
	public String getUsername() {
		return this.userName;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.authorities;
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
