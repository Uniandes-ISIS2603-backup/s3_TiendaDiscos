package co.edu.uniandes.csw.tiendadiscos.dtos;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;

/**
 *
 * @author Andrés Hernández León
 */
public class CancionDetailDTO extends CancionDTO implements Serializable {

    /**
     * Relación de cero a muchos comentarios
     */
    private List<ComentarioDTO> comentarios;
    
    /**
     * Constructor vacio.
     */
    public CancionDetailDTO()
    {
        super();
    }
    
    /**
     * Devuelve los comentarios asociados a la canción.
     * @return Lista de DTOs de comentarios.
     */
    public List<ComentarioDTO> getComentarios()
    {
        return comentarios;
    }
    
    /**
     * Modifica los comentarios de la canción.
     * @param comentarios Los nuevos comentarios.
     */
    public void setComentarios(List<ComentarioDTO> comentarios)
    {
        this.comentarios = comentarios;
    }
}