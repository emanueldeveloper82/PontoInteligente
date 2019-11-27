package br.com.eps.pontointeligente.api.controllers;

import java.security.NoSuchAlgorithmException;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.eps.pontointeligente.api.dtos.CadastroPJDto;
import br.com.eps.pontointeligente.api.response.Response;
import br.com.eps.pontointeligente.api.services.EmpresaService;
import br.com.eps.pontointeligente.api.services.FuncionarioService;

@RestController
@RequestMapping("/api/cadastrar-pj")
@CrossOrigin(origins = "*")
public class CadastroPJController {
	
	private static final Logger log = LoggerFactory.getLogger(CadastroPJController.class);
	
	public CadastroPJController() {}
	
	@Autowired
	private FuncionarioService funcionarioService;
	
	@Autowired
	private EmpresaService empresaService;
	
	@PostMapping
	public ResponseEntity<Response<CadastroPJDto>> cadastrar(@Valid @RequestBody CadastroPJDto cadastroPJDto, 
			BindingResult result) throws NoSuchAlgorithmException {
		log.info("Cadastrando uma PJ: {}", cadastroPJDto.toString());
		
		
		
		
		return null;
	}
	
	
	
	

}
