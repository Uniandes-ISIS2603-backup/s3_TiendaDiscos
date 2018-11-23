/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendadiscos.resources;

import co.edu.uniandes.csw.tiendadiscos.dtos.ComentarioDTO;
import co.edu.uniandes.csw.tiendadiscos.ejb.CancionLogic;
import co.edu.uniandes.csw.tiendadiscos.ejb.ComentarioLogic;
import co.edu.uniandes.csw.tiendadiscos.ejb.UsuarioLogic;
import co.edu.uniandes.csw.tiendadiscos.entities.ComentarioEntity;
import co.edu.uniandes.csw.tiendadiscos.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;

/**
 *
 * @author Andrés Hernández
 */
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class ComentarioCancionResource {

    private static final Logger LOGGER = Logger.getLogger(ComentarioCancionResource.class.getName());

    @Inject
    private ComentarioLogic logic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    @Inject
    private CancionLogic cancionLogic;

    @Inject
    private UsuarioLogic usuarioLogic;

    @POST
    @Path("{usuariosId: \\d+}")
    public ComentarioDTO createComentarioCancion(@PathParam("vinilosId") Long viniloId, @PathParam("cancionesId") Long cancionesId, @PathParam("usuariosId") Long usuariosId, ComentarioDTO comentario) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "ComentarioCancionResource createComentarioCancion: input: cancionesId {0} , usuariosId {1} , comentario {2}", new Object[]{cancionesId, usuariosId, comentario});
    
        ComentarioDTO nuevo = new ComentarioDTO(logic.createComentarioCancion(viniloId, cancionesId, usuariosId, comentario.toEntity()));
        LOGGER.log(Level.INFO, "ComentarioCancionResource creteComentarioCancion: output: comentario nuevo {0}", nuevo);
        return nuevo;
    }

    @GET
    public List<ComentarioDTO> getComentarios(@PathParam("cancionesId") Long usuariosId) {
        List<ComentarioDTO> resp = new ArrayList<>();
        List<ComentarioEntity> temp = logic.getComentariosToCancion(usuariosId);
        for (ComentarioEntity com : temp) {
            resp.add(new ComentarioDTO(com));
        }
        return resp;
    }

    @DELETE
    @Path("{usuariosId: \\d+}")
    public void deleteComentario(@PathParam("usuariosId") Long usuariosId, @PathParam("comentarioId") Long comentarioId) throws BusinessLogicException {
        ComentarioEntity nuevo = logic.getComentario(comentarioId);
        if (nuevo == null) {
            throw new BusinessLogicException("No existe la asociación entre el usuario y el comentario");
        }
        logic.deleteComentario(comentarioId);
    }
}
