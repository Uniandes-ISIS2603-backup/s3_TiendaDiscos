/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendadiscos.resources;

import co.edu.uniandes.csw.tiendadiscos.dtos.BillingInformationDTO;
import co.edu.uniandes.csw.tiendadiscos.dtos.BillingInformationDetailDTO;
import javax.ws.rs.*;

/**
 *
 * @author Kevin Blanco
 */
@Produces("aplication/json")
@Consumes("aplication/json")
public class BillingInformationResource {

    /**
     * Crea el billing al usuario. Este solo se crea una vez y es unico
     * @param usuariosId id asociado al billing
     * @return 
     */
    @POST
    public BillingInformationDTO createBillingInformation(@PathParam("usuariosId") Long usuariosId, BillingInformationDTO billingDto) {

        return billingDto;
    }
    
    /**
     * Retorna la informacion del billing 
     * @return 
     */
    @GET
    public BillingInformationDTO getBilling() {
        return null;
    }

    /**
     * @GET public BillingInformationDetailDTO getAllBilling() { return null; }
    *
     */
    
    
    /**
     * modifica la informacion del billing
     * @return 
     */
    @PUT
   public BillingInformationDTO putBilling () {
       
       return null;
   }
   
   
   /**
    * retorna la tarjeta asociadas al billing con el id proporcionado
    * @param usuarioId id del usuario
    * @return 
    */
   @GET
   @Path("/tarjetasDeCredito")
   public Class<TarjetaCreditoResource> getTarjetaCreditoResource (@PathParam("usuariosId") Long usuariosId) {
   
   return TarjetaCreditoResource.class;
   }
   
}
