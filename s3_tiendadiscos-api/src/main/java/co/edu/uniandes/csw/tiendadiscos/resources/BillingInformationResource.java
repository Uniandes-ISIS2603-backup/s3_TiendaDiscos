/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendadiscos.resources;

import co.edu.uniandes.csw.tiendadiscos.dtos.BillingInformationDTO;
import co.edu.uniandes.csw.tiendadiscos.dtos.BillingInformationDetailDTO;
import co.edu.uniandes.csw.tiendadiscos.dtos.UsuarioDTO;
import co.edu.uniandes.csw.tiendadiscos.ejb.BillingInformationLogic;
import co.edu.uniandes.csw.tiendadiscos.entities.BillingInformationEntity;
import co.edu.uniandes.csw.tiendadiscos.entities.UsuarioEntity;
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
 * @author Kevin Blanco
 */
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class BillingInformationResource {

    private static final Logger LOGGER = Logger.getLogger(BillingInformationResource.class.getName());

    @Inject
    private BillingInformationLogic billingLogic;

    @GET
    public BillingInformationDetailDTO getBilling(@PathParam("usuariosId") Long usuariosId) {
        LOGGER.log(Level.INFO, "BillingInformationResource getBilling: input: {0}", usuariosId);
        BillingInformationEntity billingEntity = billingLogic.getBilling(usuariosId);
        if (billingEntity == null) {
            throw new WebApplicationException("El recurso /usurios/" + usuariosId + "/billing  no existe.", 404);
        }
        BillingInformationDetailDTO detailDTO = new BillingInformationDetailDTO(billingEntity);
        LOGGER.log(Level.INFO, "BillingInformationResource getBilling: output: {0}", detailDTO.toString());
        return detailDTO;
    }

    @POST
    public BillingInformationDTO createBilling(@PathParam("usuariosId") Long usuariosId, BillingInformationDTO billig) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "BillingInformationResource createBilling: input: {0}", billig.toString());
        BillingInformationDTO nuevoBillingDTO = new BillingInformationDTO(billingLogic.createBilling(usuariosId, billig.toEntity()));
        LOGGER.log(Level.INFO, "BillingInformationResource createBilling: output: {0}", nuevoBillingDTO.toString());
        return nuevoBillingDTO;
    }

    @PUT
    public BillingInformationDetailDTO updateBilling(@PathParam("usuariosId") Long usuariosId, BillingInformationDetailDTO billing) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "BillingResource updateBilling: input: usuariosId: {0} , billing: {1}", new Object[]{usuariosId, billing.toString()});

        BillingInformationEntity entity = billingLogic.getBilling(usuariosId);
        if (entity == null) {
            throw new WebApplicationException("El recurso /usuarios/" + usuariosId + "/billing  no existe.", 404);

        }
        BillingInformationDetailDTO billingDTO = new BillingInformationDetailDTO(billingLogic.updateBilling(usuariosId, billing.toEntity()));
        LOGGER.log(Level.INFO, "BillingResource updateBilling: output:{0}", billingDTO.toString());
        return billingDTO;
    }

    @DELETE
    public void deleteBilling(@PathParam("usuariosId") Long usuariosId) throws BusinessLogicException {
      BillingInformationEntity entity = billingLogic.getBilling(usuariosId);
        if (entity == null) {
            throw new WebApplicationException("El recurso /usuarios/" + usuariosId + "/billing  no existe.", 404);
        }
        billingLogic.deleteBilling(usuariosId);
    }

    @Path("/tarjetasDeCredito")
    public Class<MedioDePagoResource> getTarjetaResource(@PathParam("usuariosId") Long usuariosId) {

        return MedioDePagoResource.class;
    }

}