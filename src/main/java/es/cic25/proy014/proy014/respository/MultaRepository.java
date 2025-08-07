package es.cic25.proy014.proy014.respository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import es.cic25.proy014.proy014.model.Multa;


public interface MultaRepository extends JpaRepository<Multa,Long>{

    @Query("SELECT m FROM Multa m WHERE m.pagada = :pagada")
    List<Multa> findByPagado(@Param("pagada")boolean pagada);
}
