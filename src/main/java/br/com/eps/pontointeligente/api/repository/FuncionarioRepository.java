package br.com.eps.pontointeligente.api.repository;

import br.com.eps.pontointeligente.api.entity.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Transactional(readOnly = true)
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
	
	public Funcionario findByCpf(String cpf);
	
	public Funcionario findByEmail(String email);
	
	public Funcionario findByCpfOrEmail(String cpf, String email);
	
	List<Funcionario> findByEmpresaId(Long id);

}
