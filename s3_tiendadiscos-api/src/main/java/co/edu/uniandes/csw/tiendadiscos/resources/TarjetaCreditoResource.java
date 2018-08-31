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
    public TarjetaCreditoDTO createTarjetaCredito(@PathParam("usuariosId") Long usuariosId) {

        return null;
    }
    
    /**
     * tarjetas de credito del billing del usuario
     * @param usuarioId
     * @return 
     */
    @GET
    public List<TarjetaCreditoDTO> getTarjetasCredito(@PathParam("usuariosId") Long usuariosId) {

        return null;
    }
    
    /**
     * tarjeta en especifico del usuario
     * @param usuariosId id usuario 
     * @param tarjetaCreditoId id tarjeta
     * @return 
     */
    @GET
    @Path("{tarjetaCreditoId: \\d+}")
    public TarjetaCreditoDTO getTarjetaCredito(@PathParam("usuariosId") Long usuariosId, @PathParam("tarjetaCreditoId") Long tarjetaCreditoId) {

        return null;
    }

    /**
     * actualizar datos tarjeta
     * @param usuariosId id usuario
     * @param tarjetaCreditoId id tarjeta
     * @return 
     */
    @PUT
    public TarjetaCreditoDTO putTarjetaCredito(@PathParam("usuariosId") Long usuariosId, @PathParam("tarjetaCreditoId") Long tarjetaCreditoId) {
        return null;

    }
    
    /**
     * eliminar tarjeta en especifico del usuario
     * @param usuariosId id usuario
     * @param tarjetaCreditoId  id tarjeta 
     */
    @DELETE
    public void deleteTarjetaCredito(@PathParam("usuariosId") Long usuariosId, @PathParam("tarjetaCreditoId") Long tarjetaCreditoId) {
    
    
    }


}
