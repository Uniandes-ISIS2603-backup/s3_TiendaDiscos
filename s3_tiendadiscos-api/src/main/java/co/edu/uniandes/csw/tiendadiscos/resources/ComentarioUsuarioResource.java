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
import javax.inject.Inject;
import javax.ws.rs.*;

/**
 *
 * @author Sebastian Martinez
 */

@Produces("application/json")
@Consumes("application/json")
public class ComentarioUsuarioResource {
    private static final Logger LOGGER = Logger.getLogger(ComentarioUsuarioResource.class.getName());
    
    @Inject
    private ComentarioLogic logic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.
    
    /**
     * 
     * @param usuarioId
     * @param comentario
     * @return 
     */
    @POST
    @Path("{usuarios2Id: \\d+}")
    public ComentarioDTO createComentarioUsuario(@PathParam("usuariosId") Long usuariosId, @PathParam("usuarios2Id") Long usuarios2Id, ComentarioDTO comentario) throws BusinessLogicException 
    {
        ComentarioDTO nuevo = new ComentarioDTO(logic.createComentarioUsuario(usuariosId, usuarios2Id, comentario.toEntity()));
        return nuevo;
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
        // if(!comentario.getId().equals(comentarioId))
        //     throw new BusinessLogicException("Los id no coinciden");
        // ComentarioEntity nuevo = logic.getComentario(comentarioId, usuariosId);
        // if(nuevo == null)
        //     throw new BusinessLogicException("No existe la asociación entre el usuario y el comentario");
        // return new ComentarioDTO(logic.updateComentario(usuariosId, nuevo));
        return null;
    }

    /**
     * 
     */
    @DELETE
    @Path("{comentarioId: \\d+}")
    public void deleteComentario(@PathParam("usuariosId") Long usuariosId,@PathParam("comentarioId") Long comentarioId) throws BusinessLogicException
    {
        // ComentarioEntity nuevo = logic.getComentario(comentarioId, usuariosId);
        // if(nuevo == null)
        //     throw new BusinessLogicException("No existe la asociación entre el usuario y el comentario");
        
        // logic.deleteComentario(usuariosId, comentarioId);
       
    }
}
