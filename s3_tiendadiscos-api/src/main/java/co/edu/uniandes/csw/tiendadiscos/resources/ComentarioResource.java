/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendadiscos.resources;

import co.edu.uniandes.csw.tiendadiscos.dtos.ComentarioDTO;
//import co.edu.uniandes.csw.tiendadiscos.ejb.ComentarioLogic;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.websocket.server.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;

/**
 *
 * @author Sebastian Martinez
 */
@Path("usuarios/usuariosId/comentarios")
@Consumes("application/json")
@Produces("application/json")

public class ComentarioResource {
    private static final Logger LOGGER = Logger.getLogger(ComentarioResource.class.getName());
    
    @Inject
  //  private ComentarioLogic editorialLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.
    
    /**
     * 
     * @param comentario
     * @return 
     */
    @POST
    public ComentarioDTO createComentario(ComentarioDTO comentario) 
    {
        return comentario;
    }
    
    /**
     * 
     * @param comentarioId
     * @return
     * @throws WebApplicationException 
     */
    @GET
    @Path("(comentarioId: \\d+")
    public ComentarioDTO getComentario(@PathParam("comentarioId") Long comentarioId) throws WebApplicationException
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
     * @param comentario
     * @return 
     */
    @PUT
    @Path("(comentarioId: \\d+")
    public ComentarioDTO putComentario(ComentarioDTO comentario)
    {
        return comentario;
    }
    
    /**
     * 
     */
    @DELETE
    public void deleteComentario()
    {
        
    }
}
