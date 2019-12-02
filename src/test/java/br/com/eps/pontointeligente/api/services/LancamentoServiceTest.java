package br.com.eps.pontointeligente.api.services;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.Optional;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.eps.pontointeligente.api.entity.Lancamento;
import br.com.eps.pontointeligente.api.repository.LancamentoRepository;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@SpringBootTest
public class LancamentoServiceTest {
	

	@MockBean
	private LancamentoRepository lancamentoRepository;
	
	@Autowired
	private LancamentoService lancamentoService;
	
	@Before
	public void setUp() {
		
		BDDMockito.given(this.lancamentoRepository
				.findByFuncionarioIdFuncionario(Mockito.anyLong(), Mockito.any(PageRequest.class)))
		.willReturn(new PageImpl<Lancamento>(new ArrayList<Lancamento>()));
		
		BDDMockito.given(this.lancamentoRepository.getOne(Mockito.anyLong())).willReturn(new Lancamento());
		
		BDDMockito.given(this.lancamentoRepository.save(Mockito.any(Lancamento.class))).willReturn(new Lancamento());
	}
	
	/**
	 * Teste com Pageble e pageRequest. Prestar atenção!
	 */
	@Test
	public void testBuscarLancamentosPorFuncionario() {
		Sort sort = Sort.by("FuncionarioIdFuncionario"); 
		Page<Lancamento> lancamento = this.lancamentoService.buscarPorIdFuncionario(1L, PageRequest.of(0, 10, sort));
		assertNotNull(lancamento);
	}
	
	@Test
	public void testPersistirLancamento() {
		Lancamento lancamento = this.lancamentoService.persistirLancamento(new Lancamento());
		assertNotNull(lancamento);
	}
	
	@Test
	public void testarBuscarPorIdLancamento() {
		Optional<Lancamento> lancamento = this.lancamentoService.buscarPorIdLancamento(1L);
		assertNotNull(lancamento);
	}
}
