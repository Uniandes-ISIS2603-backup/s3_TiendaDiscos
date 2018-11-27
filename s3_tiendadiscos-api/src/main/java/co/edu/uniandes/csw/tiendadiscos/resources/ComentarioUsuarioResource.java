/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendadiscos.resources;

import co.edu.uniandes.csw.tiendadiscos.dtos.ComentarioDTO;
import co.edu.uniandes.csw.tiendadiscos.ejb.ComentarioLogic;
import co.edu.uniandes.csw.tiendadiscos.ejb.UsuarioLogic;
import co.edu.uniandes.csw.tiendadiscos.entities.ComentarioEntity;
import co.edu.uniandes.csw.tiendadiscos.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.ws.rs.*;

/**
 *
 * @author Andrés Hernández
 */
@Produces("application/json")
@Consumes("application/json")
public class ComentarioUsuarioResource 
{
    private static final Logger LOGGER = Logger.getLogger(ComentarioUsuarioResource.class.getName());
    
    @Inject
    private ComentarioLogic logic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.
    
    @Inject
    private UsuarioLogic usuarioLogic;
    
    @POST
    @Path("{usuarios2Id: \\d+}")
    public ComentarioDTO createComentarioUsuario(@PathParam("usuariosId") Long usuariosId, @PathParam("usuarios2Id") Long usuarios2Id, ComentarioDTO comentario) throws BusinessLogicException 
    {
        LOGGER.log(Level.INFO , "ComentarioUsuarioResource createComentarioUsuario: input: usuariosId1: {0} , usuarioId2: {1} , comentario {2}", new Object[]{usuariosId, usuarios2Id, comentario});
        if(usuarioLogic.getUsuario(usuarios2Id) == null)
            throw new WebApplicationException("El usuario con id: " + usuarios2Id + " destino no existe.", 404);
        if(usuarioLogic.getUsuario(usuariosId) == null)
            throw new WebApplicationException("El usuario con id: "+ usuariosId + " que comento no existe." , 404);
        ComentarioDTO nuevo = new ComentarioDTO(logic.createComentarioUsuario(usuariosId, usuarios2Id, comentario.toEntity()));
        LOGGER.log(Level.INFO, "ComentarioUsuarioResource createComentarioUsuario: output: Comentario {0}", comentario);
        return nuevo;
    }
    
    @GET
    public List<ComentarioDTO> getComentarios(@PathParam("usuariosId") Long usuariosId)
    {
        List<ComentarioDTO> resp = new ArrayList<>();
        List<ComentarioEntity> temp = logic.getComentariosToUsuarios(usuariosId);
        for(ComentarioEntity com : temp)
            resp.add(new ComentarioDTO(com));        
        return resp;
    }
        
    @PUT
    @Path("{comentarioId: \\d+}")
    public ComentarioDTO putComentario(@PathParam("usuariosId") Long usuariosId,@PathParam("comentarioId") Long comentarioId, ComentarioDTO comentario) throws BusinessLogicException
    {
         if(!comentario.getId().equals(comentarioId))
             throw new BusinessLogicException("Los id no coinciden");
         ComentarioEntity nuevo = logic.getComentario(comentarioId);
         if(nuevo == null)
             throw new BusinessLogicException("No existe la asociación entre el usuario y el comentario");
         if(usuarioLogic.getUsuario(usuariosId) == null)
             throw new WebApplicationException("El recurso /usuarios/"+ usuariosId + " no existe.", 404);
         if(logic.getComentario(comentarioId) == null)
             throw new WebApplicationException("El recurso /comentarios/"+ comentarioId + " no existe.", 404);
        return new ComentarioDTO(logic.updateComentario(usuariosId, nuevo));        
    }
}