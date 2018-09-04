/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendadiscos.resources;

import co.edu.uniandes.csw.tiendadiscos.dtos.BillingInformationDTO;
import co.edu.uniandes.csw.tiendadiscos.dtos.UsuarioDTO;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 *
 * @author Kevin Blanco
 */
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class BillingInformationResource {
    
    @GET
    public BillingInformationDTO getBilling(@PathParam("usuariosId") Long booksId){
        
        return new BillingInformationDTO();
    }
    
    @POST 
    public BillingInformationDTO createBilling (BillingInformationDTO billig) {
        return billig;
    }
    
    @PUT
    public BillingInformationDTO updateBilling (BillingInformationDTO billing) {
    
    return billing ;
    }
    
    @DELETE 
    public void deleteBilling (){
    
    }
    
      @Path("/tarjetasDeCredito")
    public Class<TarjetaCreditoResource> getTarjetaResource (@PathParam("usuariosId") Long booksId) {
        
        return TarjetaCreditoResource.class;
}
    
}
