/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendadiscos.test.logic;

import co.edu.uniandes.csw.tiendadiscos.ejb.WishListLogic;
import co.edu.uniandes.csw.tiendadiscos.entities.UsuarioEntity;
import co.edu.uniandes.csw.tiendadiscos.entities.WishListEntity;
import co.edu.uniandes.csw.tiendadiscos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.tiendadiscos.persistence.WishListPersistence;
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
 * @author SebastianMartinez
 */
@RunWith(Arquillian.class)
public class WishListLogicTest {
    
    private PodamFactory factory = new PodamFactoryImpl();
    
    @Inject
    private WishListLogic logic;
    
    @PersistenceContext
    private EntityManager em;
    
    @Inject
    private UserTransaction utx;
    
    private List<WishListEntity> data = new ArrayList<WishListEntity>();
    
    private List<UsuarioEntity> dataUsuario = new ArrayList<UsuarioEntity>();
    
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(WishListEntity.class.getPackage())
                .addPackage(WishListLogic.class.getPackage())
                .addPackage(WishListPersistence.class.getPackage())
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
        em.createQuery("delete from WishListEntity").executeUpdate();
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
        WishListEntity newEntity = factory.manufacturePojo(WishListEntity.class);
        newEntity.setUsuario(dataUsuario.get(0));
        data.add(newEntity);
        em.persist(newEntity);
        WishListEntity newEntity2 = factory.manufacturePojo(WishListEntity.class);
        newEntity2.setUsuario(dataUsuario.get(2));
        data.add(newEntity2);
        em.persist(newEntity2);
    }
    
        /**
     * Prueba para crear un Review.
     *
     * @throws co.edu.uniandes.csw.tiendaDiscos.exceptions.BusinessLogicException
     */
    @Test
    public void createWishListTest() throws BusinessLogicException{
        WishListEntity newEntity = factory.manufacturePojo(WishListEntity.class);
        newEntity.setUsuario(dataUsuario.get(1));
        WishListEntity result = logic.createWishList(dataUsuario.get(1).getId(),newEntity);
        Assert.assertNotNull(result);
        WishListEntity entity = em.find(WishListEntity.class, result.getId());
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertTrue(newEntity.getVinilos().isEmpty());
    }
    
    
    /**
     * Prueba para consultar la lista de Reviews.
     *
     * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException
     */
    @Test
    public void getReviewsTest() throws BusinessLogicException {
        WishListEntity newEntity = data.get(0);
        WishListEntity nuevo = logic.getWishList(dataUsuario.get(0).getId());
        Assert.assertNotNull(nuevo);
        Assert.assertEquals(newEntity.getId(),nuevo.getId());
    }
    
    /**
     * Prueba para actualizar un Review.
     */
    @Test
    public void updateReviewTest() throws BusinessLogicException {
        WishListEntity entity = data.get(0);
        WishListEntity pojoEntity = factory.manufacturePojo(WishListEntity.class);

        pojoEntity.setId(entity.getId());
        logic.updateWishList(pojoEntity,entity.getUsuario().getId());

        WishListEntity resp = em.find(WishListEntity.class, entity.getId());

        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        Assert.assertTrue(resp.getVinilos().isEmpty());
    }
    
        /**
     * Prueba para eliminar un Review.
     *
     * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException
     */
    @Test
    public void deleteReviewTest() throws BusinessLogicException {
        WishListEntity entity = data.get(1);
        logic.deleteWishList(entity.getId());
        WishListEntity deleted = em.find(WishListEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
    
}