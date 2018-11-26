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
 *
 * @author Kevin Blanco
 */
public class BillingInformationDetailDTO extends BillingInformationDTO implements Serializable {

    // relación  cero o muchos tarjeta credito 
    private List<MedioDePagoDTO> tarjetas;

    public BillingInformationDetailDTO() {
        super();
    }

    /**
     * Constructor para transformar un Entity a un DTO
     *
     * @param billingInformationEntity La entidad de la cual se construye el DTO
     */
    public BillingInformationDetailDTO(BillingInformationEntity billingInformationEntity) {
        super(billingInformationEntity);

        if (billingInformationEntity.getTarjetas() != null && billingInformationEntity.getTarjetas().size() > 0) {
            tarjetas = new ArrayList<>();

            for (MedioDePagoEntity tarjeta : billingInformationEntity.getTarjetas()) {
                tarjetas.add(new MedioDePagoDTO(tarjeta));
            }
        }
    }

    public BillingInformationEntity toEntity() {
        BillingInformationEntity entity = super.toEntity();
        if (tarjetas != null) {
            List<MedioDePagoEntity> tarjetasEntity = new ArrayList<>();

            for (MedioDePagoDTO tarjetaCreditoDTO : getTarjetas()) {
                tarjetasEntity.add(tarjetaCreditoDTO.toEntity());
            }

            entity.setTarjetas(tarjetasEntity);
        }
        return entity;
    }

    public List<MedioDePagoDTO> getTarjetas() {
        return tarjetas;
    }

    public void setTarjetas(List<MedioDePagoDTO> tarjetas) {
        this.tarjetas = tarjetas;
    }
}
