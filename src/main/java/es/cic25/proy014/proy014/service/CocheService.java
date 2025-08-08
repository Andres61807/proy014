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

@Service
@Transactional
public class CocheService {

    @Autowired
    private CocheRepository cocheRepository;
    @Autowired
    private MultaRepository multaRepository;

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
        Coche cocheOriginal=cocheRepository.findById(coche.getId()).orElse(null);
        if (coche.getPlaza().getId()!=cocheOriginal.getPlaza().getId()){
            Multa multa=new Multa();
            multa.setCoche(coche);
            multa.setFecha(LocalDate.now());
            multa.setImporte(100);
            multa.setPagada(false);
            multa.setCoche(coche);

            coche.getMultas().add(multa);
        }
        return cocheRepository.save(coche);
    }

    public Coche multarCoche(Coche coche, Plaza plaza){
        if (coche.getPlaza().getId()!=plaza.getId()){
            Multa multa=new Multa();
            multa.setCoche(coche);
            multa.setFecha(LocalDate.now());
            multa.setImporte(100);
            multa.setPagada(false);
            multa.setCoche(coche);

            coche.setPlaza(plaza);

            coche.getMultas().add(multa);
        }
        return cocheRepository.save(coche);
    }

    public Coche multarCoche(Coche coche,double importe){
        Multa multa=new Multa();
        multa.setCoche(coche);
        multa.setFecha(LocalDate.now());
        multa.setImporte(importe);
        multa.setPagada(false);
        multaRepository.save(multa);

        return cocheRepository.save(coche);
    }

    public void deleteCoche(Long id){
        cocheRepository.deleteById(id);
    }
}
