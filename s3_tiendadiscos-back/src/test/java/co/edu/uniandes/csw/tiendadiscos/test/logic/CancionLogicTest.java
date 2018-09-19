/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendadiscos.test.logic;


import co.edu.uniandes.csw.tiendadiscos.ejb.CancionLogic;
import co.edu.uniandes.csw.tiendadiscos.entities.CancionEntity;
import co.edu.uniandes.csw.tiendadiscos.persistence.CancionPersistence;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.junit.Assert;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 * Pruebas de lógica Canción. 
 * 
 * @author Andrés Hernández.
 */
@RunWith(Arquillian.class)
public class CancionLogicTest {
    
    
    private PodamFactory factory = new PodamFactoryImpl();
    
    @Inject
    private CancionLogic cancionLogic;
    
    @PersistenceContext
    private EntityManager em;
    
    @Inject
    private UserTransaction utx;
    
    private List<CancionEntity> data = new ArrayList();
    
    /**
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     * El jar contiene las clases, el descriptor de la base de datos y el
     * archivo beans.xml para resolver la inyección de dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(CancionEntity.class.getPackage())
                .addPackage(CancionLogic.class.getPackage())
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
        for (int i = 0; i < 3; i++) {
            CancionEntity entity = factory.manufacturePojo(CancionEntity.class);
            em.persist(entity);
            data.add(entity);
        }
    }
    
    /**
     * Prueba para crear una canción.
     */
    @Test
    public void createCancionTest()
    {
        CancionEntity newEntity = factory.manufacturePojo(CancionEntity.class);
        CancionEntity result = cancionLogic.createCancion(newEntity);
        Assert.assertNotNull(result);
        Assert.assertEquals(newEntity.getNombre(), result.getNombre());
        Assert.assertEquals(newEntity.getDuracion(), result.getDuracion());
        Assert.assertEquals(newEntity.getPreviewURI() , result.getPreviewURI());
        Assert.assertEquals(newEntity.getDescripcion(), result.getDescripcion());
        Assert.assertEquals(newEntity.getCalificacion(), result.getCalificacion());
        Assert.assertEquals(newEntity.getVinilo(), result.getVinilo());
        Assert.assertEquals(newEntity.getComentarios(), result.getComentarios());       
    }   
    
    /**
     * Prueba para consultar una canción.
     */
    @Test
    public void getCancionesTest()
    {
        List<CancionEntity> list = cancionLogic.getCanciones();
        Assert.assertEquals(data.size(), list.size());
        for(CancionEntity entity : list)
        {
            boolean found = false;
            for(CancionEntity storedEntity : data)
            {
                if(entity.getId().equals(storedEntity.getId()))
                    found = true;
            }
            Assert.assertTrue(found);
        }
    }
    
    /**
     * Prueba para consultar una canción.
     */
    public void getCancionTest()
    {
        CancionEntity entity = data.get(0);
        CancionEntity resultEntity = cancionLogic.getCancion(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getId(), resultEntity.getId());
        Assert.assertEquals(entity.getNombre(), resultEntity.getNombre());
        Assert.assertEquals(entity.getDuracion(), resultEntity.getDuracion());
        Assert.assertEquals(entity.getPreviewURI() , resultEntity.getPreviewURI());
        Assert.assertEquals(entity.getDescripcion(), resultEntity.getDescripcion());
        Assert.assertEquals(entity.getCalificacion(), resultEntity.getCalificacion());
        Assert.assertEquals(entity.getVinilo(), resultEntity.getVinilo());
        Assert.assertEquals(entity.getComentarios(), resultEntity.getComentarios());
    }
    
    /**
     * Prueba para actualizar una canción.
     */
    @Test
    public void updateCancionTest()
    {
        CancionEntity entity = data.get(0);
        CancionEntity pojoEntity = factory.manufacturePojo(CancionEntity.class);
        
        pojoEntity.setId(entity.getId());
        
        cancionLogic.updateCancion(pojoEntity.getId(), pojoEntity);
        
        CancionEntity resp = em.find(CancionEntity.class, entity.getId());
        
        Assert.assertNotNull(pojoEntity);
        Assert.assertEquals(resp.getId(), pojoEntity.getId());
        Assert.assertEquals(resp.getNombre(), pojoEntity.getNombre());
        Assert.assertEquals(resp.getDuracion(), pojoEntity.getDuracion());
        Assert.assertEquals(resp.getPreviewURI() , pojoEntity.getPreviewURI());
        Assert.assertEquals(resp.getDescripcion(), pojoEntity.getDescripcion());
        Assert.assertEquals(resp.getCalificacion(), pojoEntity.getCalificacion());
        Assert.assertEquals(resp.getVinilo(), pojoEntity.getVinilo());
        Assert.assertEquals(resp.getComentarios(), pojoEntity.getComentarios());
    }
    
    /**
     * Prueba para eliminar una cancion.
     */
    @Test
    public void deleteCancionTest()
    {
        CancionEntity entity = data.get(0);
        cancionLogic.deleteCancion(entity.getId());
        CancionEntity deleted = em.find(CancionEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
}