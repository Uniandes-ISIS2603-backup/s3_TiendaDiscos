package co.edu.uniandes.csw.tiendadiscos.persistance;


import co.edu.uniandes.csw.tiendadiscos.entities.TransaccionEntity;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;



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
   
    @Inject
    private TransaccionPersistence TransaccionPersistence;

    @PersistenceContext(unitName = "VinylAppPU")
    private EntityManager em;
    
    private static final Logger LOGGER = Logger.getLogger(ComentarioPersistence.class.getName());

   public TransaccionEntity create(TransaccionEntity transaccionEntity){
       LOGGER.log(Level.INFO, "Creando una transaccion nueva");
       em.persist(transaccionEntity);
       LOGGER.log(Level.INFO, "Saliendo de crear una transaccion nueva");
       return transaccionEntity;
   } 
}
