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

import es.cic25.proy014.proy014.model.Plaza;
import es.cic25.proy014.proy014.service.PlazaService;

@RestController
@RequestMapping("/plaza")
public class PlazaController {

    @Autowired
    private PlazaService plazaService;
    
    @GetMapping("/{id}")
    public Plaza getPlaza(@PathVariable long id){
        return plazaService.getPlaza(Long.valueOf(id));
    }

    @GetMapping("/listado")
    public List<Plaza> getPlazas(){
        return plazaService.getPlazas();
    }

    @GetMapping("/plazas-vacias")
    public List<Plaza> getPlazasVacias(){
        return plazaService.getPlazasVacias();
    }

    @PostMapping()
    public Plaza createPlaza(@RequestBody Plaza plaza){
        return plazaService.createPlaza(plaza);
    }

    @PutMapping()
    public Plaza updatePlaza(@RequestBody Plaza plaza){
        return plazaService.updatePlaza(plaza);
    }
    
    @DeleteMapping("/{id}")
    public void deletePlaza(@PathVariable long id){
        plazaService.deletePlaza(Long.valueOf(id));
    }
}
