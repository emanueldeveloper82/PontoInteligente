package br.com.eps.pontointeligente.api.repository;

import java.util.List;

import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import br.com.eps.pontointeligente.api.entity.Lancamento;


@Transactional(readOnly = true)
@NamedQueries(value={ 
	@NamedQuery(name = "LancamentoRepository.findByFuncionarioIdFuncionario", 
			   query = "SELECT l FROM Lancamento l WHERE l.funcionario.idfuncionario =:idfuncionario")
})
public interface LancamentoRepository extends JpaRepository<Lancamento, Long> {
	
	public List<Lancamento> findByFuncionarioIdFuncionario(@Param("idfuncionario") Long idfuncionario);
	
	public Page<Lancamento> findByFuncionarioIdFuncionario(@Param("idfuncionario") Long idfuncionario, Pageable pageable);
	
	Lancamento findFirstByFuncionarioIdFuncionarioOrderByDataCriacaoDesc(Long funcionarioId);

	List<Lancamento> findByFuncionarioIdFuncionarioOrderByDataLancamentoDesc(Long funcionarioId);

}
