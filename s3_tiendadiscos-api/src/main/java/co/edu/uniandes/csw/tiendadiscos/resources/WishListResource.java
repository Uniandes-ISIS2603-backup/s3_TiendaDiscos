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
     private static final Logger LOGGER = Logger.getLogger(WishListResource.class.getName());
    
   @Inject
   private WishListLogic logic;
    
    @POST
    public WishListDTO createWishList(@PathParam("usuariosId") Long usuariosId,WishListDTO wishList)throws BusinessLogicException{
        WishListDTO nuevoWishDTO = new WishListDTO(logic.createWishList(usuariosId, wishList.toEntity()));
        return nuevoWishDTO;
    }
    
    
    
    @GET 
    public WishListDetailDTO getWishList(@PathParam("usuariosId") Long usuariosId)throws BusinessLogicException
    {
        WishListEntity entity = logic.getWishList(usuariosId);
        if(entity==null)
            throw new WebApplicationException("El recurso /usuario/"+ usuariosId+ " no tiene wishList",404);
        WishListDetailDTO nuevo = new WishListDetailDTO(entity);
        return nuevo;
    }
    
    @DELETE
    @Path("{usuariosId: \\d+}")
    public void deleteWishList(@PathParam("usuariosId") Long usuariosId)throws BusinessLogicException
    {
        if(logic.getWishList(usuariosId) == null)
            throw new WebApplicationException("El recurso /usuario/"+ usuariosId + " no tiene una wishList asociada.", 404);
        logic.deleteWishList(usuariosId);
    }
    
    /**
     * 
     * @param usuariosId
     * @param wishList
     * @return 
    */
    @PUT
    @Path("{usuariosId: \\d+}") 
    public WishListDTO putWishList(@PathParam("usuariosId") Long usuariosId, WishListDTO wishList)throws BusinessLogicException
    {
        WishListEntity entity = logic.getWishList(usuariosId);
        if(entity==null)
            throw new WebApplicationException("El recurso /usuario/"+ usuariosId+ " no tiene wishList");
        WishListDTO wishNueva = new WishListDTO(logic.updateWishList(wishList.toEntity(), usuariosId));
        return wishNueva;
    }   
}