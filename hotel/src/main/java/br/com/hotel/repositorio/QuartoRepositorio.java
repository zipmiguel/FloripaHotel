package br.com.hotel.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.hotel.model.Quarto;

@Repository
public interface QuartoRepositorio extends JpaRepository<Quarto, Long> {
    Quarto findBynumero(String numero);
    int countBystatus(Boolean disponivel);
}