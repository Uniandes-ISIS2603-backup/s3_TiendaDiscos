/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendadiscos.resources;

import co.edu.uniandes.csw.tiendadiscos.dtos.ComentarioDTO;
import co.edu.uniandes.csw.tiendadiscos.ejb.ComentarioLogic;
import co.edu.uniandes.csw.tiendadiscos.ejb.TransaccionLogic;
import co.edu.uniandes.csw.tiendadiscos.ejb.UsuarioLogic;
import co.edu.uniandes.csw.tiendadiscos.entities.ComentarioEntity;
import co.edu.uniandes.csw.tiendadiscos.entities.TransaccionEntity;
import co.edu.uniandes.csw.tiendadiscos.entities.UsuarioEntity;
import co.edu.uniandes.csw.tiendadiscos.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.ws.rs.*;

/**
 *
 * @author Sebastian Martinez
 */
@Produces("application/json")
@Consumes("application/json")
public class ComentarioTransaccionResource 
{
    private static final Logger LOGGER = Logger.getLogger(ComentarioTransaccionResource.class.getName());
    
    @Inject
    private ComentarioLogic logic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.
    
    @Inject
    private TransaccionLogic transaccionLogic;
    
    @Inject
    private UsuarioLogic usuarioLogic;
    
    @POST
    @Path("{transaccionesId: \\d+}")
    public ComentarioDTO createComentarioUsuario(@PathParam("transaccionesId") Long transaccionId,ComentarioDTO comentario, @PathParam("usuarioId") Long usuarioId ) throws BusinessLogicException 
    {
        TransaccionEntity transaccionEntity = transaccionLogic.get(transaccionId);
        if(transaccionEntity == null)
            throw new WebApplicationException("Transaccion con el id: " + transaccionId + " no existe.", 404);
        UsuarioEntity usuarioEntity = usuarioLogic.getUsuario(usuarioId);
        if(usuarioEntity == null)
            throw new WebApplicationException("Usuario con el id: " + usuarioId + " no existe.", 404);
        ComentarioDTO nuevo = new ComentarioDTO(logic.createComentarioTransaccion(transaccionId, usuarioId, comentario.toEntity()));
        return nuevo;
    }

    
    @GET
    public List<ComentarioDTO> getComentarios(@PathParam("usuariosId") Long usuariosId)
    {
        List<ComentarioDTO> resp = new ArrayList<ComentarioDTO>();
        List<ComentarioEntity> temp = new ArrayList<ComentarioEntity>();
        temp = logic.getComentariosToUsuarios(usuariosId);
        for(ComentarioEntity com : temp)
            resp.add(new ComentarioDTO(com));        
        return resp;
    }
    

    @DELETE
    @Path("{comentarioId: \\d+}")
    public void deleteComentario(@PathParam("comentarioId") Long comentarioId) throws BusinessLogicException
    {
        ComentarioEntity nuevo = logic.getComentario(comentarioId);
        if(nuevo == null)
            throw new BusinessLogicException("No existe el comentario");
        logic.deleteComentario(comentarioId);
    }
}
