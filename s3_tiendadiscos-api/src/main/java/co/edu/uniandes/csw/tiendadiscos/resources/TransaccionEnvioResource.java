/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendadiscos.resources;

import co.edu.uniandes.csw.tiendadiscos.dtos.EnvioDTO;
import co.edu.uniandes.csw.tiendadiscos.ejb.EnvioLogic;
import co.edu.uniandes.csw.tiendadiscos.entities.EnvioEntity;
import co.edu.uniandes.csw.tiendadiscos.exceptions.BusinessLogicException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Camilo Andres Salinas Martinez
 */
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@RequestScoped
public class TransaccionEnvioResource 
{
    private static final Logger LOGGER = Logger.getLogger(TransaccionEnvioResource.class.getName());

    @Inject
    private EnvioLogic envioLogic;

    @POST
    @Path("envio")
    public EnvioDTO crearEnvio(@PathParam("transaccionesId") Long transaccionId, EnvioDTO envio) throws BusinessLogicException 
    {
        LOGGER.log(Level.INFO, "transaccionEnvioResource crearEnvio: input: {0}", envio);
        EnvioDTO nuevoEnvioDTO = new EnvioDTO(envioLogic.create(envio.toEntity(), transaccionId));
        LOGGER.log(Level.INFO, "transaccionEnvioResource crearEnvio: output: {0}", nuevoEnvioDTO);
        return nuevoEnvioDTO;
    }

    @GET
    @Path("envio")
    public EnvioDTO obtenerEnvio(@PathParam("transaccionesId") Long transaccionesId) 
    {
        LOGGER.log(Level.INFO, "TransaccionEnvioResource obtenerEnvio: input: {0}", transaccionesId);
        EnvioEntity entity = envioLogic.getEnvio(transaccionesId);
        if (entity == null) 
            throw new WebApplicationException("El recurso /transacciones/" + transaccionesId + "/envio no existe.", 404);
        EnvioDTO nuevo = new EnvioDTO(entity);
        LOGGER.log(Level.INFO, "TransaccionEnvioResource obtenerEnvio: output: {0}", nuevo);
        return nuevo;
    }

    @PUT
    @Path("envio")
    public EnvioDTO actualizarEnvio(@PathParam("transaccionesId") Long transaccionesId, EnvioDTO envio) throws BusinessLogicException 
    {
        LOGGER.log(Level.INFO, "transaccionEnvioResource actualizarEnvio: input: {0}", envio);
        EnvioDTO nuevoEnvio = new EnvioDTO(envioLogic.updateEnvio(transaccionesId, envio.toEntity()));
        LOGGER.log(Level.INFO, "transaccionEnvioResource actualizarEnvio: output: {0}", nuevoEnvio);
        return nuevoEnvio;
    }

    @DELETE
    @Path("envio")
    public void eliminarEnvio(@PathParam("transaccionesId") Long transaccionesId) throws BusinessLogicException 
    {
        LOGGER.log(Level.INFO, "transaccionEnvioResource eliminarEnvio: input: {0}", transaccionesId);
        envioLogic.deleteEnvio(transaccionesId);
        LOGGER.log(Level.INFO, "transaccionEnvioResource eliminarEnvio: output: {0}", transaccionesId);
    }
}