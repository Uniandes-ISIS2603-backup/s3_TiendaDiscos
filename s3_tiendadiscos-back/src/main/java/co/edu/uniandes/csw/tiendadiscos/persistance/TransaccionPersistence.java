package co.edu.uniandes.csw.tiendadiscos.persistance;


import co.edu.uniandes.csw.tiendadiscos.entities.TransaccionEntity;
import static com.sun.xml.internal.ws.spi.db.BindingContextFactory.LOGGER;
import java.util.logging.Level;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import sun.util.logging.PlatformLogger;

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

    @PersistenceContext
    private EntityManager em;

   public TransaccionEntity create(TransaccionEntity transaccionEntity){
       em.persist(transaccionEntity);
       LOGGER.log(Level.INFO, "Saliendo de crear una transaccion nueva");
       return transaccionEntity;
   } 
}
