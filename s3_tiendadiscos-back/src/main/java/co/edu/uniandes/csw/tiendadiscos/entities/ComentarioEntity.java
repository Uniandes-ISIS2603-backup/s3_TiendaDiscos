/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendadiscos.entities;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.contenido);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ComentarioEntity other = (ComentarioEntity) obj;
        if (!Objects.equals(this.contenido, other.contenido)) {
            return false;
        }
        if (!Objects.equals(this.transaccion, other.transaccion)) {
            return false;
        }
        if (!Objects.equals(this.usuarioDestino, other.usuarioDestino)) {
            return false;
        }
        if (!Objects.equals(this.usuarioInicio, other.usuarioInicio)) {
            return false;
        }
        if (!Objects.equals(this.vinilo, other.vinilo)) {
            return false;
        }
        return Objects.equals(this.cancion, other.cancion);
    }
}