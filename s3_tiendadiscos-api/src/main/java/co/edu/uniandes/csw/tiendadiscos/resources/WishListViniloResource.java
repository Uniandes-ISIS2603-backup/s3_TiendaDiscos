/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendadiscos.resources;

import co.edu.uniandes.csw.tiendadiscos.dtos.WishListDetailDTO;
import co.edu.uniandes.csw.tiendadiscos.dtos.ViniloDetailDTO;
import co.edu.uniandes.csw.tiendadiscos.ejb.UsuarioLogic;
import co.edu.uniandes.csw.tiendadiscos.ejb.ViniloLogic;
import co.edu.uniandes.csw.tiendadiscos.ejb.WishListLogic;
import co.edu.uniandes.csw.tiendadiscos.entities.ViniloEntity;
import co.edu.uniandes.csw.tiendadiscos.entities.WishListEntity;
import co.edu.uniandes.csw.tiendadiscos.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Andrés Hernández
 */
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class WishListViniloResource 
{
    private static final Logger LOGGER = Logger.getLogger(CarritoDeComprasVinilosResource.class.getName());

    @Inject
    private UsuarioLogic usuarioLogic;
    
    @Inject
    private WishListLogic wishListLogic;
    
    @Inject
    private ViniloLogic viniloLogic;
    
    @POST
    @Path("{vinilosId: \\d+}")
    public WishListDetailDTO addViniloWishList(@PathParam("usuariosId") Long usuariosId, @PathParam("vinilosId") Long vinilosId) throws BusinessLogicException
    {
        LOGGER.log(Level.INFO, "WishListViniloResource addViniloWishList: input: usuariosId: {0} , vinilosId: {1}", new Object[]{usuariosId, vinilosId});
        if(usuarioLogic.getUsuario(usuariosId)== null)
            throw new WebApplicationException("El recurso /usuarios/"+ usuariosId + " no existe.", 404);
        if(viniloLogic.getVinilo(vinilosId) == null)
            throw new WebApplicationException("El recurso /vinilos/"+ vinilosId + " no existe.", 404);
        if(wishListLogic.getWishList(usuariosId) == null)
            throw new WebApplicationException("El subrecurso carrito de compras del usuario con id:"+ usuariosId+ " no existe.",404);
        WishListEntity wishListEntity = wishListLogic.agregarVinilo(usuariosId, vinilosId);
        WishListDetailDTO newWishListDTO = new WishListDetailDTO(wishListEntity);
        LOGGER.log(Level.INFO, "WishListViniloResource addViniloWishList: output: {0}", newWishListDTO);
        return newWishListDTO;
        
    }
    
    @GET
    @Path("{vinilosId: \\d+}")
    public ViniloDetailDTO getViniloWishList(@PathParam("usuariosId") Long usuariosId, @PathParam("vinilosid") Long vinilosId)
    {
        LOGGER.log(Level.INFO, "WishListViniloResource getViniloWishList: input: usuariosId: {0} , vinilosId: {1}", new Object[]{usuariosId, vinilosId});
        if(usuarioLogic.getUsuario(usuariosId) == null)
            throw new WebApplicationException("El recurso /usuarios/"+ usuariosId + " no existe.", 404);
        WishListEntity wishListEntity = wishListLogic.getWishList(usuariosId);
        if(wishListEntity == null)
            throw new WebApplicationException("El subrecurso carrito de compras del usuario con id:"+ usuariosId+ " no existe.",404);
        ViniloEntity viniloEntity = viniloLogic.getVinilo(vinilosId);
        if(viniloEntity == null)
            throw new WebApplicationException("El recurso /vinilos/"+ vinilosId + " no existe.", 404);
        ViniloDetailDTO viniloDTO = null;
        if(!wishListEntity.getVinilos().contains(viniloEntity))
            throw new WebApplicationException("El vinilo no existe en la wishList.", 404);
        viniloDTO = new ViniloDetailDTO(viniloEntity);
        LOGGER.log(Level.INFO , "WishListViniloResource getViniloWishList: output: {0}", viniloDTO);
        return viniloDTO;
    }
    
    public List<ViniloDetailDTO> getVinilosWishList(@PathParam("usuariosId") Long usuariosId)
    {
        LOGGER.log(Level.INFO , "WishListViniloResource getVinilosWishList: input: usuariosId : {0}", usuariosId);
        WishListEntity wishListEntity = wishListLogic.getWishList(usuariosId);
        List<ViniloDetailDTO> vinilos = new ArrayList<>();
        for(ViniloEntity vinilo : wishListEntity.getVinilos())
            vinilos.add(new ViniloDetailDTO(vinilo));
        LOGGER.log(Level.INFO, "WishListViniloResource getVinilosWishList: output: {0}", vinilos);
        return vinilos;
    }
    
    @DELETE
    @Path("{vinilosId: \\d+}")
    public void deleteViniloWishList(@PathParam("usuariosId") Long usuariosId, @PathParam("vinilosId") Long vinilosId) throws BusinessLogicException
    {
        LOGGER.log(Level.INFO, "WishListViniloResource deleteViniloWishList: input: usuariosId {0} , vinilosId {1}", new Object[]{usuariosId, vinilosId});
        wishListLogic.eliminarVinilo(usuariosId, vinilosId);
        LOGGER.log(Level.INFO, "WishListViniloResource deleteViniloWishList: output: usuariosId {0} , vinilosId {1}", new Object[]{usuariosId, vinilosId});
    }
}