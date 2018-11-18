/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendadiscos.test.logic;

import co.edu.uniandes.csw.tiendadiscos.ejb.ComentarioLogic;
import co.edu.uniandes.csw.tiendadiscos.entities.ComentarioEntity;
import co.edu.uniandes.csw.tiendadiscos.entities.UsuarioEntity;
import co.edu.uniandes.csw.tiendadiscos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.tiendadiscos.persistence.ComentarioPersistence;
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
 * @author Sebastian Martinez
 */
@RunWith(Arquillian.class)
public class ComentarioLogicTest {
    
    private PodamFactory factory = new PodamFactoryImpl();
    
    @Inject
    private ComentarioLogic logic;
    
    @PersistenceContext
    private EntityManager em;
    
    @Inject
    private UserTransaction utx;
    
    private List<ComentarioEntity> data = new ArrayList<ComentarioEntity>();
    
    private List<UsuarioEntity> dataUsuario = new ArrayList<UsuarioEntity>();
    
    /**
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     * El jar contiene las clases, el descriptor de la base de datos y el
     * archivo beans.xml para resolver la inyección de dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ComentarioEntity.class.getPackage())
                .addPackage(ComentarioLogic.class.getPackage())
                .addPackage(ComentarioPersistence.class.getPackage())
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
        em.createQuery("delete from ComentarioEntity").executeUpdate();
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
            ComentarioEntity comentario = factory.manufacturePojo(ComentarioEntity.class);
            comentario.setUsuarioI(dataUsuario.get(0));
            em.persist(comentario);
            data.add(comentario);
        }
    }
    
    /**
     * Prueba para consultar un Review.
     */
    @Test
    public void getComentariosTest() {
        List<ComentarioEntity> list = logic.getComentariosToUsuarios(dataUsuario.get(0).getId());
        Assert.assertEquals(data.size(), list.size());
        for (ComentarioEntity entity : list) {
            boolean found = false;
            for (ComentarioEntity storedEntity : data) {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }
    
    /**
     * Prueba para consultar un Review.
     */
    @Test
    public void getComentarioTest() {
        ComentarioEntity entity = data.get(0);
        List<ComentarioEntity> resultEntity = logic.getComentariosToUsuarios(dataUsuario.get(0).getId());
        Assert.assertNotNull(resultEntity);
        

    }
    
    /**
     * Prueba para actualizar un Review.
     */
    @Test
    public void updateComentarioTest() {
        ComentarioEntity entity = data.get(0);
        ComentarioEntity pojoEntity = factory.manufacturePojo(ComentarioEntity.class);

        pojoEntity.setId(entity.getId());

        logic.updateComentario(dataUsuario.get(0).getId(), pojoEntity);

        ComentarioEntity resp = em.find(ComentarioEntity.class, entity.getId());

        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        Assert.assertEquals(pojoEntity.getContenido(), resp.getContenido());
    }
    
    
    @Test
    public void deleteReviewTest() throws BusinessLogicException {
        ComentarioEntity entity = data.get(0);
        logic.deleteComentario(entity.getId());
        ComentarioEntity deleted = em.find(ComentarioEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }


}