/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendadiscos.resources;

import co.edu.uniandes.csw.tiendadiscos.dtos.WishListDTO;
import co.edu.uniandes.csw.tiendadiscos.ejb.WishListLogic;
import co.edu.uniandes.csw.tiendadiscos.entities.WishListEntity;
import co.edu.uniandes.csw.tiendadiscos.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.websocket.server.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;

/**
 *
 * @author Sebastian Martinez
 */
@Consumes("application/json")
@Produces("application/json")
public class WishListResource {
    private static final Logger LOGGER = Logger.getLogger(WishListResource.class.getName());
    
   @Inject
   private WishListLogic logic;
    
    @POST
    public WishListDTO createWishList(@PathParam("usuarioId") Long usuarioId,WishListDTO wishList)throws BusinessLogicException{
        WishListDTO nuevoWishListDTO = new WishListDTO(logic.createWishList(usuarioId, wishList.toEntity()));
        return nuevoWishListDTO;
    }
    
    
    
    @GET 
    public WishListDTO getWishList(@PathParam("usuarioId") Long usuarioId)throws BusinessLogicException{
        WishListEntity entity = logic.get(usuarioId);
        if(entity==null)
            throw new WebApplicationException("El recurso /usuario/"+ usuarioId+ " no tiene wishList");
        WishListDTO nuevo = new WishListDTO(entity);
        return nuevo;
    }
    
    @DELETE
    @Path("{wishListId: \\d+}")
    public void deleteWishList(@PathParam("wishListId") Long wishListId){
        logic.delete(wishListId);

    }
    /**
     * 
     * @param whislist
     * @return 
    */
    @PUT
    @Path("{wishListId: \\d+}")    
    public WishListDTO putComentario(@PathParam("usuarioId") Long usuarioId, @PathParam("wishListId") Long wishListId,WishListDTO whislist)throws BusinessLogicException
    {
        if (wishListId.equals(whislist.getId())) {
            throw new BusinessLogicException("Los ids del Review no coinciden.");
        }
        WishListEntity entity = logic.get(usuarioId);
        if(entity==null)
            throw new WebApplicationException("El recurso /usuario/"+ usuarioId+ " no tiene wishList");
        WishListDTO comentario = new WishListDTO(logic.update(whislist.toEntity(), usuarioId));
        return comentario;
    }
}
