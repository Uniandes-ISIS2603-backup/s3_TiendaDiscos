/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendadiscos.ejb;

import co.edu.uniandes.csw.tiendadiscos.entities.BillingInformationEntity;
import co.edu.uniandes.csw.tiendadiscos.entities.MedioDePagoEntity;
import co.edu.uniandes.csw.tiendadiscos.entities.UsuarioEntity;
import co.edu.uniandes.csw.tiendadiscos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.tiendadiscos.persistence.MedioDePagoPersistence;
import co.edu.uniandes.csw.tiendadiscos.persistence.UsuarioPersistence;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Kevin Blanco
 */
@Stateless
public class MedioDePagoLogic {

    private static final Logger LOGGER = Logger.getLogger(BillingInformationLogic.class.getName());

    @Inject
    private MedioDePagoPersistence persistence;

    @Inject
    private UsuarioPersistence usuarioPersistence;

    public MedioDePagoEntity createTarjeta(Long usuariosId, MedioDePagoEntity tarjeta) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de creación de la tarjeta");
        UsuarioEntity usuario = usuarioPersistence.find(usuariosId);
        if (usuario == null) {
            //Esta exception la produce usuario BORRAR
            throw new BusinessLogicException("No existe el usuario con ese id");
        }
        BillingInformationEntity billing = usuario.getBillingInformation();

        if (billing == null) {
            throw new BusinessLogicException("El usuario no tiene billing asignado");
        }

        if (!tarjeta.getNumero().equals(tarjeta.getNumeroVerificacion())) {
            throw new BusinessLogicException("Numero de verificacion no coincide");

        }
        if (tarjeta.getFechaVencimiento().compareTo(new Date()) < 0) {
            throw new BusinessLogicException("Fecha invalida");
        }

        tarjeta.setBilling(billing);
        LOGGER.log(Level.INFO, "Termina proceso de creación de la tarjeta");
        return persistence.create(tarjeta);

    }

    public MedioDePagoEntity getTarjeta(Long usuariosId, Long tarjetaId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar la tarjeta con id = {0}", tarjetaId);
        
        MedioDePagoEntity tarjeta = persistence.find(usuarioPersistence.find(usuariosId).getBillingInformation().getId(), tarjetaId);
        if (tarjeta == null) {
            LOGGER.log(Level.SEVERE, "La tarjeta con el id = {0} no existe", tarjetaId);
        }
        return tarjeta;
    }

    public List<MedioDePagoEntity> getTarjetas(Long usuariosId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar las tarjetas del usuario con id = {0}", usuariosId);
        UsuarioEntity usuario = usuarioPersistence.find(usuariosId);

        BillingInformationEntity billing = usuario.getBillingInformation();

        if (billing == null) {
            throw new BusinessLogicException("El usuario con " + usuariosId + "no tiene un billing asociado");
        }
        return billing.getTarjetas();
    }

    public MedioDePagoEntity updateTarjeta(Long usuariosId, Long tarjetaId, MedioDePagoEntity tarjeta) throws BusinessLogicException {

        LOGGER.log(Level.INFO, "Inicia proceso de actualizar la tarjeta con id" + tarjetaId + " del usuario con id = {0}", usuariosId);
        UsuarioEntity usuario = usuarioPersistence.find(usuariosId);
        BillingInformationEntity billing = usuario.getBillingInformation();

        if (billing == null) {
            throw new BusinessLogicException("El usuario no tiene billing asignado");
        }

        if (persistence.find(billing.getId(), tarjetaId) == null) {
            throw new BusinessLogicException("La tarjeta a actualizar no existe");
        }

        if (!tarjeta.getNumero().equals(tarjeta.getNumeroVerificacion())) {
            throw new BusinessLogicException("Numero de verificacion no coincide");

        }
        if (tarjeta.getFechaVencimiento().compareTo(new Date()) < 0) {
            throw new BusinessLogicException("Fecha invalida");
        }

        MedioDePagoEntity newEntity = persistence.update(tarjeta);

        //Usuario maneja exception de que exista 
        LOGGER.log(Level.INFO, "Termina proceso de actualizar la tarjeta con id = {0}", newEntity.getId());
        return newEntity;

    }

    public void deleteTarjeta(Long usuariosId, Long tarjetaId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar la tarjeta con id " + tarjetaId+" del usuario con id = {0}", usuariosId);
        BillingInformationEntity billing = usuarioPersistence.find(usuariosId).getBillingInformation();
        MedioDePagoEntity tarjeta = persistence.find(billing.getId(),tarjetaId);
        
        if (tarjeta == null){
            throw new BusinessLogicException("La tarjeta con id " + tarjetaId + "no existe");
        }
        
        persistence.delete(tarjetaId);
        LOGGER.log(Level.INFO, "Termina proceso de borrar la tarjeta con id " + tarjetaId+" del usuario con id  = {0}", usuariosId);
    }
}