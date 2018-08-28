/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendadiscos.dtos;

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
        
   public enum estado{
       PENDIENTE,
       PROGRESO,
       RECIBIDO
   }
   private Long id; 
   private String direccionEntrega; 
   private String estado; 
    
   public EnvioDTO(){
   }
   

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    @Override
    public String toString(){
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
   
   
    
}
