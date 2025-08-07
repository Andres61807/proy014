package es.cic25.proy014.proy014.respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import es.cic25.proy014.proy014.model.Plaza;

public interface PlazaRepository extends JpaRepository<Plaza,Long> {
   
    @Query("SELECT p FROM Plaza p WHERE p.coche IS NULL")
    List<Plaza> listPlazasVacias();
}
