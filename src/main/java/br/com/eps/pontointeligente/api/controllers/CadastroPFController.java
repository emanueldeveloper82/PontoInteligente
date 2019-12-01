package br.com.eps.pontointeligente.api.controllers;

import java.math.BigDecimal;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

import javax.validation.Valid;

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
import br.com.eps.pontointeligente.api.dtos.CadastroPFDto;
import br.com.eps.pontointeligente.api.entity.Empresa;
import br.com.eps.pontointeligente.api.entity.Funcionario;
import br.com.eps.pontointeligente.api.enums.PerfilEnum;
import br.com.eps.pontointeligente.api.response.Response;
import br.com.eps.pontointeligente.api.services.EmpresaService;
import br.com.eps.pontointeligente.api.services.FuncionarioService;
import br.com.eps.pontointeligente.api.utils.PasswordUtils;

/**
 * Controller para cadastro de pessoa física.
 * @author emanuel developer
 * 
 */
@RestController
@RequestMapping("/api/cadastrar-pf")
@CrossOrigin(origins = "*")
public class CadastroPFController {
	
	private static final Logger log = LoggerFactory.getLogger(CadastroPJController.class);
	
	/**
	 * Construtor
	 */
	public CadastroPFController() {}
	
	@Autowired
	private FuncionarioService funcionarioService;
	
	@Autowired
	private EmpresaService empresaService;
	
	
	/**
	 * Cadastrar  uma pessoa física na base de dados.
	 * 
	 * @param cadastroPFDto
	 * @param result
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	@PostMapping
	public ResponseEntity<Response<CadastroPFDto>> cadastrar(@Valid @RequestBody CadastroPFDto cadastroPFDto,
			BindingResult result) throws NoSuchAlgorithmException {
		
		log.info("Cadastrando PF: {}", cadastroPFDto.toString());
		
		Response<CadastroPFDto> response = new Response<>();
		
		validarDadosExistentes(cadastroPFDto, result);
		Funcionario funcionario = this.converterDtoParaPF(cadastroPFDto, result);

		if (result.hasErrors()) {
			log.error("Erro validando dados de cadastro PF: {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}
		
		Optional<Empresa> empresa = this.empresaService.buscarPorNumCnpj(cadastroPFDto.getCnpj());
		empresa.ifPresent(emp -> funcionario.setEmpresa(emp));
		this.funcionarioService.persistirFuncionario(funcionario);
		
		response.setData(this.converterPFParaDto(funcionario));
		return ResponseEntity.ok(response);
	}
	
	/**
	 * Verifica se a empresa existe e verifica se o funcionário está cadastrado.
	 * 
	 * @param cadastroPJDto
	 * @param result
	 */
	private void validarDadosExistentes(CadastroPFDto cadastroPFDto, BindingResult result) {
		
		log.info("Validando dados existentes de PF: {}", cadastroPFDto.toString());
		
		Optional<Empresa> empresa = this.empresaService.buscarPorNumCnpj(cadastroPFDto.getCnpj());
		if(!empresa.isPresent()) {
			result.addError(new ObjectError("empresa", "Empresa não cadastrada."));			
		}
		
		this.funcionarioService.buscarFuncionarioPorNumCpf(cadastroPFDto.getCpf()).ifPresent(
				func -> result.addError(new ObjectError("Funcionario", "Já existe um funcionário com este CPF.")) );
		
		this.funcionarioService.buscarFuncionarioPorEmail(cadastroPFDto.getEmail()).ifPresent(
				func -> result.addError(new ObjectError("Funcionario", "Já existe um funcionário com este E-mail.")) );
	}
	
	/**
	 * Converte dados do Dto para PF.
	 * @param cadastroPFDto
	 * @param result
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	private Funcionario converterDtoParaPF(CadastroPFDto cadastroPFDto, BindingResult result)
			throws NoSuchAlgorithmException {
		Funcionario funcionario = new Funcionario();
		
		funcionario.setNome(cadastroPFDto.getNome());
		funcionario.setEmail(cadastroPFDto.getEmail());
		funcionario.setNumCpf(cadastroPFDto.getCpf());
		funcionario.setPerfil(PerfilEnum.ROLE_USUARIO);
		funcionario.setSenha(PasswordUtils.generateBCrypt(cadastroPFDto.getSenha()));
		
		cadastroPFDto.getQtdHorasAlmoco()
		.ifPresent(qtdHorasAlmoco -> funcionario.setQtdHorasAlmoco(Float.valueOf(qtdHorasAlmoco)));
		
		cadastroPFDto.getQtdHorasTrabalhoDia()
		.ifPresent(qtdHorasTrabDia -> funcionario.setQtdHorasTrabalhoDia(Float.valueOf(qtdHorasTrabDia)));
				
		cadastroPFDto.getValorHora()
		.ifPresent(valorHora -> funcionario.setValorHora(new BigDecimal(valorHora)));
		
		return funcionario;		
	}
	
	
	/**
	 * Converte PF para Dto
	 * 
	 * @param funcionario
	 * @return CadastroPFDto
	 */
	private CadastroPFDto converterPFParaDto(Funcionario funcionario) {
		CadastroPFDto cadastroPFDto = new CadastroPFDto();
		cadastroPFDto.setId(funcionario.getIdFuncionario());
		cadastroPFDto.setNome(funcionario.getNome());
		cadastroPFDto.setEmail(funcionario.getEmail());
		cadastroPFDto.setCpf(funcionario.getNumCpf());
		cadastroPFDto.setCnpj(funcionario.getEmpresa().getNumCnpj());
		
		funcionario.getQtdHorasAlmocoOpt()
		.ifPresent(qtdHorasAlmoco -> cadastroPFDto.setQtdHorasAlmoco(Optional.of(Float.toString(qtdHorasAlmoco))));
		
		funcionario.getQtdHorasTrabalhoDiaOpt()
		.ifPresent(qtdHorasTrabDia -> cadastroPFDto.setQtdHorasTrabalhoDia(Optional.of(Float.toString(qtdHorasTrabDia))));
		
		funcionario.getValorHoraOpt()
		.ifPresent(valorHora -> cadastroPFDto.setValorHora(Optional.of(valorHora.toString())));

		return cadastroPFDto;
	}
	

}
