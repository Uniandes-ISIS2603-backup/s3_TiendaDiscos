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
import java.util.logging.Level;
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
 *  Clase que implementa el subRecurso "Wishlist"
 * @author Sebastian Martinez
 */
@Consumes("application/json")
@Produces("application/json")
public class WishListResource 
{

    private static final Logger LOGGER = Logger.getLogger(WishListResource.class.getName());
    
   @Inject
   private WishListLogic logic;
    
   /**
    * Crea una nueva WishList con la información que se recibe en el cuerpo de la petición
    * y se regresa un objeto identico con un id auto-generado por la base de datos.
    * @param wishList {@link WishListDTO} - La wishList que se desea guardar.
    * @return JSON {@link WishListDTO} - La wishList guardada con el atributo id.
    * @throws BussinessLogicException si el usuario con el id usuarioId ya tiene asignado un Wishlist.
    *                                 Si no existe un usuario con ese id.
    */
    @POST
    public WishListDTO createWishList(@PathParam("usuariosId") Long usuariosId,WishListDTO wishList)throws BusinessLogicException
    {
        LOGGER.log(Level.INFO, "WishListResource createWishList: input:{0}" , wishList.toString());
        WishListDTO nuevoWishListDTO = new WishListDTO(logic.createWishList(usuariosId, wishList.toEntity()));
        LOGGER.log(Level.INFO, "WishListResource createWishList: output:{0}", nuevoWishListDTO.toString());
        return nuevoWishListDTO;
    }
    
    
    /**
     * Busca y devuelve la wishList del usuario con el id recibido en la URL.
     * 
     * @param usuariosId el ID desl usuario del cual se busca su WishList.
     * @return {@link WishListDTO} - La wishList encontrada en el usuario
     * @throws BusinessLogicException Si no existe un usuario con ese Id.  
     *                                Su ek usuario con ese ide no tiene una WishList.
     */
    @GET 
    public WishListDTO getWishList(@PathParam("usuariosId") Long usuariosId)throws BusinessLogicException{
        WishListEntity entity = logic.getWishList(usuariosId);
        if(entity==null)
            throw new WebApplicationException("El recurso /usuario/"+ usuariosId+ " no tiene wishList");
        WishListDTO nuevo = new WishListDTO(entity);
        return nuevo;
    }
    
    /**
     * Elimina la conexión entre el la wishlist y el usuario recibidos en la URL.
     * @
     */
    @DELETE
    public void deleteWishList(@PathParam("usuariosId") Long usuariosId) throws BusinessLogicException
    {
        logic.deleteWishList(usuariosId);

    }
    
    /**
     * 
     * @param usuariosId
     * @param whislist
     * @return 
    */
    @PUT 
    public WishListDTO putWishlist(@PathParam("usuariosId") Long usuariosId, WishListDTO whislist)throws BusinessLogicException
    {
        WishListEntity entity = logic.getWishList(usuariosId);
        if(entity==null)
            throw new WebApplicationException("El recurso /usuario/"+ usuariosId+ " no tiene wishList");
        WishListDTO comentario = new WishListDTO(logic.updateWishList(whislist.toEntity(), usuariosId));
        return comentario;
    }

    /** linkhl estuvo aquí*/
}