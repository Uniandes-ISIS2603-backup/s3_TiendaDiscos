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
public class ComentarioTransaccionResource {
    private static final Logger LOGGER = Logger.getLogger(ComentarioTransaccionResource.class.getName());
    
    @Inject
    private ComentarioLogic logic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.
    
    /**
     * 
     * @param usuarioId
     * @param comentario
     * @return 
     */
    @POST
    @Path("{transaccionesId: \\d+}")
    public ComentarioDTO createComentarioUsuario(@PathParam("transaccionesId") Long transaccionId,ComentarioDTO comentario, @PathParam("usuarioId") Long usuarioId ) throws BusinessLogicException 
    {
        ComentarioDTO nuevo = new ComentarioDTO(logic.createComentarioTransaccion(transaccionId, usuarioId, comentario.toEntity()));
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
