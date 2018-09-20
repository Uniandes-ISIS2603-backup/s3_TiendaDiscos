/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendadiscos.resources;

import co.edu.uniandes.csw.tiendadiscos.dtos.TransaccionDTO;
import co.edu.uniandes.csw.tiendadiscos.ejb.TransaccionLogic;
import co.edu.uniandes.csw.tiendadiscos.entities.TransaccionEntity;
import co.edu.uniandes.csw.tiendadiscos.exceptions.BusinessLogicException;
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
    

    @Inject
    TransaccionLogic logic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    @POST
    public TransaccionDTO createTransaccion(TransaccionDTO transaccion) throws BusinessLogicException{
        TransaccionDTO nuevoCarritoDeComprasDTO = new  TransaccionDTO(logic.createCarritoDeCompras(transaccion.toEntity()));
        return nuevoCarritoDeComprasDTO;
    }
    
    @PUT
    @Path("{transaccionesId: \\d+}")
    public TransaccionDTO updateTransaccion(@PathParam("transaccionesId") Long transaccionesId,TransaccionDTO transaccion){
        
        TransaccionEntity entity = logic.get(transaccionesId);
        
        TransaccionDTO transaccion2 = new TransaccionDTO(logic.update(transaccion.toEntity(), transaccionesId));
        return transaccion2;
    }
    
    @GET
    @Path("{transaccionesId: \\d+}")
    public TransaccionDTO getTransaccion(@PathParam("transaccionesId") Long transaccionesId){
        TransaccionEntity entity = logic.get(transaccionesId);
        //if(entity==null)
        //   throw new WebApplicationException("El recurso /transacciones no existe/"+ transaccionesId+ " .");
        TransaccionDTO nuevo = new TransaccionDTO(entity);
        return nuevo;
    }
    
    @GET
    public List<TransaccionDTO> getTransacciones(){
        return null;
    }
    
    @DELETE
    @Path("{transaccionesId: \\d+}")
    public void deleteTransaccion(@PathParam("transaccionesId") Long transaccionesId) throws BusinessLogicException{
        logic.delete(transaccionesId);
        
    }
    @Path("{transaccionesId: \\d+}/envio")
    public Class<TransaccionEnvioResource> getTransaccionEnvioResource(@PathParam("transaccionesId") Long transaccionId){
        
        return TransaccionEnvioResource.class;
    
    }
    
}
