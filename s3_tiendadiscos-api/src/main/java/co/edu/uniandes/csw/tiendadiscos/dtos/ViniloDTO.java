/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendadiscos.dtos;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author estudiante
 */
@Entity
public class ViniloDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * Nombre del vinilo.
     */
    private String nombre;
    
    /**
     * Nombre del artista del vinilo.
     */
    private String artista;
    
    /**
     * Fecha de lanzamiento del vinilo.
     */
    private Date fechaLanzamiento;
    
    /**
     * Productora que lanzó el vinilo.
     */
    private String productora;
    
    /**
     * Información adicional del 
     */
    private String informacionAdicional;
    
    /**
     * 
     */
    private String previewURI;

    public String getNombre() {
        return nombre;
    }

    public String getArtista() {
        return artista;
    }

    public Date getFechaLanzamiento() {
        return fechaLanzamiento;
    }

    public String getProductora() {
        return productora;
    }

    public String getInformacionAdicional() {
        return informacionAdicional;
    }

    public String getPreviewURI() {
        return previewURI;
    }  
 
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ViniloDTO)) {
            return false;
        }
        ViniloDTO other = (ViniloDTO) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.edu.uniandes.csw.tiendadiscos.dtos.ViniloDTO[ id=" + id + " ]";
    }    
}
