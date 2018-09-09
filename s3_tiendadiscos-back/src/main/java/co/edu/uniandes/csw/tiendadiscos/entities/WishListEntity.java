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
import javax.persistence.OneToMany;

/**
 *
 * @author estudiante
 */
@Entity
public class WishListEntity extends BaseEntity implements Serializable {

   

    
    private Double costo;
    
    @OneToMany
    private List<ViniloEntity> vinilos;

    public List<ViniloEntity> getVinilos()
    {
        return vinilos;
    }
    public Double getCosto()
    {
        return costo;
    }

    
    public void setCosto(Double costo)
    {
        this.costo = costo;
    }
    
    public void setVinilos(List<ViniloEntity> vinilos)
    {
        this.vinilos =  vinilos;
    }
    
}
