package br.com.eps.pontointeligente.api.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordUtils {
	
	private static final Logger log = LoggerFactory.getLogger(PasswordUtils.class);
	
	public PasswordUtils() {}
	
	public static String generateBCrypt(String password) {
		
		if (password == null) {
			return password;
		}
		
		log.info("Gerando hash com o BCrypt.");
		BCryptPasswordEncoder bCryptEncoder = new BCryptPasswordEncoder();

		return bCryptEncoder.encode(password);
	}

	/** CRIPTOGRAFANDO A SENHA PARA TESTE   DEIXAR COMENTADO E DESCOMENTAR SOMENTE QUANDO NECESSARIO*/
	/**EXECUTAR COM JAVA APPLICATION E PEGAR O VALOR DA SENHA NO LOG*/
	/*public static void main(String[] args) {
		log.info(new BCryptPasswordEncoder()
				.encode("admin@123"));
	} */
	

}
