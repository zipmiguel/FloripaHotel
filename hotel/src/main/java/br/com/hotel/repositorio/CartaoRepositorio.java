package br.com.hotel.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.hotel.model.Cartao;
@Repository
public interface CartaoRepositorio extends JpaRepository<Cartao,Long> {

}