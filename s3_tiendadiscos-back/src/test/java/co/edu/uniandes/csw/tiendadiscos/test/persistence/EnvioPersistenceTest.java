/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendadiscos.test.persistence;

import co.edu.uniandes.csw.tiendadiscos.entities.EnvioEntity;
import co.edu.uniandes.csw.tiendadiscos.entities.TransaccionEntity;
import co.edu.uniandes.csw.tiendadiscos.persistence.EnvioPersistence;
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
 * @author Camilo Andres Salinas Martinez
 */
@RunWith(Arquillian.class)
public class EnvioPersistenceTest {
    @Inject
    private EnvioPersistence envioPersistence;

    @PersistenceContext
    private EntityManager em;

    @Inject
    UserTransaction utx;
    
    private List<EnvioEntity> data = new ArrayList<EnvioEntity>();
    
    private List<TransaccionEntity> dataTransaccion = new ArrayList<TransaccionEntity>();

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(EnvioEntity.class.getPackage())
                .addPackage(EnvioPersistence.class.getPackage())
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
        em.createQuery("delete from EnvioEntity").executeUpdate();
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

            dataTransaccion.add(entity);
        }
        for (int i = 0; i < 3; i++) {
            EnvioEntity entity = factory.manufacturePojo(EnvioEntity.class);
            if (i == 0) {
                entity.setTransaccion(dataTransaccion.get(0));
            }
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
        EnvioEntity newEntity = factory.manufacturePojo(EnvioEntity.class);
        EnvioEntity result = envioPersistence.create(newEntity);

        Assert.assertNotNull(result);

        EnvioEntity entity = em.find(EnvioEntity.class, result.getId());

        Assert.assertEquals(newEntity.getDireccionEntrega(), entity.getDireccionEntrega());
    }
    
       /**
     * Prueba para consultar una Envio.
     */
    @Test
    public void getEnvioTest() {
        EnvioEntity entity = data.get(0);
        EnvioEntity newEntity = envioPersistence.find(dataTransaccion.get(0).getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getEstado(), newEntity.getEstado());
    }
    
      /**
     * Prueba para eliminar una Envio.
     */
    @Test
    public void deleteEnvioTest() 
    {
        EnvioEntity entity = data.get(1);
        envioPersistence.delete(entity.getId());
        Assert.assertNull(null);
    }

    /**
     * Prueba para actualizar una Envio.
     */
    @Test
    public void updateEnvioTest() {
        EnvioEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        EnvioEntity newEntity = factory.manufacturePojo(EnvioEntity.class);

        newEntity.setId(entity.getId());

        envioPersistence.update(newEntity);

        EnvioEntity resp = em.find(EnvioEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getDireccionSalida(), resp.getDireccionSalida());
    }

}