package br.com.eps.pontointeligente.api.controllers;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.eps.pontointeligente.api.dtos.EmpresaDto;
import br.com.eps.pontointeligente.api.entity.Empresa;
import br.com.eps.pontointeligente.api.response.Response;
import br.com.eps.pontointeligente.api.services.EmpresaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * Controller de Empresas.
 * @author emanuel developer
 * 
 */
@Api(value = "API de Empresa.")
@RestController
@RequestMapping("/api/empresas")
@CrossOrigin(origins = "*")
public class EmpresaController {
	
	private static final Logger log = LoggerFactory.getLogger(CadastroPJController.class);
	
	@Autowired
	private EmpresaService empresaService;
	
	public EmpresaController() {}
	
	/**
	 * Método que busca uma empresa pelo CNPJ.
	 * 
	 * @param cnpj
	 * @return ResponseEntity<Response<EmpresaDto>>
	 */
	@ApiOperation(value = "Buscar uma empresa pelo CNPJ.")
	@GetMapping("/cnpj/{cnpj}")
	public ResponseEntity<Response<EmpresaDto>> buscarPorCnpj(@PathVariable("cnpj") String cnpj) {
		
		log.info("Buscando empresa pelo CNPJ: {}", cnpj);
		
		Response<EmpresaDto> response = new Response<>();
		Optional<Empresa> empresa = this.empresaService.buscarPorNumCnpj(cnpj);

		if (!empresa.isPresent()) {
			log.info("Empresa não localizada pelo CNPJ: {}", cnpj);
			response.getErrors().add("Empresa não localizada pelo CNPJ "+ cnpj);
			return ResponseEntity.badRequest().body(response);
		}
		
		response.setData(this.converterEmpresaParaDto(empresa.get()));
		return ResponseEntity.ok(response);
	}

	/**
	 * Método que converte uma empresa para DTO.
	 * @param empresa
	 * @return
	 */
	private EmpresaDto converterEmpresaParaDto(Empresa empresa) {
		EmpresaDto empresaDto = new EmpresaDto();
		empresaDto.setId(empresa.getIdEmpresa());
		empresaDto.setCnpj(empresa.getNumCnpj());
		empresaDto.setRazaoSocial(empresa.getRazaoSocial());
		return empresaDto;
	}
}
