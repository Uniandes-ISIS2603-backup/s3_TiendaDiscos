/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendadiscos.resources;

import co.edu.uniandes.csw.tiendadiscos.dtos.TarjetaCreditoDTO;
import java.util.List;
import javax.ws.rs.*;

/**
 *
 * @author Kevin Blanco
 */
public class TarjetaCreditoResource {

    // Necesito el id de Billing ???
    @POST
    public TarjetaCreditoDTO createTarjetaCredito(@PathParam("usuarioId") Long usuarioId) {

        return null;
    }
    
    /**
     * tarjetas de credito del billing del usuario
     * @param usuarioId
     * @return 
     */
    @GET
    public List<TarjetaCreditoDTO> getTarjetasCredito(@PathParam("usuarioId") Long usuarioId) {

        return null;
    }
    
    /**
     * tarjeta en especifico del usuario
     * @param usuarioId id usuario 
     * @param tarjetaCreditoId id tarjeta
     * @return 
     */
    @GET
    @Path("{tarjetaCreditoId: \\d+}")
    public TarjetaCreditoDTO getTarjetaCredito(@PathParam("usuarioId") Long usuarioId, @PathParam("tarjetaCreditoId") Long tarjetaCreditoId) {

        return null;
    }

    /**
     * actualizar datos tarjeta
     * @param usuarioId id usuario
     * @param tarjetaCreditoId id tarjeta
     * @return 
     */
    @PUT
    public TarjetaCreditoDTO putTarjetaCredito(@PathParam("usuarioId") Long usuarioId, @PathParam("tarjetaCreditoId") Long tarjetaCreditoId) {
        return null;

    }
    
    /**
     * eliminar tarjeta en especifico del usuario
     * @param usuarioId id usuario
     * @param tarjetaCreditoId  id tarjeta 
     */
    @DELETE
    public void deleteTarjetaCredito(@PathParam("usuarioId") Long usuarioId, @PathParam("tarjetaCreditoId") Long tarjetaCreditoId) {
    
    
    }


}
