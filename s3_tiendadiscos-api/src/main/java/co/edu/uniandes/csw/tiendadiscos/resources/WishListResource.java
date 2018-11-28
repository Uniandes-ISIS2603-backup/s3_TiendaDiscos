/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendadiscos.resources;

import co.edu.uniandes.csw.tiendadiscos.dtos.WishListDTO;
import co.edu.uniandes.csw.tiendadiscos.dtos.WishListDetailDTO;
import co.edu.uniandes.csw.tiendadiscos.ejb.WishListLogic;
import co.edu.uniandes.csw.tiendadiscos.entities.WishListEntity;
import co.edu.uniandes.csw.tiendadiscos.exceptions.BusinessLogicException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;

/**
 *
 * @author Laura Isabella Forero Camacho
 */
@Consumes("application/json")
@Produces("application/json")
public class WishListResource {
    
    private static final String INIC_ERROR = "El recurso /usuario/";
    
    private static final String FIN_ERROR = " no tiene una wishList asociada.";
    
    private static final Logger LOGGER = Logger.getLogger(WishListResource.class.getName());
    
   @Inject
   private WishListLogic logic;
    
    @POST
    public WishListDTO createWishList(@PathParam("usuariosId") Long usuariosId,WishListDTO wishList)throws BusinessLogicException
    {
        LOGGER.log(Level.INFO , "WishListResource createWishList: input: usuariosId: {0}, wishList´{1}", new Object[]{usuariosId, wishList});
        WishListDTO nuevoWishDTO = new WishListDTO(logic.createWishList(usuariosId, wishList.toEntity()));
        LOGGER.log(Level.INFO, "WishListResource createWishList: output: {0}", nuevoWishDTO);
        return nuevoWishDTO;
    }    
    
    @GET 
    public WishListDetailDTO getWishList(@PathParam("usuariosId") Long usuariosId)
    {
        LOGGER.log(Level.INFO, "WishListResource getWishList: input: {0}", usuariosId);
        WishListEntity entity = logic.getWishList(usuariosId);
        if(entity==null)
            throw new WebApplicationException(INIC_ERROR+ usuariosId+ FIN_ERROR,404);
        WishListDetailDTO detailDTO = new WishListDetailDTO(entity);
        LOGGER.log(Level.INFO , "WishListResource getWishList: output: {0}", detailDTO);
        return detailDTO;
    }
    
    @DELETE
    @Path("{usuariosId: \\d+}")
    public void deleteWishList(@PathParam("usuariosId") Long usuariosId)throws BusinessLogicException
    {
        LOGGER.log(Level.INFO , "WishListResource deleteWishList: input: {0}", usuariosId); 
        if(logic.getWishList(usuariosId) == null)
            throw new WebApplicationException(INIC_ERROR + usuariosId + FIN_ERROR, 404);
        logic.deleteWishList(usuariosId);
        LOGGER.log(Level.INFO , "WishListResource deleteWishList: output: void");
    }
    
    /**
     * 
     * @param usuariosId
     * @param wishList
     * @return 
    */
    @PUT
    @Path("{usuariosId: \\d+}") 
    public WishListDTO updateWishList(@PathParam("usuariosId") Long usuariosId, WishListDTO wishList)throws BusinessLogicException
    {
        LOGGER.log(Level.INFO , "WishListResource updateWishList: input: usuariosId: {0}, wishList:{1}", new Object[]{usuariosId, wishList});
        WishListEntity entity = logic.getWishList(usuariosId);
        if(entity==null)
            throw new WebApplicationException(INIC_ERROR+ usuariosId+ FIN_ERROR, 404);
        WishListDTO wishNueva = new WishListDTO(logic.updateWishList(wishList.toEntity(), usuariosId));
        LOGGER.log(Level.INFO, "WishListResource updateWishList: output: {0}", wishNueva);
        return wishNueva;
    }   
}