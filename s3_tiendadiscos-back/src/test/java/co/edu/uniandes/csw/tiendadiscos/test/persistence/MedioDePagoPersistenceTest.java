/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendadiscos.test.persistence;

import co.edu.uniandes.csw.tiendadiscos.entities.BillingInformationEntity;
import co.edu.uniandes.csw.tiendadiscos.entities.MedioDePagoEntity;
import co.edu.uniandes.csw.tiendadiscos.persistence.MedioDePagoPersistence;
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
public class MedioDePagoPersistenceTest {

    @Inject
    private MedioDePagoPersistence tarjetaPersistence;

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
     * Listas que tiene los datos de prueba.
     */
    private List<BillingInformationEntity> dataBilling = new ArrayList<BillingInformationEntity>();

    private List<MedioDePagoEntity> dataTarjeta = new ArrayList<MedioDePagoEntity>();

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(MedioDePagoEntity.class.getPackage())
                .addPackage(MedioDePagoPersistence.class.getPackage())
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
            dataBilling.add(entity);
        }
        for (int i = 0; i < 3; i++) {
            MedioDePagoEntity entity = factory.manufacturePojo(MedioDePagoEntity.class);
            if (i == 0) {
                entity.setBilling(dataBilling.get(0));
            }
            em.persist(entity);
            dataTarjeta.add(entity);
        }
    }

    /**
     * Prueba para crear una Tarjeta.
     */
    @Test
    public void createTarjetaTest() {
        PodamFactory factory = new PodamFactoryImpl();
        MedioDePagoEntity newEntity = factory.manufacturePojo(MedioDePagoEntity.class);
        MedioDePagoEntity result = tarjetaPersistence.create(newEntity);

        Assert.assertNotNull(result);

        MedioDePagoEntity entity = em.find(MedioDePagoEntity.class, result.getId());

        Assert.assertEquals(newEntity.getNumero(), entity.getNumero());
        Assert.assertEquals(newEntity.getCvc(), entity.getCvc());
        Assert.assertEquals(newEntity.getName(), entity.getName());
        Assert.assertEquals(newEntity.getFechaVencimiento(), entity.getFechaVencimiento());

    }

    /**
     * Prueba para consultar una tarjeta.
     */
    @Test
    public void getTarjetaTest() {
        MedioDePagoEntity entity = dataTarjeta.get(0);
        MedioDePagoEntity newEntity = em.find(MedioDePagoEntity.class, entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(newEntity.getNumero(), entity.getNumero());
        Assert.assertEquals(newEntity.getCvc(), entity.getCvc());
        Assert.assertEquals(newEntity.getName(), entity.getName());
        Assert.assertEquals(newEntity.getFechaVencimiento(), entity.getFechaVencimiento());
    }

    /**
     * Prueba para eliminar una Tarjeta.
     */
    @Test
    public void deleteBillingTest() {
        MedioDePagoEntity entity = dataTarjeta.get(0);
        tarjetaPersistence.delete(entity.getId());
        MedioDePagoEntity deleted = em.find(MedioDePagoEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * Prueba para actualizar una Tarjeta.
     */
    @Test
    public void updateBillingTest() {
        MedioDePagoEntity entity = dataTarjeta.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        MedioDePagoEntity newEntity = factory.manufacturePojo(MedioDePagoEntity.class);

        newEntity.setId(entity.getId());

        tarjetaPersistence.update(newEntity);

        MedioDePagoEntity resp = em.find(MedioDePagoEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getNumero(), resp.getNumero());
        Assert.assertEquals(newEntity.getCvc(), resp.getCvc());
        Assert.assertEquals(newEntity.getName(), resp.getName());
        Assert.assertEquals(newEntity.getFechaVencimiento(), resp.getFechaVencimiento());

    }

}
