/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendadiscos.entities;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author Laura Isabella Forero Camacho
 */
@Entity
public class TransaccionEntity extends BaseEntity implements Serializable{
    
    
    /**
     * id único del comprador.
     */
    @PodamExclude
    @ManyToOne
    private UsuarioEntity usuarioComprador;
    
    /**
     * id único del vendedor.
     */
    @PodamExclude
    @ManyToOne
    private UsuarioEntity usuarioVendedor;
    
    
    @PodamExclude
    @OneToMany(mappedBy = "transaccion", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<ComentarioEntity> comentarios;
    
    
    @PodamExclude
    @OneToOne(mappedBy = "transaccion",cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private EnvioEntity envio;
    
    @PodamExclude
    @ManyToOne
    private ViniloEntity vinilo;
    /**
     * Forma de pago de la transaccion.
     */
    private String formaDePago;
    
    /**
     * Estado de la transaccion.
     */
    private String estado;
    

    public EnvioEntity getEnvio() 
    {
        return envio;
    }

    public void setEnvio(EnvioEntity envio) 
    {
        this.envio = envio;
    }
    
    public ViniloEntity getVinilo() 
    {
        return vinilo;
    }

    public void setVinilo(ViniloEntity vinilo) 
    {
        this.vinilo = vinilo;
    }

    private void setComentarios(List<ComentarioEntity> comentarios)
    {
        this.comentarios = comentarios;
    }
    
    /**
     * Modifica el valor del atributo vendedorID.
     * @param vendedor nuevo valor del atributo.
     */
    public void setUsuarioVendedor(UsuarioEntity  vendedor) 
    {
        this.usuarioVendedor = vendedor;
    }

    /**
     * Modifica el valor del atributo compradorID.
     * @param comprador nuevo valor del atributo.
     */
    public void setUsuarioComprador(UsuarioEntity  comprador) 
    {
        this.usuarioComprador = comprador;
    }

    /**
     * Modifica el valor del atributo formaDePago.
     * @param formaDePago nuevo valor del atributo.
     */
    public void setFormaDePago(String formaDePago)
    {
        this.formaDePago = formaDePago;
    }
    
    /**
     * Modifica el valor del atributo estado.
     * @param estado nuevo valor del atributo.
     */
    public void setEstado(String estado) 
    {
        this.estado = estado;
    }

    /**
     * Obtiene le atributo nombre.
     * @return atributo nombre.
     */
    public UsuarioEntity  getUsuarioVendedor() 
    {
        return usuarioVendedor;
    }

    /**
     * Retorna la duración de la cación.
     * @return atributo duración.
     */
    public UsuarioEntity  getUsuarioComprador() 
    {
        return usuarioComprador;
    }

    /**
     * Obtiene el atributo preview URI.
     * @return previewURI
     */
    public String getEstado() 
    {
        return estado;
    }

    /**
     * Obtiene la descripcion.
     * @return atributo descripcion.
     */
    public String getFormaDePago() 
    {
        return formaDePago;
    }   

    public List<ComentarioEntity> getComentario()
    {
        return comentarios;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 41 * hash + Objects.hashCode(this.formaDePago);
        hash = 41 * hash + Objects.hashCode(this.estado);
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
        final TransaccionEntity other = (TransaccionEntity) obj;
        if (!Objects.equals(this.formaDePago, other.formaDePago)) {
            return false;
        }
        if (!Objects.equals(this.estado, other.estado)) {
            return false;
        }
        if (!Objects.equals(this.usuarioComprador, other.usuarioComprador)) {
            return false;
        }
        if (!Objects.equals(this.usuarioVendedor, other.usuarioVendedor)) {
            return false;
        }
        if (!Objects.equals(this.comentarios, other.comentarios)) {
            return false;
        }
        if (!Objects.equals(this.envio, other.envio)) {
            return false;
        }
        if (!Objects.equals(this.vinilo, other.vinilo)) {
            return false;
        }
        return true;
    }
}