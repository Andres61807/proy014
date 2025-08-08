package es.cic25.proy014.proy014.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.cic25.proy014.proy014.model.Multa;
import es.cic25.proy014.proy014.respository.MultaRepository;

@Service
@Transactional
public class MultaService {
     @Autowired
    private MultaRepository multaRepository;

    public Multa getMulta(Long id) {
        return multaRepository.findById(id).orElse(null);
    }

    public List<Multa> getMultas() {
        return multaRepository.findAll();
    }

    public Multa createMulta(Multa multa) {
        return multaRepository.save(multa);
    }

    public Multa updateMulta(Multa multa) {
        return multaRepository.save(multa);
    }

    public void deleteMulta(Long id) {
        multaRepository.deleteById(id);
    }
}
