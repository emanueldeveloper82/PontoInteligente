package br.com.eps.pontointeligente.api.security;

import java.util.Collection;

import org.springframework.security.authentication.RememberMeAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

public class AuthenticationToken extends RememberMeAuthenticationToken {
	
	private static final long serialVersionUID = 1L;

	
	 /**
     * Constructor.
     *
     * @param key         to identify if this object made by an authorised client
     * @param principal   the principal (typically a <code>UserDetails</code>)
     * @param authorities the authorities granted to the principal
     * @throws IllegalArgumentException if a <code>null</code> was passed
     */
	public AuthenticationToken(String key, Object principal, Collection<? extends GrantedAuthority> authorities) {
		super(key, principal, authorities);
	}
	
	public AuthenticationToken(String name) {
        this(name, name, null);
    }

}
