/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendadiscos.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author Kevin Blanco
 */
@Entity
public class MedioDePagoEntity extends BaseEntity implements Serializable {

    @PodamExclude
    @ManyToOne
    private BillingInformationEntity billing;
    
    /**
     * numero de la tarjeta
     */
    private Integer numero;

    /**
     * numero de la tarjeta de cred verificacion
     */
    private Integer numeroVerificacion;

    /**
     * fecha de vencimiento de la tarjeta de cred
     */
    @Temporal(TemporalType.DATE)
    private Date fechaVencimiento;

    /**
     * nombre del propietario de la tarjeta
     */
    private String name;

    /**
     * Codigo CVC de la tarjeta
     */
    private String cvc;
    
    /**
     * Imagen del medio de pago.
     */
    private String imagen;

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    /**
     * Retorna billing asociado a la tarjeta
     * @return  billing asociado a la tarjeta
     */
    public BillingInformationEntity getBilling() {
        return billing;
    }
    /**
     * modifica billing asociado a la tarjeta
     * @param billing billing que se agregara a la tarjeta 
     */
    public void setBilling(BillingInformationEntity billing) {
        this.billing = billing;
    }
    
    

    /**
     * retorna numero de la tarjeta
     *
     * @return numero de la tarjeta
     */
    public Integer getNumero() {
        return numero;
    }
    
   

    /**
     * modifica numero de la tarjeta
     *
     * @param numero numero nuevo de la tarjeta
     */
    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    /**
     * retorna numero de verificacion de la tarjeta
     *
     * @return numero de verificacion de la tarjeta
     */
    public Integer getNumeroVerificacion() {
        return numeroVerificacion;
    }

    /**
     * modifica numero de verificacion de la tarjeta
     *
     * @param numeroVErificacion numero nuevo de verificacion
     */
    public void setNumeroVerificacion(Integer numeroVErificacion) {
        this.numeroVerificacion = numeroVErificacion;
    }

    /**
     * retorna fecha de vencimiento de la tarjeta
     *
     * @return fecha de vencimiento de la tarjeta
     */
    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }

    /**
     * modifica la fecha de vencimiento de la tarjeta
     *
     * @param fechaVEncimiento nueva fecha de vencimiento de la tarjeta
     */
    public void setFechaVencimiento(Date fechaVEncimiento) {
        this.fechaVencimiento = fechaVEncimiento;
    }

    /**
     * retorna nombre de la persona a quien pertenece la tarjeta
     *
     * @return nombre del propietario de la tarjeta
     */
    public String getName() {
        return name;
    }

    /**
     * modifica el nombre del propietario de la tarjeta
     *
     * @param name nombre nuevo del propietario de la tarjeta
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * retorna codigo CVC de la tarjeta
     *
     * @return codigio CVC de la tarjeta
     */
    public String getCvc() {
        return cvc;
    }

    /**
     * modifica codigo CVC de la tarjeta
     *
     * @param cvc nuevo codigo CVC de la tarjeta
     */
    public void setCvc(String cvc) {
        this.cvc = cvc;
    }

    @Override
    public int hashCode() 
    {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.numero);
        hash = 29 * hash + Objects.hashCode(this.numeroVerificacion);
        hash = 29 * hash + Objects.hashCode(this.fechaVencimiento);
        hash = 29 * hash + Objects.hashCode(this.name);
        hash = 29 * hash + Objects.hashCode(this.cvc);
        hash = 29 * hash + Objects.hashCode(this.imagen);
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
        final MedioDePagoEntity other = (MedioDePagoEntity) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.cvc, other.cvc)) {
            return false;
        }
        if (!Objects.equals(this.imagen, other.imagen)) {
            return false;
        }
        if (!Objects.equals(this.billing, other.billing)) {
            return false;
        }
        if (!Objects.equals(this.numero, other.numero)) {
            return false;
        }
        if (!Objects.equals(this.numeroVerificacion, other.numeroVerificacion)) {
            return false;
        }
        return Objects.equals(this.fechaVencimiento, other.fechaVencimiento);
    }
}