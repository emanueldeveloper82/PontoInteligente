package br.com.eps.pontointeligente.api.controllers;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import br.com.eps.pontointeligente.api.entity.Empresa;
import br.com.eps.pontointeligente.api.services.EmpresaService;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc //Deve ser adicionado o contexto web para rodar testes da camada web.
@ActiveProfiles("prod")
public class EmpresaControllerTest {
	
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private EmpresaService empresaService;
	
	private static final String BUSCAR_EMPRESA_CNPJ_URL = "/api/empresas/cnpj/";
	private static final Long ID = Long.valueOf(1);
	private static final String CNPJ = "57817356000130";
	private static final String RAZAO_SOCIAL = "Empresa abc";
	
	
	@Test
	@WithMockUser
	public void testBuscarEmpresaCnpjInvalido() throws Exception {
		BDDMockito.given(this.empresaService.buscarPorNumCnpj(Mockito.anyString())).willReturn(Optional.empty());

		mockMvc.perform(MockMvcRequestBuilders.get(BUSCAR_EMPRESA_CNPJ_URL + CNPJ)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.errors").value("Empresa não localizada pelo CNPJ "+ CNPJ));		
	}
	
	@Test
	@WithMockUser
	public void testBuscarEmpresaCnpjValido() throws Exception {
		
		BDDMockito.given(this.empresaService.buscarPorNumCnpj(Mockito.anyString()))
					.willReturn(Optional.of(this.obterDadosEmpresa()));
		
		mockMvc.perform(MockMvcRequestBuilders.get(BUSCAR_EMPRESA_CNPJ_URL + CNPJ)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.data.id").value(ID))
				.andExpect(jsonPath("$.data.razaoSocial", equalTo(RAZAO_SOCIAL)))
				.andExpect(jsonPath("$.data.cnpj", equalTo(CNPJ)))
				.andExpect(jsonPath("$.errors").isEmpty());
	}
	
	private Empresa obterDadosEmpresa() {
		Empresa empresa = new Empresa();
		empresa.setIdEmpresa(ID);
		empresa.setRazaoSocial(RAZAO_SOCIAL);
		empresa.setNumCnpj(CNPJ);
		return empresa;
	}
}
