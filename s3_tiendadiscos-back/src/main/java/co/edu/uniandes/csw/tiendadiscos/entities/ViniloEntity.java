/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendadiscos.entities;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Andrés Hernández.
 */
@Entity
public class ViniloEntity extends BaseEntity implements Serializable 
{
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
     @Temporal(TemporalType.DATE)
    private Date fechaLanzamiento;
    
    /**
     * Productora que lanzó el vinilo.
     */
    private String productora;
    
    /**
     * Información adicional del proveedor. 
     */
    private String informacionAdicional;
    
    /**
     * URI que redirige a la vista previa del vinilo si esta disponible.
     */
    private String previewURI;
    
    /**
     * Calificación promedio del vinilo.
     */
    private Double calificacion;
    
    // TODO 
 //   @OneToMany(mappedBy = "")
//    private List<CancionEntity> canciones = new ArrayList<CancionEntity>();
     
     /**
     * Obtiene el atributo nombre. 
     * @param nombre  atributo nombre.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Modifica el valor del del atributo artista.
     * @param artista valor del atributo.
     */
    public void setArtista(String artista) {
        this.artista = artista;
    }

    /**
     * Modifica el valor del atributo fecha de lanzamiento.
     * @param fechaLanzamiento nuevo valor del atributo.
     */
    public void setFechaLanzamiento(Date fechaLanzamiento) {
        this.fechaLanzamiento = fechaLanzamiento;
    }

    /**
     * Modifica el valor del atributo productora.
     * @param productora nuevo valor del atributo.
     */
    public void setProductora(String productora) {
        this.productora = productora;
    }

    /**
     * Modifica la información adicional dada por el proveedor. 
     * @param informacionAdicional nuevo valor del atributo.
     */
    public void setInformacionAdicional(String informacionAdicional) {
        this.informacionAdicional = informacionAdicional;
    }

    /**
     * Modifica el valor del atributo previewURI.
     * @param previewURI nuevo valor del atributo.
     */
    public void setPreviewURI(String previewURI) {
        this.previewURI = previewURI;
    }
    
  
    /**
     * Obtiene el atributo calificacion.
     * @return atributo calificación.
     */
    public Double getCalificacion() {
        return calificacion;
    }

    /**
     * Establece el valor del atributo calificación.
     * @param calificacion nuevo valor de la calificación.
     */
    public void setCalificacion(Double calificacion) {
        this.calificacion = calificacion;
    }

   
    
    /**
     * Obtiene el atributo nombre.
     * @return atributo nombre.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Obtiene el atributo artista.
     * @return atributo artista.
     */
    public String getArtista() {
        return artista;
    }

    /**
     * Obtiene el atributo fecha de lanzamiento.
     * @return atributo fechaLanzamiento.
     */
    public Date getFechaLanzamiento() {
        return fechaLanzamiento;
    }
    
    /**
     * Obtiene el atributo productora.
     * @return atributo productora.
     */
    public String getProductora() {
        return productora;
    }

    /**
     * Obtiene la información adicional del vinilo.
     * @return atributo informacionAdicional.
     */
    public String getInformacionAdicional() {
        return informacionAdicional;
    }

    /**
     * Obtiene el atributo previewURI.
     * @return atributo previewURI.
     */
    public String getPreviewURI() {
        return previewURI;
    }    
}
