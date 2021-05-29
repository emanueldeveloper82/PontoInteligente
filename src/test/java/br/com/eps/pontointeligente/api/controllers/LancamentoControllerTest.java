package br.com.eps.pontointeligente.api.controllers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.eps.pontointeligente.api.dtos.LancamentoDto;
import br.com.eps.pontointeligente.api.entity.Funcionario;
import br.com.eps.pontointeligente.api.entity.Lancamento;
import br.com.eps.pontointeligente.api.enums.TipoLancamentoEnum;
import br.com.eps.pontointeligente.api.services.FuncionarioService;
import br.com.eps.pontointeligente.api.services.LancamentoService;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("prod")
public class LancamentoControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private LancamentoService lancamentoService;
	
	@MockBean
	private FuncionarioService funcionarioService;
	
	private static final String URL_BASE = "/api/lancamentos/";
	private static final Long ID_FUNCIONARIO = 1L;
	private static final Long ID_LANCAMENTO = 1L;
	private static final String TIPO = TipoLancamentoEnum.INICIO_TRABALHO.name();
	private static final Date DATA = new Date();
	
	private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	@Test
	@WithMockUser
	public void testCadastrarLancamento() throws Exception {
		Lancamento lancamento = obterDadosLancamento();
		BDDMockito.given(this.funcionarioService.buscarPorId(Mockito.anyLong())).willReturn(Optional.of(new Funcionario()));
		BDDMockito.given(this.lancamentoService.persistirLancamento(Mockito.any(Lancamento.class))).willReturn(lancamento);
		
		mockMvc.perform(MockMvcRequestBuilders.post(URL_BASE)
				.content(this.obterJsonRequisicaoPost())
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.data.idLancamento").value(ID_LANCAMENTO))
				.andExpect(jsonPath("$.data.tipoLancamento").value(TIPO))
				.andExpect(jsonPath("$.data.dataLancamento").value(this.dateFormat.format(DATA)))
				.andExpect(jsonPath("$.data.funcionarioId").value(ID_FUNCIONARIO))
				.andExpect(jsonPath("$.errors").isEmpty());
	}
	
	@Test
	@WithMockUser
	public void testCadastrarLancamentoFuncionarioIdInvalido() throws Exception {
		BDDMockito.given(this.funcionarioService.buscarPorId(Mockito.anyLong())).willReturn(Optional.empty());

		mockMvc.perform(MockMvcRequestBuilders.post(URL_BASE)
				.content(this.obterJsonRequisicaoPost())
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.errors").value("Funcionário não encontrado. ID inexistente."))
				.andExpect(jsonPath("$.data").isEmpty());
	}
	
	@Test
	@WithMockUser(username = "admin@empresa.com", roles = {"ADMIN"})
	public void testRemoverLancamento() throws Exception {
		BDDMockito.given(this.lancamentoService.buscarPorIdLancamento(Mockito.anyLong())).willReturn(Optional.of(new Lancamento()));

		mockMvc.perform(MockMvcRequestBuilders.delete(URL_BASE + ID_LANCAMENTO)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}
	
	
	@Test
	@WithMockUser
	public void testRemoverLancamentoAcessoNegado() throws Exception {
		
		BDDMockito.given(this.lancamentoService.buscarPorIdLancamento(Mockito.anyLong()))
		.willReturn(Optional.of(new Lancamento()));

		mockMvc.perform(MockMvcRequestBuilders.delete(URL_BASE + ID_LANCAMENTO)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isForbidden());
	}

	private String obterJsonRequisicaoPost() throws JsonProcessingException {
		LancamentoDto lancamentoDto = new LancamentoDto();
		lancamentoDto.setIdLancamento(null);
		lancamentoDto.setDataLancamento(this.dateFormat.format(DATA));
		lancamentoDto.setTipoLancamento(TIPO);
		lancamentoDto.setFuncionarioId(ID_FUNCIONARIO);
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(lancamentoDto);
	}

	private Lancamento obterDadosLancamento() {
		Lancamento lancamento = new Lancamento();
		lancamento.setIdLancamento(ID_LANCAMENTO);
		lancamento.setDataLancamento(DATA);
		lancamento.setTipoLancamentoEnum(TipoLancamentoEnum.valueOf(TIPO));
		lancamento.setFuncionario(new Funcionario());
		lancamento.getFuncionario().setIdFuncionario(ID_FUNCIONARIO);
		return lancamento;
	}	

}
