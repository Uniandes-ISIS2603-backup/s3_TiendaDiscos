/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendadiscos.resources;

import co.edu.uniandes.csw.tiendadiscos.dtos.TarjetaCreditoDTO;
import java.util.ArrayList;
import java.util.List;
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
 * @author Kevin Blanco
 */
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class TarjetaCreditoResource {

    // Necesito el id de Billing ???
    @POST
    public TarjetaCreditoDTO createTarjetaCredito(@PathParam("usuariosId") Long usuariosId, TarjetaCreditoDTO tarjeta) {

        return tarjeta;
    }
    
    /**
     * tarjetas de credito del billing del usuario
     * @param usuarioId
     * @return 
     */
    @GET
    public List<TarjetaCreditoDTO> getTarjetasCredito(@PathParam("usuariosId") Long usuariosId) {

        return new ArrayList<TarjetaCreditoDTO>();
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

        return new TarjetaCreditoDTO();
    }

    /**
     * actualizar datos tarjeta
     * @param usuariosId id usuario
     * @param tarjetaCreditoId id tarjeta
     * @return 
     */
    @PUT
    public TarjetaCreditoDTO putTarjetaCredito(@PathParam("usuariosId") Long usuariosId, @PathParam("tarjetaCreditoId") Long tarjetaCreditoId, TarjetaCreditoDTO tarjeta) {
        return tarjeta;

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
