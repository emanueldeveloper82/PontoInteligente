package br.com.eps.pontointeligente.api.repository;

import br.com.eps.pontointeligente.api.entity.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Transactional(readOnly = true)
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
	
	public Funcionario findByNumCpf(String numCpf);
	
	public Funcionario findByEmail(String email);
	
	public Funcionario findByNumCpfOrEmail(String numCpf, String email);
	
	List<Funcionario> findByEmpresaIdEmpresa(Long id);

}
