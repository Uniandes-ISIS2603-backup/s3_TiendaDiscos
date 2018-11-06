/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendadiscos.resources;

import co.edu.uniandes.csw.tiendadiscos.dtos.ViniloDTO;
import co.edu.uniandes.csw.tiendadiscos.ejb.ViniloLogic;
import co.edu.uniandes.csw.tiendadiscos.entities.ViniloEntity;
import co.edu.uniandes.csw.tiendadiscos.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.ws.rs.*;

/**
 *
 * @author Andrés Hernández
 */
@Produces("application/json")
@Consumes("application/json")
public class ViniloUsuarioResource 
{
    private static final Logger LOGGER = Logger.getLogger(ViniloUsuarioResource.class.getName());
    
    @Inject
    private ViniloLogic logic; //Variable para acceder a la lógica de la aplicación
    
    @POST
    @Path("{usuariosId: \\d+}")
    public ViniloDTO createViniloUsuario(@PathParam("usuariosId") Long usuariosId, ViniloDTO vinilo) throws BusinessLogicException
    {
        ViniloDTO nuevo = new ViniloDTO(logic.createViniloUsuario(usuariosId, vinilo.toEntity()));
        return nuevo;
    }
    
    /**
     * 
     * @param usuarioId
     * @return 
     */
    @GET
    public List<ViniloDTO> getVinilosByUsuario(@PathParam("usuariosId") Long usuarioId)
    {
        List<ViniloDTO> resp = new ArrayList<ViniloDTO>();
        List<ViniloEntity> list = logic.getVinilosByUsuario(usuarioId);
        for(ViniloEntity entity : list)
            resp.add(new ViniloDTO(entity));
        return resp;
    }
    @PUT
    @Path("{usuariosId: \\d+}")
    public ViniloDTO putVinilo()       
    {
        return null;
    }
    
    @DELETE
    @Path("{usuariosId: \\d+}")
    public void deleteVinilo(@PathParam("usuariosId") Long usuarioId, Long viniloId)
    {
        
    }
}
