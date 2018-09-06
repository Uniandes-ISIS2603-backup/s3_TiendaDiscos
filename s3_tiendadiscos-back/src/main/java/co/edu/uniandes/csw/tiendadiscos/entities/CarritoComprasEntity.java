/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendadiscos.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;

/**
 *
 * @author Laura Isabella Forero Camacho
 */
@Entity
public class CarritoComprasEntity extends BaseEntity implements Serializable{

    public CarritoComprasEntity() {
    }
    
    private List<ViniloEntity> vinilos;
    
    public void setVinilosDeCarritoCompras(List<ViniloEntity> vinilos) {
        this.vinilos = vinilos;
    }

    
    public List<ViniloEntity>  getVinilosDeCarritoCompras() {
        return vinilos;
    }
    
}
