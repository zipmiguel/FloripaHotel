package br.com.hotel.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.hotel.model.Quarto;

@Repository
public interface QuartoRepositorio extends JpaRepository<Quarto, Long> {
    Quarto findBynumero(String numero);
    int countBystatus(Boolean disponivel);

    @Query(value = "select count(*) from hotel.quarto where status = 1 and id_tipoquarto = :idTipoQuarto",nativeQuery = true)
    int contarPeloTipoQuarto(@Param("idTipoQuarto") Long idTipoQuarto);
    
    @Query(value = "select *from hotel.quarto where status=1 and id_tipoquarto = :tipoQuarto",nativeQuery = true)
    List<Quarto> findByTipoQuarto(@Param("tipoQuarto") Long tipoQuarto);
}