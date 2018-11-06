/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendadiscos.test.logic;

import co.edu.uniandes.csw.tiendadiscos.ejb.MedioDePagoLogic;
import co.edu.uniandes.csw.tiendadiscos.entities.BillingInformationEntity;
import co.edu.uniandes.csw.tiendadiscos.entities.MedioDePagoEntity;
import co.edu.uniandes.csw.tiendadiscos.entities.UsuarioEntity;
import co.edu.uniandes.csw.tiendadiscos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.tiendadiscos.persistence.MedioDePagoPersistence;
import java.util.ArrayList;
import java.util.Date;
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
public class MedioDePagoLogicTest {

    private PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private MedioDePagoLogic tarjetaLogic;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    private List<MedioDePagoEntity> dataTarjeta = new ArrayList<MedioDePagoEntity>();

    private List<BillingInformationEntity> dataBilling = new ArrayList<BillingInformationEntity>();

    private List<UsuarioEntity> dataUsuario = new ArrayList<UsuarioEntity>();

    /**
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     * El jar contiene las clases, el descriptor de la base de datos y el
     * archivo beans.xml para resolver la inyección de dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(MedioDePagoEntity.class.getPackage())
                .addPackage(MedioDePagoLogic.class.getPackage())
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

        for (int i = 0; i < 3; i++) {
            BillingInformationEntity entity = factory.manufacturePojo(BillingInformationEntity.class);
            entity.setUsuario(dataUsuario.get(i));
            em.persist(entity);
            dataBilling.add(entity);

        }

        for (int i = 0; i < 3; i++) {
            MedioDePagoEntity entity = factory.manufacturePojo(MedioDePagoEntity.class);
            entity.setBilling(dataBilling.get(0));
            em.persist(entity);
            dataTarjeta.add(entity);

        }

    }

    /**
     * Prueba para crear un Tarjeta.
     *
     * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException
     */
    @Test
    public void createTarjetaTest() throws BusinessLogicException {
        MedioDePagoEntity newEntity = factory.manufacturePojo(MedioDePagoEntity.class);
        newEntity.setBilling(dataBilling.get(0));
        newEntity.setNumeroVerificacion(newEntity.getNumero());
        Date fecha = new Date();
        newEntity.setFechaVencimiento(new Date(fecha.getTime() + 10000));

        MedioDePagoEntity result = tarjetaLogic.createTarjeta(dataBilling.get(1).getId(), newEntity);
        Assert.assertNotNull(result);
        MedioDePagoEntity entity = em.find(MedioDePagoEntity.class, result.getId());
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getBilling().getId(), entity.getBilling().getId());
        Assert.assertEquals(newEntity.getName(), entity.getName());
        Assert.assertEquals(newEntity.getNumero(), entity.getNumero());
        Assert.assertEquals(newEntity.getFechaVencimiento(), entity.getFechaVencimiento());
        Assert.assertEquals(newEntity.getCvc(), entity.getCvc());
    }

    /**
     * Prueba para consultar la lista de tarjetas.
     *
     * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException
     */
    @Test
    public void getTarjetasTest() throws BusinessLogicException {
        List<MedioDePagoEntity> list = tarjetaLogic.getTarjetas(dataUsuario.get(0).getId());
        Assert.assertEquals(dataTarjeta.size(), list.size());
        for (MedioDePagoEntity entity : list) {
            boolean found = false;
            for (MedioDePagoEntity storedEntity : dataTarjeta) {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * Prueba para consultar una tarjeta.
     */
    @Test
    public void getTarjetaTest() {
        MedioDePagoEntity entity = dataTarjeta.get(0);
        Long algo1 = dataUsuario.get(0).getId();
        Long algo2 = entity.getId();
        MedioDePagoEntity resultEntity = tarjetaLogic.getTarjeta(dataUsuario.get(0).getId(), entity.getId());
        Assert.assertNotNull(resultEntity);

        Assert.assertEquals(resultEntity.getId(), entity.getId());
        Assert.assertEquals(resultEntity.getBilling().getId(), entity.getBilling().getId());
        Assert.assertEquals(resultEntity.getName(), entity.getName());
        Assert.assertEquals(resultEntity.getNumero(), entity.getNumero());
        Assert.assertEquals(resultEntity.getFechaVencimiento(), entity.getFechaVencimiento());
        Assert.assertEquals(resultEntity.getCvc(), entity.getCvc());
    }

    /**
     * Prueba para actualizar una Tarjeta.
     */
    @Test
    public void updateTarjetaTest() throws BusinessLogicException {
        MedioDePagoEntity entity = dataTarjeta.get(0);
        MedioDePagoEntity pojoEntity = factory.manufacturePojo(MedioDePagoEntity.class);

        pojoEntity.setId(entity.getId());
        pojoEntity.setBilling(dataBilling.get(0));
        pojoEntity.setNumeroVerificacion(pojoEntity.getNumero());
        Date fecha = new Date();
        pojoEntity.setFechaVencimiento(new Date(fecha.getTime() + 10000));

        tarjetaLogic.updateTarjeta(dataUsuario.get(0).getId(), entity.getId(), pojoEntity);

        MedioDePagoEntity resp = em.find(MedioDePagoEntity.class, entity.getId());

        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        Assert.assertEquals(pojoEntity.getName(), resp.getName());
        Assert.assertEquals(pojoEntity.getBilling().getId(), resp.getBilling().getId());
        Assert.assertEquals(pojoEntity.getNumero(), resp.getNumero());
        Assert.assertEquals(pojoEntity.getFechaVencimiento(), resp.getFechaVencimiento());
        Assert.assertEquals(pojoEntity.getCvc(), resp.getCvc());
    }
    

    /**
     * Prueba para eliminar una tarjeta.
     *
     * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException
     */
    @Test
    public void deleteTarjetaTest() throws BusinessLogicException {
        MedioDePagoEntity entity = dataTarjeta.get(0);
        tarjetaLogic.deleteTarjeta(dataUsuario.get(0).getId(), entity.getId());
        MedioDePagoEntity deleted = em.find(MedioDePagoEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
    
        /**
     * Prueba para eliminarle un billing a un usuario que no tiene un billing
     * asociado
     *
     * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void deleteTarjetaDeUsuarioNoPertenece() throws BusinessLogicException {
        UsuarioEntity entity = dataUsuario.get(2);
        tarjetaLogic.deleteTarjeta(entity.getId(), dataTarjeta.get(0).getId());
    }
    
    

}
