/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendadiscos.test.persistence;
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
import co.edu.uniandes.csw.tiendadiscos.entities.ComentarioEntity;
import co.edu.uniandes.csw.tiendadiscos.entities.UsuarioEntity;
import co.edu.uniandes.csw.tiendadiscos.entities.WishListEntity;
import co.edu.uniandes.csw.tiendadiscos.persistence.ComentarioPersistence;


/**
 *
 * @author Sebastian Martinez
 */
@RunWith(Arquillian.class)
public class ComentarioPersistenceTest {
    
    @Inject
    private ComentarioPersistence comentarioPersistence;

    @PersistenceContext
    private EntityManager em;
    
    @Inject
    UserTransaction utx;
    
    private List<ComentarioEntity> data = new ArrayList<ComentarioEntity>();
    
    private List<UsuarioEntity> dataUsuario = new ArrayList<UsuarioEntity>();
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ComentarioEntity.class.getPackage())
                .addPackage(ComentarioPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
    
    @Test
    public void createComentario(){
        PodamFactory factory = new PodamFactoryImpl();
        ComentarioEntity newEntity = factory.manufacturePojo(ComentarioEntity.class);
        ComentarioEntity result = comentarioPersistence.create(newEntity);

        Assert.assertNotNull(result);

        ComentarioEntity entity = em.find(ComentarioEntity.class, result.getId());

        Assert.assertEquals(newEntity.getContenido(),entity.getContenido());
    }
    
    @Test
    public void update()
    {
        ComentarioEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        ComentarioEntity newEntity = factory.manufacturePojo(ComentarioEntity.class);
        newEntity.setId(entity.getId());
        comentarioPersistence.update(newEntity);
        ComentarioEntity result = em.find(ComentarioEntity.class,entity.getId());
        Assert.assertEquals(newEntity.getContenido(), result.getContenido());
        Assert.assertEquals(entity.getId(), result.getId());
    }
    
    @Test
    public void getTest()
    {
        ComentarioEntity entity= data.get(0);
        List<ComentarioEntity> comentario = comentarioPersistence.findAllToUsuario(dataUsuario.get(0).getId());
        Assert.assertNotNull(comentario);
//        Assert.assertEquals(entity.getContenido(),comentario.getContenido());
//        Assert.assertEquals(entity.getCancion()==null,comentario.getCancion()==null);
//        Assert.assertEquals(entity.getVinilo()==null,comentario.getVinilo()==null);
//        Assert.assertEquals(entity.getUsuario()==null,comentario.getUsuario()==null);
//        Assert.assertEquals(entity.getUsuarioI()==null,comentario.getUsuarioI()==null);
//        Assert.assertEquals(entity.getTransaccion()==null,comentario.getTransaccion()==null);
    }
    @Test
    public void getAllHTest()
    {   Long a = dataUsuario.get(0).getId();
        List<ComentarioEntity> lista = comentarioPersistence.findAllToUsuario(a);
        Assert.assertEquals(lista.size(), data.size());
        for(ComentarioEntity wish : lista)
        {
            boolean probar=false;
            for(ComentarioEntity wish2 : data)
            {
                if(wish.getId().equals(wish2.getId()))
                    probar=true;
            }
            Assert.assertTrue(probar);
       }
    }
    @Test
    public void deleteTest()
    {
        ComentarioEntity entity = data.get(0);
        comentarioPersistence.delete(entity.getId());
        Assert.assertNull(em.find(ComentarioEntity.class, entity.getId()));
    }
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
        em.createQuery("delete from ComentarioEntity").executeUpdate();
        em.createQuery("delete from UsuarioEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
    */ 
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            UsuarioEntity temp = factory.manufacturePojo(UsuarioEntity.class);
            em.persist(temp);
            dataUsuario.add(temp);

        }
        for(int j = 0; j <3;j++){
            ComentarioEntity entity = factory.manufacturePojo(ComentarioEntity.class);
            entity.setUsuarioI(dataUsuario.get(0));
            em.persist(entity);
            data.add(entity);
        }

    }
    
}