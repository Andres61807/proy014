
package es.cic25.proy014.proy014.model;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class Plaza {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_plaza")
    private Long id;

    private String ubicacion;

    private int piso;

    private boolean trastero;

    @OneToOne(mappedBy = "plaza")
    @JsonManagedReference
    private Coche coche;

    public Plaza() {
    }

    public Plaza(Long id, String ubicacion, int piso, boolean trastero) {
        this.id = id;
        this.ubicacion = ubicacion;
        this.piso = piso;
        this.trastero = trastero;
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
