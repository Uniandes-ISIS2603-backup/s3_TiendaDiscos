/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendadiscos.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author estudiante
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
}
