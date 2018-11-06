/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendadiscos.persistence;

import co.edu.uniandes.csw.tiendadiscos.entities.MedioDePagoEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Kevin Blanco
 */
@Stateless
public class MedioDePagoPersistence {

    @PersistenceContext(unitName = "VinylAppPU")
    protected EntityManager em;
    private static final Logger LOGGER = Logger.getLogger(MedioDePagoPersistence.class.getName());

    public MedioDePagoEntity create(MedioDePagoEntity tarjetaEntity) {
        LOGGER.log(Level.INFO, "Creando una tarjeta nueva");
        em.persist(tarjetaEntity);
        LOGGER.log(Level.INFO, "Saliendo de crear una tarjeta nueva ");
        return tarjetaEntity;
    }

    public MedioDePagoEntity find(Long billingId, Long tarjetaId) {
        LOGGER.log(Level.INFO, "Consultando tarjeta con id={0} del billing con id = " + billingId, tarjetaId);
        TypedQuery<MedioDePagoEntity> q = em.createQuery("select p from TarjetaCreditoEntity p where (p.billing.id = :billingId) and (p.id = :tarjetaId)", MedioDePagoEntity.class);
        q.setParameter("billingId", billingId);
        q.setParameter("tarjetaId", tarjetaId);
        List<MedioDePagoEntity> results = q.getResultList();
        MedioDePagoEntity tarjeta = null;

        if (results == null) {
            tarjeta = null;
        } else if (results.isEmpty()) {
            tarjeta = null;
        } else if (results.size() >= 1) {
            tarjeta = results.get(0);
        }
        LOGGER.log(Level.INFO, "Saliendo de consultar la tarjeta  con id = {0} del billing con id =" + billingId, tarjetaId);
        return tarjeta;
    }

    public MedioDePagoEntity update(MedioDePagoEntity tarjetaEntity) {
        LOGGER.log(Level.INFO, "Actualizando tarjeta con id={0}", tarjetaEntity.getId());

        LOGGER.log(Level.INFO, "Saliendo de actualizar tarjeta con id = {0}", tarjetaEntity.getId());

        return em.merge(tarjetaEntity);

    }

    public void delete(Long tarjetaId) {
        LOGGER.log(Level.INFO, "Borrando tarjeta con id={0}", tarjetaId);

        MedioDePagoEntity tarjetaEntity = em.find(MedioDePagoEntity.class, tarjetaId);

        em.remove(tarjetaEntity);
        LOGGER.log(Level.INFO, "Saliendo de borrar tarjeta con id = {0}", tarjetaId);

    }

}
