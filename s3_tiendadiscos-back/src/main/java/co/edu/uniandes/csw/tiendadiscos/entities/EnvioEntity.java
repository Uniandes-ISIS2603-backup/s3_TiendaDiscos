/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendadiscos.entities;

import java.io.Serializable;
import java.util.Objects;
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
    private String posicionActual;
    
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

    public String getPosicionActual() {
        return posicionActual;
    }

    public void setPosicionActual(String posicionActual) {
        this.posicionActual = posicionActual;
    }

    @Override
    public int hashCode() 
    {
        int hash = 7;
        hash = 61 * hash + Objects.hashCode(this.direccionEntrega);
        hash = 61 * hash + Objects.hashCode(this.direccionSalida);
        hash = 61 * hash + Objects.hashCode(this.estado);
        hash = 61 * hash + Objects.hashCode(this.posicionActual);
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
        final EnvioEntity other = (EnvioEntity) obj;
        if (!Objects.equals(this.direccionEntrega, other.direccionEntrega)) {
            return false;
        }
        if (!Objects.equals(this.direccionSalida, other.direccionSalida)) {
            return false;
        }
        if (!Objects.equals(this.estado, other.estado)) {
            return false;
        }
        if (!Objects.equals(this.posicionActual, other.posicionActual)) {
            return false;
        }
        if (!Objects.equals(this.transaccion, other.transaccion)) {
            return false;
        }
        return true;
    }
}