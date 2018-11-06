/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendadiscos.test.logic;

import co.edu.uniandes.csw.tiendadiscos.ejb.BillingInformationLogic;
import co.edu.uniandes.csw.tiendadiscos.entities.BillingInformationEntity;
import co.edu.uniandes.csw.tiendadiscos.entities.UsuarioEntity;
import co.edu.uniandes.csw.tiendadiscos.exceptions.BusinessLogicException;
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
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author Kevin Blanco
 */
@RunWith(Arquillian.class)
public class BillingInfoLogicTest {
    
    private PodamFactory factory = new PodamFactoryImpl();
    
    @Inject
    private BillingInformationLogic billingLogic;
    
    @PersistenceContext
    private EntityManager em;
    
    @Inject
    private UserTransaction utx;
    
    private List<BillingInformationEntity> data = new ArrayList<BillingInformationEntity>();
    
    private List<UsuarioEntity> dataUsuario = new ArrayList<UsuarioEntity>();

    /**
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     * El jar contiene las clases, el descriptor de la base de datos y el
     * archivo beans.xml para resolver la inyección de dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(BillingInformationEntity.class.getPackage())
                .addPackage(BillingInformationLogic.class.getPackage())
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
        em.createQuery("delete from UsuarioEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        
        for (int i = 0; i < 4; i++) {
            UsuarioEntity entity = factory.manufacturePojo(UsuarioEntity.class);
            em.persist(entity);
            dataUsuario.add(entity);
        }
        
        for (int i = 0; i < 3; i++) {
            BillingInformationEntity entity = factory.manufacturePojo(BillingInformationEntity.class);
            entity.setUsuario(dataUsuario.get(i));
            em.persist(entity);
            data.add(entity);
            
        }
    }

    /**
     * Prueba para crear un Billing.
     *
     * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException
     */
    @Test
    public void createBillingTest() throws BusinessLogicException {
        BillingInformationEntity newEntity = factory.manufacturePojo(BillingInformationEntity.class);
        newEntity.setCuentaAhorro("123321");
        newEntity.setUsuario(dataUsuario.get(1));
        BillingInformationEntity result = billingLogic.createBilling(dataUsuario.get(1).getId(), newEntity);
        Assert.assertNotNull(result);
        BillingInformationEntity entity = em.find(BillingInformationEntity.class, result.getId());
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getCuentaAhorro(), entity.getCuentaAhorro());
        Assert.assertEquals(newEntity.getUsuario().getId(), entity.getUsuario().getId());
    }

    /**
     * Prueba para consultar un Billing.
     */
    @Test
    public void getBillingTest() {
        BillingInformationEntity entity = data.get(1);
        BillingInformationEntity resultEntity = billingLogic.getBilling(dataUsuario.get(1).getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(resultEntity.getId(), entity.getId());
        Assert.assertEquals(resultEntity.getCuentaAhorro(), entity.getCuentaAhorro());
        Assert.assertEquals(resultEntity.getUsuario().getId(), entity.getUsuario().getId());
    }

    /**
     * Prueba para actualizar un Billing.
     */
    @Test
    public void updateBillingTest() throws BusinessLogicException {
        BillingInformationEntity entity = data.get(0);
        BillingInformationEntity pojoEntity = factory.manufacturePojo(BillingInformationEntity.class);
        
        pojoEntity.setId(entity.getId());
        pojoEntity.setCuentaAhorro("321321");
        pojoEntity.setUsuario(entity.getUsuario());
        
        billingLogic.updateBilling(dataUsuario.get(0).getId(), pojoEntity);
        
        BillingInformationEntity resp = em.find(BillingInformationEntity.class, entity.getId());
        
        Assert.assertNotNull(resp);
        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        Assert.assertEquals(pojoEntity.getCuentaAhorro(), resp.getCuentaAhorro());
        Assert.assertEquals(pojoEntity.getUsuario().getId(), resp.getUsuario().getId());
    }

    /**
     * Prueba para eliminar un Billing.
     *
     * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException
     */
    @Test
    public void deleteBillingTest() throws BusinessLogicException {
        BillingInformationEntity entity = data.get(0);
        billingLogic.deleteBilling(dataUsuario.get(0).getId());
        BillingInformationEntity deleted = em.find(BillingInformationEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * Prueba para eliminarle un billing a un usuario que no tiene un billing
     * asociado
     *
     * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void deletebillingConUsuarioNoTieneNingunoAsociadoTest() throws BusinessLogicException {
        UsuarioEntity entity = dataUsuario.get(3);
        billingLogic.deleteBilling(entity.getId());
    }
    
}