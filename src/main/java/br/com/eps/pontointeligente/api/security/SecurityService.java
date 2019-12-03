package br.com.eps.pontointeligente.api.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.eps.pontointeligente.api.controllers.CadastroPJController;
import br.com.eps.pontointeligente.api.exceptions.BadRequestException;

@Service
@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
public class SecurityService {
	
	private static final Logger log = LoggerFactory.getLogger(SecurityService.class);

//	@Autowired
//	private TokenLogadoService tokenService;

	

	@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
	public Credencial autenticar(AuthCredencial credencial, String token) {
		try {
			return this.singleSignOnProvider.autenticar(token, credencial).execute().body();
		} catch (IOException e) {
			log.error("Não foi possível processar a solicitação", e);
		}
		return null;
	}

	public boolean invalidar(String token) {
		try {
			TokenLogado tokenRemover = new TokenLogado();
			tokenRemover.setToken(token);
			this.tokenService.invalidarToken(tokenRemover);
			return true;
		} catch (Exception ex) {
			log.error("Não foi possível processar a solicitação", ex);
			throw new BadRequestException("Logout", "Não foi possivel realizar o logout", "Logout");
		}
	}

	public boolean isTokenValido(String token) {
		return this.tokenService.findByOne(token).isPresent();
	}
	
	

}
