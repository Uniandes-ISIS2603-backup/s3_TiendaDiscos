/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendadiscos.test.logic;


import co.edu.uniandes.csw.tiendadiscos.ejb.CarritoDeComprasLogic;
import co.edu.uniandes.csw.tiendadiscos.entities.CarritoDeComprasEntity;
import co.edu.uniandes.csw.tiendadiscos.entities.UsuarioEntity;

import co.edu.uniandes.csw.tiendadiscos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.tiendadiscos.persistence.CarritoDeComprasPersistence;

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
 * @author Laura Forero Camacho
 */
@RunWith(Arquillian.class)
public class CarritoDeComprasLogicTest {
    
    private PodamFactory factory = new PodamFactoryImpl();
    
    @Inject
    private CarritoDeComprasLogic logic;
    
    @PersistenceContext
    private EntityManager em;
    
    @Inject
    private UserTransaction utx;
    
    private List<CarritoDeComprasEntity> data = new ArrayList<CarritoDeComprasEntity>();
    
    private List<UsuarioEntity> dataUsuario = new ArrayList<UsuarioEntity>();
    
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(CarritoDeComprasEntity.class.getPackage())
                .addPackage(CarritoDeComprasLogic.class.getPackage())
                .addPackage(CarritoDeComprasPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
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
        em.createQuery("delete from CarritoDeComprasEntity").executeUpdate();
        em.createQuery("delete from UsuarioEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {

        for (int i = 0; i < 3; i++) {
            UsuarioEntity entity = factory.manufacturePojo(UsuarioEntity.class);
            em.persist(entity);
            dataUsuario.add(entity);
        }
        CarritoDeComprasEntity newEntity = factory.manufacturePojo(CarritoDeComprasEntity.class);
        newEntity.setUsuario(dataUsuario.get(0));
        data.add(newEntity);
        em.persist(newEntity);
        CarritoDeComprasEntity newEntity2 = factory.manufacturePojo(CarritoDeComprasEntity.class);
        newEntity2.setUsuario(dataUsuario.get(1));
        data.add(newEntity2);
        em.persist(newEntity2);
    }
    
        /**
     * Prueba para crear un CarritoDeCompras
     */
    @Test
    public void createTest() throws BusinessLogicException{
        CarritoDeComprasEntity newEntity = factory.manufacturePojo(CarritoDeComprasEntity.class);
        newEntity.setUsuario(dataUsuario.get(0));
        CarritoDeComprasEntity result = logic.create(dataUsuario.get(0).getId(),newEntity);
        Assert.assertNotNull(result);
        CarritoDeComprasEntity entity = em.find(CarritoDeComprasEntity.class, result.getId());
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertTrue(newEntity.getVinilosDeCarritoCompras().isEmpty());
    }
    
    
    /**
     * Prueba para consultar el CarritoDeCompras
     */
    @Test
    public void getTest() throws BusinessLogicException {
        CarritoDeComprasEntity newEntity = data.get(0);
        CarritoDeComprasEntity nuevo = logic.get(dataUsuario.get(0).getId());
    }
    
    /**
     * Prueba para actualizar un CarritoDeCompras
     */
    @Test
    public void updateTest() throws BusinessLogicException {
        CarritoDeComprasEntity entity = data.get(0);
        CarritoDeComprasEntity pojoEntity = factory.manufacturePojo(CarritoDeComprasEntity.class);

        pojoEntity.setId(entity.getId());
        logic.update( pojoEntity,entity.getUsuario().getId());

        CarritoDeComprasEntity resp = em.find(CarritoDeComprasEntity.class, entity.getId());

        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        Assert.assertTrue(resp.getTransaccionesDeCarritoCompras().isEmpty());
    }
    
        /**
     * Prueba para eliminar un CarritoDeCompras.
     *
     * 
     * @throws co.edu.uniandes.csw.tiendadiscos.exceptions.BusinessLogicException
     */
    @Test
    public void deleteTest() throws BusinessLogicException {
        CarritoDeComprasEntity entity = data.get(1);
        logic.delete(entity.getId());
        CarritoDeComprasEntity deleted = em.find(CarritoDeComprasEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
    
}