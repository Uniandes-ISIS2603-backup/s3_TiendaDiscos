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
    
    /**
     * 
     * @param vinilo
     * @return 
     */
    @POST
    public ViniloDTO createVinilo(ViniloDTO vinilo) throws BusinessLogicException
    {
        ViniloDTO viniloCreado =new ViniloDTO(viniloLogic.createVinilo(vinilo.toEntity()));
                
        //LOGGER.log(level.INFO, "ViniloResource createVinilo: input: (0)" , vinilo.toString());
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
    public ViniloDTO getVinilo(@PathParam("vinilosId") Long vinilosId)
    {
        /*ViniloEntity viniloEntity = ViniloLogic.getVinilo(viniloId);
        if(viniloEntity == null)
        {
            throw new WebApplicationException("El recurso /vinilo/" + viniloId + " no existe." , 404);
        }
        ViniloDTO detailDTO = new ViniloDTO(viniloEntity);
        
        return detailDTO;*/
        return null;
    }
    
    @GET
    public List<ViniloDetailDTO> getVinilos()
    {
        List<ViniloDetailDTO> vinilos = listEntity2DTO(viniloLogic.getVinilos());
        return vinilos;
    }
    
    /**
     * 
     * @param vinilosId
     * @param vinilo
     * @return 
     *
    @PUT
    @Path("{vinilosId: \\d+}")
    public ViniloDTO putVinilo(@PathParam("vinilosId") Long vinilosId , ViniloDTO vinilo)
    {
        return vinilo;
    }/
    
    /**
    * 
    * @param vinilosId 
    */
    @DELETE
    @Path("{vinilosId: \\d+}")
    public void deleteVinilo(@PathParam("vinilosId") Long vinilosId)
    {
        
    }
    
    @Path("{vinilosId: \\d+}/canciones")
    public Class<CancionViniloResource> getCancionViniloResource(@PathParam("vinilosId") Long vinilosId)
    {
        return CancionViniloResource.class;
    }
    
    @Path("{vinilosId: \\d+}/comentarios")
    public Class<ComentarioViniloResource> geComentariosResource(@PathParam("vinilosId") Long vinilosId) {
        
        return ComentarioViniloResource.class;
    }





    private List<ViniloDetailDTO> listEntity2DTO(List<ViniloEntity> entityList)
    {
        List<ViniloDetailDTO> list = new ArrayList<ViniloDetailDTO>();
        for(ViniloEntity entity : entityList)
            list.add(new ViniloDetailDTO(entity));
        return list;
    }
}