/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendadiscos.test.persistence;
import co.edu.uniandes.csw.tiendadiscos.entities.CarritoDeComprasEntity;
import co.edu.uniandes.csw.tiendadiscos.entities.UsuarioEntity;
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
 * @author estudiante
 */
@RunWith(Arquillian.class)
public class CarritoDeComprasPersistenceTest {
     @Inject
    private CarritoDeComprasPersistence carritoDeComprasPersistence;
    
     @Inject
    UserTransaction utx;
     
     @PersistenceContext
    private EntityManager em;
     
    /**
     * Lista que tiene los datos de prueba.
     */
    private List<CarritoDeComprasEntity> data = new ArrayList<CarritoDeComprasEntity>();
    private List<UsuarioEntity> dataUsuario = new ArrayList<UsuarioEntity>();
     @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(CarritoDeComprasEntity.class.getPackage())
                .addPackage(CarritoDeComprasPersistence.class.getPackage())
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
    @Test
    public void createCarritoDeComprasTest(){
        PodamFactory factory= new PodamFactoryImpl();
        CarritoDeComprasEntity newEntity= factory.manufacturePojo(CarritoDeComprasEntity.class);
        CarritoDeComprasEntity result= carritoDeComprasPersistence.create(newEntity);
        Assert.assertNotNull(result);
        
        CarritoDeComprasEntity entity = em.find(CarritoDeComprasEntity.class, result.getId());

        Assert.assertEquals(newEntity.getTotalCostDeCarritoCompras(), entity.getTotalCostDeCarritoCompras());
    }
    
    /**
     * Limpia las tablas que están implicadas en la prueba.
     */
    private void clearData() {
        em.createQuery("delete from TransaccionEntity").executeUpdate();
        em.createQuery("delete from UsuarioEntity").executeUpdate();

    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            UsuarioEntity entity = factory.manufacturePojo(UsuarioEntity.class);
            em.persist(entity);
            dataUsuario.add(entity);
        }
        for (int i = 0; i < 3; i++) {
            CarritoDeComprasEntity entity = factory.manufacturePojo(CarritoDeComprasEntity.class);
            if (i == 0 ){
            entity.setUsuario(dataUsuario.get(0));
            }
            em.persist(entity);
            data.add(entity);
        }
    }
    /**
     * Prueba para consultar un carrito de compras.
     */
    @Test
    public void getCarritoDeComprasTest() {
        CarritoDeComprasEntity entity = data.get(0);
        CarritoDeComprasEntity newEntity = carritoDeComprasPersistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getTotalCostDeCarritoCompras(), newEntity.getTotalCostDeCarritoCompras());
    }

    /**
     * Prueba para eliminar un carrito de compras.
     */
    @Test
    public void deleteCarritoDeComprasTest() {
        CarritoDeComprasEntity entity = data.get(0);
        carritoDeComprasPersistence.delete(entity.getId());
        CarritoDeComprasEntity deleted = em.find(CarritoDeComprasEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * Prueba para actualizar un carrito de compras.
     */
    @Test
    public void updateCarritoDeComprasTest() {
        CarritoDeComprasEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        CarritoDeComprasEntity newEntity = factory.manufacturePojo(CarritoDeComprasEntity.class);

        newEntity.setId(entity.getId());

        carritoDeComprasPersistence.update(newEntity);

        CarritoDeComprasEntity resp = em.find(CarritoDeComprasEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getTotalCostDeCarritoCompras(), resp.getTotalCostDeCarritoCompras());
    }
    
    
}
