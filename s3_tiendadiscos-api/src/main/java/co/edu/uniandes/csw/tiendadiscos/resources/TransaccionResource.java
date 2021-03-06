/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendadiscos.resources;

import co.edu.uniandes.csw.tiendadiscos.dtos.TransaccionDTO;
import co.edu.uniandes.csw.tiendadiscos.ejb.TransaccionLogic;
import co.edu.uniandes.csw.tiendadiscos.entities.TransaccionEntity;
import co.edu.uniandes.csw.tiendadiscos.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
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
@Path("transacciones")
@Consumes("application/json")
@Produces("application/json")
@RequestScoped
public class TransaccionResource 
{

    private static final Logger LOGGER = Logger.getLogger(TransaccionResource.class.getName());

    private static final String NO_EXISTE = " no existe.";
    
    private static final String INIC_ERROR = "El recurso /transacciones/";
    
    @Inject
    TransaccionLogic logic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    @POST
    public TransaccionDTO createTransaccion(TransaccionDTO transaccion) throws BusinessLogicException 
    {
        LOGGER.log(Level.INFO, "transaccionResource createTransaccion: input: {0}", transaccion);
        TransaccionDTO nuevaTransaccionDTO = new TransaccionDTO(logic.create(transaccion.toEntity()));
        LOGGER.log(Level.INFO, "transaccionResource createTransaccion: output: {0}", nuevaTransaccionDTO);
        return nuevaTransaccionDTO;
    }

    @GET
    @Path("{transaccionesId: \\d+}")
    public TransaccionDTO getTransaccion(@PathParam("transaccionesId") Long transaccionesId) 
    {
        LOGGER.log(Level.INFO, "TransaccionResource getTransaccion: input: {0}", transaccionesId);
        TransaccionEntity entity = logic.get(transaccionesId);
        if (entity == null)
            throw new WebApplicationException(INIC_ERROR + transaccionesId + NO_EXISTE, 404);
        TransaccionDTO nuevo = new TransaccionDTO(entity);
        LOGGER.log(Level.INFO, "TransaccionResource getTransaccion: output: {0}", nuevo);
        return nuevo;
    }

    @GET
    public List<TransaccionDTO> getTransacciones() 
    {
        LOGGER.log(Level.INFO, "TransaccionResource getTransacciones: input: void");
        List<TransaccionDTO> listaTransacciones = listEntityDTO(logic.getTransacciones());
        LOGGER.log(Level.INFO, "TransaccionResource getTransacciones: output: {0}", listaTransacciones);
        return listaTransacciones;
    }

    private List<TransaccionDTO> listEntityDTO(List<TransaccionEntity> entityList) 
    {
        List<TransaccionDTO> list = new ArrayList<>();
        for (TransaccionEntity entity : entityList) 
            list.add(new TransaccionDTO(entity));
        return list;
    }

    @PUT
    @Path("{transaccionesId: \\d+}")
    public TransaccionDTO updateTransaccion(@PathParam("transaccionesId") Long transaccionesId, TransaccionDTO transaccion) throws BusinessLogicException 
    {
        LOGGER.log(Level.INFO, "transaccionResource updateTransaccion: input: {0}", transaccion);
        transaccion.setId(transaccionesId);
        if (logic.get(transaccionesId) == null) 
            throw new WebApplicationException(INIC_ERROR + transaccionesId + NO_EXISTE, 404);
        TransaccionDTO transaccionDTO = new TransaccionDTO(logic.update(transaccion.toEntity(), transaccionesId));
        LOGGER.log(Level.INFO, "transaccionResource putTransaccion: output: {0}", transaccionDTO);
        return transaccionDTO;
    }

    @DELETE
    @Path("{transaccionesId: \\d+}")
    public void deleteTransaccion(@PathParam("transaccionesId") Long transaccionesId) throws BusinessLogicException 
    {
        LOGGER.log(Level.INFO, "transaccionResource deleteTransaccion: input: {0}", transaccionesId);
        TransaccionEntity entity = logic.get(transaccionesId);
        if (entity == null) 
            throw new WebApplicationException(INIC_ERROR + transaccionesId + NO_EXISTE, 404);
        logic.delete(transaccionesId);
        LOGGER.log(Level.INFO, "transaccionResource deleteTransaccion: output: {0}", transaccionesId);
    }

    @Path("{transaccionesId: \\d+}")
    public Class<TransaccionEnvioResource> getTransaccionEnvioResource(@PathParam("transaccionesId") Long transaccionId) 
    {
        if (logic.get(transaccionId) == null) 
            throw new WebApplicationException(INIC_ERROR + transaccionId + NO_EXISTE, 404);
        return TransaccionEnvioResource.class;
    }

    @Path("{transaccionesId: \\d+}/comentarios")
    public Class<ComentarioTransaccionResource> getComentariosResource(@PathParam("transaccionesId") Long transaccionId) 
    {
        if (logic.get(transaccionId) == null) 
            throw new WebApplicationException(INIC_ERROR + transaccionId + NO_EXISTE, 404);
        return ComentarioTransaccionResource.class;
    }
}
