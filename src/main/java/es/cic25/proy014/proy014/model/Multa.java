package es.cic25.proy014.proy014.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;


@Entity
public class Multa {

    @Column(insertable = false)
    private static final double IMPORTE_DIARIO=5;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_multa")
    private Long id;
    
    private int dias;

    private double importe;

    private boolean pagada;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id")
    @JsonIgnoreProperties("multas")
    private Coche coche;

    public Multa() {
    }

    public Multa(Long id, double importe, boolean pagada,  Coche coche,int dias) {
        this.id = id;
        this.importe = importe;
        this.pagada = pagada;
        this.coche=coche;
        this.dias=dias;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getImporte() {
        return importe;
    }

    public void setImporte(double importe) {
        this.importe = importe;
    }

    public boolean isPagada() {
        return pagada;
    }

    public void setPagada(boolean pagada) {
        this.pagada = pagada;
    }

    public int getDias() {
        return dias;
    }

    public void setDias(int dias) {
        this.dias = dias;
    }
    
    public Coche getCoche() {
        return coche;
    }

    public void setCoche(Coche coche) {
        this.coche = coche;
    }

    public static double getImporteDiario() {
        return IMPORTE_DIARIO;
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
        Multa other = (Multa) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Multa [id=" + id + ", dias=" + dias + ", importe=" + importe + ", pagada=" + pagada + ", coche=" + coche
                + "]";
    }

    

    
}
