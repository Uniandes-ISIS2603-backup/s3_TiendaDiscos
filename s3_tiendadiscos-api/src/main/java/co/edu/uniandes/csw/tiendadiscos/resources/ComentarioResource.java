/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendadiscos.resources;

import co.edu.uniandes.csw.tiendadiscos.dtos.ComentarioDTO;
import co.edu.uniandes.csw.tiendadiscos.ejb.ComentarioLogic;
import co.edu.uniandes.csw.tiendadiscos.entities.ComentarioEntity;
import co.edu.uniandes.csw.tiendadiscos.exceptions.BusinessLogicException;
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
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class ComentarioResource {
    private static final Logger LOGGER = Logger.getLogger(ComentarioResource.class.getName());
    
    @Inject
    private ComentarioLogic logic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.
    
    /**
     * 
     * @param usuarioId
     * @param comentario
     * @return 
     */
    @POST
    public ComentarioDTO createComentario(@PathParam("usuarioId") Long usuarioId,ComentarioDTO comentario) throws BusinessLogicException 
    {
        ComentarioDTO nuevo = new ComentarioDTO(logic.createComentario(usuarioId, comentario.toEntity()));
        return nuevo;
    }
    
    /**
     * 
     * @param comentarioId
     * @return
     * @throws WebApplicationException 
     */
    @GET
    @Path("{comentariosId: \\d+}")
    public ComentarioDTO getComentario(@PathParam("usuarioId") Long usuarioId,@PathParam("comentariosId") Long comentariosId) 
    {
        ComentarioEntity nuevo = logic.getComentario(comentariosId, usuarioId);
        ComentarioDTO resp = new ComentarioDTO(nuevo);
        return null;
    }
    
    @GET
    public List<ComentarioDTO> getComentarios(@PathParam("usuarioId") Long usuarioId)
    {
        List<ComentarioDTO> resp = new ArrayList<ComentarioDTO>();
        List<ComentarioEntity> temp = new ArrayList<ComentarioEntity>();
        temp = logic.getComentarios(usuarioId);
        for(ComentarioEntity com : temp)
            resp.add(new ComentarioDTO(com));        
        return resp;
    }
    
    /**
     * 
     * @param comentario
     * @return 
    */
    @PUT
    @Path("{comentarioId: \\d+}")
    public ComentarioDTO putComentario(@PathParam("usuarioId") Long usuarioId,@PathParam("comentarioId") Long comentarioId, ComentarioDTO comentario) throws BusinessLogicException
    {
        if(!comentario.getId().equals(comentarioId))
            throw new BusinessLogicException("Los id no coinciden");
        ComentarioEntity nuevo = logic.getComentario(comentarioId, usuarioId);
        if(nuevo == null)
            throw new BusinessLogicException("No existe la asociación entre el usuario y el comentario");
        return new ComentarioDTO(logic.updateComentario(usuarioId, nuevo));
    }

    /**
     * 
     */
    @DELETE
    @Path("{comentarioId: \\d+}")
    public void deleteComentario(@PathParam("usuarioId") Long usuarioId,@PathParam("comentarioId") Long comentarioId) throws BusinessLogicException
    {
        ComentarioEntity nuevo = logic.getComentario(comentarioId, usuarioId);
        if(nuevo == null)
            throw new BusinessLogicException("No existe la asociación entre el usuario y el comentario");
        
        logic.deleteComentario(usuarioId, comentarioId);
    }
}
