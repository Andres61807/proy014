
package es.cic25.proy014.proy014.respository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import es.cic25.proy014.proy014.model.Coche;

public interface CocheRepository extends JpaRepository <Coche,Long>{
    
    @Query("SELECT c FROM Coche c LEFT JOIN c.multas m WHERE m IS NOT NULL")
    List<Coche> listCochesMultados();
}
