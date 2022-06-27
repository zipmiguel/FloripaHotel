package br.com.hotel.repositorio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.hotel.model.TipoQuarto;
import java.util.List;

//Luiz Eduardo 
@Repository
@Transactional
public interface TipoQuartoRepositorio extends JpaRepository<TipoQuarto,Long>{

    @Query(nativeQuery = true,value = "select * from tipoQuarto where StatusTipoQuarto = true")
    List<TipoQuarto> BuscarTiposQuartoTrue();
    
    @Modifying(clearAutomatically = true)
    @Query(value = "update tipoquarto set StatusTipoQuarto = false where idTipoQuarto = :idquarto",nativeQuery = true)
    void CancelaTipoQuarto(@Param("idquarto") Long idQuarto);
}