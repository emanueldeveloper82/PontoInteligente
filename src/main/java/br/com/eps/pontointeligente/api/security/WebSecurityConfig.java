package br.com.eps.pontointeligente.api.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
//	private final SecurityInterceptor securityInterceptor;
//
//	@Autowired
//	public SegurancaConfigurator(SecurityInterceptor securityInterceptor) {
//		this.securityInterceptor = securityInterceptor;
//	}
//
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		http.authorizeRequests().antMatchers("/").fullyAuthenticated().and().csrf().disable().sessionManagement()
//				.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED).and().logout().invalidateHttpSession(true)
//				.deleteCookies("JSESSIONID", "JSESSIONID-SSO");
//	}
//
//	@Override
//	public void addInterceptors(InterceptorRegistry registry) {
//		registry.addInterceptor(securityInterceptor);
//	}
	
	
	
	/** CRIPTOGRAFANDO A SENHA PARA TESTE*/
//	public static void main(String[] args) {
//		
//		System.out.println(new BCryptPasswordEncoder().encode("123456"));
//	}
	
}
