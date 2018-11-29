/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendadiscos.resources;

import co.edu.uniandes.csw.tiendadiscos.dtos.CarritoDeComprasDetailDTO;
import co.edu.uniandes.csw.tiendadiscos.dtos.ViniloDetailDTO;
import co.edu.uniandes.csw.tiendadiscos.ejb.CarritoDeComprasLogic;
import co.edu.uniandes.csw.tiendadiscos.ejb.UsuarioLogic;
import co.edu.uniandes.csw.tiendadiscos.ejb.ViniloLogic;
import co.edu.uniandes.csw.tiendadiscos.entities.CarritoDeComprasEntity;
import co.edu.uniandes.csw.tiendadiscos.entities.ViniloEntity;
import co.edu.uniandes.csw.tiendadiscos.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.*;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Andrés Hernández
 */
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CarritoDeComprasVinilosResource {

    private static final Logger LOGGER = Logger.getLogger(CarritoDeComprasVinilosResource.class.getName());

    @Inject
    private UsuarioLogic usuarioLogic;

    @Inject
    private CarritoDeComprasLogic logic;

    @Inject
    private ViniloLogic viniloLogic;

    @POST
    @Path("{vinilosId: \\d+}")
    public CarritoDeComprasDetailDTO addViniloCarritoCompras(@PathParam("usuariosId") Long usuariosId, @PathParam("vinilosId") Long vinilosId) throws BusinessLogicException 
    {
        LOGGER.log(Level.INFO, "CarritoDeComprasVinilosResource addViniloCarritoCompras con id : input: {0}", vinilosId);
        if (usuarioLogic.getUsuario(usuariosId) == null) {
            throw new WebApplicationException("El recurso /usuarios/" + usuariosId + " no existe.", 404);
        }
        if (viniloLogic.getVinilo(vinilosId) == null) {
            throw new WebApplicationException("El recurso /vinilos/" + vinilosId + " no existe.", 404);
        }
        if (logic.get(usuariosId) == null) {
            throw new WebApplicationException("El subrecurso carrito de compras del usuario con id:" + usuariosId + " no existe.", 404);
        }
        CarritoDeComprasEntity carrito = logic.agregarVinilo(usuariosId, vinilosId);
        CarritoDeComprasDetailDTO newCarritoDto = new CarritoDeComprasDetailDTO(carrito);
        LOGGER.log(Level.INFO, "CarritoDeComprasVinilosResource addViniloCarritoCompras: output: {0}", newCarritoDto);
        return newCarritoDto;
    }

    @GET
    @Path("{vinilosId: \\d+}")
    public ViniloDetailDTO getViniloCarritoCompras(@PathParam("usuariosId") Long usuariosId, @PathParam("vinilosId") Long vinilosId) {
        LOGGER.log(Level.INFO, "CarritoDeComprasVinilosResource getViniloCarritoCompras con id : input: {0}", vinilosId);
        if (usuarioLogic.getUsuario(usuariosId) == null) {
            throw new WebApplicationException("El recurso /usuarios/" + usuariosId + " no existe.", 404);
        }
        CarritoDeComprasEntity carrito = logic.get(usuariosId);
        if (carrito == null) {
            throw new WebApplicationException("El subrecurso carrito de compras del usuario con id:" + usuariosId + " no existe.", 404);
        }
        ViniloEntity vinilo = viniloLogic.getVinilo(vinilosId);
        if (vinilo == null) {
            throw new WebApplicationException("El recurso /vinilos/" + vinilosId + " no existe.", 404);
        }
        ViniloDetailDTO viniloDTO = null;
        List<ViniloEntity> vinilos = carrito.getVinilosDeCarritoCompras();
        for (ViniloEntity vinilo1 : vinilos) {
            if (vinilo1.getId().equals(vinilo.getId()) && vinilo1.getInformacionAdicional().equals(vinilo.getInformacionAdicional()) && vinilo1.getNombre().equals(vinilo.getNombre())) 
                viniloDTO = new ViniloDetailDTO(vinilo);
        }
        if (viniloDTO == null) 
            throw new WebApplicationException("El vinilo no existe en el carrito", 404);
        LOGGER.log(Level.INFO, "CarritoDeComprasVinilosResource getViniloCarritoCompras: output: {0}", viniloDTO);
        return viniloDTO;
    }

    @GET
    public List<ViniloDetailDTO> getVinilosCarritoCompras(@PathParam("usuariosId") Long usuariosId) 
    {
        LOGGER.log(Level.INFO, "CarritoDeComprasVinilosResource getVinilosCarritoCompras del usaurio {0}", usuariosId);
        CarritoDeComprasEntity carrito = logic.get(usuariosId);
        List<ViniloDetailDTO> vinilos = new ArrayList<>();
        for (ViniloEntity vinilosDeCarritoCompra : carrito.getVinilosDeCarritoCompras())
            vinilos.add(new ViniloDetailDTO(vinilosDeCarritoCompra));
        LOGGER.log(Level.INFO, "CarritoDeComprasVinilosResource getVinilosCarritoCompras: output: {0}", vinilos);
        return vinilos;
    }

    @DELETE
    @Path("{vinilosId: \\d+}")
    public void deleteViniloCarritoCompras(@PathParam("usuariosId") Long usuariosId, @PathParam("vinilosId") Long vinilosId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "CarritoDeComprasVinilosResource deleteViniloCarritoCompras con id : input: {0}", vinilosId);
        CarritoDeComprasEntity carrito = logic.eliminarVinilo(usuariosId, vinilosId);
        LOGGER.log(Level.INFO, "CarritoDeComprasVinilosResource deleteViniloCarritoCompras: output: {0}", carrito);
    }

}
