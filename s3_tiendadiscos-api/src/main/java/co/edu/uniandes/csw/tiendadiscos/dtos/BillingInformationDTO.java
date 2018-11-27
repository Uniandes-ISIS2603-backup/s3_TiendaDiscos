/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendadiscos.dtos;

import co.edu.uniandes.csw.tiendadiscos.entities.BillingInformationEntity;
import java.io.Serializable;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * BillingInformationDTO Objeto de transferencia de datos de Billing. Los DTO contienen las
 * representaciones de los JSON que se transfieren entre el cliente y el 
 * servidor.
 * 
 * Al serializarse como JSON esta clase implementa el siguiente modelo: <br>
 * <pre>
 *  {
 *      "id": number,
 *      "cuentaAhorro": String,
 *      "spent": number,
 *      "recieved": number
 *  }
 * </pre> Por ejemplo un autor se representa asi:<br>
 *
 * <pre>
 * {
 *      "cuentaAhorro": "123321123",
 *      "id": 1,
 *      "recieved": 0,
 *      "spent": 0
 *  }
 * </pre>
 * 
 * @author Kevin Blanco
 */
public class BillingInformationDTO  implements Serializable{
    
    /**
     * ID unico de BillingInformation
     */
    private Long id;
    
    /**
     * numero de cuenta de ahorro
     */
    private String cuentaAhorro;
    
    /**
     * Dinero gastado
     */
    private double spent;
    
    /**
     * dinero recibido
     */
    private double recieved ;
    
    /**
     * Usuario asociado
     */
    private UsuarioDTO usuario;
    
    
    /**
     * constructor vacio
     */
    public BillingInformationDTO () 
    {}

    /**
     * Construye una Billing Information apartir de un objeto BillingInformationEntity.
     * 
     * @param billingInformationlEntity 
     */
    public BillingInformationDTO (BillingInformationEntity billingInformationlEntity) 
    {
        if (billingInformationlEntity != null) {
            this.id = billingInformationlEntity.getId();
            this.cuentaAhorro = billingInformationlEntity.getCuentaAhorro();
            this.recieved =billingInformationlEntity.getRecieved();
            this.spent = billingInformationlEntity.getSpent();
        }
    }
    
    /**
     * Retorna id del Billing 
     * @return id del Billing 
     */
    public Long getId() 
    {
        return id;
    }
    
    /**
     * retorna cuenta de ahorro asociada al billing 
     * @return cuenta de ahorro asociada al billing
     */
    public String getCuentaAhorro() 
    {
        return cuentaAhorro;
    }

    /**
     * retorna dinero gastado en el billing 
     * @return dinero gastado en el billing 
     */
    public Double getSpent() 
    {
        return spent;
    }
    
    /**
     * retorna dinero recibido en el billing 
     * @return dinero recibido en el billing 
     */
    public Double getRecieved() 
    {
        return recieved;
    }
    
   /**
    * modifica id 
    * @param id nuevo id 
    */
    public void setId(Long id) 
    {
        this.id = id;
    }

    /**
     * modifica el numero de la cuenta de ahorro del billing 
     * @param cuentaAhorro nuevo numero de cuenta de ahorro
     */
    public void setCuentaAhorro(String cuentaAhorro) 
    {
        this.cuentaAhorro = cuentaAhorro;
    }
    
    /**
     * modifica el dinero gastado en el billing
     * @param spent nuevo valor del dinero gastado
     */
    public void setSpent(Double spent) 
    {
        this.spent = spent;
    }

    /**
     * modifica dinero recibido en el Billing
     * @param recieved nuevo valor del dinero recibido
     */
    public void setRecieved(Double recieved) 
    {
        this.recieved = recieved;
    }

    /**
     * Devuelve el usuario asociado al billing 
     * @return El usuario asociado al billing.
    */
    public UsuarioDTO getUsuario() 
    {
        return usuario;
    }
    
    /**
     * Modifica el usuario asociado al billing 
     * @param usuario usuario nuevo para el billing
     */
    public void setUsuario(UsuarioDTO usuario) 
    {
        this.usuario = usuario;
    }
        
    /**
     * Convierte un objeto BillingInformationDTO a BillingInformationEntity
     *
     * @return Entity con los valores del DTO
     */
    public BillingInformationEntity toEntity () 
    {        
        BillingInformationEntity billingInformationEntity = new BillingInformationEntity();
        billingInformationEntity.setId(this.id);
        billingInformationEntity.setCuentaAhorro(this.getCuentaAhorro());
        billingInformationEntity.setRecieved(this.getRecieved());
        billingInformationEntity.setSpent(this.getSpent());
        return billingInformationEntity;
    }
    
    @Override
    public String toString() 
    {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }    
}