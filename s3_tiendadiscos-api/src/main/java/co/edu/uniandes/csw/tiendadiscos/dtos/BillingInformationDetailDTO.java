/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendadiscos.dtos;

import co.edu.uniandes.csw.tiendadiscos.entities.BillingInformationEntity;
import co.edu.uniandes.csw.tiendadiscos.entities.MedioDePagoEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que extiende de {@link BillingInformationDTO} para manejar las relaciones entre los
 * BillingInformationDTO y otros DTOs. Para conocer el contenido de un BillingInformation vaya a la
 * documentacion de {@link BillingInformationDTO}
 * 
 * Al serializarse como JSON esta clase implementa el siguiente modelo: <br>
 * <pre>
 * {
 *      "id": number,
 *      "cuentaAhorro": String,
 *      "spent": number,
 *      "recieved": number,
 *      "tarjetas": [{@link MedioDePagoDTO}]
 *  }
 * </pre> Por ejemplo un autor se representa asi:<br>
 *
 * <pre>
 * {
 *      "cuentaAhorro": "123321123",
 *      "id": 1,
 *      "recieved": 0,
 *      "spent": 0,
 *      "tarjetas": [{}]
 *  }
 * </pre>
 * @author Kevin Blanco
 */
public class BillingInformationDetailDTO extends BillingInformationDTO implements Serializable 
{

    // relación  cero o muchos tarjeta credito 
    private List<MedioDePagoDTO> tarjetas;

    /**
     * Constructor vacio.
     */
    public BillingInformationDetailDTO() 
    {
        super();
    }

    /**
     * Crea un objeto BillingInformationDetailDTO a partir de un objeto BillingInformationEntity
     * incluyendo los atributos de BillingInformationDTO
     *
     * @param billingInformationEntity La entidad de la cual se construye el DTO.
     */
    public BillingInformationDetailDTO(BillingInformationEntity billingInformationEntity) 
    {
        super(billingInformationEntity);
        if (billingInformationEntity.getTarjetas() != null && billingInformationEntity.getTarjetas().isEmpty()) 
        {
            tarjetas = new ArrayList<>();
            for (MedioDePagoEntity tarjeta : billingInformationEntity.getTarjetas())
                tarjetas.add(new MedioDePagoDTO(tarjeta));            
        }
    }

    /**
     * Convierte un objeto BillingInformationDetailDTO a BillingEntity incluyendo los
     * atributos de BillingInformationDTO.
     * 
     * @return Nuevo objeto BillingInformationEntity.
     */
    @Override
    public BillingInformationEntity toEntity() {
        BillingInformationEntity entity = super.toEntity();
        if (tarjetas != null) 
        {
            List<MedioDePagoEntity> tarjetasEntity = new ArrayList<>();
            for (MedioDePagoDTO tarjetaCreditoDTO : getTarjetas())
                tarjetasEntity.add(tarjetaCreditoDTO.toEntity());
            entity.setTarjetas(tarjetasEntity);
        }
        return entity;
    }

    /**
     * Obtiene la lista de tarjetas del Billing.
     * 
     * @return Las tajetas.
     */
    public List<MedioDePagoDTO> getTarjetas() 
    {
        return tarjetas;
    }
    
    /**
     * Modifica la lista de tarjetas del Billing.
     * 
     * @param tarjetas tarjetas to be set.
     */
    public void setTarjetas(List<MedioDePagoDTO> tarjetas) 
    {
        this.tarjetas = tarjetas;
    }
}
