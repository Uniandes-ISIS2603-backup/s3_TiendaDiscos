/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendadiscos.persistence;

import co.edu.uniandes.csw.tiendadiscos.entities.TarjetaCreditoEntity;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Kevin Blanco
 */
@Stateless
public class TarjetaCreditoPersistence {

    @PersistenceContext(unitName = "VinylAppPU")
    protected EntityManager em;
    private static final Logger LOGGER = Logger.getLogger(TarjetaCreditoPersistence.class.getName());

    public TarjetaCreditoEntity create(TarjetaCreditoEntity tarjetaEntity) {
        LOGGER.log(Level.INFO, "Creando una tarjeta nueva");
        em.persist(tarjetaEntity);
        LOGGER.log(Level.INFO, "Saliendo de crear una tarjeta nueva ");
        return tarjetaEntity;
    }
    
     public TarjetaCreditoEntity find(Long tarjetaId) {
        LOGGER.log(Level.INFO, "Consultando tarjeta con id={0}", tarjetaId);
        return em.find(TarjetaCreditoEntity.class, tarjetaId);

    }

    public TarjetaCreditoEntity update(TarjetaCreditoEntity tarjetaEntity) {
        LOGGER.log(Level.INFO, "Actualizando tarjeta con id={0}", tarjetaEntity.getId());

        LOGGER.log(Level.INFO, "Saliendo de actualizar tarjeta con id = {0}", tarjetaEntity.getId());

        return em.merge(tarjetaEntity);

    }

    public void delete(Long tarjetaId) {
        LOGGER.log(Level.INFO, "Borrando tarjeta con id={0}", tarjetaId);
        TarjetaCreditoEntity tarjetaEntity = find(tarjetaId);
        em.remove(tarjetaEntity);
        LOGGER.log(Level.INFO, "Saliendo de borrar tarjeta con id = {0}", tarjetaId);

    }

    
}
