/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendadiscos.resources;

import co.edu.uniandes.csw.tiendadiscos.dtos.WishListDTO;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.websocket.server.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 *
 * @author Sebastian Martinez
 */
@Consumes("application/json")
@Produces("application/json")
public class WishListResource {
    private static final Logger LOGGER = Logger.getLogger(WishListResource.class.getName());
    
    @POST
    public WishListDTO createWishList(WishListDTO wishList){
        return wishList;
    }
    
    
    
    @GET 
    @Path("{wishListId: \\d+}")
    public WishListDTO getWishList(@PathParam("wishListId") Long wishListId){
        
        return new WishListDTO();
    }
    
    @GET 
    public List<WishListDTO> getWishLists(){
        
        return new ArrayList<WishListDTO>();
    }
    
    @DELETE
    public boolean deleteWishList(){
        return true;
    }
    /**
     * 
     * @param whislist
     * @return 
    */
    @PUT
    @Path("{wishListId: \\d+}")    
    public WishListDTO putComentario( @PathParam("wishListId") Long wishListId,WishListDTO whislist)
    {
        return whislist;
    }
}
