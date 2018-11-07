/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendadiscos.dtos;

import co.edu.uniandes.csw.tiendadiscos.entities.MedioDePagoEntity;
import java.io.Serializable;
import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 *
 * @author Kevin Blanco
 */
public class MedioDePagoDTO implements Serializable {

    /**
     * id único de la tarjeta de credito.
     */
    private Long id;

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
    private Date fechaVencimiento;

    /**
     * nombre del propietario de la tarjeta
     */
    private String name;

    /**
     * Codigo CVC de la tarjeta
     */
    private String cvc;
    
    private String imagen;

    
    
    
    /**
     * Constructor vacio
     */
    public MedioDePagoDTO() {
    }

    /**
     * Construye un tarjeta de credito apartir de la entity
     *
     * @param tarjetaCreditoEntity DTO a completar
     */
    public MedioDePagoDTO(MedioDePagoEntity tarjetaCreditoEntity) {
        if (tarjetaCreditoEntity != null) {
            this.id = tarjetaCreditoEntity.getId();
            this.fechaVencimiento = tarjetaCreditoEntity.getFechaVencimiento();
            this.cvc = tarjetaCreditoEntity.getCvc();
            this.imagen = tarjetaCreditoEntity.getImagen();
            this.name = tarjetaCreditoEntity.getName();
            this.numero = tarjetaCreditoEntity.getNumero();
            this.numeroVerificacion = tarjetaCreditoEntity.getNumeroVerificacion();
            //TERMINAR

        }
    }

    /**
     * retorna id
     *
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * modifica id
     *
     * @param id id de la tarjeta
     */
    public void setId(Long id) {
        this.id = id;
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

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
    
    
    

    /**
     * convierte DTO a Entity
     *
     * @return Entity con los valores del DTO
     */
    public MedioDePagoEntity toEntity() {
        MedioDePagoEntity tarjetaCreditoEntity = new MedioDePagoEntity();
        tarjetaCreditoEntity.setId(this.id);
        tarjetaCreditoEntity.setName(this.name);
        tarjetaCreditoEntity.setFechaVencimiento(this.getFechaVencimiento());
        tarjetaCreditoEntity.setNumeroVerificacion(getNumeroVerificacion());
        tarjetaCreditoEntity.setNumero(getNumero());
        tarjetaCreditoEntity.setCvc(this.getCvc());
        tarjetaCreditoEntity.setImagen(this.imagen);
        return tarjetaCreditoEntity;
    }

    @Override
    public String toString() {

        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);

    }

}