package es.cic25.proy014.proy014.testcontroler;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import org.springframework.test.web.servlet.MvcResult;

import es.cic25.proy014.proy014.model.Coche;
import es.cic25.proy014.proy014.model.Multa;
import es.cic25.proy014.proy014.model.Plaza;
import es.cic25.proy014.proy014.respository.CocheRepository;
import es.cic25.proy014.proy014.respository.MultaRepository;
import es.cic25.proy014.proy014.respository.PlazaRepository;
import io.micrometer.core.instrument.binder.okhttp3.OkHttpObservationConvention;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class CocheControlerIntegrationTest {
    
    @Autowired
    private CocheRepository cocheRepository;
    @Autowired 
    private MultaRepository multaRepository;
    @Autowired
    private PlazaRepository plazaRepository;
    @Autowired 
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    private Plaza plaza;
    private Plaza plaza2;
    private Plaza plaza3;
    private Plaza plaza4;
    private Plaza plaza5;

    private Coche coche;
    private Coche coche2;
    private Coche coche3;
    private Coche coche4;
    private Coche coche5;
    private Coche coche6;

    private Multa multa;
    private Multa multa2;
    private Multa multa3;
    
    @BeforeEach
    void setup(){
        plazaRepository.deleteAll();
        cocheRepository.deleteAll();
        multaRepository.deleteAll();
        //se crean las plazas
        plaza=new Plaza();
        plaza2=new Plaza();
        plaza3=new Plaza();
        plaza4=new Plaza();
        plaza5=new Plaza();
        //se le dan datos a las plazas
        plaza.setPiso(1);
        plaza.setTrastero(false);
        

        plaza2.setPiso(2);
        plaza2.setTrastero(false);
        
        plaza3.setPiso(3);
        plaza3.setTrastero(true);
        
        plaza4.setPiso(4);
        plaza4.setTrastero(false);
        
        plaza5.setPiso(5);
        plaza5.setTrastero(true);

        //se guardan las plazas
        plaza=plazaRepository.save(plaza);
        plaza2=plazaRepository.save(plaza2);
        plaza3=plazaRepository.save(plaza3);
        plaza4=plazaRepository.save(plaza4);
        plaza5=plazaRepository.save(plaza5);
        
        //se crean coches
        coche=new Coche();
        coche2=new Coche();
        coche3=new Coche();
        coche4=new Coche();
        coche5=new Coche();
        coche6=new Coche();

        //se le dan datos a los cohes
        coche.setPropietario("Paco");
        coche.setMatricula("123");

        coche2.setPropietario("Pepe");
        coche2.setMatricula("456");

        coche3.setPropietario("Juan Antonio");
        coche3.setMatricula("789");
        
        //se guardan los coches en la db
        coche=cocheRepository.save(coche);
        coche2=cocheRepository.save(coche2);
        coche3=cocheRepository.save(coche3);
        coche6=cocheRepository.save(coche6);
        //se crean las multas
        multa=new Multa();
        multa2=new Multa();
        multa3=new Multa();
        
        //se le dan datos a las multas
        multa.setImporte(100);
        multa.setPagada(false);

        
        multa2.setImporte(100);
        multa2.setPagada(false);
        
        
        multa3.setImporte(100);
        multa3.setPagada(false);
        
        //se guardan las multas
        multa=multaRepository.save(multa);
        multa2=multaRepository.save(multa2);
        multa3=multaRepository.save(multa3);

        //asignacion de plazas correspondientes a los coches
        coche.setPlazaAsignada(plaza);
        coche2.setPlazaAsignada(plaza2);
        coche3.setPlazaAsignada(plaza3);
        coche4.setPlazaAsignada(plaza4);
        coche5.setPlazaAsignada(plaza5);

        //aparcar un coche en una plaza
        plaza.setCocheAparcado(coche);
        plaza2.setCocheAparcado(coche2);

        plaza.getCochesAsignados().add(coche);
        plaza.getCochesAsignados().add(coche2);
        
        plaza2.getCochesAsignados().add(coche);
        plaza2.getCochesAsignados().add(coche2);
        plaza2.getCochesAsignados().add(coche3);
        plaza2.getCochesAsignados().add(coche4);
        plaza2.getCochesAsignados().add(coche5);
        //asignacion de multas coche
        multa.setCoche(coche);
        multa2.setCoche(coche);
        multa3.setCoche(coche);

        coche.getMultas().add(multa);
        coche.getMultas().add(multa2);
        coche.getMultas().add(multa3);

        
    }

    @Test
    void getCocheTest() throws Exception{
        MvcResult mvcResult=mockMvc.perform(get("/coche/"+coche.getId().toString()))
            .andDo(print())
            .andExpect(status().isOk())
            .andReturn();

        Coche cocheComprobacion=objectMapper.readValue(mvcResult.getResponse().getContentAsString(),Coche.class);
        
        assertTrue(cocheComprobacion.getPlazaAparcado()!=null);
        assertEquals("123",cocheComprobacion.getMatricula());
        assertEquals(3,cocheComprobacion.getMultas().size());
    }

    @Test
    void getCochesTest() throws Exception{
        mockMvc.perform(get("/coche/listado"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(result ->{
                String response=result.getResponse().getContentAsString();
                List<Coche> resultado=objectMapper.readValue(response,new TypeReference<List<Coche>>() {});
                
                assertTrue(resultado.size()>=3);
            });
    }

    @Test
    void getCochesMultadosTest() throws Exception{
        mockMvc.perform(get("/coche/listado/multados"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(result ->{
                String response=result.getResponse().getContentAsString();
                List<Coche> resultado=objectMapper.readValue(response,new TypeReference<List<Coche>>() {});
                
                assertEquals(1,resultado.size());
            });
    }

    @Test
    void createCocheTest() throws Exception{
        Coche cocheNuevo = new Coche();
        cocheNuevo.setPropietario("yo");
        cocheNuevo.setMatricula("matriculapamultas");
        cocheNuevo.setPlazaAparcado(plaza4);

        String cocheJson=objectMapper.writeValueAsString(cocheNuevo);

        MvcResult mvcResult=mockMvc.perform(post("/coche")
            .contentType("application/json")
            .content(cocheJson))
            .andDo(print())
            .andExpect(status().isOk())
            .andReturn();

        Coche cocheResultado=objectMapper.readValue(mvcResult.getResponse().getContentAsString(),Coche.class);

        assertTrue(cocheResultado!=null);
    }

    @Test
    void updateCocheTest() throws Exception{
        coche.setPropietario("yo");
        coche.setMatricula("Aa123");
        coche.getMultas().remove(0);

        String cocheJson=objectMapper.writeValueAsString(coche);

        MvcResult mvcResult=mockMvc.perform(put("/coche")
            .contentType("application/json")
            .content(cocheJson))
            .andDo(print())
            .andExpect(status().isOk())
            .andReturn();

        Coche cocheResultado=objectMapper.readValue(mvcResult.getResponse().getContentAsString(),Coche.class);

        assertEquals("yo", cocheResultado.getPropietario());
        assertEquals("Aa123", cocheResultado.getMatricula());
        assertEquals(2, cocheResultado.getMultas().size());
    }

    @Test
    void updateAparcarMultaCocheTest() throws Exception{        
        String cocheJson=objectMapper.writeValueAsString(coche);


        MvcResult mvcResult=mockMvc.perform(put("/coche/aparcar/"+plaza4.getId().toString())
            .contentType("application/json")
            .content(cocheJson))
            .andDo(print())
            .andExpect(status().isOk())
            .andReturn();

        Coche cocheResultado=objectMapper.readValue(mvcResult.getResponse().getContentAsString(),Coche.class);
        assertEquals(4, cocheResultado.getMultas().size());
    }

    @Test
    void updateAparcarCocheTest() throws Exception{        
        String cocheJson=objectMapper.writeValueAsString(coche);

       mockMvc.perform(put("/coche/aparcar/"+plaza4.getId().toString())
            .contentType("application/json")
            .content(cocheJson))
            .andDo(print())
            .andExpect(status().isOk());            
    }

    @Test
    void updateAparcarCocheExceptionTest() throws Exception{        
        String cocheJson=objectMapper.writeValueAsString(coche);

        mockMvc.perform(put("/coche/aparcar/"+plaza2.getId().toString())
            .contentType("application/json")
            .content(cocheJson))
            .andDo(print())
            .andExpect(status().isBadRequest());            
    }       

    @Test
    void updateAsignarPlazaTest() throws Exception {
        String cocheJson=objectMapper.writeValueAsString(plaza4);

        MvcResult mvcResult=mockMvc.perform(put("/plaza/asignar/"+coche.getId().toString())
            .contentType("application/json")
            .content(cocheJson))
            .andDo(print())
            .andExpect(status().isOk())
            .andReturn();

        Plaza plazaResultado=objectMapper.readValue(mvcResult.getResponse().getContentAsString(),Plaza.class);
        assertEquals(1, plazaResultado.getCochesAsignados().size());
    }

    @Test
    void updateAsignarPlazaExceptionTest() throws Exception {
        
        String cocheJson=objectMapper.writeValueAsString(plaza);

        mockMvc.perform(put("/plaza/asignar/"+coche6.getId().toString())
            .contentType("application/json")
            .content(cocheJson))
            .andDo(print())
            .andExpect(status().isBadRequest())
            .andReturn();

        }


}
