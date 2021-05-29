package br.com.eps.pontointeligente.api.security.jwt;

import br.com.eps.pontointeligente.api.entity.Funcionario;
import br.com.eps.pontointeligente.api.enums.PerfilEnum;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import java.util.ArrayList;
import java.util.List;

public class JwtUserFactory {
	
	private JwtUserFactory() {
	}

	/**
	 * Converte e gera um JwtUser com base nos dados de um funcionário.
	 * 
	 * @param funcionario
	 * @return JwtUser
	 */
	public static JwtUser create(Funcionario funcionario) {
		return new JwtUser(funcionario.getIdFuncionario(), funcionario.getEmail(), funcionario.getSenha(),
				mapToGrantedAuthorities(funcionario.getPerfil()));
	}

	/**
	 * Converte o perfil do usuário para o formato utilizado pelo Spring Security.
	 * 
	 * @param perfilEnum
	 * @return List<GrantedAuthority>
	 */
	private static List<GrantedAuthority> mapToGrantedAuthorities(PerfilEnum perfilEnum) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority(perfilEnum.toString()));
		return authorities;
	}
	

}
