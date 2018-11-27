/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendadiscos.dtos;

import co.edu.uniandes.csw.tiendadiscos.adapters.DateAdapter;
import co.edu.uniandes.csw.tiendadiscos.entities.MedioDePagoEntity;
import java.io.Serializable;
import java.util.Date;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * MedioDePagoDTO Objeto de transferencia de datos de Medio de pago. Los DTO contienen las
 * representaciones de los JSON que se transfieren entre el cliente y el
 * servidor.
 *
 * Al serializarse como JSON esta clase implementa el siguiente modelo: <br>
 * <pre>
 *  {
 *      "id":number,
 *      "numero":number,
 *      "numeroVerificacion":number,
 *      "fechaVencimiento":Date,
 *      "name":String,
 *      "cvc":String,
 *      "imagen":String
 *  }
 * </pre>
 * 
 * @author Kevin Blanco
 */
public class MedioDePagoDTO implements Serializable {

    /**
     * id único de la tarjeta de credito.
     */
    private Long id;

    /**
     * Numero de la tarjeta.
     */
    private Integer numero;

    /**
     * Numero de la tarjeta de cred verificacion.
     */
    private Integer numeroVerificacion;

    /**
     * Fecha de vencimiento de la tarjeta de cred.
     */
    @XmlJavaTypeAdapter(DateAdapter.class)
    private Date fechaVencimiento;

    /**
     * Nombre del propietario de la tarjeta.
     */
    private String name;

    /**
     * Codigo CVC de la tarjeta.
     */
    private String cvc;

    /**
     * Imagen asociada al medio de pago.
     */
    private String imagen;

    /**
     * Constructor vacio
     */
    public MedioDePagoDTO() 
    {}

    /**
     * Crea un objeto MedioDePagoDTO a partir de un objeto MedioDePagoEntity
     *
     * @param tarjetaCreditoEntity Entidad MedioDePagoEntity desde la cual se va a crear el 
     * nuevo objeto.
     * 
     */
    public MedioDePagoDTO(MedioDePagoEntity tarjetaCreditoEntity) 
    {
        if (tarjetaCreditoEntity != null) 
        {
            this.id = tarjetaCreditoEntity.getId();
            this.fechaVencimiento = tarjetaCreditoEntity.getFechaVencimiento();
            this.cvc = tarjetaCreditoEntity.getCvc();
            this.imagen = tarjetaCreditoEntity.getImagen();
            this.name = tarjetaCreditoEntity.getName();
            this.numero = tarjetaCreditoEntity.getNumero();
            this.numeroVerificacion = tarjetaCreditoEntity.getNumeroVerificacion();
        }
    }

    /**
     * Retorna id.
     *
     * @return id
     */
    public Long getId() 
    {
        return id;
    }

    /**
     * Modifica id.
     *
     * @param id id de la tarjeta
     */
    public void setId(Long id) 
    {
        this.id = id;
    }

    /**
     * Retorna numero de la tarjeta
     *
     * @return numero de la tarjeta
     */
    public Integer getNumero() 
    {
        return numero;
    }

    /**
     * Modifica numero de la tarjeta
     *
     * @param numero numero nuevo de la tarjeta
     */
    public void setNumero(Integer numero) 
    {
        this.numero = numero;
    }

    /**
     * Retorna numero de verificacion de la tarjeta
     *
     * @return numero de verificacion de la tarjeta
     */
    public Integer getNumeroVerificacion() 
    {
        return numeroVerificacion;
    }

    /**
     * Modifica numero de verificacion de la tarjeta
     *
     * @param numeroVerificacion numero nuevo de verificacion
     */
    public void setNumeroVerificacion(Integer numeroVerificacion) 
    {
        this.numeroVerificacion = numeroVerificacion;
    }

    /**
     * Retorna fecha de vencimiento de la tarjeta
     *
     * @return fecha de vencimiento de la tarjeta
     */
    public Date getFechaVencimiento() 
    {
        return fechaVencimiento;
    }

    /**
     * Modifica la fecha de vencimiento de la tarjeta
     *
     * @param fechaVencimiento nueva fecha de vencimiento de la tarjeta
     */
    public void setFechaVencimiento(Date fechaVencimiento) 
    {
        this.fechaVencimiento = fechaVencimiento;
    }

    /**
     * Retorna nombre de la persona a quien pertenece la tarjeta
     *
     * @return nombre del propietario de la tarjeta
     */
    public String getName() 
    {
        return name;
    }

    /**
     * Modifica el nombre del propietario de la tarjeta
     *
     * @param name nombre nuevo del propietario de la tarjeta
     */
    public void setName(String name) 
    {
        this.name = name;
    }

    /**
     * Retorna codigo CVC de la tarjeta
     *
     * @return codigio CVC de la tarjeta
     */
    public String getCvc() 
    {
        return cvc;
    }

    /**
     * Modifica codigo CVC de la tarjeta
     *
     * @param cvc nuevo codigo CVC de la tarjeta
     */
    public void setCvc(String cvc) 
    {
        this.cvc = cvc;
    }

    /**
     * Obtiene la imagen del medio de pago.
     * @return Atributo imagen.
     */
    public String getImagen() 
    {
        return imagen;
    }

    /**
     * Modifica la imagen del medio de pago.
     * @param imagen nuevo valor del atributo.
     */
    public void setImagen(String imagen) 
    {
        this.imagen = imagen;
    }

    /**
     * convierte DTO a Entity
     *
     * @return Entity con los valores del DTO
     */
    public MedioDePagoEntity toEntity() 
    {
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
    public String toString() 
    {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}