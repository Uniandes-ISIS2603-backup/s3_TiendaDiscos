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
public class ComentarioViniloResource {
    private static final Logger LOGGER = Logger.getLogger(ComentarioViniloResource.class.getName());
    
    @Inject
    private ComentarioLogic logic; // Variable para acceder a la l??gica de la aplicaci??n. Es una inyecci??n de dependencias.
    
    /**
     * 
     * @param usuarioId
     * @param comentario
     * @return 
     */
    @POST
    @Path("{usuariosId: \\d+}")
    public ComentarioDTO createComentarioUsuario(@PathParam("vinilosId") Long vinilosId,ComentarioDTO comentario,@PathParam("usuariosId") Long usuarioId) throws BusinessLogicException 
    {
        ComentarioDTO nuevo = new ComentarioDTO(logic.createComentarioVinilo(vinilosId, usuarioId, comentario.toEntity()));
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
<<<<<<< HEAD:s3_tiendadiscos-api/src/main/java/co/edu/uniandes/csw/tiendadiscos/resources/ComentarioResource.java
        if(!comentario.getId().equals(comentarioId))
            throw new BusinessLogicException("Los id no coinciden");
        ComentarioEntity nuevo = logic.getComentario(comentarioId, usuariosId);
        if(nuevo == null)
            throw new BusinessLogicException("No existe la asociaci??n entre el usuario y el comentario");
        return new ComentarioDTO(logic.updateComentario(usuariosId, nuevo));
=======
        // if(!comentario.getId().equals(comentarioId))
        //     throw new BusinessLogicException("Los id no coinciden");
        // ComentarioEntity nuevo = logic.getComentario(comentarioId, usuariosId);
        // if(nuevo == null)
        //     throw new BusinessLogicException("No existe la asociación entre el usuario y el comentario");
        // return new ComentarioDTO(logic.updateComentario(usuariosId, nuevo));
        return null;
>>>>>>> e0c1817de2e002340d396768891f6eb2b3ee4590:s3_tiendadiscos-api/src/main/java/co/edu/uniandes/csw/tiendadiscos/resources/ComentarioViniloResource.java
    }

    /**
     * 
     */
    @DELETE
    @Path("{comentarioId: \\d+}")
    public void deleteComentario(@PathParam("usuariosId") Long usuariosId,@PathParam("comentarioId") Long comentarioId) throws BusinessLogicException
    {
<<<<<<< HEAD:s3_tiendadiscos-api/src/main/java/co/edu/uniandes/csw/tiendadiscos/resources/ComentarioResource.java
        ComentarioEntity nuevo = logic.getComentario(comentarioId, usuariosId);
        if(nuevo == null)
            throw new BusinessLogicException("No existe la asociaci??n entre el usuario y el comentario");
=======
        // ComentarioEntity nuevo = logic.getComentario(comentarioId, usuariosId);
        // if(nuevo == null)
        //     throw new BusinessLogicException("No existe la asociación entre el usuario y el comentario");
>>>>>>> e0c1817de2e002340d396768891f6eb2b3ee4590:s3_tiendadiscos-api/src/main/java/co/edu/uniandes/csw/tiendadiscos/resources/ComentarioViniloResource.java
        
        // logic.deleteComentario(usuariosId, comentarioId);
    }
}
