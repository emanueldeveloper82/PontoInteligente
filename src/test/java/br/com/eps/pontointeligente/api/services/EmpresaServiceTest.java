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

import br.com.eps.pontointeligente.api.entity.Empresa;
import br.com.eps.pontointeligente.api.repository.EmpresaRepository;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@SpringBootTest
public class EmpresaServiceTest {
	
	@MockBean
	private EmpresaRepository empresaRepository;
	
	@Autowired
	private EmpresaService empresaService;
		
	private static final String CNPJ = "12345678910";
	
	
	@Before
	public void setUp() {
		BDDMockito.given(this.empresaRepository.findByNumCnpj(Mockito.anyString())).willReturn(new Empresa());
		BDDMockito.given(this.empresaRepository.save(Mockito.any(Empresa.class))).willReturn(new Empresa());	
	}
	
	@Test
	public void testBuscarPorNumCnpj() {
		Optional<Empresa> empresa = this.empresaService.buscarPorNumCnpj(CNPJ);
		assertTrue(empresa.isPresent());		
	}
	
	@Test
	public void testSalvarEmpresa() {
		Empresa empresa = this.empresaService.persistir(new Empresa());				
		assertNotNull(empresa);		
	}
	
	
	
	
	

}
