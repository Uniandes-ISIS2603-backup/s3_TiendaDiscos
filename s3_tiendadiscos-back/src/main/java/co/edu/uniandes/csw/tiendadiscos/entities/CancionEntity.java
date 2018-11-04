/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendadiscos.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author Andrés Hernández
 */
@Entity
public class CancionEntity extends BaseEntity implements Serializable
{
    /**
     * Nombre de la canción.
     */
    private String nombre;
    
    /**
     * duración de la canción.
     */
    private String duracion;
    
    /**
     * URI del preview de la canción.
     */
    private String previewURI;
    
    /**
     * Descripción del proveedor.
     */
    private String descripcion;
    
    /**
     * Calificación promedio del vinilo.
     */
    private Double calificacion;
    
    @PodamExclude
    @ManyToOne
    private ViniloEntity vinilo;
    
    @PodamExclude
    @OneToMany(mappedBy = "cancion", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<ComentarioEntity> comentarios;    
    
    /**
     * Modifica el valor del atributo nombre.
     * @param nombre nuevo valor del atributo.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Modifica el valor de la duración.
     * @param duracion nuevo valor de la duración.
     */
    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    /**
     * Modifica el valor de la URI,
     * @param previewURI nuevo valor de la URI.
     */
    public void setPreviewURI(String previewURI) {
        this.previewURI = previewURI;
    }
    
    /**
     * Modifica el atributo descripción.
     * @param descripcion nuevo valor de la descripción del proveedor.
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    /**
     * Establece el valor del atributo calificación.
     * @param calificacion nuevo valor de la calificación.
     */
    public void setCalificacion(Double calificacion) {
        this.calificacion = calificacion;
    }
    
    /**
     * Modifica el vinilo al que pertenece la canción.
     * @param vinilo El nuevo vinilo.
     */
    public void setVinilo(ViniloEntity vinilo)
    {
        this.vinilo = vinilo;
    }
    
    /**
     * Modifica los comentarios de la canción.
     * @param comentarios Los nuevos comentarios.
     */
    public void setComentarios(List<ComentarioEntity> comentarios)
    {
        this.comentarios = comentarios;
    }

    /**
     * Obtiene le atributo nombre.
     * @return atributo nombre.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Retorna la duración de la cación.
     * @return atributo duración.
     */
    public String getDuracion() {
        return duracion;
    }

    /**
     * Obtiene el atributo preview URI.
     * @return previewURI
     */
    public String getPreviewURI() {
        return previewURI;
    }

    /**
     * Obtiene la descripcion.
     * @return atributo descripcion.
     */
    public String getDescripcion() {
        return descripcion;
    }
    
    /**
     * Obtiene el atributo calificacion.
     * @return atributo calificación.
     */
    public Double getCalificacion() {
        return calificacion;
    }
    
    /**
     * Devuelve el vinilo al que pertenece la canción.
     * @return Una entidad de vinilo.
     */
    public ViniloEntity getVinilo()
    {
        return vinilo;
    }
    
    /**
     * Devuelve los comentarios que referencian a la canción.
     * @return La lista de entidades de tipo Comentario.
     */
    public List<ComentarioEntity> getComentarios()
    {
        return comentarios;
    }
}
