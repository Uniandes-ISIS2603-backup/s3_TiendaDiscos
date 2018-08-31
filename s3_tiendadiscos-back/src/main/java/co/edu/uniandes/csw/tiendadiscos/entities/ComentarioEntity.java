/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendadiscos.entities;
 import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import uk.co.jemos.podam.common.PodamExclude;
 /**
 * Clase que representa un comentario en la persistencia y permite su serialización
 *
 * @author ISIS2603
 */
@Entity
public class ComentarioEntity extends BaseEntity implements Serializable{
    
    
    //Atributos
    private String contenido;
    
    //private TransaccionEntity transaccion;
    
    private UsuarioEntity usuario;
    
    private ViniloEntity vinilo;
    
    private CancionEntity cancion;
    
    
    
            
     

    
    /**
     * @return conexion con la transaccion
     */
    //@ManyToOne
    //public TransaccionEntity getTransaccion()
    //{
    //    return transaccion;
    //}
    
    /**
     * @return conexion con el usuario
     */
    @ManyToOne
    public UsuarioEntity getUsuario()
    {
        return usuario;
    }
    /**
     * @return conexion con el vinilo
     */
    @ManyToOne
    public ViniloEntity getVinilo()
    {
        return vinilo;
    }
    
    
        
    /**
     * @return conexion con el vinilo
     */
    @ManyToOne
    public CancionEntity getCancion()
    {
        return cancion;
    }
    
        
    /**
     * @return contenido del comentario
     */
    public String getContenido()
    {
        return contenido;
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
    public void setUsuario(UsuarioEntity usuario)
    {
        this.usuario = usuario;
    }
    
    /** 
     * @param transaccion usuario al que se comenta
     */
    //public void setTransacciono(TransaccionEntity transaccion)
    //{
    //    this.transaccion = transaccion;
    //}
    
    /** 
     * @param cancion usuario al que se comenta
     */
    public void setCancion(CancionEntity cancion)
    {
        this.cancion = cancion;
    }
    
    /** 
     * @param cancion usuario al que se comenta
     */
    public void setVinilo(ViniloEntity vinilo)
    {
        this.vinilo = vinilo;
    }
    
}