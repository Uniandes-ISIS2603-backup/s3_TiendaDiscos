/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendadiscos.resources;

import co.edu.uniandes.csw.tiendadiscos.dtos.CarritoDeComprasDetailDTO;
import co.edu.uniandes.csw.tiendadiscos.dtos.ViniloDTO;
import co.edu.uniandes.csw.tiendadiscos.dtos.ViniloDetailDTO;
import co.edu.uniandes.csw.tiendadiscos.ejb.CarritoDeComprasLogic;
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
    private CarritoDeComprasLogic logic;

    @Inject
    private ViniloLogic viniloLogic;

    @POST
    @Path("{vinilosId: \\d+}")
    public CarritoDeComprasDetailDTO addViniloCarritoCompras(@PathParam("usuariosId") Long usuariosId, @PathParam("vinilosId") Long vinilosId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "CarritoDeComprasVinilosResource addViniloCarritoCompras con id : input: {0}", vinilosId);
        CarritoDeComprasEntity carrito = logic.agregarVinilo(usuariosId, vinilosId);
        CarritoDeComprasDetailDTO newCarritoDto = new CarritoDeComprasDetailDTO(carrito);
        LOGGER.log(Level.INFO, "CarritoDeComprasVinilosResource addViniloCarritoCompras: output: {0}", newCarritoDto.toString());

        return newCarritoDto;
    }

    @GET
    @Path("{vinilosId: \\d+}")
    public ViniloDetailDTO getViniloCarritoCompras(@PathParam("usuariosId") Long usuariosId, @PathParam("vinilosId") Long vinilosId) {
        LOGGER.log(Level.INFO, "CarritoDeComprasVinilosResource getViniloCarritoCompras con id : input: {0}", vinilosId);
        CarritoDeComprasEntity carrito = logic.get(usuariosId);
        ViniloEntity vinilo = viniloLogic.getVinilo(vinilosId);
        ViniloDetailDTO viniloDTO = null;
        if (carrito.getVinilosDeCarritoCompras().contains(vinilo)) {
            viniloDTO = new ViniloDetailDTO(vinilo);

        } else {
            throw new WebApplicationException("El vinilo no existe en el carrito", 404);
        }
        LOGGER.log(Level.INFO, "CarritoDeComprasVinilosResource getViniloCarritoCompras: output: {0}", viniloDTO.toString());

        return viniloDTO;
    }

    @GET
    public List<ViniloDetailDTO> getVinilosCarritoCompras(@PathParam("usuariosId") Long usuariosId) {

        LOGGER.log(Level.INFO, "CarritoDeComprasVinilosResource getVinilosCarritoCompras del usaurio " + usuariosId);
        CarritoDeComprasEntity carrito = logic.get(usuariosId);
        List<ViniloDetailDTO> vinilos = new ArrayList<ViniloDetailDTO>();
        for (ViniloEntity vinilosDeCarritoCompra : carrito.getVinilosDeCarritoCompras()) {
            vinilos.add(new ViniloDetailDTO(vinilosDeCarritoCompra));
        }
        LOGGER.log(Level.INFO, "CarritoDeComprasVinilosResource getVinilosCarritoCompras: output: {0}", vinilos.toString());

        return vinilos;
    }

    @DELETE
    @Path("{vinilosId: \\d+}")
    public void deleteViniloCarritoCompras(@PathParam("usuariosId") Long usuariosId,@PathParam("vinilosId") Long vinilosId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "CarritoDeComprasVinilosResource deleteViniloCarritoCompras con id : input: {0}", vinilosId);
        CarritoDeComprasEntity carrito = logic.eliminarVinilo(usuariosId, vinilosId);
        LOGGER.log(Level.INFO, "CarritoDeComprasVinilosResource deleteViniloCarritoCompras: output: {0}", carrito.toString());
    }

}
