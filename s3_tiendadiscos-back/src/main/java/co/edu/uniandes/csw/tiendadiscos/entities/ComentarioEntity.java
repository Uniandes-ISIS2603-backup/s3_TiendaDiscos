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

 /**
 * Clase que representa un comentario en la persistencia y permite su serialización
 *
 * @author Sebastian Martinez
 */
@Entity
public class ComentarioEntity extends BaseEntity implements Serializable{
    
    
    //Atributos
    private String contenido;
    
    @OneToOne
    private TransaccionEntity transaccion;
    
    @ManyToOne
    private UsuarioEntity usuario;
    
    @ManyToOne
    private ViniloEntity vinilo;
    
    @ManyToOne
    private CancionEntity cancion;
    
 
    public TransaccionEntity getTransaccion()
    {
        return transaccion;
    }
    public UsuarioEntity getUsuario()
    {
        return usuario;
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
    public void setUsuario(UsuarioEntity usuario)
    {
        this.usuario = usuario;
    }
    public void setTransacciono(TransaccionEntity transaccion)
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