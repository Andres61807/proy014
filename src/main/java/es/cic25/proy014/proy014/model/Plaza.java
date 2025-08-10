
package es.cic25.proy014.proy014.model;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Plaza {

    @Column(insertable = false)
    private static final int MAX_PLAZAS=5;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_plaza")
    private Long id;

    private String ubicacion;

    private int piso;

    private boolean trastero;

    @OneToOne(mappedBy = "plazaAparcado")
    @JsonIgnoreProperties(value = {"plazaAparcado","plazaAsignada"})
    private Coche cocheAparcado;

    @OneToMany(mappedBy = "plazaAsignada")
    @JsonIgnoreProperties(value = {"plazaAparcado","plazaAsignada"})
    private List<Coche> cochesAsignados=new ArrayList<>();
    
    public Plaza() {
    }

    public Plaza(Long id, String ubicacion, int piso, boolean trastero, Coche coche) {
        this.id = id;
        this.ubicacion = ubicacion;
        this.piso = piso;
        this.trastero = trastero;
        this.cocheAparcado=coche;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String nombre) {
        this.ubicacion = nombre;
    }

    public int getPiso() {
        return piso;
    }

    public void setPiso(int piso) {
        this.piso = piso;
    }

    public boolean isTrastero() {
        return trastero;
    }

    public void setTrastero(boolean trastero) {
        this.trastero = trastero;
    }

    public Coche getCocheAparcado() {
        return cocheAparcado;
    }

    public void setCocheAparcado(Coche coche) {
        this.cocheAparcado = coche;
    }
    
     public List<Coche> getCochesAsignados() {
        return cochesAsignados;
    }

    public void setCochesAsignados(List<Coche> cochesAsignados) {
        this.cochesAsignados = cochesAsignados;
    }
       
    public static int getMaxPlazas() {
        return MAX_PLAZAS;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Plaza other = (Plaza) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "Plaza [id=" + id + ", nombre=" + ubicacion + ", piso=" + piso + ", trastero=" + trastero + "]";
    }

    

   

    
    
}
