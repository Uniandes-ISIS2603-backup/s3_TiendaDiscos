/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendadiscos.resources;

import co.edu.uniandes.csw.tiendadiscos.dtos.CarritoDeComprasDTO;
import co.edu.uniandes.csw.tiendadiscos.ejb.CarritoDeComprasLogic;
import co.edu.uniandes.csw.tiendadiscos.entities.CarritoDeComprasEntity;
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
@RequestScoped

public class CarritoDeComprasResource {
    
    private static final Logger LOGGER = Logger.getLogger(CarritoDeComprasResource.class.getName());
    
     @Inject
   private CarritoDeComprasLogic logic;
    
     @POST
    
    public CarritoDeComprasDTO createCarritoCompras(@PathParam("usuarioId") Long usuarioId, CarritoDeComprasDTO carritoCompras) throws BusinessLogicException{
        CarritoDeComprasDTO nuevoCarritoDeComprasDTO = new CarritoDeComprasDTO(logic.create(usuarioId, carritoCompras.toEntity()));
        return nuevoCarritoDeComprasDTO;
    }
    
    
    
    @GET
   
//Solo existe un carrito de compras por usuario no es necesario tener un id de este.
    public CarritoDeComprasDTO getCarritoCompras(@PathParam("usuarioId") Long usuarioId){
        CarritoDeComprasEntity entity = logic.get(usuarioId);
        if(entity==null)
            throw new WebApplicationException("El recurso /usuario/"+ usuarioId+ " no tiene carrito de compras");
        CarritoDeComprasDTO nuevo = new CarritoDeComprasDTO(entity);
        return nuevo;
    }
    
   
    
    @DELETE
    //Solo existe un carrito de compras por usuario no es necesario tener un id de este.
    @Path("{carritoDeComprasId: \\d+}")
    public void deleteCarritoCompras(@PathParam("carritoDeComprasId") Long carritoDeComprasId){
         logic.delete(carritoDeComprasId);
        
    }
    
    
    
}
