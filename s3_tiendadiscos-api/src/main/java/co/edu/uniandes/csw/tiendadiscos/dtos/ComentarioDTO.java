package co.edu.uniandes.csw.tiendadiscos.dtos;

import co.edu.uniandes.csw.tiendadiscos.entities.ComentarioEntity;
import java.io.Serializable;

/**
 * ComentarioDTO Objeto de transferencia de datos de Comentarios. Los DTO contienen las
 * representaciones de los JSON que se transfieren entre el cliente y el
 * servidor.
 *
 * Al serializarse como JSON esta clase implementa el siguiente modelo: <br>
 * <pre>
 *  {
 *      "id":number,
 *      "escritoPor": 
 *  }
 * 
 * @author Sebastian Martinez y Andrés :)
 */
public class ComentarioDTO implements Serializable
{   
    /**
     * Id del comentario.
     */
    private Long id;

    /**
     * 
     */
    private UsuarioDTO escritoPor;

    private String contenido;
    
    private UsuarioDTO usuario;
    
    private ViniloDTO vinilo;
    
    private TransaccionDTO transaccion;
    
    private CancionDTO cancion;    
    
    /**
     * Contructor por defecto
     */
    public ComentarioDTO(){ }
    
   /** 
    * Crea un objeto ComentarioDTO a partir de un objeto ComentarioEntity.
    * 
    * @param comentarioEntity Entidad ComentarioEntity desde la cual se va a crear el 
    * nuevo objeto.
    */
    public ComentarioDTO(ComentarioEntity comentarioEntity)
    {
        if(comentarioEntity != null)
        {
            this.id = comentarioEntity.getId();            
            this.contenido = comentarioEntity.getContenido();            
            if(comentarioEntity.getVinilo() != null)
                this.vinilo = new ViniloDTO(comentarioEntity.getVinilo());
            
            if(comentarioEntity.getCancion() != null)
                this.cancion = new CancionDTO(comentarioEntity.getCancion());
            
            if(comentarioEntity.getTransaccion() != null)
                this.transaccion = new TransaccionDTO(comentarioEntity.getTransaccion());            
            
            if(comentarioEntity.getUsuario() != null)
                this.usuario = new UsuarioDTO(comentarioEntity.getUsuario());            
            
            if(comentarioEntity.getUsuarioI() != null)
                this.escritoPor = new UsuarioDTO(comentarioEntity.getUsuarioI());          
        }
    }
    
    /**
     * Convertir un objeto ComentarioDTO a ComentarioEntity.
     * 
     * @return Nuevo objeto ComentarioEntity
     */
    public ComentarioEntity toEntity()
    {
        ComentarioEntity comentario = new ComentarioEntity();
        comentario.setId(id);
        comentario.setContenido(contenido);
        if(this.escritoPor!= null)
            comentario.setUsuarioI(escritoPor.toEntity());
        if(this.transaccion!=null)
            comentario.setTransaccion(this.transaccion.toEntity());
        if(this.usuario!=null)
            comentario.setUsuario(this.usuario.toEntity());
        if(this.cancion!=null)
            comentario.setCancion(this.cancion.toEntity());
        if(this.vinilo!=null)
            comentario.setVinilo(this.vinilo.toEntity());
        
        return comentario;
    }
    
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
     * Devuelve el usuario que escribio el comentario.
     * 
     * @return el usuario que escribió el comentario.
     */
    public UsuarioDTO getEscritoPor()
    {
        return escritoPor;
    }

    /**
     * Devuelve el id del comentario.
     * 
     * @return el comentario
     */
    public String getContenido()
    {
        return contenido;
    }

    /**
     * Devuelve el id de la transaccion.
     * 
     * @return conexion con la transaccion.
     */
    public TransaccionDTO getTransaccion()
    {
        return transaccion;
    }
    
    /**
     * Devuelve el id del usuario.
     * @return conexion con el usuario
     */
    public UsuarioDTO getUsuario()
    {
        return usuario;
    }
    
    /**
     * Devuelve el id del vinilo.
     * @return conexion con el vinilo
     */
    public ViniloDTO getVinilo()
    {
        return vinilo;
    } 
        
    /**
     * Devuelve el id de la canción.
     * @return conexion con la canción
     */
    public CancionDTO getCancion()
    {
        return cancion;
    }

    /**
     * Modifica el id del comentario
     * 
     * @param id nuevo id
     */
    public void setId(Long id)
    {
        this.id = id;
    }
    
    /**
     * Modifica 
     * @param escritoPor usuario que comento.
     */
    public void setEscritoPor(UsuarioDTO escritoPor)
    {
        this.escritoPor = escritoPor;
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
     * @param usuario usuario al que se comenta
     */
    public void setUsuario(UsuarioDTO usuario)
    {
        this.usuario = usuario;
    }
    
    /** 
     * @param transaccion usuario al que se comenta
     */
    public void setTransaccion(TransaccionDTO transaccion)
    {
        this.transaccion = transaccion;
    }
    
    /** 
     * @param cancion usuario al que se comenta
     */
    public void setCancion(CancionDTO cancion)
    {
        this.cancion = cancion;
    }
    
    /** 
     * @param vinilo vinilo al que se comenta.
     */
    public void setVinilo(ViniloDTO vinilo)
    {
        this.vinilo = vinilo;
    }
   
}