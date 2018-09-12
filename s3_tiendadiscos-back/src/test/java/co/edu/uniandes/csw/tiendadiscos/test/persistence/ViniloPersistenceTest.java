/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendadiscos.test.persistence;


import co.edu.uniandes.csw.tiendadiscos.entities.ViniloEntity;
import co.edu.uniandes.csw.tiendadiscos.persistence.ViniloPersistence;
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
/**
 *
 * @author Andrés Hernández
 */
@RunWith(Arquillian.class)
public class ViniloPersistenceTest {
    
    @Inject
    private ViniloPersistence viniloPersistence;
    
    @PersistenceContext
    private EntityManager em;


    @Inject
    UserTransaction utx;
    
    private List<ViniloEntity> data = new ArrayList<ViniloEntity>();
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ViniloEntity.class.getPackage())
                .addPackage(ViniloPersistence.class.getPackage())
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

    /**
     * Limpia las tablas que están implicadas en la prueba.
     */
    private void clearData() {
        em.createQuery("delete from ViniloEntity").executeUpdate();
    }
    
        /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {

            ViniloEntity entity = factory.manufacturePojo(ViniloEntity.class);

            em.persist(entity);

            data.add(entity);
        }
    }
    

    /**
     * Prueba para crear una Envio.
     */
    @Test
    public void createEnvioTest() {
        PodamFactory factory = new PodamFactoryImpl();
        ViniloEntity newEntity = factory.manufacturePojo(ViniloEntity.class);
        ViniloEntity result = viniloPersistence.create(newEntity);

        Assert.assertNotNull(result);

        ViniloEntity entity = em.find(ViniloEntity.class, result.getId());

        Assert.assertEquals(newEntity.getNombre(), entity.getNombre());
    }
}
