/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendadiscos.resources;

import co.edu.uniandes.csw.tiendadiscos.dtos.ViniloDTO;
import co.edu.uniandes.csw.tiendadiscos.dtos.ViniloDetailDTO;
import co.edu.uniandes.csw.tiendadiscos.ejb.ViniloLogic;
import co.edu.uniandes.csw.tiendadiscos.entities.*;
import co.edu.uniandes.csw.tiendadiscos.exceptions.BusinessLogicException;
import javax.enterprise.context.RequestScoped;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Andrés Hernández
 */
@Path("vinilos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RequestScoped
public class ViniloResource 
{
    private static final Logger LOGGER = Logger.getLogger(ViniloResource.class.getName());
    
    @Inject
    private ViniloLogic viniloLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.
    
    
    
    @POST
    public ViniloDTO createVinilo(ViniloDTO vinilo) throws BusinessLogicException
    {
        LOGGER.log(Level.INFO, "ViniloResource createVinilo: input: (0)" , vinilo);
        ViniloDTO viniloCreado =new ViniloDTO(viniloLogic.createVinilo(vinilo.toEntity()));
        LOGGER.log(Level.INFO, "ViniloResource createVinilo: output: {0}" , viniloCreado);        
        return viniloCreado;
    }
    
    /**
     * 
     * @param vinilosId
     * @return
     * @throws WebApplicationException 
     */
    @GET
    @Path("{vinilosId: \\d+}")
    public ViniloDetailDTO getVinilo(@PathParam("vinilosId") Long vinilosId)
    {
        LOGGER.log(Level.INFO, "ViniloResource getVinilo: input: {0}", vinilosId);
        ViniloEntity viniloEntity = viniloLogic.getVinilo(vinilosId);
        if(viniloEntity == null)
            throw new WebApplicationException("El recurso /vinilo/" + vinilosId + " no existe." , 404);
        ViniloDetailDTO detailDTO = new ViniloDetailDTO(viniloEntity);
        LOGGER.log(Level.INFO, "Vinilo Resource getVinilo: output: {0}", detailDTO);
        return detailDTO;
    }
    
    @GET
    public List<ViniloDetailDTO> getVinilos()
    {
        LOGGER.info("ViniloResource getVinilos: input: void");
        List<ViniloDetailDTO> vinilos = listEntity2DTO(viniloLogic.getVinilos());
        LOGGER.log(Level.INFO, "ViniloResource getVinilos: output: {0}", vinilos);
        return vinilos;
    }
    
    @PUT
    @Path("{vinilosId: \\d+}")
    public ViniloDetailDTO updateVinilo(@PathParam("vinilosId") Long vinilosId , ViniloDTO vinilo)
    {
        LOGGER.log(Level.INFO , "ViniloResource updateVinilo: input: vinilosId: {0} , vinilo: {1}", new Object[]{vinilosId, vinilo});
        vinilo.setId(vinilosId);
        if(viniloLogic.getVinilo(vinilosId)== null)
            throw new WebApplicationException("El recurso /vinilos/" + vinilosId + " no existe." , 404);
        ViniloDetailDTO detailDTO = new ViniloDetailDTO(viniloLogic.updateVinilo(vinilosId, vinilo.toEntity()));
        LOGGER.log(Level.INFO , "ViniloResource updateVinilo: output: {0}" , detailDTO);
        return detailDTO;
    }
    
    /**
    * 
    * @param vinilosId 
    */
    @DELETE
    @Path("{vinilosId: \\d+}")
    public void deleteVinilo(@PathParam("vinilosId") Long vinilosId)
    {
        LOGGER.log(Level.INFO, "ViniloResource deleteVinilo: input: {0}", vinilosId);
        if(viniloLogic.getVinilo(vinilosId)== null)
            throw new WebApplicationException("El recurso /vinilos/" + vinilosId + " no existe." , 404);
        viniloLogic.deleteVinilo(vinilosId);
        LOGGER.log(Level.INFO , "ViniloResource deleteVinilo: output: void");
    }
    
    @Path("{vinilosId: \\d+}/canciones")
    public Class<CancionViniloResource> getCancionViniloResource(@PathParam("vinilosId") Long vinilosId)
    {
        if(viniloLogic.getVinilo(vinilosId)== null)
            throw new WebApplicationException("El recurso /vinilos/" + vinilosId + " no existe." , 404);
        return CancionViniloResource.class;
    }
    
    @Path("{vinilosId: \\d+}/comentarios")
    public Class<ComentarioViniloResource> getComentariosResource(@PathParam("vinilosId") Long vinilosId) 
    {
        if(viniloLogic.getVinilo(vinilosId)== null)
            throw new WebApplicationException("El recurso /vinilos/" + vinilosId + " no existe." , 404);
        return ComentarioViniloResource.class;
    }


    private List<ViniloDetailDTO> listEntity2DTO(List<ViniloEntity> entityList)
    {
        List<ViniloDetailDTO> list = new ArrayList<>();
        for(ViniloEntity entity : entityList)
            list.add(new ViniloDetailDTO(entity));
        return list;
    }
    
    /**
    @Path("usuarios")
    public Class<ViniloUsuarioResource> getViniloUsuarioResource(@PathParam("usuariosId") Long usuariosId) 
    {        
        return ViniloUsuarioResource.class;
    }*/
}