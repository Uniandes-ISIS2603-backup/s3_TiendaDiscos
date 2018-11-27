/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendadiscos.dtos;

import co.edu.uniandes.csw.tiendadiscos.entities.EnvioEntity;
import java.io.Serializable;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * EnvioDTO Objeto de transferencia de datos de Usuario. Los DTO contienen las
 * representaciones de los JSON que se transfieren entre el cliente y el
 * servidor.
 *
 * Al serializarse como JSON esta clase implementa el siguiente modelo: <br>
 * <pre>
 *   {
 *      "id":number,
 *      "direccionSalida":String,
 *      "direccionEntrega":string,
 *      "estado":string
 *   }
 * </pre> Por ejemplo un autor se representa asi:<br>
 *
 * <pre>
 *   {
 *      "id":1231231,
 *      "direccionSalida": "cra13 #45a-11"
 *      "direccionEntrega":"cra 1 #120d-47",
 *      "estado":"PENDIENTE"
 *   }
 * </pre>
 *
 * @author Camilo Andres Salinas Martinez
 */
public class EnvioDTO implements Serializable 
{       
    /**
     * Enumeración para llevar cuenta del estado del envio.
     */
    public enum estado
    {
        /**
         * Constante que indica el estado pendiente.
         */
        PENDIENTE,
        /**
         * Constante que indica el estado en progreso.
         */
        PROGRESO,
        /**
         * Constante que indica el estado recibido.
         */
        RECIBIDO
    }
   
    /**
     * Identificador único del envio.
     */
    private Long id;
   
    /**
     * Dirección de salida del envio.
     */
    private String direccionSalida; 
   
    /**
     * Dirección de entrega del envio.
     */
    private String direccionEntrega; 
   
    /**
     * Estado del envio.
     */
    private String estado; 
   
    /**
     * Constructor por defecto.
     */
    public EnvioDTO()
    { }
   
    /**
     * Crea un objeto EnvioDTO a partir de un objeto EnvioEntity.
     * 
     * @param envioEntity Entidad EnvioEntity desde la cual se va a crear el
     * nuevo objeto.
     */
    public EnvioDTO(EnvioEntity envioEntity)
    {
        if(envioEntity != null)
        {
            this.id = envioEntity.getId();
            this.direccionSalida = envioEntity.getDireccionSalida();
            this.direccionEntrega = envioEntity.getDireccionEntrega();
            this.estado = envioEntity.getEstado();
        }
    }
   
    /**
     * Convierte un objeto EnvioDTO a EnvioEntity.
     * 
     * @return Nuevo objeto EnvioEntity.
     */
    public EnvioEntity toEntity()
    {
        EnvioEntity newEntity = new EnvioEntity();
        newEntity.setId(this.id);
        newEntity.setDireccionSalida(direccionSalida);
        newEntity.setDireccionEntrega(direccionEntrega);
        newEntity.setEstado(estado);       
        return newEntity;
    }
   
    /**
     * Devuelve el ID del Envio.
     *
     * @return the id
     */
    public Long getId() 
    {
        return id;
    }
    /**
     * Modifica el ID del envio..
     *
     * @param id the id to set
     */
    public void setId(Long id) 
    {
        this.id = id;
    }
    
    /**
     * Devuelve la direccion de entrega del envio.
     *
     * @return the direccionEntrega
     */
    public String getDireccionEntrega() 
    {
        return direccionEntrega;
    }

    /**
     * Establece el valor del atributo direccion de entrega.
     * @param direccionEntrega nuevo valor del atributo.
     */
    public void setDireccionEntrega(String direccionEntrega) 
    {
        this.direccionEntrega = direccionEntrega;
    }
    
    /**
     * Obtiene el atributo dirección de salida.
     * @return atributo direcciónSalida
     */
    public String getDireccionSalida() 
    {
        return direccionSalida;
    }

    /**
     * Establece el valor del atributo dirección de salida.
     * @param direccionSalida nuevo valor del atributo.
     */
    public void setDireccionSalida(String direccionSalida) 
    {
        this.direccionSalida = direccionSalida;
    }
    
    /**
     * Obtiene el atributo estado.
     * @return atributo estado.
     */
    public String getEstado() 
    {
        return estado;
    }

    /**
     * Establece el valor del atributo estado.
     * @param estado el nuevo valor del atributo.
     */
    public void setEstado(String estado) 
    {
        this.estado = estado;
    }
    
    @Override
    public String toString()
    {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }   
}