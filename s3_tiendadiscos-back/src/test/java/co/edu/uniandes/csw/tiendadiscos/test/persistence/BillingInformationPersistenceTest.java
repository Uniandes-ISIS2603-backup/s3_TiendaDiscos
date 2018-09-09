/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendadiscos.test.persistence;

import co.edu.uniandes.csw.tiendadiscos.entities.BillingInformationEntity;
import co.edu.uniandes.csw.tiendadiscos.persistence.BillingInformationPersistence;
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
public class BillingInformationPersistenceTest {

    @Inject
    private BillingInformationPersistence billingPersistence;

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
    private List<BillingInformationEntity> data = new ArrayList<BillingInformationEntity>();

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(BillingInformationEntity.class.getPackage())
                .addPackage(BillingInformationPersistence.class.getPackage())
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
        em.createQuery("delete from BillingInformationEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {

            BillingInformationEntity entity = factory.manufacturePojo(BillingInformationEntity.class);

            em.persist(entity);

            data.add(entity);
        }
    }

    /**
     * Prueba para crear un billing.
     */
    @Test
    public void createBillingTest() {
        PodamFactory factory = new PodamFactoryImpl();
        BillingInformationEntity newEntity = factory.manufacturePojo(BillingInformationEntity.class);
        BillingInformationEntity result = billingPersistence.create(newEntity);

        Assert.assertNotNull(result);

        BillingInformationEntity entity = em.find(BillingInformationEntity.class, result.getId());

        Assert.assertEquals(newEntity.getCuentaAhorro(), entity.getCuentaAhorro());
    }

    /**
     * Prueba para consultar un billing.
     */
    @Test
    public void getBillingTest() {
        BillingInformationEntity entity = data.get(0);
        BillingInformationEntity newEntity = billingPersistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getCuentaAhorro(), newEntity.getCuentaAhorro());
    }
    
      /**
     * Prueba para eliminar un Billing.
     */
    @Test
    public void deleteBillingTest() {
        BillingInformationEntity entity = data.get(0);
        billingPersistence.delete(entity.getId());
        BillingInformationEntity deleted = em.find(BillingInformationEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * Prueba para actualizar un billing.
     */
    @Test
    public void updateBillingTest() {
        BillingInformationEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        BillingInformationEntity newEntity = factory.manufacturePojo(BillingInformationEntity.class);

        newEntity.setId(entity.getId());

        billingPersistence.update(newEntity);

        BillingInformationEntity resp = em.find(BillingInformationEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getCuentaAhorro(), resp.getCuentaAhorro());
    }

}
