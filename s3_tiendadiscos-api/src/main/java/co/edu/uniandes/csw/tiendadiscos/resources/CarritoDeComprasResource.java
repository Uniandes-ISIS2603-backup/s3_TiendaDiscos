/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendadiscos.resources;

import co.edu.uniandes.csw.tiendadiscos.dtos.CarritoComprasDTO;
import co.edu.uniandes.csw.tiendadiscos.dtos.TransaccionDTO;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 *
 * @author Laura Isabella Forero Camacho
 */
@Path("usuarios/usuariosId/carritosdecompras")
@Consumes("application/json")
@Produces("application/json")
@RequestScoped

public class CarritoComprasResource {
    
    private static final Logger LOGGER = Logger.getLogger(CarritoComprasResource.class.getName());
    
     @POST
    public CarritoComprasDTO createCarritoCompras(CarritoComprasDTO carritoCompras){
        return carritoCompras;
    }
    
    
    
    @GET
    //Solo existe un carrito de compras por usuario no es necesario tener un id de este.
    public TransaccionDTO getCarritoCompras(){
        
        return null;
    }
    
   
    
    @DELETE
    //Solo existe un carrito de compras por usuario no es necesario tener un id de este.
    public void deleteCarritoCompras(){
        
        
    }
    
    
    
}
