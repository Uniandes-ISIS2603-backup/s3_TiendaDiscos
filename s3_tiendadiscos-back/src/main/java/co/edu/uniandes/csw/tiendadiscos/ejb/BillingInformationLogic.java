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
import java.util.List;
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
     * @param BillingEntity Objeto de BillingEntity con los datos nuevos
     * @param usuarioId id del usuario el cual sera padre del nuevo Billing.
     * @return Objeto de BillingEntity con los datos nuevos y su ID.
     * @throws BusinessLogicException si el usuario con id usuarioId ya tiene
     * asignado un billing
     *
     */
    public BillingInformationEntity createBilling(Long usuariosId, BillingInformationEntity billing) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de creación del billing");
        UsuarioEntity usuario = usuarioPersistence.find(usuariosId);
        if (usuario == null) {
            //Esta exception la produce usuario BORRAR
            throw new BusinessLogicException("No existe el usuario con ese id");

        } if (usuario.getBillingInformation() == null) {
            throw new BusinessLogicException("El usuario ya tiene una Billing asignada");
        }
        usuario.setBillingInformation(billing);
        LOGGER.log(Level.INFO, "Termina proceso de creación del billing");

        return persistence.create(billing);

    }

    public BillingInformationEntity getBilling(Long usuariosId){
        UsuarioEntity usuario = usuarioPersistence.find(usuariosId);
        
        
        //Usuario nul necesito la logic del usuario para poder probar
        // Error 
            if (usuario == null) {
                throw new WebApplicationException("pailas", 404);
            }
        BillingInformationEntity billing = usuario.getBillingInformation();
        Long billingId = billing.getId();
        LOGGER.log(Level.INFO, "Inicia proceso de consultar el billing con id = {0}", billingId);
        if (billing == null) {
            LOGGER.log(Level.SEVERE, "El usuario con el id = {0} no tiene billing", billingId);
        }
        return billing;
    }
    
    //BILLING ID ???
    public BillingInformationEntity updateBilling(Long usuariosId, BillingInformationEntity billing) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar el billing del usuario con id = {0}", usuariosId);
        
        if(usuarioPersistence.find(usuariosId).getBillingInformation()== null){
            throw new BusinessLogicException("El usuario no tiene asociado un billing");
        }
       
        BillingInformationEntity newEntity = persistence.update(billing);
        
        //Usuario maneja exception de que exista 
        
        LOGGER.log(Level.INFO, "Termina proceso de actualizar la editorial con id = {0}", newEntity.getId());
        return newEntity;
    }
    
     public void deleteBilling(Long usuariosId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar el billing  del usuario con id = {0}", usuariosId);
        persistence.delete(usuarioPersistence.find(usuariosId).getBillingInformation().getId());
        LOGGER.log(Level.INFO, "Termina proceso de borrar el billing del usuario con id  = {0}", usuariosId);
    }
}
