package co.edu.uniandes.csw.tiendadiscos.dtos;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author Andrés Hernández
 */
@Entity
public class ViniloDetailDTO extends ViniloDTO implements Serializable
{
    /**
     * Relación de 1 a muchas canciones.
     */
    public List<CancionDTO> canciones;
    
    /**
     * Relación de 0 a muchos comentarios.
     */
    private List<ComentarioDTO> comentarios;    
    
    @Id
    private Long id;

   /**
    * Constructor vacio.
    */
   public ViniloDetailDTO()
   {
       super();
   }
   
   /**
     * Devuelve los comentarios asociados al vinilo.
     * @return Lista de DTOs de comentarios.
     */
    public List<ComentarioDTO> getComentarios()
    {
        return comentarios;
    }
    
    /**
     * Devuelve las canciones asociadas al vinilo.
     * @return atributo canciones.
     */
    public List<CancionDTO> getCanciones() {
        return canciones;
    }

    /**
     * Modifica las canciones del vinilo.
     * @param canciones nuevas canciones vinilo.
     */
    public void setCanciones(List<CancionDTO> canciones) {
        this.canciones = canciones;
    }
    
    /**
     * Modifica los comentarios del vinilo.
     * @param comentarios Los nuevos comentarios.
     */
    public void setComentarios(List<ComentarioDTO> comentarios)
    {
        this.comentarios = comentarios;
    }
   
     
    
    @Override
    public String toString() {
        return "co.edu.uniandes.csw.tiendadiscos.dtos.ViniloDetailDTO[ id=  ]";
    }   

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
