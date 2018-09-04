/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendadiscos.resources;

import co.edu.uniandes.csw.tiendadiscos.dtos.CancionDTO;
import java.util.logging.Logger;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Andrés Hernández
 */
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CancionViniloResource {
    
    private static final Logger LOGGER = Logger.getLogger(CancionViniloResource.class.getName());
    
    @POST
    public CancionDTO addCancion(@PathParam("vinilosId") Long vinilosId, CancionDTO cancion )
    {
        return cancion;
    }
    
    @GET
    @Path("{cancionesId: \\d+}")
    public CancionDTO getCancionVinilo(@PathParam("cancionesId") Long cancionId)
    {
        return null;
    }
    
    @PUT
    @Path("{cancionesId: \\d+}")
    public CancionDTO updateCancionVinilo(CancionDTO cancion)
    {
        return cancion;
    }
    
    @DELETE
    @Path("{cancionesId: \\d+}")
    public void deleteCancionVinilo(CancionDTO cancion)
    {
        
    }
}