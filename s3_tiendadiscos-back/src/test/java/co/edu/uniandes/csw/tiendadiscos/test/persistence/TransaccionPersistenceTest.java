/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendadiscos.test.persistence;
import co.edu.uniandes.csw.tiendadiscos.entities.TransaccionEntity;
import co.edu.uniandes.csw.tiendadiscos.persistance.TransaccionPersistence;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;
 /**
 *
 * @author estudiante
 */
@RunWith(Arquillian.class)
public class TransaccionPersistenceTest {
     @Inject
    private TransaccionPersistence transaccionPersistence;
     @PersistenceContext
    private EntityManager em;
     @Test
    public void createTransaccionTest(){
        PodamFactory factory= new PodamFactoryImpl();
        TransaccionEntity newEntity= factory.manufacturePojo(TransaccionEntity.class);
        TransaccionEntity result= transaccionPersistence.create(newEntity);
        Assert.assertNotNull(result);
    }
    
}
