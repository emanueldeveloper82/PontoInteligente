package br.com.eps.pontointeligente.api.repository;

import br.com.eps.pontointeligente.api.entity.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface EmpresaRepository extends JpaRepository<Empresa, Long> {
	
	@Transactional(readOnly = true)
	public Empresa findByCnpj(String cnpj);

}
