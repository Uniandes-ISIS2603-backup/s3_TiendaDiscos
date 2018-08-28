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
package co.edu.uniandes.csw.bookstore.test.logic;

import co.edu.uniandes.csw.bookstore.ejb.OrganizationLogic;
import co.edu.uniandes.csw.bookstore.ejb.PrizeLogic;
import co.edu.uniandes.csw.bookstore.entities.AuthorEntity;
import co.edu.uniandes.csw.bookstore.entities.OrganizationEntity;
import co.edu.uniandes.csw.bookstore.entities.PrizeEntity;
import co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException;
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
 * Pruebas de logica de Prizes
 *
 * @author ISIS2603
 */
@RunWith(Arquillian.class)
public class PrizeLogicTest {

    private PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private PrizeLogic prizeLogic;

    @Inject
    private OrganizationLogic orgLogic;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    private List<OrganizationEntity> orgData = new ArrayList<>();

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
                .addPackage(OrganizationEntity.class.getPackage())
                .addPackage(PrizeLogic.class.getPackage())
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
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        for (int i = 0; i < 3; i++) {
            PrizeEntity entity = factory.manufacturePojo(PrizeEntity.class);
            OrganizationEntity orgEntity = factory.manufacturePojo(OrganizationEntity.class);
            em.persist(orgEntity);
            entity.setOrganization(orgEntity);
            orgEntity.setPrize(entity);
            em.persist(entity);
            data.add(entity);
            orgData.add(orgEntity);
        }
        AuthorEntity author = factory.manufacturePojo(AuthorEntity.class);
        em.persist(author);
        author.getPrizes().add(data.get(2));
        data.get(2).setAuthor(author);
    }

    /**
     * Prueba para crear un Prize.
     *
     * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException
     */
    @Test
    public void createPrizeTest() throws BusinessLogicException {
        PrizeEntity newEntity = factory.manufacturePojo(PrizeEntity.class);
        OrganizationEntity newOrgEntity = factory.manufacturePojo(OrganizationEntity.class);

        newOrgEntity = orgLogic.createOrganization(newOrgEntity);
        newEntity.setOrganization(newOrgEntity);
        PrizeEntity result = prizeLogic.createPrize(newEntity);
        Assert.assertNotNull(result);
        PrizeEntity entity = em.find(PrizeEntity.class, result.getId());
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getName(), entity.getName());
        Assert.assertEquals(newEntity.getDescription(), entity.getDescription());
        Assert.assertEquals(newEntity.getFechaEntrega(), entity.getFechaEntrega());
    }

    /**
     * Prueba para crear un Prize con organizacion nula.
     *
     * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void createPrizeConOrganizacionInvalida1Test() throws BusinessLogicException {
        PrizeEntity newEntity = factory.manufacturePojo(PrizeEntity.class);
        newEntity.setOrganization(null);
        prizeLogic.createPrize(newEntity);
    }

    /**
     * Prueba para crear un Prize con una organizacion que no existe.
     *
     * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void createPrizeConOrganizacionInvalida2Test() throws BusinessLogicException {
        PrizeEntity newEntity = factory.manufacturePojo(PrizeEntity.class);
        OrganizationEntity organization = new OrganizationEntity();
        organization.setId(Long.MIN_VALUE);
        newEntity.setOrganization(organization);
        prizeLogic.createPrize(newEntity);
    }

    /**
     * Prueba para crear un Prize con una organizacion que ya tiene premio.
     *
     * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void createPrizeConOrganizacionInvalida3Test() throws BusinessLogicException {
        PrizeEntity newEntity = factory.manufacturePojo(PrizeEntity.class);
        newEntity.setOrganization(orgData.get(0));
        prizeLogic.createPrize(newEntity);
    }

    /**
     * Prueba para consultar la lista de Prizes.
     */
    @Test
    public void getPrizesTest() {
        List<PrizeEntity> list = prizeLogic.getPrizes();
        Assert.assertEquals(data.size(), list.size());
        for (PrizeEntity entity : list) {
            boolean found = false;
            for (PrizeEntity storedEntity : data) {
                if (entity.getId().equals(storedEntity.getId())) {
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
        PrizeEntity resultEntity = prizeLogic.getPrize(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getId(), resultEntity.getId());
        Assert.assertEquals(entity.getName(), resultEntity.getName());
        Assert.assertEquals(entity.getDescription(), resultEntity.getDescription());
        Assert.assertEquals(entity.getFechaEntrega(), resultEntity.getFechaEntrega());
    }

    /**
     * Prueba para actualizar un Prize.
     */
    @Test
    public void updateBookTest() {
        PrizeEntity entity = data.get(0);
        PrizeEntity pojoEntity = factory.manufacturePojo(PrizeEntity.class);

        pojoEntity.setId(entity.getId());

        prizeLogic.updatePrize(pojoEntity.getId(), pojoEntity);

        PrizeEntity resp = em.find(PrizeEntity.class, entity.getId());

        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        Assert.assertEquals(pojoEntity.getName(), resp.getName());
        Assert.assertEquals(pojoEntity.getDescription(), resp.getDescription());
        Assert.assertEquals(pojoEntity.getFechaEntrega(), resp.getFechaEntrega());
    }

    /**
     * Prueba para eliminar un Prize.
     *
     * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void deleteBookTest() throws BusinessLogicException {
        PrizeEntity entity = data.get(2);
        prizeLogic.deletePrize(entity.getId());
    }

}
