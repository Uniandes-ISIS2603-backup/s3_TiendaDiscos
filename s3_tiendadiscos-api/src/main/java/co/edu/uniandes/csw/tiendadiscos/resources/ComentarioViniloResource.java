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
    
    /**
     * 
     * @param usuarioId
     * @param comentario
     * @return 
     */
    @POST
    @Path("{usuariosId: \\d+}")
    public ComentarioDTO createComentarioVinilo(@PathParam("vinilosId") Long vinilosId,ComentarioDTO comentario,@PathParam("usuariosId") Long usuarioId) throws BusinessLogicException 
    {       
        ComentarioDTO nuevo = new ComentarioDTO(logic.createComentarioVinilo(vinilosId, usuarioId, comentario.toEntity()));
        return nuevo;
    }
 
    
    @GET
    public List<ComentarioDTO> getComentarios(@PathParam("vinilosId") Long vinilosId)
    {
        List<ComentarioDTO> resp = new ArrayList<ComentarioDTO>();
        List<ComentarioEntity> temp = new ArrayList<ComentarioEntity>();
        temp = logic.getComentariosToVinilo(vinilosId);
        for(ComentarioEntity com : temp)
            resp.add(new ComentarioDTO(com));        
        return resp;
    }
}
