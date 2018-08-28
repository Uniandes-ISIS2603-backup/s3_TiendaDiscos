package co.edu.uniandes.csw.tiendadiscos.dtos;

import co.edu.uniandes.csw.tiendadiscos.entities.ComentarioEntity;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


/**
 *
 * @author Sebastian Martinez
 */
public class ComentarioDTO implements Serializable{
   
    //Constantes
    public static final String TRANSACCION = "transaccion";
    public static final String VINILO = "vinilo";
    public static final String CANCIÓN = "cancion";
    public static final String USUARIO = "usuario";
    
    //Atributos
    private Long id;
    private String tipo;
    private String contenido;
    private Long id2; //Id del tipo al que se comenta
    
    
    
    
    /**
     * Contructor por defecto
     */
    public ComentarioDTO(){    
    }
    
   /* 
     * Constructor apartir de la entidad
     * @param comentarioEntity La entidad del comentario  
     
    public ComentarioDTO(ComentarioEntity comentarioEntity)
    {
        if(comentarioEntity != null)
        {
            this.id = comentarioEntity.getId();
        }
    }
    */
    
    /**
     * Devuelve el id del comentario
     * 
     * @return id el comentario
     */
    public Long getId()
    {
        return id;
    }
    
    /**
     * Devuelve el tipo del comentario
     * 
     * @return a quien va dirgido el comentario
     */
    public String getTipo()
    {
        return tipo;
    }
    
    /**
     * Devuelve el id del comentario
     * 
     * @return el comentario
     */
    public String darContenido()
    {
        return contenido;
    }
    
    /**
     * Devuelve el id del comentario
     * 
     * @return id de la clase al cual se comenta
     */
    public Long getId2()
    {
        return id2;
    }
    
    /**
     * Cambia el id del comentario
     * 
     * @param id nuevo id
     */
    public void setId(Long id)
    {
        this.id = id;
    }
    
    /** Cambia el id de la clase comentada
     * 
     * @param id nuevo id2
     */
    public void setId2(Long id2)
    {
        this.id2 = id2;
    }
    
    /**
     * Cambia el contenido del comentario
     * 
     * @param contenido nuevo comentario
     */
    public void setContenido(String contenido)
    {
        this.contenido = contenido;
    }
    
    /**
     * Cambia el tipo del comentario
     * 
     * @param tipo nuevo tipo
     */
    public void setTipo(String tipo)
    {
        this.tipo = tipo;
    }
    
    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }
}
