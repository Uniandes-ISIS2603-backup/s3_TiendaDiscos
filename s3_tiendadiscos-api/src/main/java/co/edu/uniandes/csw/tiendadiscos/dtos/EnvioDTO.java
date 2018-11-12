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
 *      "direccionEntrega":string,
 *      "estado":string
 *   }
 * </pre> Por ejemplo un autor se representa asi:<br>
 *
 * <pre>
 *
 *   {
 *      "id":1231231,
 *      "direccionEntrega":"cra 1 #120d-47",
 *      "estado":"PENDIENTE"
 *   }
 *
 * </pre>
 *
 * @author Camilo Andres Salinas Martinez
 */
public class EnvioDTO implements Serializable {
        
   public enum estado
   {
       PENDIENTE,
       PROGRESO,
       RECIBIDO
   }
   
   private Long id;
   
   private String direccionSalida; 
   
   private String direccionEntrega; 
   
   private String estado; 
   
   /**
    * Constructor por defecto.
    */
   public EnvioDTO()
   { }
   
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
     * Devuelve el ID de la editorial.
     *
     * @return the id
     */
    public Long getId() {
        return id;
    }
    /**
     * Modifica el ID de la editorial.
     *
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }
     /**
     * Devuelve la direccion de entrega del envio.
     *
     * @return the direccionEntrega
     */
    public String getDireccionEntrega() {
        return direccionEntrega;
    }

    public void setDireccionEntrega(String direccionEntrega) {
        this.direccionEntrega = direccionEntrega;
    }
    public String getDireccionSalida() {
        return direccionSalida;
    }

    public void setDireccionSalida(String direccionSalida) {
        this.direccionSalida = direccionSalida;
    }
    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    @Override
    public String toString(){
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }   
}