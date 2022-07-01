package br.com.hotel.repositorio;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.hotel.model.Reserva;

@Repository
@Transactional
public interface ReservaRepositorio extends JpaRepository<Reserva, Long> {
    // Reserva findBycodigoReserva(Long codigoReserva);

    @Query(value = "select *from reserva where codigoReserva = :codigoReserva2",nativeQuery = true)
    Reserva searchCodeReserva(@Param("codigoReserva2") String codigoReserva);
}