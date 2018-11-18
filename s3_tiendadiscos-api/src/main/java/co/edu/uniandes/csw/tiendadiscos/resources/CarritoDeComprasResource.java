/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendadiscos.resources;

import co.edu.uniandes.csw.tiendadiscos.dtos.CarritoDeComprasDTO;
import co.edu.uniandes.csw.tiendadiscos.dtos.CarritoDeComprasDetailDTO;
import co.edu.uniandes.csw.tiendadiscos.ejb.CarritoDeComprasLogic;
import co.edu.uniandes.csw.tiendadiscos.entities.CarritoDeComprasEntity;
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
public class CarritoDeComprasResource 
{
    private static final Logger LOGGER = Logger.getLogger(CarritoDeComprasResource.class.getName());
    
   @Inject
   private CarritoDeComprasLogic logic;
    
    @POST
    public CarritoDeComprasDTO createCarritoDeCompras(@PathParam("usuariosId") Long usuariosId,CarritoDeComprasDTO carritoDeCompras)throws BusinessLogicException{
        CarritoDeComprasDTO nuevoCarritoDTO = new CarritoDeComprasDTO(logic.create(usuariosId, carritoDeCompras.toEntity()));
        return nuevoCarritoDTO;
    }
    
    
    
    @GET 
    public CarritoDeComprasDetailDTO getCarritoDeCompras(@PathParam("usuariosId") Long usuariosId)throws BusinessLogicException{
        CarritoDeComprasEntity entity = logic.get(usuariosId);
        if(entity==null)
            throw new WebApplicationException("El recurso /usuario/"+ usuariosId+ " no tiene Carrito de compras");
        CarritoDeComprasDetailDTO nuevo = new CarritoDeComprasDetailDTO(entity);
        return nuevo;
    }
    
    @DELETE
    @Path("{usuariosId: \\d+}")
    public void deleteCarritoDeCompras(@PathParam("usuariosId") Long usuariosId)throws BusinessLogicException
    {
        if(null == logic.get(usuariosId))
            throw new WebApplicationException("El recurso/usuario/" + usuariosId + " no tiene un carrito de compras.", 404);
        logic.delete(usuariosId);
    }
    
    /**
     * 
     * @param usuariosId
     * @param carritoDeCompras
     * @return 
     * @throws BusinessLogicException 
    */
    @PUT
    @Path("{usuariosId: \\d+}")   
    public CarritoDeComprasDTO putCarritoDeCompras(@PathParam("usuariosId") Long usuariosId, CarritoDeComprasDTO carritoDeCompras)throws BusinessLogicException
    {
        CarritoDeComprasEntity entity = logic.get(usuariosId);
        if(entity==null)
            throw new WebApplicationException("El recurso /usuario/"+ usuariosId+ " no tiene wishList");
        CarritoDeComprasDTO carritoDeComprasNuevo = new CarritoDeComprasDTO(logic.update(carritoDeCompras.toEntity(), usuariosId));
        return carritoDeComprasNuevo;
    }

}