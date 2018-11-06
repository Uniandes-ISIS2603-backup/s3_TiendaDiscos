/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendadiscos.resources;

import co.edu.uniandes.csw.tiendadiscos.dtos.CancionDTO;
import co.edu.uniandes.csw.tiendadiscos.ejb.CancionLogic;
import co.edu.uniandes.csw.tiendadiscos.entities.CancionEntity;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.websocket.server.PathParam;
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
    
    @Inject
    private CancionLogic cancionLogic;
    
    @POST
    public CancionDTO createCancion(CancionDTO cancion){
        CancionDTO nuevaCancion = new CancionDTO(cancionLogic.createCancion(cancion.toEntity()));
        return nuevaCancion;
    }
    
    @GET
    public List<CancionDTO> getCanciones()
    {
        List<CancionDTO> canciones= listEntity2DetailDTO(cancionLogic.getCanciones());
        return canciones;
    }
    
    @GET
    @Path("{cancionesId: \\d+}")
    public CancionDTO getCancion(@PathParam("cancionesId") Long cancionesId)
    {
       CancionDTO cancion = new CancionDTO(cancionLogic.getCancion(cancionesId));
       return cancion;       
        
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
