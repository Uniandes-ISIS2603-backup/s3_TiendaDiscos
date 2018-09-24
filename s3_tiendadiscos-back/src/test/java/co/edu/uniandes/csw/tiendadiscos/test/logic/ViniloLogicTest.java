/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.edu.uniandes.csw.tiendadiscos.test.logic;

import co.edu.uniandes.csw.tiendadiscos.ejb.ViniloLogic;
import co.edu.uniandes.csw.tiendadiscos.entities.ViniloEntity;
import co.edu.uniandes.csw.tiendadiscos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.tiendadiscos.persistence.ViniloPersistence;
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
 *  Pruebas de la lógica de Vinilo.
 * 
 * @author Andrés Hernández
 */
@RunWith(Arquillian.class)
public class ViniloLogicTest {
    
    private PodamFactory factory = new PodamFactoryImpl();
    
    @Inject
    private ViniloLogic viniloLogic;
    
    @PersistenceContext
    private EntityManager em;
    
    @Inject
    private UserTransaction utx;
    
    private List<ViniloEntity> data = new ArrayList();
    
    /**
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     * El jar contiene las clases, el descriptor de la base de datos y el
     * archivo beans.xml para resolver la inyección de dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ViniloEntity.class.getPackage())
                .addPackage(ViniloLogic.class.getPackage())
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
        for (int i = 0; i < 3; i++) {
            ViniloEntity entity = factory.manufacturePojo(ViniloEntity.class);
            em.persist(entity);
            data.add(entity);
        }
    }
    
    /**
     * Prueba para crear un vinilo.
     */
    @Test
    public void createViniloTest() throws BusinessLogicException
    {
        ViniloEntity newEntity = factory.manufacturePojo(ViniloEntity.class);
        ViniloEntity result = viniloLogic.createVinilo(newEntity);
        Assert.assertNotNull(result);
        Assert.assertEquals(newEntity.getNombre(), result.getNombre());
        Assert.assertEquals(newEntity.getArtista(), result.getArtista());
        Assert.assertEquals(newEntity.getFechaLanzamiento(), result.getFechaLanzamiento());
        Assert.assertEquals(newEntity.getProductora(), result.getProductora());
        Assert.assertEquals(newEntity.getInformacionAdicional(), result.getInformacionAdicional());
        Assert.assertEquals(newEntity.getPreviewURI(), result.getPreviewURI());
        Assert.assertEquals(newEntity.getCalificacion(), result.getCalificacion());
        Assert.assertEquals(newEntity.getPrecio(), result.getPrecio());
        Assert.assertEquals(newEntity.getCanciones(), result.getCanciones());
        Assert.assertEquals(newEntity.getWishLists(), result.getWishLists());
        Assert.assertEquals(newEntity.getCarritosDeCompras(), result.getCarritosDeCompras());
        Assert.assertEquals(newEntity.getTransacciones(), result.getTransacciones());
        Assert.assertEquals(newEntity.getUsuario(), result.getUsuario());
    }
    
    /**
     * Prueba para consultar un vinilo.
     */
    @Test
    public void getVinilosTest()
    {
        List<ViniloEntity> list = viniloLogic.getVinilos();
        Assert.assertEquals(data.size(), list.size());
        for(ViniloEntity entity: list)
        {
            boolean found = false;
            for(ViniloEntity storedEntity : data)
            {
                if(entity.getId().equals(storedEntity.getId()))
                    found = true;
            }
            Assert.assertTrue(found);
        }
    }
    
    /**
     * Prueba para consultar un vinilo.
     */
    @Test
    public void getViniloTest()
    {
        ViniloEntity entity = data.get(0);
        ViniloEntity result = viniloLogic.getVinilo(entity.getId());
                
        Assert.assertNotNull(result);
        Assert.assertEquals(entity.getNombre(), result.getNombre());
        Assert.assertEquals(entity.getArtista(), result.getArtista());
        Assert.assertEquals(entity.getFechaLanzamiento(), result.getFechaLanzamiento());
        Assert.assertEquals(entity.getProductora(), result.getProductora());
        Assert.assertEquals(entity.getInformacionAdicional(), result.getInformacionAdicional());
        Assert.assertEquals(entity.getPreviewURI(), result.getPreviewURI());
        Assert.assertEquals(entity.getCalificacion(), result.getCalificacion());
        Assert.assertEquals(entity.getPrecio(), result.getPrecio());
        Assert.assertEquals(entity.getCanciones(), result.getCanciones());
        Assert.assertEquals(entity.getWishLists(), result.getWishLists());
        Assert.assertEquals(entity.getCarritosDeCompras(), result.getCarritosDeCompras());
        Assert.assertEquals(entity.getTransacciones(), result.getTransacciones());
        Assert.assertEquals(entity.getUsuario(), result.getUsuario());
    }
    
    /**
     * Prueba para actualizar un vinilo.
     */
    @Test
    public void updateViniloTest()
    {
        ViniloEntity entity = data.get(0);
        ViniloEntity pojoEntity = factory.manufacturePojo(ViniloEntity.class);
        
        pojoEntity.setId(entity.getId());
        
        viniloLogic.updateVinilo(pojoEntity.getId(), pojoEntity);
        
        ViniloEntity resp = em.find(ViniloEntity.class, entity.getId());
        
        Assert.assertNotNull(resp);
        Assert.assertEquals(pojoEntity.getNombre(), resp.getNombre());
        Assert.assertEquals(pojoEntity.getArtista(), resp.getArtista());
        Assert.assertEquals(pojoEntity.getFechaLanzamiento(), resp.getFechaLanzamiento());
        Assert.assertEquals(pojoEntity.getProductora(), resp.getProductora());
        Assert.assertEquals(pojoEntity.getInformacionAdicional(), resp.getInformacionAdicional());
        Assert.assertEquals(pojoEntity.getPreviewURI(), resp.getPreviewURI());
        Assert.assertEquals(pojoEntity.getCalificacion(), resp.getCalificacion());
        Assert.assertEquals(pojoEntity.getPrecio(), resp.getPrecio());
        Assert.assertEquals(pojoEntity.getCanciones(), resp.getCanciones());
        Assert.assertEquals(pojoEntity.getWishLists(), resp.getWishLists());
        Assert.assertEquals(pojoEntity.getCarritosDeCompras(), resp.getCarritosDeCompras());
        Assert.assertEquals(pojoEntity.getTransacciones(), resp.getTransacciones());
        Assert.assertEquals(pojoEntity.getUsuario(), resp.getUsuario());
    }
    
    /**
     * Prueba para eliminar un vinilo.
     */
    @Test
    public void deleteViniloTest()
    {
        ViniloEntity entity = data.get(0);
        viniloLogic.deleteVinilo(entity.getId());
        ViniloEntity deleted = em.find(ViniloEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
}