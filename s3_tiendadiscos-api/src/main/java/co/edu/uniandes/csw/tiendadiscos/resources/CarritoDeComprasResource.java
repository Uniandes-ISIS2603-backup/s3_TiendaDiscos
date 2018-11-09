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
import javax.enterprise.context.RequestScoped;
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
public class CarritoDeComprasResource {
     private static final Logger LOGGER = Logger.getLogger(WishListResource.class.getName());
    
   @Inject
   private CarritoDeComprasLogic logic;
    
    @POST
    public CarritoDeComprasDTO createCarritoDeCompras(@PathParam("usuariosId") Long usuariosId,CarritoDeComprasDTO carritoDeCompras)throws BusinessLogicException{
        CarritoDeComprasDTO nuevoCarritoDTO = new CarritoDeComprasDTO(logic.create(usuariosId, carritoDeCompras.toEntity()));
        return nuevoCarritoDTO;
    }
    
    
    
    @GET 
    public CarritoDeComprasDTO getCarritoDeCompras(@PathParam("usuariosId") Long usuariosId)throws BusinessLogicException{
        CarritoDeComprasEntity entity = logic.get(usuariosId);
        if(entity==null)
            throw new WebApplicationException("El recurso /usuario/"+ usuariosId+ " no tiene wishList");
        CarritoDeComprasDTO nuevo = new CarritoDeComprasDTO(entity);
        return nuevo;
    }
    
    @DELETE
    @Path("{wishListId: \\d+}")
    public void deleteCarritoDeCompras(@PathParam("carritoDeComprasId") Long carritoDeComprasId)throws BusinessLogicException{
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
    public CarritoDeComprasDTO putCarritoDeCompras(@PathParam("usuariosId") Long usuariosId, CarritoDeComprasDTO carritoDeCompras)throws BusinessLogicException
    {
        CarritoDeComprasEntity entity = logic.get(usuariosId);
        if(entity==null)
            throw new WebApplicationException("El recurso /usuario/"+ usuariosId+ " no tiene wishList");
        CarritoDeComprasDTO carritoDeComprasNuevo = new CarritoDeComprasDTO(logic.update(carritoDeCompras.toEntity(), usuariosId));
        return carritoDeComprasNuevo;
    }
    
}