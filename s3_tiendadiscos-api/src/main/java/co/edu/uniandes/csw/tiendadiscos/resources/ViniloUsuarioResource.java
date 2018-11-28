/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendadiscos.resources;

import co.edu.uniandes.csw.tiendadiscos.dtos.ViniloDTO;
import co.edu.uniandes.csw.tiendadiscos.dtos.ViniloDetailDTO;
import co.edu.uniandes.csw.tiendadiscos.ejb.UsuarioLogic;
import co.edu.uniandes.csw.tiendadiscos.ejb.ViniloLogic;
import co.edu.uniandes.csw.tiendadiscos.entities.ViniloEntity;
import co.edu.uniandes.csw.tiendadiscos.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;

/**
 *
 * @author Andrés Hernández
 */
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class ViniloUsuarioResource {

    private static final Logger LOGGER = Logger.getLogger(ViniloUsuarioResource.class.getName());

    @Inject
    private ViniloLogic logic; //Variable para acceder a la lógica de la aplicación
    
    @Inject
    private UsuarioLogic usuarioLogic;

    @POST
    @Path("{usuariosId: \\d+}")
    public ViniloDTO createViniloUsuario(@PathParam("usuariosId") Long usuariosId, ViniloDTO vinilo) throws BusinessLogicException 
    {
        if(usuarioLogic.getUsuario(usuariosId) == null)
            throw new WebApplicationException("El recurso /usuarios/"+ usuariosId + " no existe.", 404);
        LOGGER.log(Level.INFO, "ViniloResource createVinilo: input: {0}", vinilo);
        ViniloDTO nuevo = new ViniloDTO(logic.createViniloUsuario(usuariosId, vinilo.toEntity()));
        LOGGER.log(Level.INFO, "ViniloResource createVinilo: output: {0}", nuevo);
        return nuevo;
    }

    /**
     *
     * @param usuarioId
     * @return
     * @throws BusinessLogicException
     */
    @GET
    @Path("{usuariosId: \\d+}")
    public List<ViniloDetailDTO> getVinilosByUsuario(@PathParam("usuariosId") Long usuarioId) throws BusinessLogicException 
    {
        if(usuarioLogic.getUsuario(usuarioId)== null)
            throw new WebApplicationException("El recurso /usuarios/"+ usuarioId);
        List<ViniloDetailDTO> resp = new ArrayList<>();
        List<ViniloEntity> list = logic.getVinilosByUsuario(usuarioId);
        for (ViniloEntity entity : list) 
            resp.add(new ViniloDetailDTO(entity));
        return resp;
    }   
}
