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
@ActiveProfiles("prod")
@SpringBootTest
public class EmpresaServiceTest {
	
	@MockBean
	public EmpresaRepository repository;
	
	@Autowired
	public EmpresaService service;
		
	private static final String CNPJ = "12345678910";
	
	
	@Before
	public void setUp() {
		BDDMockito.given(this.repository.findByNumCnpj(Mockito.anyString())).willReturn(new Empresa());
		BDDMockito.given(this.repository.save(Mockito.any(Empresa.class))).willReturn(new Empresa());
	}
	
	@Test
	public void testBuscarPorNumCnpj() {
		Empresa empresa= new Empresa();
		empresa.setIdEmpresa(1L);
		empresa.setNumCnpj(CNPJ);
		Mockito.when(repository.findByNumCnpj(empresa.getNumCnpj()))
				.thenReturn(empresa);
		assertNotNull(service.buscarPorNumCnpj(empresa.getNumCnpj()));
	}
	
	@Test
	public void testSalvarEmpresa() {
		Empresa empresa = new Empresa();
		empresa.setIdEmpresa(1L);
		empresa.setNumCnpj(CNPJ);
		assertNotNull(service.persistir(empresa));
	}
	
	
	
	
	

}
