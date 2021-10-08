package br.com.eps.pontointeligente.api.controllers;

import br.com.eps.pontointeligente.api.dtos.CadastroPJDto;
import br.com.eps.pontointeligente.api.entity.Empresa;
import br.com.eps.pontointeligente.api.entity.Funcionario;
import br.com.eps.pontointeligente.api.enums.PerfilEnum;
import br.com.eps.pontointeligente.api.response.Response;
import br.com.eps.pontointeligente.api.services.EmpresaService;
import br.com.eps.pontointeligente.api.services.FuncionarioService;
import br.com.eps.pontointeligente.api.utils.PasswordUtils;
import io.swagger.annotations.Api;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.security.NoSuchAlgorithmException;
import javax.validation.Valid;

/**
 * Controller para cadastro de pessoa juridica.
 * @author emanuel developer
 * 
 */
@Api(value = "API de cadastro de Pessoa Juridica.")
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

	@Autowired
	private ModelMapper modelMapper;
	
	/**
	 * Cadastra uma Pessoa Juridica na base de dados.
	 * @param cadastroPJDto
	 * @param result
	 * @return ResponseEntity<Response<CadastroPJDto>>
	 * @throws NoSuchAlgorithmException
	 */
	@PostMapping
	public ResponseEntity<Response<CadastroPJDto>> cadastrar(@Valid @RequestBody CadastroPJDto cadastroPJDto, 
			BindingResult result) throws NoSuchAlgorithmException {
		log.info("Cadastrando uma PJ: {}", cadastroPJDto.toString());
				
		Response<CadastroPJDto> response = new Response<>();
		
		this.validarDadosExistentes(cadastroPJDto, result);

		Empresa empresa = modelMapper.map(cadastroPJDto, Empresa.class);

		//Todo: Ajustar isso para um generic;
		Funcionario funcionario = modelMapper.map(cadastroPJDto, Funcionario.class);
		funcionario.setPerfil(PerfilEnum.ROLE_ADMIN);
		funcionario.setSenha(PasswordUtils.generateBCrypt(cadastroPJDto.getSenha()));

		/**verifica se existe erros de validação.*/
		if (result.hasErrors()) {
			log.error("Erro ao validar cadastro de PJ: {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}
		
		this.empresaService.persistir(empresa);
		funcionario.setEmpresa(empresa);
		this.funcionarioService.persistirFuncionario(funcionario);
		
//		response.setData(this.converteFuncionarioParaDTO(funcionario));

		response.setData(modelMapper.map(funcionario, CadastroPJDto.class));
		
		return ResponseEntity.ok(response);
	}
	
	/**
	 * Valida se existe a empresa ou funcionário já existe na base de dados.
	 * 
	 * @param cadastroPJDto
	 * @param result
	 */
	private void validarDadosExistentes(CadastroPJDto cadastroPJDto, BindingResult result) {
		
		log.info("Validando dados existentes de PJ: {}", cadastroPJDto.toString());
		
		this.empresaService.buscarPorCnpj(cadastroPJDto.getCnpj())
				.ifPresent(emp -> result
						.addError(new ObjectError("empresa",
								"Já existe uma empresa com este CNPJ.")));
		
		this.funcionarioService.buscarFuncionarioPorCpf(cadastroPJDto.getCpf())
				.ifPresent(func -> result
						.addError(new ObjectError("Funcionario",
								"Já existe um funcionário com este CPF.")));
		
		this.funcionarioService.buscarFuncionarioPorEmail(cadastroPJDto.getEmail())
				.ifPresent(func -> result.addError(new ObjectError("Funcionario",
						"Já existe um funcionário com este E-mail.")));
	}
	
	
	/**
	 * Converte os dados do DTO para a Empresa.
	 * 
	 * @param cadastroPJDto
	 * @return Empresa
	 */
	private Empresa converterDtoParaEmpresa(CadastroPJDto cadastroPJDto) {
		
		log.info("Convertenndo dados do DTo para a Empresa CNPJ: {}", cadastroPJDto.getCnpj());
		
		Empresa empresa = new Empresa();
		empresa.setCnpj(cadastroPJDto.getCnpj());
		empresa.setRazaoSocial(cadastroPJDto.getRazaoSocial());
		
		return empresa;
	}
	
	/**
	 * Converte dados do DTO para Funcionário.
	 * 
	 * @param cadastroPJDto
	 * @return Funcionario
	 */
	private Funcionario converteDtoParaFuncionario(CadastroPJDto cadastroPJDto) {
		
		log.info("Convertenndo dados do DTo para o funcionario CPF: {}", cadastroPJDto.getCpf());
		
		Funcionario funcionario = new Funcionario();
		funcionario.setNome(cadastroPJDto.getNome());
		funcionario.setEmail(cadastroPJDto.getEmail());
		funcionario.setCpf(cadastroPJDto.getCpf());
		funcionario.setPerfil(PerfilEnum.ROLE_ADMIN);
		funcionario.setSenha(PasswordUtils.generateBCrypt(cadastroPJDto.getSenha()));
		
		return funcionario;
	}
	
	/**
	 * Converte dados do DTO para Funcionário.
	 * 
	 * @param funcionario
	 * @return CadastroPJDto
	 */
	private CadastroPJDto converteFuncionarioParaDTO(Funcionario funcionario) {
		
		log.info("Convertenndo dados do Funcionario para o DTo CPF: {}", funcionario.getCpf());
		
		CadastroPJDto cadastroPJDto = new CadastroPJDto();
		
		cadastroPJDto.setId(funcionario.getId());
		cadastroPJDto.setCpf(funcionario.getCpf());
		cadastroPJDto.setNome(funcionario.getNome());
		cadastroPJDto.setEmail(funcionario.getEmail());
		cadastroPJDto.setRazaoSocial(funcionario.getEmpresa().getRazaoSocial());
		cadastroPJDto.setCnpj(funcionario.getEmpresa().getCnpj());
		
		return cadastroPJDto;
	}

}
