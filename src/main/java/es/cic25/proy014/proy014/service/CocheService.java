package es.cic25.proy014.proy014.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.cic25.proy014.proy014.model.Coche;
import es.cic25.proy014.proy014.model.Multa;
import es.cic25.proy014.proy014.model.Plaza;
import es.cic25.proy014.proy014.respository.CocheRepository;
import es.cic25.proy014.proy014.respository.MultaRepository;
import es.cic25.proy014.proy014.respository.PlazaRepository;

@Service
@Transactional
public class CocheService {

    @Autowired
    private CocheRepository cocheRepository;
    @Autowired
    private MultaRepository multaRepository;
    @Autowired
    private PlazaRepository plazaRepository;

    @Transactional(readOnly = true)
    public Coche getCoche(Long id){
        return cocheRepository.findById(id).orElse(null);
    }

    @Transactional(readOnly = true)
    public List<Coche> getCoches(){
        return cocheRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Coche> getCochesMultados(){
        return cocheRepository.listCochesMultados();
    }

    public Coche createCoche(Coche coche){
        return cocheRepository.save(coche);
    }

    public Coche updateCoche(Coche coche){
        return cocheRepository.save(coche);
    }

    public Coche aparcaCoche(Coche coche,Long id){
        Plaza plazaNuevaAparcar=plazaRepository.findById(id).orElse(null);
        Plaza plazaAsignada=plazaRepository.findById(coche.getPlazaAsignada().getId()).get();
        if(plazaNuevaAparcar.getCocheAparcado()==null){
            if (plazaAsignada.getId()!=plazaNuevaAparcar.getId()){
                Multa multa=new Multa();
                multa.setCoche(coche);
                multa.setPagada(false);
                multa.setCoche(coche);

                coche.setPlazaAparcado(plazaNuevaAparcar);

                coche.getMultas().add(multa);
                return cocheRepository.save(coche);
            } else {
                coche.setPlazaAparcado(plazaNuevaAparcar);
                return cocheRepository.save(coche);
            }
        } else {
            throw new PlazaOcupadaException("La plaza ya esta ocupada");
        }
    }

    public Coche multarCoche(Coche coche,double importe){
        Multa multa=new Multa();
        multa.setCoche(coche);
        multa.setImporte(importe);
        multa.setPagada(false);
        multaRepository.save(multa);

        return cocheRepository.save(coche);
    }

    public void deleteCoche(Long id){
        cocheRepository.deleteById(id);
    }
}
