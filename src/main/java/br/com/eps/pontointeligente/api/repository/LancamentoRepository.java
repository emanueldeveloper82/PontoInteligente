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
@NamedQueries({
	@NamedQuery(name = "LancamentoRepository.findByIdFuncionario", 
			   query = "SELECT l FROM Lancamento l WHERE l.funcionario.idFuncionario = :idFuncionario ")
})
public interface LancamentoRepository extends JpaRepository<Lancamento, Integer> {
	
	public List<Lancamento> findByFuncionario(@Param("idFuncionario") Integer idFuncionario);
	
	public Page<Lancamento> findByFuncionario(@Param("idFuncionario") Integer idFuncionario, Pageable pageable);

}
