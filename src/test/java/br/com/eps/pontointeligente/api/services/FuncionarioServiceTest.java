package br.com.eps.pontointeligente.api.services;

import static org.junit.Assert.assertNotNull;
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
@ActiveProfiles("prod")
@SpringBootTest
public class FuncionarioServiceTest {

	@MockBean
	public FuncionarioRepository repository;
	
	@Autowired
	public FuncionarioService service;

	private static final String CPF = "12345678910";
	private static final String EMAIL = "alguem@qualquercoisa.com";

	@Before
	public void setUp() {
		BDDMockito.given(this.repository.save(Mockito.any(Funcionario.class))).willReturn(new Funcionario());
		BDDMockito.given(this.repository.findByNumCpf(Mockito.anyString())).willReturn(new Funcionario());
		BDDMockito.given(this.repository.findByEmail(Mockito.anyString())).willReturn(new Funcionario());
		BDDMockito.given(this.repository.getOne(Mockito.anyLong())).willReturn(new Funcionario());
	}
	
	
	@Test
	public void testarPersistir() {
		Funcionario funcionario = new Funcionario();
		funcionario.setIdFuncionario(1L);
		funcionario.setNumCpf(CPF);
		assertNotNull(service.persistirFuncionario(funcionario));
	}
	
	@Test
	public void testarBuscarPorNumCPF() {
		Funcionario funcionario = new Funcionario();
		funcionario.setIdFuncionario(1L);
		funcionario.setNumCpf(CPF);
		funcionario.setEmail(EMAIL);
		Mockito.when(repository.findByNumCpf(funcionario.getNumCpf()))
				.thenReturn(funcionario);
		assertNotNull(service.buscarFuncionarioPorEmail(funcionario.getNumCpf()));
	}
	
	@Test
	public void testarBuscarPorEmail() {
		Funcionario funcionario = new Funcionario();
		funcionario.setIdFuncionario(1L);
		funcionario.setNumCpf(CPF);
		funcionario.setEmail(EMAIL);
		Mockito.when(repository.findByEmail(funcionario.getEmail()))
				.thenReturn(funcionario);
		assertNotNull(service.buscarFuncionarioPorEmail(funcionario.getEmail()));
	}
	
	@Test
	public void testarBuscarPorId() {

		Funcionario funcionario = new Funcionario();
		funcionario.setIdFuncionario(1L);
		funcionario.setNumCpf(CPF);
		funcionario.setEmail(EMAIL);
		Mockito.when(repository.findById(funcionario.getIdFuncionario()))
				.thenReturn(Optional.of(funcionario));
		assertNotNull(service.buscarPorId(funcionario.getIdFuncionario()));
	}

}
