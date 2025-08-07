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

import es.cic25.proy014.proy014.model.Coche;
import es.cic25.proy014.proy014.service.CocheService;

@RestController
@RequestMapping("/coche")
public class CocheController {

    @Autowired
    private CocheService cocheService;
    

    @GetMapping("/{id}")
    public Coche getCoche(@PathVariable long id){
        return cocheService.getCoche(Long.valueOf(id));
    }

    @GetMapping("/listado")
    public List<Coche> getCoches(){
        return cocheService.getCoches();
    }
    
    @GetMapping()
    public List<Coche> getCochesMultados(){
        return cocheService.getCochesMultados();
    }

    @PostMapping()
    public Coche createCoche(@RequestBody Coche coche){
        return cocheService.createCoche(coche);
    }

    @PutMapping()
    public Coche updateCoche(@RequestBody Coche coche){
        return cocheService.updateCoche(coche);
    }

    @PutMapping("/multar/")
    public Coche multarCoche(@RequestBody Coche coche,@PathVariable double importe){
        return cocheService.multarCoche(coche,importe);
    }


    @DeleteMapping("/{id}")
    public void deleteCoche(@PathVariable long id){
        cocheService.deleteCoche(Long.valueOf(id));
    }
}
