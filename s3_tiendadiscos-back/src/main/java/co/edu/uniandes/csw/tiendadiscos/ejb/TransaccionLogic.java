/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendadiscos.ejb;

import co.edu.uniandes.csw.tiendadiscos.entities.TransaccionEntity;
import co.edu.uniandes.csw.tiendadiscos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.tiendadiscos.persistence.EnvioPersistence;
import co.edu.uniandes.csw.tiendadiscos.persistence.TransaccionPersistence;
import co.edu.uniandes.csw.tiendadiscos.persistence.UsuarioPersistence;
import co.edu.uniandes.csw.tiendadiscos.persistence.ViniloPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Laura Isabella Forero Camacho
 */
@Stateless
public class TransaccionLogic {

    private static final Logger LOGGER = Logger.getLogger(TransaccionLogic.class.getName());
    @Inject
    public TransaccionPersistence transaccionPersistence;

    @Inject
    private ViniloPersistence viniloPersistence;

    @Inject
    private UsuarioPersistence usuarioPersistence;

    @Inject
    private EnvioPersistence envioPersistence;

    public TransaccionEntity create(TransaccionEntity entity) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Entidad" + entity.getId());
        if (entity.getEstado() == null || entity.getFormaDePago() == null
                || entity.getUsuarioComprador() == null || entity.getUsuarioVendedor() == null
                || entity.getVinilo() == null) {
            throw new BusinessLogicException("No se aceptan valores nulos");
        }
        if (entity.getEstado().isEmpty() || entity.getFormaDePago().isEmpty()) {
            throw new BusinessLogicException("El estado o forma de pago no puede ser nulo");
        }
        if (usuarioPersistence.find(entity.getUsuarioComprador().getId()) == null || usuarioPersistence.find(entity.getUsuarioVendedor().getId()) == null) {
            throw new BusinessLogicException("Una transaccion es sobre usuarios existentes");
        }
        if (viniloPersistence.find(entity.getVinilo().getId()) == null) {
            throw new BusinessLogicException("El vinilo no existe");
        }

        return transaccionPersistence.create(entity);
    }

    public TransaccionEntity get(Long transaccionId) 
    {
        return transaccionPersistence.find(transaccionId);
    }

    public List<TransaccionEntity> getTransacciones() 
    {
        return transaccionPersistence.findAll();
    }

    public TransaccionEntity update(TransaccionEntity entity, Long transaccionId) throws BusinessLogicException 
    {
        TransaccionEntity temp = transaccionPersistence.find(transaccionId);
        if (temp == null) 
            throw new BusinessLogicException("No existe una transacción con este id.");

        if (entity.getEstado() == null || entity.getFormaDePago() == null || entity.getUsuarioComprador() == null || entity.getUsuarioVendedor() == null || entity.getVinilo() == null) 
            throw new BusinessLogicException("No se aceptan valores nulos");
        
        if (entity.getEstado().isEmpty() || entity.getFormaDePago().isEmpty()) 
            throw new BusinessLogicException("El estado o forma de pago no puede ser nulo");
        
        if (usuarioPersistence.find(entity.getUsuarioComprador().getId()) == null || usuarioPersistence.find(entity.getUsuarioVendedor().getId()) == null) 
            throw new BusinessLogicException("Una transaccion es sobre usuarios existentes");
        
        if (viniloPersistence.find(entity.getVinilo().getId()) == null) 
            throw new BusinessLogicException("El vinilo no existe");
        
        transaccionPersistence.update(entity);
        return entity;
    }

    public void delete(Long transaccionId) throws BusinessLogicException {
        TransaccionEntity temp = transaccionPersistence.find(transaccionId);
        if (temp == null) 
            throw new BusinessLogicException("No existe una transacción con este id.");
        
        if (temp.getEnvio() != null) 
            envioPersistence.delete(transaccionId);
        
        transaccionPersistence.delete(transaccionId);
    }

}
