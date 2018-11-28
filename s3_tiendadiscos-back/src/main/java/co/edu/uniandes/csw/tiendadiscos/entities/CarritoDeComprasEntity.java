/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendadiscos.entities;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author Laura Isabella Forero Camacho
 */
@Entity
public class CarritoDeComprasEntity extends BaseEntity implements Serializable
{
   
    private Double totalCost;
    
    @PodamExclude
    @ManyToMany(mappedBy = "carritosDeCompras")
    private List<ViniloEntity> vinilos;
    
    
    @PodamExclude
    @OneToOne
    private UsuarioEntity usuario;
        
    public void setTotalCostDeCarritoCompras(Double totalCost) 
    {
        this.totalCost = totalCost;
    }
    
    public Double  getTotalCostDeCarritoCompras() 
    {
        return totalCost;
    }
    
    public void setUsuario(UsuarioEntity usuario) 
    {
        this.usuario = usuario;
    }
    
    public UsuarioEntity  getUsuario() 
    {
        return usuario;
    }
    
    public void setVinilosDeCarritoCompras(List<ViniloEntity> vinilos) 
    {
        this.vinilos = vinilos;
    }
    
    public List<ViniloEntity>  getVinilosDeCarritoCompras() 
    {
        return vinilos;
    }

    @Override
    public int hashCode() 
    {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.totalCost);
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
        final CarritoDeComprasEntity other = (CarritoDeComprasEntity) obj;
        if (!Objects.equals(this.totalCost, other.totalCost)) {
            return false;
        }
        if (!Objects.equals(this.vinilos, other.vinilos)) {
            return false;
        }
        if (!Objects.equals(this.usuario, other.usuario)) {
            return false;
        }
        return true;
    }   
}