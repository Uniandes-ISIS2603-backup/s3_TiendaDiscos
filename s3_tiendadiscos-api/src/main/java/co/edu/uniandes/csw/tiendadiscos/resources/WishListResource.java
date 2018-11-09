/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendadiscos.resources;

import co.edu.uniandes.csw.tiendadiscos.dtos.CarritoDeComprasDTO;
import co.edu.uniandes.csw.tiendadiscos.dtos.WishListDTO;
import co.edu.uniandes.csw.tiendadiscos.ejb.CarritoDeComprasLogic;
import co.edu.uniandes.csw.tiendadiscos.ejb.WishListLogic;
import co.edu.uniandes.csw.tiendadiscos.entities.CarritoDeComprasEntity;
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
        WishListDTO nuevoWishDTO = new WishListDTO(logic.create(usuariosId, wishList.toEntity()));
        return nuevoWishDTO;
    }
    
    
    
    @GET 
    public WishListDTO getWishList(@PathParam("usuariosId") Long usuariosId)throws BusinessLogicException{
        WishListEntity entity = logic.get(usuariosId);
        if(entity==null)
            throw new WebApplicationException("El recurso /usuario/"+ usuariosId+ " no tiene wishList");
        WishListDTO nuevo = new WishListDTO(entity);
        return nuevo;
    }
    
    @DELETE
    @Path("{wishListId: \\d+}")
    public void deleteWishList(@PathParam("carritoDeComprasId") Long carritoDeComprasId)throws BusinessLogicException{
        logic.delete(carritoDeComprasId);

    }
    
    /**
     * 
     * @param usuariosId
     * @param whislist
     * @return 
    */
    @PUT
    @Path("{wishListId: \\d+}")    
    public WishListDTO putWishList(@PathParam("usuariosId") Long usuariosId, WishListDTO wishList)throws BusinessLogicException
    {
        WishListEntity entity = logic.get(usuariosId);
        if(entity==null)
            throw new WebApplicationException("El recurso /usuario/"+ usuariosId+ " no tiene wishList");
        WishListDTO wishNueva = new WishListDTO(logic.update(wishList.toEntity(), usuariosId));
        return wishNueva;
    }
    
}