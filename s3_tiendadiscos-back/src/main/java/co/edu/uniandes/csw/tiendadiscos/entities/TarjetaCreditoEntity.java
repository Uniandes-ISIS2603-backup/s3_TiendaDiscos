/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendadiscos.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Kevin Blanco
 */
public class TarjetaCreditoEntity extends BaseEntity implements Serializable {

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
    private Integer cvc;

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
    public Integer getCvc() {
        return cvc;
    }

    /**
     * modifica codigo CVC de la tarjeta
     *
     * @param cvc nuevo codigo CVC de la tarjeta
     */
    public void setCvc(Integer cvc) {
        this.cvc = cvc;
    }

}
