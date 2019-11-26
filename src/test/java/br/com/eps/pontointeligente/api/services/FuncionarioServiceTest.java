package br.com.eps.pontointeligente.api.services;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.eps.pontointeligente.api.entity.Funcionario;
import br.com.eps.pontointeligente.api.repository.FuncionarioRepository;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@SpringBootTest
public class FuncionarioServiceTest {

	@MockBean
	private FuncionarioRepository funcionarioRepository;
	
	@Autowired
	private FuncionarioService funcionarioService;
	
	@Before
	public void setUp() {
		BDDMockito.given(this.funcionarioRepository.save(Mockito.any(Funcionario.class))).willReturn(new Funcionario());
		BDDMockito.given(this.funcionarioRepository.findByNumCpf(Mockito.anyString())).willReturn(new Funcionario());
		BDDMockito.given(this.funcionarioRepository.findByEmail(Mockito.anyString())).willReturn(new Funcionario());
		BDDMockito.given(this.funcionarioRepository.getOne(Mockito.anyLong())).willReturn(new Funcionario());
	}
	
	
	@Test
	public void testarPersistir() {
		Funcionario funcionario = this.funcionarioService.persistirFuncionario(new Funcionario());
		assertNotNull(funcionario);
	}
	
	@Test
	public void testarBuscarPorNumCPF() {
		Optional<Funcionario> funcionario = this.funcionarioService.buscarFuncionarioPorNumCpf("123456");
		assertTrue(funcionario.isPresent());
	}
	
	@Test
	public void testarBuscarPorEmail() {
		Optional<Funcionario> funcionario = this.funcionarioService.buscarFuncionarioPorEmail("alguem@qualquercoisa.com");
		assertTrue(funcionario.isPresent());
	}
	
	@Test
	public void testarBuscarPorId() {
		Optional<Funcionario> funcionario = this.funcionarioService.buscarPorId(1L);
		assertTrue(funcionario.isPresent());
	}

}
