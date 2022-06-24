package br.com.hotel.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.hotel.model.Reserva;

@Repository
public interface ReservaRepositorio extends JpaRepository<Reserva, Long> {
    Reserva findBycodigoReserva(Long codigoReserva);
}