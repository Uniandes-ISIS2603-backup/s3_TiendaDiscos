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
     * constructor vacio
     */
    
    private UsuarioDTO usuario;
    public BillingInformationDTO () {
    
    }

    /**
         * Construye una Billing Information apartir de la entity
     * @param billingInformationlEntity 
     */
    public BillingInformationDTO (BillingInformationEntity billingInformationlEntity) {
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
    public Long getId() {
        return id;
    }
    
    /**
     * retorna cuenta de ahorro asociada al billing 
     * @return cuenta de ahorro asociada al billing
     */
    public String getCuentaAhorro() {
        return cuentaAhorro;
    }

    /**
     * retorna dinero gastado en el billing 
     * @return dinero gastado en el billing 
     */
    public Double getSpent() {
        return spent;
    }
    
    /**
     * retorna dinero recibido en el billing 
     * @return dinero recibido en el billing 
     */
    public Double getRecieved() {
        return recieved;
    }
    
   /**
    * modifica id 
    * @param id nuevo id 
    */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * modifica el numero de la cuenta de ahorro del billing 
     * @param cuentaAhorro nuevo numero de cuenta de ahorro
     */
    public void setCuentaAhorro(String cuentaAhorro) {
        this.cuentaAhorro = cuentaAhorro;
    }
    
    /**
     * modifica el dinero gastado en el billing
     * @param spent nuevo valor del dinero gastado
     */
    public void setSpent(Double spent) {
        this.spent = spent;
    }

    /**
     * modifica dinero recibido en el Billing
     * @param recieved nuevo valor del dinero recibido
     */
    public void setRecieved(Double recieved) {
        
        this.recieved = recieved;
    }

    /**
     * devuelve el usuario asociado al billing 
    */
    public UsuarioDTO getUsuario() {
        return usuario;
    }
    
    /**
     * Modifica el usuario asociado al billing 
     * @param usuario usuario nuevo para el billing
     */
    public void setUsuario(UsuarioDTO usuario) {
        this.usuario = usuario;
    }
    
    
     /**
     * convierte DTO a Entity
     *
     * @return Entity con los valores del DTO
     */
    public BillingInformationEntity toEntity () {
        
        BillingInformationEntity billingInformationEntity = new BillingInformationEntity();
        billingInformationEntity.setId(this.id);
        billingInformationEntity.setCuentaAhorro(this.getCuentaAhorro());
        billingInformationEntity.setRecieved(this.getRecieved());
        billingInformationEntity.setSpent(this.getSpent());
        return billingInformationEntity;
    }
    @Override
    public String toString() {

        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);

    }
   
    
    
    
}