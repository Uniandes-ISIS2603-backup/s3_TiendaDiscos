/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendadiscos.resources;

import co.edu.uniandes.csw.tiendadiscos.dtos.TransaccionDTO;
import java.util.List;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import static javax.ws.rs.HttpMethod.POST;
import javax.ws.rs.*;

/**
 *
 * @author Laura Isabella Forero Camacho
 */
@Path("transacciones")
@Consumes("application/json")
@Produces("application/json")
@RequestScoped
public class TransaccionResource{
    private static final Logger LOGGER = Logger.getLogger(TransaccionResource.class.getName());
    

    //@Inject
    //EditorialLogic editorialLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    @POST
    public TransaccionDTO createTransaccion(TransaccionDTO transaccion){
        return transaccion;
    }
    
    @PUT
    @Path("{transaccionesId: \\d+}")
    public TransaccionDTO updateTransaccion(@PathParam("transaccionesId") Long transaccionesId,TransaccionDTO transaccion){
        
        return transaccion;
    }
    
    @GET
    @Path("{transaccionesId: \\d+}")
    public TransaccionDTO getTransaccion(@PathParam("transaccionesId") Long transaccionesId){
        
        return null;
    }
    
    @GET
    public List<TransaccionDTO> getTransacciones(){
        
        return null;
    }
    
    @DELETE
    @Path("{transaccionesId: \\d+}")
    public void deleteTransaccion(@PathParam("transaccionesId") Long transaccionesId){
        
        
    }
    @Path("{transaccionesId: \\d+}/envio")
    public Class<EnvioResource> getTransaccionEnvioResource(@PathParam("transaccionesId") Long transaccionId){
        
        return EnvioResource.class;
    
    }
    
}
