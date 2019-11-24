package br.com.eps.pontointeligente.api.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordUtils {
	
	public static final Logger log = LoggerFactory.getLogger(PasswordUtils.class);
	
	public PasswordUtils() {}
	
	public static String generateBCript(String password) {
		
		if(password == null) {
			return password;
		}
		
		log.info("Gerando hash com o BCrypt.");
		BCryptPasswordEncoder bCryptEncoder = new BCryptPasswordEncoder();
		
		
		return bCryptEncoder.encode(password);
	}
	
	

}
