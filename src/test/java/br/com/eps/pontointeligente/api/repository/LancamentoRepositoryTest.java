package br.com.eps.pontointeligente.api.repository;


import static org.junit.Assert.assertEquals;

import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.eps.pontointeligente.api.entity.Empresa;
import br.com.eps.pontointeligente.api.entity.Funcionario;
import br.com.eps.pontointeligente.api.entity.Lancamento;
import br.com.eps.pontointeligente.api.enums.PerfilEnum;
import br.com.eps.pontointeligente.api.enums.TipoLancamentoEnum;
import br.com.eps.pontointeligente.api.utils.PasswordUtils;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("prod")
public class LancamentoRepositoryTest {
	
	@Autowired
	private LancamentoRepository lancamentoRepository;
	
	@Autowired
	private FuncionarioRepository funcionarioRepository;
	
	@Autowired
	private EmpresaRepository empresaRepository;
	
	private Long idfuncionario;

	@Before
	public void setUp() throws Exception {
		Empresa empresa = this.empresaRepository.save(obterDadosEmpresa());
		
		Funcionario funcionario = this.funcionarioRepository.save(obterDadosFuncionario(empresa));
		this.idfuncionario = funcionario.getIdFuncionario();
		
		this.lancamentoRepository.save(obterDadosLancamentos(funcionario));
		this.lancamentoRepository.save(obterDadosLancamentos(funcionario));
	}

	@After
	public void tearDown() throws Exception {
		this.empresaRepository.deleteAll();
	}

	@Test
	public void testBuscarLancamentosPorFuncionarioId() {
		List<Lancamento> lancamentos = this.lancamentoRepository.findByFuncionarioIdFuncionario(idfuncionario);
		
		assertEquals(2, lancamentos.size());
	}
	
	@Test
	public void testBuscarLancamentosPorFuncionarioIdPaginado() {
		//Pageable page = new PageRequest(0, 10, Sort.by("funcionarioId"));
		
		Sort sort = Sort.by("FuncionarioIdFuncionario"); 
		Pageable page = PageRequest.of(0, 10, sort);
		
		Page<Lancamento> lancamentos = this.lancamentoRepository.findByFuncionarioIdFuncionario(idfuncionario, page);
		
		assertEquals(2, lancamentos.getTotalElements());
	}
	
	private Lancamento obterDadosLancamentos(Funcionario funcionario) {
		Lancamento lancameto = new Lancamento();
		lancameto.setDataLancamento(new Date());
		lancameto.setTipoLancamentoEnum(TipoLancamentoEnum.INICIO_ALMOCO);
		lancameto.setFuncionario(funcionario);
		return lancameto;
	}

	private Funcionario obterDadosFuncionario(Empresa empresa) throws NoSuchAlgorithmException {
		Funcionario funcionario = new Funcionario();
		funcionario.setNome("Fulano de Tal");
		funcionario.setPerfil(PerfilEnum.ROLE_USUARIO);
		funcionario.setSenha(PasswordUtils.generateBCrypt("123456"));
		funcionario.setNumCpf("24291173474");
		funcionario.setEmail("email@email.com");
		funcionario.setEmpresa(empresa);
		return funcionario;
	}

	private Empresa obterDadosEmpresa() {
		Empresa empresa = new Empresa();
		empresa.setRazaoSocial("Empresa de exemplo");
		empresa.setNumCnpj("51463645000100");
		return empresa;
	}

}