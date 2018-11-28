/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendadiscos.resources;

import co.edu.uniandes.csw.tiendadiscos.dtos.ComentarioDTO;
import co.edu.uniandes.csw.tiendadiscos.ejb.ComentarioLogic;
import co.edu.uniandes.csw.tiendadiscos.ejb.UsuarioLogic;
import co.edu.uniandes.csw.tiendadiscos.ejb.ViniloLogic;
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
 * @author Sebastian Martinez
 */
@Produces("application/json")
@Consumes("application/json")
public class ComentarioViniloResource {

    private static final Logger LOGGER = Logger.getLogger(ComentarioViniloResource.class.getName());
    
    @Inject
    private ComentarioLogic logic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.
    
    @Inject
    private ViniloLogic viniloLogic;
    
    @Inject
    private UsuarioLogic usuarioLogic;
    /**
     * 
     * @param vinilosId
     * @param usuarioId
     * @param comentario
     * @return 
     * @throws BusinessLogicException 
     */
    @POST
    @Path("{usuariosId: \\d+}")
    public ComentarioDTO createComentarioVinilo(@PathParam("vinilosId") Long vinilosId,ComentarioDTO comentario,@PathParam("usuariosId") Long usuarioId) throws BusinessLogicException 
    {       
        LOGGER.log(Level.INFO , "ComentarioViniloResource createComentarioVinilo: input: vinilosId: {0} , comentario: {1}, usuariosId{2}", new Object[]{vinilosId, comentario, usuarioId});
        if(viniloLogic.getVinilo(vinilosId) == null)
            throw new WebApplicationException("Vinilo con id: " + vinilosId + " no existe", 404);
        if(usuarioLogic.getUsuario(usuarioId) == null)
            throw new WebApplicationException("Usuario con id: " + usuarioId + " no existe", 404);
        ComentarioDTO nuevo = new ComentarioDTO(logic.createComentarioVinilo(vinilosId, usuarioId, comentario.toEntity()));
        LOGGER.log(Level.INFO, "ComentarioViniloResource createComentarioVinilo: output: {0}", nuevo);
        return nuevo;
    }
    
    @GET
    public List<ComentarioDTO> getComentarios(@PathParam("vinilosId") Long vinilosId)
    {
        List<ComentarioDTO> resp = new ArrayList<>();
        List<ComentarioEntity> temp = logic.getComentariosToVinilo(vinilosId);
        for(ComentarioEntity com : temp)
            resp.add(new ComentarioDTO(com));        
        return resp;
    }
}
