/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendadiscos.dtos;

import co.edu.uniandes.csw.tiendadiscos.entities.BillingInformationEntity;
import java.io.Serializable;

/**
 *
 * @author estudiante
 */
public class BillingInformationDTO  implements Serializable{
    
    private Long id;
    private String cuentaAhorro;
    private Double spent;
    private Double recieved ;
    
    /**
     * 
     */
    public BillingInformationDTO () {}

     public BillingInformationDTO (BillingInformationEntity billingInformationlEntity) {
        if (billingInformationlEntity != null) {
            this.id = billingInformationlEntity.getId();
            //TERMINAR
            
           // this.name = billingInformationlEntity.getName();
        }
    }
    
    /**
     * 
     * @return 
     */
    public Long getId() {
        return id;
    }

    public String getCuentaAhorro() {
        return cuentaAhorro;
    }

    public Double getSpent() {
        return spent;
    }

    public Double getRecieved() {
        return recieved;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCuentaAhorro(String cuentaAhorro) {
        this.cuentaAhorro = cuentaAhorro;
    }

    public void setSpent(Double spent) {
        this.spent = spent;
    }

    public void setRecieved(Double recieved) {
        this.recieved = recieved;
    }
    
    
    public BillingInformationEntity toEntity () {
   
    return null;
    }
    public String toString() {
    
    return null;
    }
   
    
    
    
}
