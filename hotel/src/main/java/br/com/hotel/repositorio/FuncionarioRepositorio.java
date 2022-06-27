package br.com.hotel.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.com.hotel.model.Funcionario;
@Repository
public interface FuncionarioRepositorio extends JpaRepository<Funcionario, Long>{
	Funcionario findByCpf(String cpf);
}
