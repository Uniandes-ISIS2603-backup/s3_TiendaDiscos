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
import javax.ws.rs.*;

/**
 *
 * @author Sebastian Martinez
 */

@Produces("application/json")
@Consumes("application/json")
public class ComentarioCancionResource {
    private static final Logger LOGGER = Logger.getLogger(ComentarioCancionResource.class.getName());
    
    @Inject
    private ComentarioLogic logic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.
    
    /**
     * 
     * @param cancionId
     * @param comentario
     * @param usuariosId
     * @return
     * @throws BusinessLogicException 
     */
    @POST
    @Path("{cancionesId: \\d+}")
    public ComentarioDTO createComentarioUsuario(@PathParam("cancionesId") Long cancionId,ComentarioDTO comentario, @PathParam("usuariosId") Long usuariosId ) throws BusinessLogicException 
    {
        ComentarioDTO nuevo = new ComentarioDTO(logic.createComentarioCancion(cancionId, usuariosId, comentario.toEntity()));
        return nuevo;
    }
    
    
    
    @GET
    public List<ComentarioDTO> getComentarios(@PathParam("cancionesId") Long usuariosId)
    {
        List<ComentarioDTO> resp = new ArrayList<>();
        List<ComentarioEntity> temp = logic.getComentariosToUsuarios(usuariosId);
        for(ComentarioEntity com : temp)
            resp.add(new ComentarioDTO(com));        
        return resp;
    }
    
    

    /**
     * 
     */
    @DELETE
    @Path("{usuariosId: \\d+}")
    public void deleteComentario(@PathParam("usuariosId") Long usuariosId,@PathParam("comentarioId") Long comentarioId) throws BusinessLogicException
    {
        // ComentarioEntity nuevo = logic.getComentario(comentarioId, usuariosId);
        // if(nuevo == null)
        //     throw new BusinessLogicException("No existe la asociación entre el usuario y el comentario");
        
        // logic.deleteComentario(usuariosId, comentarioId);
    }
}
