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
public class CarritoDeComprasResource {

    private static final Logger LOGGER = Logger.getLogger(CarritoDeComprasResource.class.getName());

    @Inject
    private CarritoDeComprasLogic logic;

    @POST
    public CarritoDeComprasDTO createCarritoDeCompras(@PathParam("usuariosId") Long usuariosId, CarritoDeComprasDTO carritoDeCompras) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "CarritoDeComprasResource createCarritoDeCompras: input: {0}", carritoDeCompras);
        CarritoDeComprasDTO nuevoCarritoDTO = new CarritoDeComprasDTO(logic.create(usuariosId, carritoDeCompras.toEntity()));
        LOGGER.log(Level.INFO, "CarritoDeComprasResource createCarritoDeCompras: output: {0}", nuevoCarritoDTO);
        return nuevoCarritoDTO;
    }

    @GET
    public CarritoDeComprasDetailDTO getCarritoDeCompras(@PathParam("usuariosId") Long usuariosId) 
    {
        LOGGER.log(Level.INFO , "CarritoComprasResource getCarritoDeCompras: input:{0}", usuariosId);
        CarritoDeComprasEntity entity = logic.get(usuariosId);
        if (entity == null) 
            throw new WebApplicationException("El recurso /usuario/" + usuariosId + " no tiene Carrito de compras");
        CarritoDeComprasDetailDTO detailDTO = new CarritoDeComprasDetailDTO(entity);
        LOGGER.log(Level.INFO , "CarritoComprasResource getCarritoCompras: outpu: {0}", detailDTO);
        return detailDTO;
    }

    @DELETE
    @Path("{usuariosId: \\d+}")
    public void deleteCarritoDeCompras(@PathParam("usuariosId") Long usuariosId) throws BusinessLogicException 
    {
        if (logic.get(usuariosId) == null) 
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
    public CarritoDeComprasDTO putCarritoDeCompras(@PathParam("usuariosId") Long usuariosId, CarritoDeComprasDTO carritoDeCompras) throws BusinessLogicException 
    {
        LOGGER.log(Level.INFO , "CarritoDeComprasResource putCarritoDeCompras: input: usuariosId: {0}, carritoDeCompras: {1}", new Object[]{usuariosId, carritoDeCompras});
        CarritoDeComprasEntity entity = logic.get(usuariosId);
        if (entity == null)
            throw new WebApplicationException("El recurso /usuario/" + usuariosId + " no tiene wishList");
        CarritoDeComprasDTO detailDTO = new CarritoDeComprasDTO(logic.update(carritoDeCompras.toEntity(), usuariosId));
        LOGGER.log(Level.INFO , "CarritoDeComprasResource putCarritoDeCompras: output: {0}", detailDTO);
        return detailDTO;
    }
    
    @Path("/vinilos")
    public Class<CarritoDeComprasVinilosResource> getCarritoVinilosResource(@PathParam("usuariosId") Long usuariosId) 
    {
        if (logic.get(usuariosId)==null) 
            throw new WebApplicationException("El recurso /usuarios/" + usuariosId + "/billing  no existe.", 404);
        return CarritoDeComprasVinilosResource.class;
    }
}