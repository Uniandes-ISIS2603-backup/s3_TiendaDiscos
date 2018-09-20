/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendadiscos.test.logic;

import co.edu.uniandes.csw.tiendadiscos.ejb.TransaccionLogic;
import co.edu.uniandes.csw.tiendadiscos.entities.TransaccionEntity;
import co.edu.uniandes.csw.tiendadiscos.entities.UsuarioEntity;
import co.edu.uniandes.csw.tiendadiscos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.tiendadiscos.persistence.TransaccionPersistence;
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
 * @author Laura Isabella Forero Camacho
 */
@RunWith(Arquillian.class)
public class TransaccionLogicTest {
    
    private PodamFactory factory = new PodamFactoryImpl();
    
    @Inject
    private TransaccionLogic logic;
    
    @PersistenceContext
    private EntityManager em;
    
    @Inject
    private UserTransaction utx;
    
    private List<TransaccionEntity> data = new ArrayList<TransaccionEntity>();
    
    private List<UsuarioEntity> dataUsuario = new ArrayList<UsuarioEntity>();
    
    /**
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     * El jar contiene las clases, el descriptor de la base de datos y el
     * archivo beans.xml para resolver la inyección de dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(TransaccionEntity.class.getPackage())
                .addPackage(TransaccionLogic.class.getPackage())
                .addPackage(TransaccionPersistence.class.getPackage())
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
        em.createQuery("delete from CarritoDeComprasEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        for (int i = 0; i < 3; i++) {
            UsuarioEntity usuario = factory.manufacturePojo(UsuarioEntity.class);
            em.persist(usuario);
            dataUsuario.add(usuario);
        }
        for (int i = 0; i < 3; i++) {
            TransaccionEntity transaccion = factory.manufacturePojo(TransaccionEntity.class);
            transaccion.setUsuarioVendedor(dataUsuario.get(1));
            transaccion.setUsuarioComprador(dataUsuario.get(0));
            em.persist(transaccion);
            data.add(transaccion);
        }
    }
    
    /**
     * Prueba para consultar una transaccion
     */
    @Test
    public void getTest() {
        TransaccionEntity newEntity = data.get(0);
        TransaccionEntity nuevo = logic.get(newEntity.getId());
        Assert.assertNotNull(nuevo);
        Assert.assertEquals(newEntity.getId(),nuevo.getId());
        
    }
    
    /**
     * Prueba para actualizar un Review.
     */
    @Test
    public void updateTest() {
        TransaccionEntity entity = data.get(0);
        
        logic.update( entity,entity.getId());

        TransaccionEntity resp = em.find(TransaccionEntity.class, entity.getId());

        Assert.assertEquals(entity.getId(), resp.getId());
        
    }
    
        /**
     * Prueba para eliminar una transaccion
     */
    @Test
    public void deleteTest() throws BusinessLogicException {
        TransaccionEntity entity = data.get(1);
        logic.delete(entity.getId());
        TransaccionEntity deleted = em.find(TransaccionEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
    
}
