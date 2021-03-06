/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendadiscos.dtos;
/*imports*/
import co.edu.uniandes.csw.tiendadiscos.entities.ComentarioEntity;
import co.edu.uniandes.csw.tiendadiscos.entities.TransaccionEntity;
import co.edu.uniandes.csw.tiendadiscos.entities.UsuarioEntity;
import co.edu.uniandes.csw.tiendadiscos.entities.ViniloEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Camilo Andres Salinas Martinez
 */
public class UsuarioDetailDTO extends UsuarioDTO implements Serializable{
    
    //Relación 0 a muchos vinilos
    private List<ViniloDTO> vinilos;
    
    //Relación 0 a muchas transacciones
    private List<TransaccionDTO> transaccionesRecibidas;
    
    //Relacion 0 a muchas transacciones
    private List<TransaccionDTO> transaccionesGeneradas;
    
    //Relación 0 a muchos comentarios
    private List<ComentarioDTO> comentarios;    
    
    /**
     * Constructor por defecto.
     */
    public UsuarioDetailDTO()
    {
        super();
    }    
    
    /**
     * Crea un objeto UsuarioDetailDTO a partir de un objeto UsuarioEntity
     * incluyendo los atributos de UsuarioDTO.
     *
     * @param userEntity Entidad UsuarioEntity desde la cual se va a crear el
     * nuevo objeto.
     */
    public UsuarioDetailDTO(UsuarioEntity userEntity)
    {
        super(userEntity);
        if(userEntity.getVinilos() != null)
        {
            vinilos = new ArrayList<>();
            for(ViniloEntity actual : userEntity.getVinilos())
                vinilos.add(new ViniloDTO(actual));            
        }
        if(userEntity.getComentariosH()!= null)
        {
            comentarios = new ArrayList<>();
            for(ComentarioEntity actual : userEntity.getComentariosH())
                comentarios.add(new ComentarioDTO(actual));
        }
        if(userEntity.getTransaccionesG()!= null)
        {
            transaccionesGeneradas = new ArrayList<>();
            for(TransaccionEntity actual : userEntity.getTransaccionesG())
                transaccionesGeneradas.add(new TransaccionDTO(actual));
        }
        if(userEntity.getTransaccionesR()!= null)
        {
            transaccionesRecibidas = new ArrayList<>();
            for(TransaccionEntity actual : userEntity.getTransaccionesR())
                transaccionesRecibidas.add(new TransaccionDTO(actual));
        }
        
    }
    
    /**
     * Convierte un objeto UsuarioDetailDTO a UsuarioEntity incluyendo los
     * atributos de UsuarioDTO.
     * 
     * @return Nuevo objeto UsuarioEntity.
     */
    @Override
    public UsuarioEntity toEntity()
    {
        UsuarioEntity usuarioEntity =super.toEntity();
        if(vinilos !=null)
        {
            List<ViniloEntity> vinilosEntity = new ArrayList<>();
            for(ViniloDTO viniloDTO: vinilos)
                vinilosEntity.add(viniloDTO.toEntity());
            usuarioEntity.setVinilos(vinilosEntity);
        }
        if(comentarios !=null)
        {
            List<ComentarioEntity> comentariosEntity = new ArrayList<>();
            for(ComentarioDTO comentarioDTO: comentarios)
                comentariosEntity.add(comentarioDTO.toEntity()); 
            usuarioEntity.setComentariosH(comentariosEntity);
        }
        if(transaccionesGeneradas!=null)
        {
            List<TransaccionEntity> transaccionesGeneradasEntity = new ArrayList<>();
            for(TransaccionDTO transaccionDTO: transaccionesGeneradas)
                transaccionesGeneradasEntity.add(transaccionDTO.toEntity());
            usuarioEntity.setTransaccionesG(transaccionesGeneradasEntity);
        }
        if(transaccionesRecibidas!=null)
        {
            List<TransaccionEntity> transaccionesRecibidasEntity = new ArrayList<>();
            for(TransaccionDTO transaccionDTO: transaccionesRecibidas)
                transaccionesRecibidasEntity.add(transaccionDTO.toEntity());
            usuarioEntity.setTransaccionesR(transaccionesRecibidasEntity);
        }        
        return usuarioEntity;
    }

    /**
     * Obtiene la lista de vinilos del usuario.
     * @return los vinilos.
     */
    public List<ViniloDTO> getVinilos() 
    {
        return vinilos;
    }

    /**
     * Modifica la lista de vinilos del usuario.
     * @param vinilos los vinilos a setear.
     */
    public void setVinilos(List<ViniloDTO> vinilos) 
    {
        this.vinilos = vinilos;
    }

    /**
     * Obtiene la lista de transacciones recibidas.
     * @return las transacciones recibidas.
     */
    public List<TransaccionDTO> getTransaccionesRecibidas() 
    {
        return transaccionesRecibidas;
    }

    /**
     * Modifica la lista de transacciones recibidas.
     * @param transaccionesRecibidas las transacciones a setear.
     */
    public void setTransaccionesRecibidas(List<TransaccionDTO> transaccionesRecibidas) 
    {
        this.transaccionesRecibidas = transaccionesRecibidas;
    }

    /**
     * Obtiene la lista de transacciones generadas.
     * @return las transacciones generadas.
     */
    public List<TransaccionDTO> getTransaccionesGeneradas() 
    {
        return transaccionesGeneradas;
    }

    /**
     * Modifica la lista de transacciones generadas.
     * @param transaccionesGeneradas las transacciones a setear.
     */
    public void setTransaccionesGeneradas(List<TransaccionDTO> transaccionesGeneradas) 
    {
        this.transaccionesGeneradas = transaccionesGeneradas;
    }

    /**
     * Obtiene los comentarios hechos al usuario.
     * @return los comentarios.
     */
    public List<ComentarioDTO> getComentarios() 
    {
        return comentarios;
    }

    /**
     * Modifica la lista de comentarios.
     * @param comentarios los comentarios a setear.
     */
    public void setComentarios(List<ComentarioDTO> comentarios) 
    {
        this.comentarios = comentarios;
    }
}