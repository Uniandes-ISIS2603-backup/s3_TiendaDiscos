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
import co.edu.uniandes.csw.bookstore.entities.OrganizationEntity;
import co.edu.uniandes.csw.bookstore.entities.PrizeEntity;
import co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.bookstore.persistence.OrganizationPersistence;
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
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import java.util.ArrayList;
import java.util.List;

 
/**
 * Pruebas de logica de Organizations
 * 
 * @author ISIS2603
 */
@RunWith(Arquillian.class)
public class OrganizationLogicTest {

    private PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private OrganizationLogic organizationLogic;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    private List<OrganizationEntity> data = new ArrayList<OrganizationEntity>();

    /**
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     * El jar contiene las clases, el descriptor de la base de datos y el
     * archivo beans.xml para resolver la inyección de dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(OrganizationEntity.class.getPackage())
                .addPackage(OrganizationLogic.class.getPackage())
                .addPackage(OrganizationPersistence.class.getPackage())
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
        em.createQuery("delete from OrganizationEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        for (int i = 0; i < 3; i++) {
            OrganizationEntity entity = factory.manufacturePojo(OrganizationEntity.class);
            em.persist(entity);
            data.add(entity);
        }
        PrizeEntity prize = factory.manufacturePojo(PrizeEntity.class);
        em.persist(prize);
        prize.setOrganization(data.get(2));
        data.get(2).setPrize(prize);
    }

    /**
     * Prueba para crear un Organization.
     *
     * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException .
     */
    @Test
    public void createOrganizationTest() throws BusinessLogicException {
        OrganizationEntity newEntity = factory.manufacturePojo(OrganizationEntity.class);
        OrganizationEntity result = organizationLogic.createOrganization(newEntity);

        Assert.assertNotNull(result);
        OrganizationEntity entity = em.find(OrganizationEntity.class, result.getId());
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getName(), entity.getName());
        Assert.assertEquals(newEntity.getTipo(), entity.getTipo());
    }

    /**
     * Prueba para crear un Organization.
     *
     * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException .
     */
    @Test(expected = BusinessLogicException.class)
    public void createOrganizationConNombreRepetidoTest() throws BusinessLogicException {
        OrganizationEntity newEntity = factory.manufacturePojo(OrganizationEntity.class);
        newEntity.setName(data.get(0).getName());
        organizationLogic.createOrganization(newEntity);
    }

    /**
     * Prueba para consultar la lista de Organizations.
     */
    @Test
    public void getOrganizationsTest() {
        List<OrganizationEntity> list = organizationLogic.getOrganizations();
        Assert.assertEquals(data.size(), list.size());
        for (OrganizationEntity entity : list) {
            boolean found = false;
            for (OrganizationEntity storedEntity : data) {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * Prueba para consultar un Organization.
     */
    @Test
    public void getOrganizationTest() {
        OrganizationEntity entity = data.get(0);
        OrganizationEntity resultEntity = organizationLogic.getOrganization(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getId(), resultEntity.getId());
        Assert.assertEquals(entity.getName(), resultEntity.getName());
        Assert.assertEquals(entity.getTipo(), resultEntity.getTipo());
    }

    /**
     * Prueba para actualizar un Organization.
     */
    @Test
    public void updateOrganizationTest() {
        OrganizationEntity entity = data.get(0);
        OrganizationEntity pojoEntity = factory.manufacturePojo(OrganizationEntity.class);

        pojoEntity.setId(entity.getId());

        organizationLogic.updateOrganization(pojoEntity.getId(), pojoEntity);

        OrganizationEntity resp = em.find(OrganizationEntity.class, entity.getId());

        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        Assert.assertEquals(pojoEntity.getName(), resp.getName());
        Assert.assertEquals(pojoEntity.getTipo(), resp.getTipo());
    }

    /**
     * Prueba para eliminar un Organization.
     *
     * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException
     */
    @Test
    public void deleteOrganizationTest() throws BusinessLogicException {
        OrganizationEntity entity = data.get(0);
        organizationLogic.deleteOrganization(entity.getId());
        OrganizationEntity deleted = em.find(OrganizationEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * Prueba para eliminar un Organization con un premio.
     *
     * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void deleteOrganizationWithPrizeTest() throws BusinessLogicException {
        organizationLogic.deleteOrganization(data.get(2).getId());
    }
}
