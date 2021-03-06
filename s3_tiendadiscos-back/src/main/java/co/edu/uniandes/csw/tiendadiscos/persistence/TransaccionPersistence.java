package co.edu.uniandes.csw.tiendadiscos.persistence;


import co.edu.uniandes.csw.tiendadiscos.entities.TransaccionEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Laura Isabella Forero Camacho
 */
@Stateless
public class TransaccionPersistence {
   
    @PersistenceContext(unitName = "VinylAppPU")
    protected EntityManager em;
    
    private static final Logger LOGGER = Logger.getLogger(TransaccionPersistence.class.getName());

   public TransaccionEntity create(TransaccionEntity transaccionEntity){
       LOGGER.log(Level.INFO, "Creando una transaccion nueva");
       em.persist(transaccionEntity);
       LOGGER.log(Level.INFO, "Saliendo de crear una transaccion nueva");
       return transaccionEntity;
   } 
   public TransaccionEntity find(Long transaccionId) {
        LOGGER.log(Level.INFO, "Consultando transaccion con id={0}", transaccionId);
        return em.find(TransaccionEntity.class, transaccionId);

    }

   public List<TransaccionEntity> findAll() {
        LOGGER.log(Level.INFO, "Consultando las transacciones");
        TypedQuery query = em.createQuery("select u from TransaccionEntity u", TransaccionEntity.class);
        return query.getResultList();

    }
   
    public TransaccionEntity update(TransaccionEntity transaccionEntity) {
        LOGGER.log(Level.INFO, "Actualizando transaccion con id={0}", transaccionEntity.getId());
        TransaccionEntity updateTransaccion=em.merge(transaccionEntity);
        LOGGER.log(Level.INFO, "Saliendo de actualizar transaccion con id = {0}", transaccionEntity.getId());
        return updateTransaccion;

    }

    public void delete(Long transaccionId) {
        LOGGER.log(Level.INFO, "Borrando transaccion con id={0}", transaccionId);
        TransaccionEntity transaccionEntity = find( transaccionId);
        em.remove(transaccionEntity);
        LOGGER.log(Level.INFO, "Saliendo de borrar transaccion con id = {0}",  transaccionId);

    }
}