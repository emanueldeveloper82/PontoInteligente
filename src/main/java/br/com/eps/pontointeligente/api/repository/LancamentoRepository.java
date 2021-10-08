package br.com.eps.pontointeligente.api.repository;

import br.com.eps.pontointeligente.api.entity.Lancamento;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Transactional(readOnly = true)
@NamedQueries(value = {
	@NamedQuery(name = "LancamentoRepository.findByFuncionario",
			   query = "SELECT l FROM Lancamento l WHERE l.funcionario.idfuncionario =:idfuncionario")
})
public interface LancamentoRepository extends JpaRepository<Lancamento, Long> {
	
	public List<Lancamento> findByFuncionario(@Param("idfuncionario") Long idfuncionario);
	
	public Page<Lancamento> findByFuncionario(@Param("idfuncionario")
										 Long idfuncionario, Pageable pageable);
	
	Lancamento findFirstByFuncionarioOrderByDataCriacaoDesc(Long funcionarioId);

	List<Lancamento> findByFuncionarioOrderByDataLancamentoDesc(Long funcionarioId);

}
