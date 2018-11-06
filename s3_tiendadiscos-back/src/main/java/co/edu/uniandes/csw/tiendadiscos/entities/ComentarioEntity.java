/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendadiscos.entities;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import uk.co.jemos.podam.common.PodamExclude;

 /**
 * Clase que representa un comentario en la persistencia y permite su serialización
 *
 * @author Sebastian Martinez
 */
@Entity
public class ComentarioEntity extends BaseEntity implements Serializable{
    
    
    //Atributos
    private String contenido;
    
    @PodamExclude
    @ManyToOne
    private TransaccionEntity transaccion;
    
    @PodamExclude
    @ManyToOne
    private UsuarioEntity usuarioDestino;
    
    @PodamExclude
    @ManyToOne
    private UsuarioEntity usuarioInicio;
    
    @PodamExclude
    @ManyToOne
    private ViniloEntity vinilo;
    
    @PodamExclude
    @ManyToOne
    private CancionEntity cancion;
    
 
    public TransaccionEntity getTransaccion()
    {
        return transaccion;
    }
    public UsuarioEntity getUsuario()
    {
        return usuarioDestino;
    }
    public UsuarioEntity getUsuarioI()
    {
        return usuarioInicio;
    }
    public ViniloEntity getVinilo()
    {
        return vinilo;
    }
    public CancionEntity getCancion()
    {
        return cancion;
    }
    public String getContenido()
    {
        return contenido;
    }
    public void setContenido(String contenido)
    {
        this.contenido = contenido;
    }
    public void setUsuarioI(UsuarioEntity usuario)
    {
        this.usuarioInicio = usuario;
    }
    public void setUsuario(UsuarioEntity usuario)
    {
        this.usuarioDestino = usuario;
    }
    public void setTransaccion(TransaccionEntity transaccion)
    {
        this.transaccion = transaccion;
    }
    public void setCancion(CancionEntity cancion)
    {
        this.cancion = cancion;
    }
    public void setVinilo(ViniloEntity vinilo)
    {
        this.vinilo = vinilo;
    }
    
    public ComentarioEntity(){}
}