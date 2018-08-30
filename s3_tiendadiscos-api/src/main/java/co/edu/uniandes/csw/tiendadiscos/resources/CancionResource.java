/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendadiscos.resources;

import co.edu.uniandes.csw.tiendadiscos.dtos.CancionDTO;
import java.util.logging.Logger;
import co.edu.uniandes.csw.tiendadiscos.entities.*;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.websocket.server.PathParam;
import javax.ws.rs.*;
/**
 *
 * @author Andrés Hernández
 */
public class CancionResource {
    
    private static final Logger LOGGER = Logger.getLogger(CancionResource.class.getName());
    
    @POST
    public CancionDTO createCancion(CancionDTO cancion)
    {
        return cancion;
    }
    
    @GET
    @Path("(cancionesId: \\d+)")
    public CancionDTO getCancion(@PathParam("cancionesId") Long cancionesId)
    {
        return null;
    }
    
    @PUT
    @Path("(cancionesId: \\d+)")
    public CancionDTO updateCancion(@PathParam("cancionesId") Long cancionesId)
    {
        return null;
    }
    
    @DELETE
    @Path("(cancionesId: \\d+)")
    public void deleteTransaccion(@PathParam("cancionesId") Long cancionesId)
    {
        
    }
}
