/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendadiscos.ejb;

import co.edu.uniandes.csw.tiendadiscos.entities.BillingInformationEntity;
import co.edu.uniandes.csw.tiendadiscos.entities.TarjetaCreditoEntity;
import co.edu.uniandes.csw.tiendadiscos.entities.UsuarioEntity;
import co.edu.uniandes.csw.tiendadiscos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.tiendadiscos.persistence.TarjetaCreditoPersistence;
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
public class TarjetaCreditoLogic {

    private static final Logger LOGGER = Logger.getLogger(BillingInformationLogic.class.getName());

    @Inject
    private TarjetaCreditoPersistence persistence;

    @Inject
    private UsuarioPersistence usuarioPersistence;

    public TarjetaCreditoEntity createTarjeta(Long usuariosId, TarjetaCreditoEntity tarjeta) throws BusinessLogicException {
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

    public TarjetaCreditoEntity getTarjeta(Long usuariosId, Long tarjetaId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar la tarjeta con id = {0}", tarjetaId);
        TarjetaCreditoEntity tarjeta = persistence.find(usuariosId, tarjetaId);
        if (tarjeta == null) {
            LOGGER.log(Level.SEVERE, "La tarjeta con el id = {0} no existe", tarjetaId);
        }
        return tarjeta;
    }

    public List<TarjetaCreditoEntity> getTarjetas(Long usuariosId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar las tarjetas del usuario con id = {0}", usuariosId);
        UsuarioEntity usuario = usuarioPersistence.find(usuariosId);

        return usuario.getBillingInformation().getTarjetas();
    }
    
    

}
