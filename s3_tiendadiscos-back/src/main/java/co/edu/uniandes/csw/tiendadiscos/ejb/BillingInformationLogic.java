/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendadiscos.ejb;

import co.edu.uniandes.csw.tiendadiscos.entities.BillingInformationEntity;
import co.edu.uniandes.csw.tiendadiscos.entities.UsuarioEntity;
import co.edu.uniandes.csw.tiendadiscos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.tiendadiscos.persistence.BillingInformationPersistence;
import co.edu.uniandes.csw.tiendadiscos.persistence.UsuarioPersistence;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.WebApplicationException;

/**
 *
 * @author Kevin Blanco
 */
@Stateless
public class BillingInformationLogic {

    private static final Logger LOGGER = Logger.getLogger(BillingInformationLogic.class.getName());

    @Inject
    private BillingInformationPersistence persistence;

    @Inject
    public UsuarioPersistence usuarioPersistence;

    /**
     * Se encarga de crear un Billing en la base de datos.
     *
     * @param billingEntity Objeto de BillingEntity con los datos nuevos
     * @param usuariosId id del usuario el cual sera padre del nuevo Billing.
     * @return Objeto de BillingEntity con los datos nuevos y su ID.
     * @throws BusinessLogicException si el usuario con id usuarioId ya tiene
     * asignado un billing
     *                                Si el usuario con id usuarioId no existe.
     *
     */
    public BillingInformationEntity createBilling(Long usuariosId, BillingInformationEntity billingEntity) throws BusinessLogicException 
    {
        LOGGER.log(Level.INFO, "Inicia proceso de creación del billing");
        UsuarioEntity usuarioEntity = usuarioPersistence.find(usuariosId);
        if (usuarioEntity == null) 
            throw new BusinessLogicException("No existe el usuario con ese id");
        try 
        {
            Integer.parseInt(billingEntity.getCuentaAhorro());
        }
        catch(NumberFormatException e) 
        {    
            throw new BusinessLogicException("No es un numero de cuenta valido");
        }
        if(persistence.findBillingByUserId(usuariosId)!=null)
            throw new BusinessLogicException("El Usuario ya tiene billings");
        
        billingEntity.setUsuario(usuarioEntity);        
        
        LOGGER.log(Level.INFO, "Termina proceso de creación del billing");

        return persistence.create(billingEntity);

    }

    public BillingInformationEntity getBilling(Long usuarioId) {
        UsuarioEntity usuario = usuarioPersistence.find(usuarioId);

        //Usuario nul necesito la logic del usuario para poder probar
        // Error 
        if (usuario == null) {
            throw new WebApplicationException("El Usuario con el id"+usuarioId+"no existe.", 404);
        }
        BillingInformationEntity billing= persistence.findBillingByUserId(usuarioId);
        if(billing==null){
            throw new WebApplicationException("El Usuario con el id"+usuarioId+"no tiene billing.", 404);
        }
        return billing;
    }

    //BILLING ID ???
    public BillingInformationEntity updateBilling(Long usuariosId, BillingInformationEntity billingEntity) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar el billing del usuario con id = {0}", usuariosId);
        UsuarioEntity usuario = usuarioPersistence.find(usuariosId);
        if (usuario.getBillingInformation() == null)
            throw new BusinessLogicException("El usuario no tiene asociado un billing");
        try{
            Integer.parseInt(billingEntity.getCuentaAhorro());
        }
        catch(NumberFormatException e) 
        {    
            throw new BusinessLogicException("No es un numero de cuenta valido.");
        }
        billingEntity.setId(usuario.getBillingInformation().getId());
        billingEntity.setUsuario(usuario);
        BillingInformationEntity newEntity = persistence.update(billingEntity);
        //Usuario maneja exception de que exista 
        LOGGER.log(Level.INFO, "Termina proceso de actualizar el billing con id = {0}", newEntity.getId());
        return newEntity;
    }

    public void deleteBilling(Long usuariosId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar el billing  del usuario con id = {0}", usuariosId);
        BillingInformationEntity billing = usuarioPersistence.find(usuariosId).getBillingInformation();
        if (billing == null) {
            throw new BusinessLogicException("El usuario no tiene un billing asociado");
        }

        persistence.delete(billing.getId());
        LOGGER.log(Level.INFO, "Termina proceso de borrar el billing del usuario con id  = {0}", usuariosId);
    }
}