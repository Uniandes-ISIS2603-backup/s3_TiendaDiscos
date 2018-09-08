package co.edu.uniandes.csw.tiendadiscos.dtos;

import co.edu.uniandes.csw.tiendadiscos.entities.CancionEntity;
import java.io.Serializable;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 *
 * @author Andrés Hernández León
 */
public class CancionDTO implements Serializable 
{   
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
     * Calificación promedio del vinilo.
     */
    private Double calificacion;

    /**
     * Constructor vacio de CancionDTO.
     */
    public CancionDTO()
    {}
    
    /**
     * Conviertir Entity a DTO (Crea un nuevo DTO con los valores que recibe en
     * la entidad que viene de argumento.
     *
     * @param cancionEntity: Es la entidad que se va a convertir a DTO
     */
    public CancionDTO(CancionEntity cancionEntity)
    {
        if(cancionEntity != null)
        {
            this.descripcion = cancionEntity.getDescripcion();
            this.duracion = cancionEntity.getDuracion();
            this.id = cancionEntity.getId();
            this.nombre = cancionEntity.getNombre();
            this.previewURI = cancionEntity.getPreviewURI();
            this.calificacion = cancionEntity.getCalificacion();                  
        }
    }
    
    /**
     * Convertir DTO a Entity
     *
     * @return Un Entity con los valores del DTO
     */
    public CancionEntity toEntity()
    {
        //Creo el objeto entity vacio.
        CancionEntity cancionEntity = new CancionEntity();
        //Ahora le asigno los valores.
        cancionEntity.setDescripcion(this.descripcion);
        cancionEntity.setDuracion(this.duracion);
        cancionEntity.setId(this.id);
        cancionEntity.setNombre(this.nombre);
        cancionEntity.setPreviewURI(this.previewURI);
        cancionEntity.setCalificacion(this.calificacion);
        
        return cancionEntity;
    }
    
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
     * Establece el valor del atributo id.
     *
     * @param id nuevo valor del atributo
     *
     */
    public void setId(Long id) {
        this.id = id;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
    
}
