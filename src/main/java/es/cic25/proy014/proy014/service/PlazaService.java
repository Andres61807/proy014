package es.cic25.proy014.proy014.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.cic25.proy014.proy014.model.Plaza;
import es.cic25.proy014.proy014.respository.PlazaRepository;

@Service
@Transactional
public class PlazaService {

    @Autowired
    private PlazaRepository plazaRepository;

    public Plaza getPlaza(Long id){
        return plazaRepository.findById(id).orElse(null);
    }

    public List<Plaza> getPlazas(){
        return plazaRepository.findAll();
    }

    public List<Plaza> getPlazasVacias(){
        return plazaRepository.listPlazasVacias();
    }

    public Plaza createPlaza(Plaza plaza){
        return plazaRepository.save(plaza);
    }

    public Plaza updatePlaza(Plaza plaza){
        return plazaRepository.save(plaza);
    }

    public void deletePlaza(Long id){
        plazaRepository.deleteById(id);
    }
}
