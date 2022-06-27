package br.com.hotel.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import org.springframework.stereotype.Repository;

import br.com.hotel.model.Cargo;

@Repository
public interface CargoRepositorio extends JpaRepository<Cargo, Long> {
    Cargo findBytipoCargo(String tipoCargo);
    List<Cargo> findByIdCargo(Long idCargo);
}