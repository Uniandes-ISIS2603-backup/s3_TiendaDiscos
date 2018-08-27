package co.edu.uniandes.csw.tiendadiscos.dtos;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Andrés Hernández León
 */
@Entity
public class CancionDTO implements Serializable 
{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    /**
     * id único del vinilo.
     */
    private Long id;
    
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
     * Constructor vacio de CancionDTO.
     */
    public CancionDTO()
    {}
    
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
     * Obtiene el atributo id.
     *
     * @return atributo id.
     *
     */
    public Long getId() {
        return id;
    }

    /**
     * Establece el valor del atributo id.
     *
     * @param id nuevo valor del atributo
     *
     */
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
    public String toString() {
        return "";
    }
    
}
