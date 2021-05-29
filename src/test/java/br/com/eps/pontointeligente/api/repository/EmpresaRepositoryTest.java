package br.com.eps.pontointeligente.api.repository;

import static org.junit.Assert.assertEquals;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import br.com.eps.pontointeligente.api.entity.Empresa;


@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("prod")
public class EmpresaRepositoryTest {
	
	@Autowired
	private EmpresaRepository empresaRepository;
	
	private static final String CNPJ = "51463645000100";

	@Before
	public void setUp() throws Exception {
		Empresa empresa = new Empresa();
		empresa.setRazaoSocial("Empresa de exemplo");
		empresa.setNumCnpj(CNPJ);
		this.empresaRepository.save(empresa);
	}
	
	@After
    public final void tearDown() { 
		this.empresaRepository.deleteAll();
	}

	@Test
	public void testBuscarPorCnpj() {
		Empresa empresa = this.empresaRepository.findByNumCnpj(CNPJ);

		assertEquals(CNPJ, empresa.getNumCnpj());
	}

}