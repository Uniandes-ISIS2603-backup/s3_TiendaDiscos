/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendadiscos.test.persistence;


import co.edu.uniandes.csw.tiendadiscos.entities.ViniloEntity;
import co.edu.uniandes.csw.tiendadiscos.entities.WishListEntity;
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
 * @author Sebastian Martinez
 */
@RunWith(Arquillian.class)
public class WishListPersistenceTest {
    
    @Inject
    private WishListPersistence wishPersistence;
    
    @PersistenceContext
    private EntityManager em;
    
    @Inject
    UserTransaction utx;
    
    private List<WishListEntity> data = new ArrayList<WishListEntity>();
    
    private List<ViniloEntity> dataVinilo = new ArrayList<ViniloEntity>();
    
    @Deployment
    public static JavaArchive createDeployment()
    {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(WishListEntity.class.getPackage())
                .addPackage(WishListPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml","persistence.xml")
                .addAsManifestResource("META-INF/beans.xml","beans.xml");
    }
    
    @Before
    public void configTest()
    {
        try{
            utx.begin();
            em.joinTransaction();
            em.createQuery("delete from WishListEntity").executeUpdate();
            PodamFactory factory = new PodamFactoryImpl();
            for (int i = 0; i < 3; i++) {
                WishListEntity entity = factory.manufacturePojo(WishListEntity.class);
                em.persist(entity);
                data.add(entity);
            }
            utx.commit();
        }catch(Exception e)
        {
            e.printStackTrace();
            try{
                utx.rollback();
            }catch(Exception a){
                a.printStackTrace();
            }
        }
    }
    
    @Test
    public void createWishListTest() {
        PodamFactory factory = new PodamFactoryImpl();
        WishListEntity newEntity = factory.manufacturePojo(WishListEntity.class);
        WishListEntity result = wishPersistence.create(newEntity);

        Assert.assertNotNull(result);

        WishListEntity entity = em.find(WishListEntity.class, result.getId());

        Assert.assertEquals(newEntity.getCosto(), entity.getCosto());
    }
    
    @Test
    public void getAllTest()
    {
        List<WishListEntity> lista = wishPersistence.findAll();
        Assert.assertEquals(lista.size(), data.size());

        for(WishListEntity wish : lista)
        {
            boolean probar=false;
            for(WishListEntity wish2 : data)
            {
                if(wish.getId().equals(wish2.getId()))
                    probar=true;
            }
            Assert.assertTrue(probar);
       }
    }
    @Test
    public void getTest()
    {
        WishListEntity entity = data.get(0);
        WishListEntity result = wishPersistence.find(entity.getId());
        Assert.assertNotNull(result);
        Assert.assertEquals(entity.getCosto(), result.getCosto());
        
        List<ViniloEntity> vinilos = entity.getVinilos();
        List<ViniloEntity> viniloss = result.getVinilos();
        for(ViniloEntity vinilo : vinilos)
        {
            boolean probar = false;
            for(ViniloEntity vinilo2 : vinilos)
            {
                if(vinilo.getId().equals(vinilo2.getId()))
                    probar = true;
            }
            Assert.assertTrue(probar);
        }
    }
    
    @Test
    public void updateTest()
    {
        WishListEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        WishListEntity newEntity = factory.manufacturePojo(WishListEntity.class);
        newEntity.setId(entity.getId());
        wishPersistence.update(newEntity);
        WishListEntity result = em.find(WishListEntity.class,entity.getId());
        Assert.assertEquals(newEntity.getCosto(), result.getCosto());
        Assert.assertEquals(entity.getId(), result.getId());
        List<ViniloEntity> vinilos = entity.getVinilos();
        List<ViniloEntity> viniloss = result.getVinilos();
        for(ViniloEntity vinilo : vinilos)
        {
            boolean probar = false;
            for(ViniloEntity vinilo2 : viniloss)
            {
                if(vinilo.getId().equals(vinilo2.getId()))
                    probar = true;
            }
            Assert.assertTrue(probar);
        }
    }
    @Test
    public void deleteTest()
    {
        WishListEntity entity =data.get(0);
        wishPersistence.delete(entity.getId());
        Assert.assertNull(em.find(WishListEntity.class, entity.getId()));
    }
}
