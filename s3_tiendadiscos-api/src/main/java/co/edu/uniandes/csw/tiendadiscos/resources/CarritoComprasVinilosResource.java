/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendadiscos.resources;

import co.edu.uniandes.csw.tiendadiscos.dtos.ViniloDTO;
import java.util.logging.Logger;
import javax.ws.rs.Consumes;
import javax.ws.rs.*;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author estudiante
 */
@Path("usuarios/usuariosId/carritosCompras/vinilos")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CarritoComprasVinilosResource {
    
    private static final Logger LOGGER = Logger.getLogger(CarritoComprasVinilosResource.class.getName());
    
    @POST
    public ViniloDTO addViniloCarritoCompras(@PathParam("vinilosId") Long vinilosId, ViniloDTO vinilo){
        return vinilo;
    }
    @GET
    public ViniloDTO getViniloCarritoCompras(@PathParam("vinilosId") Long vinilosId){
        return null;
    }
    
    @PUT
    @Path("{vinilosId: \\d+}")
    public ViniloDTO addViniloCarritoCompras(ViniloDTO vinilo){
        return vinilo;
    }
    @DELETE
    @Path("{vinilosId: \\d+}")
    public void deleteViniloCarritoCompras(ViniloDTO vinilo){
        
    }

   
    
   
   
   
    
   
   
    
    
}
