/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendadiscos.resources;

import co.edu.uniandes.csw.tiendadiscos.dtos.TransaccionDTO;
import java.util.logging.Logger;
import javax.ws.rs.Consumes;
import javax.ws.rs.*;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Laura Isabella Forero Camacho
 */
@Path("usuarios/usuariosId/carritosCompras/transacciones")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CarritoDeComprasTransaccionesResource 
{
    private static final Logger LOGGER = Logger.getLogger(CarritoDeComprasTransaccionesResource.class.getName());
    
    @POST
    @Path("{transaccionesId: \\d+}")
    public TransaccionDTO addCarritoComprasTransaccion(@PathParam("transaccionesId") Long transaccionesId, TransaccionDTO transaccion)
    {
        return transaccion;
    }
    
    @GET
    @Path("{transaccionesId: \\d+}")
    public TransaccionDTO getTransaccionCarritoCompras(@PathParam("transaccionesId") Long transaccionesId)
    {
        return null;
    }    
   
    @DELETE
    @Path("{transaccionesId: \\d+}")
    public void deleteTransaccionCarritoCompras(@PathParam("transaccionesId") Long transaccionesId)
    {
        
    }
}