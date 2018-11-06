/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendadiscos.dtos;

import co.edu.uniandes.csw.tiendadiscos.entities.CarritoDeComprasEntity;
import java.io.Serializable;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 *
 * @author estudiante
 */
public class CarritoDeComprasDTO implements Serializable {
    /**
     * id único de la transaccion.
     */
    private Long id;
    
    private Double totalCost;

    
    public CarritoDeComprasDTO() {
    }
    /**
     * Obtiene el atributo id.
     *
     * @return atributo id.
     *
     */
    public Long getId() {
        return id;
    }
    
    public Double getTotalCost() {
        return totalCost;
    }
    /**
     * Establece el valor del atributo id.
     *
     * @param id nuevo valor del atributo
     *
     */
    public void setId(Long id) {
        this.id = id;
    }
    
    public void setTotalCost(Double totalCost) {
        this.totalCost = totalCost;
    }
    public CarritoDeComprasEntity toEntity()
    {
        CarritoDeComprasEntity carritoCompras = new CarritoDeComprasEntity();
        carritoCompras.setId(this.id);
        carritoCompras.setTotalCostDeCarritoCompras(this.totalCost);
        return carritoCompras;
    }
    public CarritoDeComprasDTO(CarritoDeComprasEntity carritoCompras){
        if(carritoCompras!=null){
            this.id= carritoCompras.getId();
            this.totalCost = carritoCompras.getTotalCostDeCarritoCompras();
        }
    }
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}
