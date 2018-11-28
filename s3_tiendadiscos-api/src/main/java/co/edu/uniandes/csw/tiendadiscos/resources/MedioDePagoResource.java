/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendadiscos.resources;

import co.edu.uniandes.csw.tiendadiscos.dtos.MedioDePagoDTO;
import co.edu.uniandes.csw.tiendadiscos.ejb.MedioDePagoLogic;
import co.edu.uniandes.csw.tiendadiscos.entities.MedioDePagoEntity;
import co.edu.uniandes.csw.tiendadiscos.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
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
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;

/**
 *
 * @author Kevin Blanco
 */
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class MedioDePagoResource {

    private static final Logger LOGGER = Logger.getLogger(MedioDePagoResource.class.getName());

    private static final String INIC_ERROR = "El recurso /usuarios/";
    
    private static final String NO_EXISTE = " no existe.";
    
    @Inject
    private MedioDePagoLogic tarjetaLogic;

    @POST
    public MedioDePagoDTO createTarjetaCredito(@PathParam("usuariosId") Long usuariosId, MedioDePagoDTO tarjeta) throws BusinessLogicException 
    {
        LOGGER.log(Level.INFO, "TarjetaResource createTarjeta: input: {0}", tarjeta);
        MedioDePagoDTO nuevoTarjetaDTO = new MedioDePagoDTO(tarjetaLogic.createTarjeta(usuariosId, tarjeta.toEntity()));
        LOGGER.log(Level.INFO, "TarjetaResource createTarjeta: output: {0}", nuevoTarjetaDTO);
        return nuevoTarjetaDTO;
    }

    /**
     * tarjetas de credito del billing del usuario
     *
     * @param usuariosId
     * @return
     * @throws BusinessLogicException
     */
    @GET
    public List<MedioDePagoDTO> getTarjetasCredito(@PathParam("usuariosId") Long usuariosId) throws BusinessLogicException 
    {
        LOGGER.log(Level.INFO, "TarjetaResource getReviews: input: {0}", usuariosId);
        List<MedioDePagoDTO> listaDTOs = listEntity2DTO(tarjetaLogic.getTarjetas(usuariosId));
        LOGGER.log(Level.INFO, "EditorialBooksResource getBooks: output: {0}", listaDTOs);
        return listaDTOs;
    }

    /**
     * tarjeta en especifico del usuario
     *
     * @param usuariosId id usuario
     * @param tarjetaCreditoId id tarjeta
     * @return
     * @throws co.edu.uniandes.csw.tiendadiscos.exceptions.BusinessLogicException
     */
    @GET
    @Path("{tarjetaCreditoId: \\d+}") 
    public MedioDePagoDTO getTarjetaCredito(@PathParam("usuariosId") Long usuariosId, @PathParam("tarjetaCreditoId") Long tarjetaCreditoId) throws BusinessLogicException
    {
        LOGGER.log(Level.INFO, "TarjetaCreditoResource getTarjetaCredito: input: {0}", tarjetaCreditoId);
        MedioDePagoEntity entity = tarjetaLogic.getTarjeta(usuariosId, tarjetaCreditoId);
        if (entity == null)
            throw new WebApplicationException(INIC_ERROR + usuariosId + "/billing/tarjetasDeCredito" + tarjetaCreditoId + NO_EXISTE, 404);
        MedioDePagoDTO tarjetaDTO = new MedioDePagoDTO(entity);
        LOGGER.log(Level.INFO, "TarjetaCreditoResource getTarjetaCredito: output: {0}", tarjetaDTO);
        return tarjetaDTO;
    }


    /**
     * eliminar tarjeta en especifico del usuario
     *
     * @param usuariosId id usuario
     * @param tarjetaCreditoId id tarjeta
     */
    @DELETE
    @Path("{tarjetaId: \\d+}")
    public void deleteTarjetaCredito(@PathParam("usuariosId") Long usuariosId, @PathParam("tarjetaId") Long tarjetaCreditoId) throws BusinessLogicException 
    {
        MedioDePagoEntity entity = tarjetaLogic.getTarjeta(usuariosId, tarjetaCreditoId);
        if (entity == null) 
            throw new WebApplicationException(INIC_ERROR + usuariosId + "/billing/tarjetasDeCredito/" + tarjetaCreditoId + NO_EXISTE, 404);
        tarjetaLogic.deleteTarjeta(usuariosId, tarjetaCreditoId);
    }

    /**
     * Lista de entidades a DTO.
     *
     * Este método convierte una lista de objetos TarjetaCreditoEntity a una
     * lista de objetos TarjetaCreditoDTO (json)
     *
     * @param entityList corresponde a la lista de tarjetas de tipo Entity que
     * vamos a convertir a DTO.
     * @return la lista de tarjetas en forma DTO (json)
     */
    private List<MedioDePagoDTO> listEntity2DTO(List<MedioDePagoEntity> entityList) 
    {
        List<MedioDePagoDTO> list = new ArrayList<>();
        for (MedioDePagoEntity entity : entityList) 
            list.add(new MedioDePagoDTO(entity));
        return list;
    }
    private ExecutorService executorService = java.util.concurrent.Executors.newCachedThreadPool();

    @PUT
    @Path(value = "{tarjetaId: \\d+}")
    public void updateTarjetaCredito(@Suspended final AsyncResponse asyncResponse, @PathParam(value = "usuariosId") final Long usuariosId, @PathParam(value = "tarjetaId") final Long tarjetaCreditoId, final MedioDePagoDTO tarjeta) 
    {
        executorService.submit(new Runnable() {
            public void run() {
                try {
                    asyncResponse.resume(doUpdateTarjetaCredito(usuariosId, tarjetaCreditoId, tarjeta));
                } catch (BusinessLogicException ex) {
                    Logger.getLogger(MedioDePagoResource.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    private MedioDePagoDTO doUpdateTarjetaCredito(@PathParam("usuariosId") Long usuariosId, @PathParam("tarjetaId") Long tarjetaCreditoId, MedioDePagoDTO tarjeta) throws BusinessLogicException 
    {
        LOGGER.log(Level.INFO, "ReviewResource updateTarjetaCredito: input: usuariosId: {0} , tarjetaCreditoId: {1} , review:{2}", new Object[]{usuariosId, tarjetaCreditoId, tarjeta});
        MedioDePagoEntity entity = tarjetaLogic.getTarjeta(usuariosId, tarjetaCreditoId);
        if (entity == null) 
            throw new WebApplicationException(INIC_ERROR + usuariosId + "/billing/tarjetasDeCredito/" + tarjetaCreditoId + NO_EXISTE, 404);
        MedioDePagoDTO tarjetaDTO = new MedioDePagoDTO(tarjetaLogic.updateTarjeta(usuariosId, tarjetaCreditoId, tarjeta.toEntity()));
        LOGGER.log(Level.INFO, "TarjetaResource updateTarjetaCredito: output:{0}", tarjetaDTO);
        return tarjetaDTO;
    }

}