/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendadiscos.test.persistence;
import co.edu.uniandes.csw.tiendadiscos.entities.TransaccionEntity;
import co.edu.uniandes.csw.tiendadiscos.persistence.TransaccionPersistence;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;
 /**
 *
 * @author estudiante
 */
@RunWith(Arquillian.class)
public class CarritoDeComprasPersistenceTest {
     @Inject
    private TransaccionPersistence transaccionPersistence;
    
     @Inject
    UserTransaction utx;
     
     @PersistenceContext
    private EntityManager em;
     
     @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(TransaccionEntity.class.getPackage())
                .addPackage(TransaccionPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    /**
     * Configuración inicial de la prueba.
     */
    @Before
    public void configTest() {
        try {
            utx.begin();
            em.joinTransaction();
            utx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                utx.rollback();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }
    @Test
    public void createTransaccionTest(){
        PodamFactory factory= new PodamFactoryImpl();
        TransaccionEntity newEntity= factory.manufacturePojo(TransaccionEntity.class);
        TransaccionEntity result= transaccionPersistence.create(newEntity);
        Assert.assertNotNull(result);
    }
    
    
}
