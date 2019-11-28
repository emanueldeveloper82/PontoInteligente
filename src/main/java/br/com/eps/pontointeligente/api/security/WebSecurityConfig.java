package br.com.eps.pontointeligente.api.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	/**
	 * REALIZA AS CONFIGURAÇÕES DE ACESSO
	 * */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/**").permitAll().anyRequest().authenticated().and().csrf().disable();
	}
	
	
	
	/** CRIPTOGRAFANDO A SENHA PARA TESTE*/
//	public static void main(String[] args) {
//		
//		System.out.println(new BCryptPasswordEncoder().encode("123456"));
//	}
	
}
