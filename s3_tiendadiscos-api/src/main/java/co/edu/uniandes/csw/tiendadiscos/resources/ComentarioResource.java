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
    private ComentarioLogic logic; // Variable para acceder a la l??gica de la aplicaci??n. Es una inyecci??n de dependencias.
    
    /**
     * 
     * @param usuarioId
     * @param comentario
     * @return 
     */
    @POST
    public ComentarioDTO createComentario(@PathParam("usuariosId") Long usuariosId,ComentarioDTO comentario) throws BusinessLogicException 
    {
        ComentarioDTO nuevo = new ComentarioDTO(logic.createComentario(usuariosId, comentario.toEntity()));
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
    public ComentarioDTO getComentario(@PathParam("usuariosId") Long usuariosId,@PathParam("comentariosId") Long comentariosId) 
    {
        ComentarioEntity nuevo = logic.getComentario(comentariosId, usuariosId);
        ComentarioDTO resp = new ComentarioDTO(nuevo);
        return null;
    }
    
    @GET
    public List<ComentarioDTO> getComentarios(@PathParam("usuariosId") Long usuariosId)
    {
        List<ComentarioDTO> resp = new ArrayList<ComentarioDTO>();
        List<ComentarioEntity> temp = new ArrayList<ComentarioEntity>();
        temp = logic.getComentarios(usuariosId);
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
    public ComentarioDTO putComentario(@PathParam("usuariosId") Long usuariosId,@PathParam("comentarioId") Long comentarioId, ComentarioDTO comentario) throws BusinessLogicException
    {
        if(!comentario.getId().equals(comentarioId))
            throw new BusinessLogicException("Los id no coinciden");
        ComentarioEntity nuevo = logic.getComentario(comentarioId, usuariosId);
        if(nuevo == null)
            throw new BusinessLogicException("No existe la asociaci??n entre el usuario y el comentario");
        return new ComentarioDTO(logic.updateComentario(usuariosId, nuevo));
    }

    /**
     * 
     */
    @DELETE
    @Path("{comentarioId: \\d+}")
    public void deleteComentario(@PathParam("usuariosId") Long usuariosId,@PathParam("comentarioId") Long comentarioId) throws BusinessLogicException
    {
        ComentarioEntity nuevo = logic.getComentario(comentarioId, usuariosId);
        if(nuevo == null)
            throw new BusinessLogicException("No existe la asociaci??n entre el usuario y el comentario");
        
        logic.deleteComentario(usuariosId, comentarioId);
    }
}
