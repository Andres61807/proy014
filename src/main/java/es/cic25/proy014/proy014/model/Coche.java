package es.cic25.proy014.proy014.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Coche {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_coche")
    private Long id;

    private String matricula;

    private String dueño;

    @OneToMany(mappedBy = "coche"
        ,fetch = FetchType.EAGER
        ,cascade =  {CascadeType.REMOVE,CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REFRESH}
        ,orphanRemoval = true)
    @JsonManagedReference
    private List<Multa> multas;

    @OneToOne
    @JsonBackReference
    @JoinColumn(name="")
    private Plaza plaza;

    public Coche() {
    }

    public Coche(Long id, String matricula, String dueño, List<Multa> multas, Plaza plaza) {
        this.id = id;
        this.matricula = matricula;
        this.dueño = dueño;
        this.multas = multas;
        this.plaza = plaza;
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

    public String getDueño() {
        return dueño;
    }

    public void setDueño(String dueño) {
        this.dueño = dueño;
    }

    public List<Multa> getMultas() {
        return multas;
    }

    public void setMultas(List<Multa> multas) {
        this.multas = multas;
    }

    public Plaza getPlaza() {
        return plaza;
    }

    public void setPlaza(Plaza plaza) {
        this.plaza = plaza;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
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
        return "Coche [id=" + id + ", matricula=" + matricula + ", dueño=" + dueño + ", multas=" + multas + ", plaza="
                + plaza + "]";
    }

    
}
