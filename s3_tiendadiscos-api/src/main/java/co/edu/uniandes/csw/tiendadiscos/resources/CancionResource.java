/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendadiscos.resources;

import co.edu.uniandes.csw.tiendadiscos.dtos.CancionDTO;
import java.util.List;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
/**
 *
 * @author Andrés Hernández
 */
@Path("canciones")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@RequestScoped
public class CancionResource {
    
    private static final Logger LOGGER = Logger.getLogger(CancionResource.class.getName());
    
    @POST
    public CancionDTO createCancion(CancionDTO cancion)
    {
        return cancion;
    }
    
    @GET
    public List<CancionDTO> getCanciones()
    {
        return null;
    }
    
    @GET
    @Path("{cancionesId: \\d+}")
    public CancionDTO getCancion(@PathParam("cancionesId") Long cancionesId)
    {
        return null;
    }
    
    @PUT
    @Path("{cancionesId: \\d+}")
    public CancionDTO updateCancion(@PathParam("cancionesId") Long cancionesId, CancionDTO cancion)
    {
        return cancion;
    }
    
    @DELETE
    @Path("{cancionesId: \\d+}")
    public void deleteTransaccion(@PathParam("cancionesId") Long cancionesId)
    {
        
    }
}