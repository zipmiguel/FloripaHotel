package br.com.hotel.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.hotel.model.Hospede;

@Repository
public interface LoginRepositorio extends JpaRepository<Hospede,Long> {
    Hospede findByemail(String email);
}
