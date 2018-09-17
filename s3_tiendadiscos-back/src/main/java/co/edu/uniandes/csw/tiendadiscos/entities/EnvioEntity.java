/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendadiscos.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author Camilo Andres Salinas Martinez
 */
@Entity
public class EnvioEntity extends BaseEntity implements Serializable{
    
    private String direccionEntrega;
    private String direccionSalida;
    private String estado;
    
    @PodamExclude
    @OneToOne
    private TransaccionEntity transaccion;

    public String getDireccionSalida() {
        return direccionSalida;
    }

    public void setDireccionSalida(String direccionSalida) {
        this.direccionSalida = direccionSalida;
    }

    public TransaccionEntity getTransaccion() {
        return transaccion;
    }

    public void setTransaccion(TransaccionEntity transaccion) {
        this.transaccion = transaccion;
    }

    public String getDireccionEntrega() {
        return direccionEntrega;
    }

    public void setDireccionEntrega(String direccionEntrega) {
        this.direccionEntrega = direccionEntrega;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

   
    
    
    
    
    
    
}
