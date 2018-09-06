/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendadiscos.resources;

import co.edu.uniandes.csw.tiendadiscos.dtos.ComentarioDTO;
import java.util.ArrayList;
import java.util.List;
//import co.edu.uniandes.csw.tiendadiscos.ejb.ComentarioLogic;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
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
import javax.ws.rs.*;

/**
 *
 * @author Sebastian Martinez
 */
@Path("comentarios")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class ComentarioResource {
    private static final Logger LOGGER = Logger.getLogger(ComentarioResource.class.getName());
    
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
    @Path("{comentariosId: \\d+}")
    public ComentarioDTO getComentario(@PathParam("comentariosId") Long comentariosId) 
    {
        return new ComentarioDTO();
    }
    
    @GET
    public List<ComentarioDTO> getComentarios()
    {
        return new ArrayList<>();
    }
    
    /**
     * 
     * @param comentario
     * @return 
    *//*
    @PUT
    @Path("{comentarioId: \\d+}")
    public ComentarioDTO putComentario(@PathParam("comentarioId") Long comentarioId, ComentarioDTO comentario)
    {
        return comentario;
    }
*/
    /**
     * 
     */
    @DELETE
    @Path("{comentarioId: \\d+}")
    public void deleteComentario(@PathParam("comentarioId") Long comentariosId)
    {
        
    }
}
