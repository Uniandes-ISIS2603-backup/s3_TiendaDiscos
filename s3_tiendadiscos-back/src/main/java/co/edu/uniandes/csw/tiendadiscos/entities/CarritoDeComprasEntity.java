/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendadiscos.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author Laura Isabella Forero Camacho
 */
@Entity
public class CarritoDeComprasEntity extends BaseEntity implements Serializable{

    
    private Double totalCost;
    
    @PodamExclude
    @ManyToMany(mappedBy = "carritosDeCompras", fetch = FetchType.EAGER)
    private List<ViniloEntity> vinilos;
    
    @PodamExclude
    @OneToMany(mappedBy = "carritoDeCompras", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<TransaccionEntity> transacciones;
    
    @PodamExclude
    @OneToOne
    private UsuarioEntity usuario;
    
    public void setTotalCostDeCarritoCompras(Double totalCost) {
        this.totalCost = totalCost;
    }

    
    public Double  getTotalCostDeCarritoCompras() {
        return totalCost;
    }
    
    public void setUsuario(UsuarioEntity usuario) {
        this.usuario = usuario;
    }

    
    public UsuarioEntity  getUsuario() {
        return usuario;
    }
    
    public void setVinilosDeCarritoCompras(List<ViniloEntity> vinilos) {
        this.vinilos = vinilos;
    }

    
    public List<ViniloEntity>  getVinilosDeCarritoCompras() {
        return vinilos;
    }
    
    public void setTransaccionesDeCarritoCompras(List<TransaccionEntity> transacciones) {
        this.transacciones = transacciones;
    }
    public List<TransaccionEntity>  getTransaccionesDeCarritoCompras() {
        return transacciones;
    }
    
    
    public CarritoDeComprasEntity() {
    }
}
