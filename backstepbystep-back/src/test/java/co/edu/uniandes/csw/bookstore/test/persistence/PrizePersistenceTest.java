/*
MIT License

Copyright (c) 2017 Universidad de los Andes - ISIS2603

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
 */
package co.edu.uniandes.csw.bookstore.test.persistence;

import co.edu.uniandes.csw.bookstore.entities.OrganizationEntity;
import co.edu.uniandes.csw.bookstore.entities.PrizeEntity;
import co.edu.uniandes.csw.bookstore.persistence.OrganizationPersistence;
import co.edu.uniandes.csw.bookstore.persistence.PrizePersistence;
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

/**
 * Pruebas de persistencia de Prizes
 *
 * @author ISIS2603
 */
@RunWith(Arquillian.class)
public class PrizePersistenceTest {

    @Inject
    private PrizePersistence prizePersistence;

    @Inject
    private OrganizationPersistence organizationPersistence;

    @PersistenceContext
    private EntityManager em;

    @Inject
    UserTransaction utx;

    private List<PrizeEntity> data = new ArrayList<PrizeEntity>();

    /**
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     * El jar contiene las clases, el descriptor de la base de datos y el
     * archivo beans.xml para resolver la inyección de dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(PrizeEntity.class.getPackage())
                .addPackage(PrizePersistence.class.getPackage())
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

    /**
     * Limpia las tablas que están implicadas en la prueba.
     */
    private void clearData() {
        em.createQuery("delete from PrizeEntity").executeUpdate();
        em.createQuery("delete from OrganizationEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();

        for (int i = 0; i < 3; i++) {
            PrizeEntity entity = factory.manufacturePojo(PrizeEntity.class);
            OrganizationEntity orgEntity = factory.manufacturePojo(OrganizationEntity.class);

            orgEntity.setPrize(entity);
            entity.setOrganization(orgEntity);

            em.persist(orgEntity);
            em.persist(entity);
            data.add(entity);
        }
    }

    /**
     * Prueba para crear un Prize.
     */
    @Test
    public void createPrizeTest() {
        PodamFactory factory = new PodamFactoryImpl();
        PrizeEntity newEntity = factory.manufacturePojo(PrizeEntity.class);
        OrganizationEntity newOrgEntity = factory.manufacturePojo(OrganizationEntity.class);

        newOrgEntity = organizationPersistence.create(newOrgEntity);
        newEntity.setOrganization(newOrgEntity);
        PrizeEntity result = prizePersistence.create(newEntity);

        Assert.assertNotNull(result);

        PrizeEntity entity = em.find(PrizeEntity.class, result.getId());

        Assert.assertEquals(newEntity.getName(), entity.getName());
        Assert.assertEquals(newEntity.getDescription(), entity.getDescription());
    }

    /**
     * Prueba para consultar la lista de premios.
     */
    @Test
    public void getPrizesTest() {
        List<PrizeEntity> list = prizePersistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (PrizeEntity ent : list) {
            boolean found = false;
            for (PrizeEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * Prueba para consultar un Prize.
     */
    @Test
    public void getPrizeTest() {
        PrizeEntity entity = data.get(0);
        PrizeEntity newEntity = prizePersistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getName(), newEntity.getName());
        Assert.assertEquals(entity.getDescription(), newEntity.getDescription());
    }

    /**
     * Prueba para eliminar un Prize.
     */
    @Test
    public void deletePrizeTest() {
        PrizeEntity entity = data.get(0);
        prizePersistence.delete(entity.getId());
        PrizeEntity deleted = em.find(PrizeEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * Prueba para actualizar un Prize.
     */
    @Test
    public void updatePrizeTest() {
        PrizeEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        PrizeEntity newEntity = factory.manufacturePojo(PrizeEntity.class);

        newEntity.setId(entity.getId());

        prizePersistence.update(newEntity);

        PrizeEntity resp = em.find(PrizeEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getName(), resp.getName());
        Assert.assertEquals(newEntity.getDescription(), resp.getDescription());
    }
}
