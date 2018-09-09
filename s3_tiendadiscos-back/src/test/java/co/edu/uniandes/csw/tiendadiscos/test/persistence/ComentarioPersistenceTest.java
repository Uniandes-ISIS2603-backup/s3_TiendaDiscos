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
import co.edu.uniandes.csw.tiendadiscos.entities.WishListEntity;
import co.edu.uniandes.csw.tiendadiscos.persistance.ComentarioPersistence;


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
    
    @Before
    public void configTest() {
        try {
            utx.begin();
            em.joinTransaction();
            em.createQuery("delete from ComentarioEntity").executeUpdate();
            PodamFactory factory = new PodamFactoryImpl();
            for (int i = 0; i < 3; i++) {
                ComentarioEntity entity = factory.manufacturePojo(ComentarioEntity.class);

                em.persist(entity);
                data.add(entity);
            }
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
    

    
}