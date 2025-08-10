package es.cic25.proy014.proy014.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Coche {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_coche")
    private Long id;

    private String matricula;

    private String propietario;

    //listado de nultas
    @OneToMany(mappedBy = "coche"
        ,fetch = FetchType.EAGER
        ,cascade =  {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REFRESH}
        ,orphanRemoval = true)
    @JsonIgnoreProperties("coche")
    private List<Multa> multas=new ArrayList<>();

    //plaza donde ha aparcado
    @OneToOne(cascade =  {CascadeType.MERGE,CascadeType.REFRESH},
        fetch = FetchType.EAGER)
    @JsonIgnoreProperties(value = {"cocheAparcado","cochesAsignados"})
    @JoinColumn(name="id_plaza")
    private Plaza plazaAparcado;

    //plaza asignada
    @ManyToOne
    @JsonIgnoreProperties(value={"cocheAparcado","cochesAsignados"})
    private Plaza plazaAsignada;

    public Coche() {
    }

    public Coche(Long id, String matricula, String propietario, List<Multa> multas, Plaza plaza,Plaza plazaAsignada) {
        this.id = id;
        this.matricula = matricula;
        this.propietario = propietario;
        this.multas = multas;
        this.plazaAparcado = plaza;
        this.plazaAsignada=plazaAsignada;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getPropietario() {
        return propietario;
    }

    public void setPropietario(String dueño) {
        this.propietario = dueño;
    }

    public List<Multa> getMultas() {
        return multas;
    }

    public void setMultas(List<Multa> multas) {
        this.multas = multas;
    }

    public Plaza getPlazaAparcado() {
        return plazaAparcado;
    }

    public void setPlazaAparcado(Plaza plaza) {
        this.plazaAparcado = plaza;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    public Plaza getPlazaAsignada() {
        return plazaAsignada;
    }

    public void setPlazaAsignada(Plaza plazaAsignada) {
        this.plazaAsignada = plazaAsignada;
    }

    

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Coche other = (Coche) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Coche [id=" + id + ", matricula=" + matricula + ", dueño=" + propietario + ", multas=" + multas + ", plaza="
                + plazaAparcado + "]";
    }

    
}
