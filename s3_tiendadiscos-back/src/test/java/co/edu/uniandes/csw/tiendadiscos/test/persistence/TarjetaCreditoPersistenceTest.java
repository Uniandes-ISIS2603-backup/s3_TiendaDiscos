/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendadiscos.test.persistence;


import co.edu.uniandes.csw.tiendadiscos.entities.TarjetaCreditoEntity;
import co.edu.uniandes.csw.tiendadiscos.persistence.TarjetaCreditoPersistence;
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
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author Kevin Blanco
 */
@RunWith(Arquillian.class)
public class TarjetaCreditoPersistenceTest {

    @Inject
    private TarjetaCreditoPersistence tarjetaPersistence;

    /**
     * Contexto de Persistencia que se va a utilizar para acceder a la Base de
     * datos por fuera de los métodos que se están probando.
     */
    @PersistenceContext
    private EntityManager em;

    /**
     * Variable para martcar las transacciones del em anterior cuando se
     * crean/borran datos para las pruebas.
     */
    @Inject
    UserTransaction utx;
    
    /**
     * Lista que tiene los datos de prueba.
     */
    private List<TarjetaCreditoEntity> data = new ArrayList<TarjetaCreditoEntity>();

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(TarjetaCreditoEntity.class.getPackage())
                .addPackage(TarjetaCreditoPersistence.class.getPackage())
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
        em.createQuery("delete from TarjetaCreditoEntity").executeUpdate();
    }
    
        /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {

            TarjetaCreditoEntity entity = factory.manufacturePojo(TarjetaCreditoEntity.class);

            em.persist(entity);

            data.add(entity);
        }
    }
    

    /**
     * Prueba para crear una Tarjeta.
     */
    @Test
    public void createTarjetaTest() {
        PodamFactory factory = new PodamFactoryImpl();
        TarjetaCreditoEntity newEntity = factory.manufacturePojo(TarjetaCreditoEntity.class);
        TarjetaCreditoEntity result = tarjetaPersistence.create(newEntity);

        Assert.assertNotNull(result);

        TarjetaCreditoEntity entity = em.find(TarjetaCreditoEntity.class, result.getId());

        Assert.assertEquals(newEntity.getNumero(), entity.getNumero());
    }
    
       /**
     * Prueba para consultar una tarjeta.
     */
    @Test
    public void getTarjetaTest() {
        TarjetaCreditoEntity entity = data.get(0);
        TarjetaCreditoEntity newEntity = tarjetaPersistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getNumero(), newEntity.getNumero());
    }
    
      /**
     * Prueba para eliminar una Tarjeta.
     */
    @Test
    public void deleteBillingTest() {
        TarjetaCreditoEntity entity = data.get(0);
        tarjetaPersistence.delete(entity.getId());
        TarjetaCreditoEntity deleted = em.find(TarjetaCreditoEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * Prueba para actualizar una Tarjeta.
     */
    @Test
    public void updateBillingTest() {
        TarjetaCreditoEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        TarjetaCreditoEntity newEntity = factory.manufacturePojo(TarjetaCreditoEntity.class);

        newEntity.setId(entity.getId());

        tarjetaPersistence.update(newEntity);

        TarjetaCreditoEntity resp = em.find(TarjetaCreditoEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getNumero(), resp.getNumero());
    }

}
