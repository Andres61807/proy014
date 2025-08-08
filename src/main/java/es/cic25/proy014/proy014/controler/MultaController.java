package es.cic25.proy014.proy014.controler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.cic25.proy014.proy014.model.Multa;
import es.cic25.proy014.proy014.service.MultaService;

@RestController
@RequestMapping("/multa")
public class MultaController {
    
    @Autowired
    private MultaService multaService;
    
    @GetMapping("/{id}")
    public Multa getMulta(@PathVariable long id) {
        return multaService.getMulta(Long.valueOf(id));
    }

    @GetMapping("/listado")
    public List<Multa> getMultas() {
        return multaService.getMultas();
    }

    @PostMapping()
    public Multa createMulta(@RequestBody Multa multa) {
        return multaService.createMulta(multa);
    }

    @PutMapping()
    public Multa updateMulta(@RequestBody Multa multa) {
        return multaService.updateMulta(multa);
    }
    
    @DeleteMapping("/{id}")
    public void deleteMulta(@PathVariable long id) {
        multaService.deleteMulta(Long.valueOf(id));
    }
}
