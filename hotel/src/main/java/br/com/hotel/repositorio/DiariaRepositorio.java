package br.com.hotel.repositorio;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.hotel.model.Diaria;
import br.com.hotel.model.TipoQuarto;

@Repository
public interface DiariaRepositorio extends JpaRepository<Diaria,Long>{
    Diaria findBytipoQuarto(Optional<TipoQuarto> tipoQuarto);
}
