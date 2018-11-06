/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendadiscos.test.persistence;
import co.edu.uniandes.csw.tiendadiscos.entities.TransaccionEntity;
import co.edu.uniandes.csw.tiendadiscos.persistence.TransaccionPersistence;
import java.util.ArrayList;
import java.util.List;
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
 * @author Laura Isabella Forero Camacho
 */
@RunWith(Arquillian.class)
public class TransaccionPersistenceTest {
     
    
    @Inject
    private TransaccionPersistence transaccionPersistence;
    
     @Inject
    UserTransaction utx;
     
     @PersistenceContext
    private EntityManager em;
     
    /**
     * Lista que tiene los datos de prueba.
     */
    private List<TransaccionEntity> data = new ArrayList<TransaccionEntity>();
     
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
            clearData();
            insertData();
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
        
        TransaccionEntity entity = em.find(TransaccionEntity.class, result.getId());

        Assert.assertEquals(newEntity.getVinilo(), entity.getVinilo());
    }
    
    /**
     * Limpia las tablas que están implicadas en la prueba.
     */
    private void clearData() {
        em.createQuery("delete from TransaccionEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            TransaccionEntity entity = factory.manufacturePojo(TransaccionEntity.class);
            em.persist(entity);
            data.add(entity);
        }
    }
    /**
     * Prueba para consultar una transaccion.
     */
    @Test
    public void getTransaccionTest() {
        TransaccionEntity entity = data.get(0);
        TransaccionEntity newEntity = transaccionPersistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getVinilo(), newEntity.getVinilo());
    }

    /**
     * Prueba para eliminar una transaccion.
     */
    @Test
    public void deleteTransaccionTest() {
        TransaccionEntity entity = data.get(0);
        transaccionPersistence.delete(entity.getId());
        TransaccionEntity deleted = em.find(TransaccionEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * Prueba para actualizar una transaccion.
     */
    @Test
    public void updateTransaccionTest() {
        TransaccionEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        TransaccionEntity newEntity = factory.manufacturePojo(TransaccionEntity.class);

        newEntity.setId(entity.getId());

        transaccionPersistence.update(newEntity);

        TransaccionEntity resp = em.find(TransaccionEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getVinilo(), resp.getVinilo());
    }
    
    
    
}