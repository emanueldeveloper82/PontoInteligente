package br.com.eps.pontointeligente.api.utils;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordUtilsTest {
	
	private static final String SENHA = "12345678";
	private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
	
	
	@Test
	public void testSenhaNulo() throws Exception {
		assertNull(PasswordUtils.generateBCript(null));
	}	
	
	@Test
	public void testSenha() throws Exception {
		String hash = PasswordUtils.generateBCript(SENHA);
		assertTrue(bCryptPasswordEncoder.matches(SENHA, hash));
	}
	
}
