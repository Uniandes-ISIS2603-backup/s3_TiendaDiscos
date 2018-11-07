package co.edu.uniandes.csw.tiendadiscos.dtos;

import co.edu.uniandes.csw.tiendadiscos.entities.ComentarioEntity;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


/**
 *
 * @author Sebastian Martinez y Andrés :)
 */
public class ComentarioDTO implements Serializable{
   
   
   
    private Long id;

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
     * Constructor apartir de la entidad
     * @param comentarioEntity La entidad del comentario  
    */
    public ComentarioDTO(ComentarioEntity comentarioEntity)
    {
        if(comentarioEntity != null)
        {
            this.id = comentarioEntity.getId();
            
            this.contenido = comentarioEntity.getContenido();
            
            if(comentarioEntity.getVinilo() != null)
                this.vinilo = new ViniloDTO(comentarioEntity.getVinilo());
            else
                this.vinilo = null;
            
            if(comentarioEntity.getCancion() != null)
                this.cancion = new CancionDTO(comentarioEntity.getCancion());
            else
                this.cancion = null;
            
            if(comentarioEntity.getTransaccion() != null)
                this.transaccion = new TransaccionDTO(comentarioEntity.getTransaccion());
            else
                this.transaccion = null;
            
            if(comentarioEntity.getUsuario() != null)
                this.usuario = new UsuarioDTO(comentarioEntity.getUsuario());
            else
                this.usuario = null;
            if(comentarioEntity.getUsuarioI() != null)
                this.escritoPor = new UsuarioDTO(comentarioEntity.getUsuarioI());
            else
                this.usuario= null;
            
        }
    }
    
    /**
     * Convertir comentario a entidad
     * @return entidad del comentario
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
     * Devuelve el id del comentario
     * 
     * @return el comentario
     */
    public String getContenido()
    {
        return contenido;
    }

    /**
     * @return conexion con la transaccion
     */
    public TransaccionDTO getTransaccion()
    {
        return transaccion;
    }
    
    /**
     * @return conexion con el usuario
     */
    public UsuarioDTO getUsuario()
    {
        return usuario;
    }
    /**
     * @return conexion con el vinilo
     */
    public ViniloDTO getVinilo()
    {
        return vinilo;
    } 
        
    /**
     * @return conexion con la canción
     */
    public CancionDTO getCancion()
    {
        return cancion;
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
    
    /**
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
     * @param cancion usuario al que se comenta
     */
    public void setVinilo(ViniloDTO vinilo)
    {
        this.vinilo = vinilo;
    }
   
}