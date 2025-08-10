package es.cic25.proy014.proy014.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.cic25.proy014.proy014.model.Coche;
import es.cic25.proy014.proy014.model.Plaza;
import es.cic25.proy014.proy014.respository.CocheRepository;
import es.cic25.proy014.proy014.respository.PlazaRepository;

@Service
@Transactional
public class PlazaService {

    @Autowired
    private PlazaRepository plazaRepository;
    @Autowired
    private CocheRepository cocheRepository;

    public Plaza getPlaza(Long id){
        return plazaRepository.findById(id).orElse(null);
    }

    public List<Plaza> getPlazas(){
        return plazaRepository.findAll();
    }

    public Plaza createPlaza(Plaza plaza){
        return plazaRepository.save(plaza);
    }

    public Plaza updatePlaza(Plaza plaza){
        return plazaRepository.save(plaza);
    }

    public Plaza asignaPlaza(Plaza plaza,Long id){
        if (plaza.getCochesAsignados().size()>=Plaza.getMaxPlazas()){
            throw new MaxPlazasException("Se ha alcanzado el maximo de plazas");
        } else {
            Coche cocheAsignado=cocheRepository.findById(id).orElse(null);
            plaza.getCochesAsignados().add(cocheAsignado);
            return plazaRepository.save(plaza);
        }
    }

    public Plaza desasignaPlaza(Plaza plaza,Long id){
        Coche cocheAsignado=cocheRepository.findById(id).orElse(null);
        plaza.getCochesAsignados().add(cocheAsignado);
        return plazaRepository.save(plaza);
        
    }

    public void deletePlaza(Long id){
        plazaRepository.deleteById(id);
    }
}
