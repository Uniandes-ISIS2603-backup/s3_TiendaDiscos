/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendadiscos.test.persistence;

import co.edu.uniandes.csw.tiendadiscos.entities.CancionEntity;
import co.edu.uniandes.csw.tiendadiscos.entities.ViniloEntity;
import co.edu.uniandes.csw.tiendadiscos.persistence.CancionPersistence;
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
 * @author Andrés Hernández
 */
@RunWith(Arquillian.class)
public class CancionPersistenceTest {
    
    @Inject
    private CancionPersistence cancionPersistence;
    
    @PersistenceContext
    private EntityManager em;
    
    @Inject
    UserTransaction utx;
    
    private List<ViniloEntity> dataVinilo = new ArrayList<ViniloEntity>();
    
    private List<CancionEntity> data = new ArrayList<CancionEntity>();
    
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(CancionEntity.class.getPackage())
                .addPackage(CancionPersistence.class.getPackage())
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
        em.createQuery("delete from CancionEntity").executeUpdate();
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
            dataVinilo.add(entity);
        }
        for (int i = 0; i < 3; i++) {
            
            CancionEntity entity = factory.manufacturePojo(CancionEntity.class);
            if (i == 0) {
                entity.setVinilo(dataVinilo.get(0));
            }
            
            em.persist(entity);
            
            data.add(entity);
        }
    }

    /**
     * Prueba para crear una Vinilo.
     */
    @Test
    public void createCancionTest() {
        PodamFactory factory = new PodamFactoryImpl();
        CancionEntity newEntity = data.get(0);
        CancionEntity result = cancionPersistence.create(newEntity);
        
        Assert.assertNotNull(result);
        
        CancionEntity entity = em.find(CancionEntity.class,
                result.getId());
        
        Assert.assertEquals(newEntity.getNombre(), entity.getNombre());
    }

    /**
     * Prueba para consultar la lista de Vinilos.
     */
    @Test
    public void getCancionesTest() {
        List<CancionEntity> list = cancionPersistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (CancionEntity ent : list) {
            boolean found = false;
            for (CancionEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * Prueba para consultar un Usuario.
     */
    @Test
    public void getCancionTest() {
        CancionEntity entity = data.get(0);
        CancionEntity newEntity = cancionPersistence.find(entity.getId(), entity.getVinilo().getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getId(), newEntity.getId());
        Assert.assertEquals(entity.getNombre(), newEntity.getNombre());
        Assert.assertEquals(entity.getCalificacion(), newEntity.getCalificacion());
        Assert.assertEquals(entity.getPreviewURI(), newEntity.getPreviewURI());
    }

    /**
     * Prueba para actualizar un Vinilo.
     */
    @Test
    public void updateCancionTest() {
        CancionEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        CancionEntity newEntity = factory.manufacturePojo(CancionEntity.class
        );
        
        newEntity.setId(entity.getId());
        
        cancionPersistence.update(newEntity);
        
        CancionEntity resp = em.find(CancionEntity.class,
                entity.getId());
        
        Assert.assertEquals(newEntity.getNombre(), resp.getNombre());
    }

    /**
     * Prueba para eliminar un Usuario.
     */
    @Test
    public void deleteCancionTest() {
        CancionEntity entity = data.get(0);
        cancionPersistence.delete(entity.getId());
        CancionEntity deleted = em.find(CancionEntity.class,
                entity.getId());
        Assert.assertNull(deleted);
    }
}
