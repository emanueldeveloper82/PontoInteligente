package br.com.eps.pontointeligente.api.security.jwt;

import br.com.eps.pontointeligente.api.entity.Funcionario;
import br.com.eps.pontointeligente.api.services.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private FuncionarioService funcionarioService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Funcionario> funcionario = funcionarioService.buscarFuncionarioPorEmail(username);

		if (funcionario.isPresent()) {
			return JwtUserFactory.create(funcionario.get());
		}
		throw new UsernameNotFoundException("Email não encontrado.");
	}

}
