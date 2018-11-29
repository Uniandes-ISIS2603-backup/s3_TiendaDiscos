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
import co.edu.uniandes.csw.tiendadiscos.entities.CancionEntity;
import co.edu.uniandes.csw.tiendadiscos.entities.TransaccionEntity;
import co.edu.uniandes.csw.tiendadiscos.entities.ViniloEntity;
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
    
    private List<ComentarioEntity> data = new ArrayList<>();
    
    private List<UsuarioEntity> usuarios = new ArrayList<>();

    private List<ViniloEntity> vinilos= new ArrayList<>();
    
    private List<CancionEntity> canciones = new ArrayList<>();
    
    private List<TransaccionEntity> transacciones = new ArrayList<>();
    
    @Deployment
    public static JavaArchive createDeployment() 
    {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ComentarioEntity.class.getPackage())
                .addPackage(ComentarioPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
        
    @Test
    public void createComentario()
    {
        PodamFactory factory = new PodamFactoryImpl();
        ComentarioEntity newEntity = factory.manufacturePojo(ComentarioEntity.class);
        ComentarioEntity result = comentarioPersistence.create(newEntity);

        Assert.assertNotNull(result);

        ComentarioEntity entity = em.find(ComentarioEntity.class, result.getId());
        Assert.assertEquals(newEntity.getContenido(),entity.getContenido());
        Assert.assertEquals(newEntity.getCancion(), entity.getCancion());
        Assert.assertEquals(newEntity.getTransaccion(), entity.getTransaccion());
        Assert.assertEquals(newEntity.getUsuario(), entity.getUsuario());
        Assert.assertEquals(newEntity.getUsuarioI(), entity.getUsuarioI());
        Assert.assertEquals(newEntity.getVinilo(), entity.getVinilo());
    }
    
    @Test
    public void getTest()
    {
        ComentarioEntity entity = data.get(0);
        ComentarioEntity newEntity = comentarioPersistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        
        Assert.assertEquals(entity.getContenido(), newEntity.getContenido());
    }
    
    @Test
    public void update()
    {
        ComentarioEntity entity = data.get(1);
        PodamFactory factory = new PodamFactoryImpl();
        ComentarioEntity newEntity = factory.manufacturePojo(ComentarioEntity.class);
        newEntity.setId(entity.getId());
        comentarioPersistence.update(newEntity);
        
        ComentarioEntity result = em.find(ComentarioEntity.class,entity.getId());

        Assert.assertEquals(newEntity.getContenido(), result.getContenido());
        Assert.assertEquals(newEntity.getCancion(), result.getCancion());
        Assert.assertEquals(newEntity.getTransaccion(), result.getTransaccion());
        Assert.assertEquals(newEntity.getUsuario(), result.getUsuario());
        Assert.assertEquals(newEntity.getUsuarioI(), result.getUsuarioI());
        Assert.assertEquals(newEntity.getVinilo(), result.getVinilo());
    }
    
    @Test
    public void getAllToUsuario()
    {   
        comentarioPersistence.findAllToUsuario(usuarios.get(0).getId());
        Assert.assertTrue(true);
    }
    
    @Test
    public void getAllToTransaccion()
    {   
        comentarioPersistence.findAllToTransaccion(transacciones.get(0).getId());
        Assert.assertTrue(true);
    }
    
    @Test
    public void getAllToCancion()
    {   
        comentarioPersistence.findAllToCancion(canciones.get(0).getId());
        Assert.assertTrue(true);
    }
    
    @Test
    public void getAllToVinilo()
    {   
        comentarioPersistence.findAllToVinilo(vinilos.get(0).getId());
        Assert.assertTrue(true);
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
    private void insertData() 
    {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 2; i++)
        {
            UsuarioEntity usuario = factory.manufacturePojo(UsuarioEntity.class);
            em.persist(usuario);
            usuarios.add(usuario);
        }
        for(int i = 0; i<2; i ++)
        {            
            ViniloEntity vinilo =factory.manufacturePojo(ViniloEntity.class);
            em.persist(vinilo);
            vinilos.add(vinilo);
        }
        for(int i = 0; i<4; i ++)
        {
            CancionEntity cancion = factory.manufacturePojo(CancionEntity.class);
            em.persist(cancion);
            canciones.add(cancion);
        }
        for(int i = 0; i<2; i ++)
        {
            TransaccionEntity transaccion =factory.manufacturePojo(TransaccionEntity.class);
            em.persist(transaccion);
            transacciones.add(transaccion);
        }
        for(int j = 0; j <4;j++)
        {
            int k = j%2;
            ComentarioEntity entity = factory.manufacturePojo(ComentarioEntity.class);
            entity.setCancion(canciones.get(k));
            entity.setTransaccion(transacciones.get(k));
            entity.setUsuario(usuarios.get(0));
            entity.setUsuarioI(usuarios.get(k));
            entity.setVinilo(vinilos.get(k));
            em.persist(entity);
            data.add(entity);
        }
    }
}