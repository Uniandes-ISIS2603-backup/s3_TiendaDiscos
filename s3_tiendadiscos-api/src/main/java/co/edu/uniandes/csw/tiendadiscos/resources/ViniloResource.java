 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendadiscos.resources;

import co.edu.uniandes.csw.tiendadiscos.dtos.ViniloDTO;
import co.edu.uniandes.csw.tiendadiscos.ejb.ViniloLogic;
import co.edu.uniandes.csw.tiendadiscos.entities.*;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.websocket.server.PathParam;
import javax.ws.rs.*;

/**
 *
 * @author Andrés Hernández
 */
@Path("vinilos")
@Produces("aplication/json")
@Consumes("aplication/json")
public class ViniloResource 
{
    private static final Logger LOGGER = Logger.getLogger(ViniloResource.class.getName());
    
    @Inject
    private ViniloLogic editorialLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.
    
    /**
     * 
     * @param vinilo
     * @return 
     */
    @POST
    public ViniloDTO createVinilo(ViniloDTO vinilo) 
    {
        //LOGGER.log(level.INFO, "ViniloResource createVinilo: input: (0)" , vinilo.toString());
        return vinilo;
    }
    
    /**
     * 
     * @param viniloId
     * @return
     * @throws WebApplicationException 
     */
    @GET
    @Path("(vinilosId: \\d+")
    public ViniloDTO getVinilo(@PathParam("VinilosId") Long viniloId) throws WebApplicationException
    {
        /*ViniloEntity viniloEntity = ViniloLogic.getVinilo(viniloId);
        if(viniloEntity == null)
        {
            throw new WebApplicationException("El recurso /vinilo/" + viniloId + " no existe." , 404);
        }
        ViniloDTO detailDTO = new ViniloDTO(viniloEntity);
        
        return detailDTO;*/
        return null;
    }
    
    /**
     * 
     * @param vinilo
     * @return 
     */
    @PUT
    @Path("(vinilosId: \\d+")
    public ViniloDTO putVinilo(ViniloDTO vinilo)
    {
        return vinilo;
    }
    
    /**
     * 
     */
    @DELETE
    public void deleteVinilo()
    {
        
    }
    
}