/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendadiscos.resources;

import co.edu.uniandes.csw.tiendadiscos.dtos.ViniloDTO;
import co.edu.uniandes.csw.tiendadiscos.dtos.ViniloDetailDTO;
import co.edu.uniandes.csw.tiendadiscos.ejb.ViniloLogic;
import co.edu.uniandes.csw.tiendadiscos.entities.ViniloEntity;
import co.edu.uniandes.csw.tiendadiscos.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;



/**
 *
 * @author Andrés Hernández
 */
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
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
    @Path("{usuariosId: \\d+}")
    public List<ViniloDetailDTO> getVinilosByUsuario(@PathParam("usuariosId") Long usuarioId)
    {
        List<ViniloDetailDTO> resp = new ArrayList<>();
        List<ViniloEntity> list = logic.getVinilosByUsuario(usuarioId);
        for(ViniloEntity entity : list)
            resp.add(new ViniloDetailDTO(entity));
        return resp;
    }
    /**
    @PUT
    @Path("{vinilosId: \\d+}")
    public ViniloDTO putVinilo()       
    {
        return null;
    }
    
    @DELETE
    public void deleteVinilo(@PathParam("usuariosId") Long usuarioId, Long viniloId)
    {
        
    }
    * **///
}
