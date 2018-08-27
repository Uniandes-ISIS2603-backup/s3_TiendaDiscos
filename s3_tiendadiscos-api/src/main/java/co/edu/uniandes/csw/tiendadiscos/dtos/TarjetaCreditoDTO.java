/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendadiscos.dtos;

import co.edu.uniandes.csw.tiendadiscos.entities.TarjetaCreditoEntity;
import java.util.Date;

/**
 *
 * @author estudiante
 */
public class TarjetaCreditoDTO {

    private Long id;
    private Integer numero;
    private Integer numeroVErificacion;
    private Date fechaVEncimiento;
    private String name;
    private Integer cvc;

    public TarjetaCreditoDTO() {
    }

    public TarjetaCreditoDTO(TarjetaCreditoEntity tarjetaCreditoEntity) {
        if (tarjetaCreditoEntity != null) {
            this.id = tarjetaCreditoEntity.getId();
            //TERMINAR

        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public Integer getNumeroVErificacion() {
        return numeroVErificacion;
    }

    public void setNumeroVErificacion(Integer numeroVErificacion) {
        this.numeroVErificacion = numeroVErificacion;
    }

    public Date getFechaVEncimiento() {
        return fechaVEncimiento;
    }

    public void setFechaVEncimiento(Date fechaVEncimiento) {
        this.fechaVEncimiento = fechaVEncimiento;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCvc() {
        return cvc;
    }

    public void setCvc(Integer cvc) {
        this.cvc = cvc;
    }

    public TarjetaCreditoEntity toEntity() {

        return null;
    }

    public String toString() {

        return "";
    }

}
