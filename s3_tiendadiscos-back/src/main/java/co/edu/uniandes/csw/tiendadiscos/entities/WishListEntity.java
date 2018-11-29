/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendadiscos.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import java.util.List;
import java.util.Objects;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author Sebastián Martinez
 */
@Entity
public class WishListEntity extends BaseEntity implements Serializable {

    private Double costo;
    
    @PodamExclude
    @ManyToMany(mappedBy = "wishLists", fetch = FetchType.EAGER)
    private List<ViniloEntity> vinilos;
    
    @PodamExclude
    @OneToOne
    private UsuarioEntity usuario;
    
    public List<ViniloEntity> getVinilos()
    {
        return vinilos;
    }
    public Double getCosto()
    {
        return costo;
    }

    public UsuarioEntity getUsuario()
    {
        return usuario;
    }
    public void setCosto(Double costo)
    {
        this.costo = costo;
    }
    
    public void setVinilos(List<ViniloEntity> vinilos)
    {
        this.vinilos =  vinilos;
    }
    public void setUsuario(UsuarioEntity usuario)
    {
        this.usuario = usuario;
    }

    @Override
    public int hashCode() 
    {
        int hash = 5;
        hash = 29 * hash + Objects.hashCode(this.costo);
        return hash;
    }

    @Override
    public boolean equals(Object obj) 
    {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final WishListEntity other = (WishListEntity) obj;
        if (!Objects.equals(this.costo, other.costo)) {
            return false;
        }
        if (!Objects.equals(this.vinilos, other.vinilos)) {
            return false;
        }
        return Objects.equals(this.usuario, other.usuario);
    }
    
    
}