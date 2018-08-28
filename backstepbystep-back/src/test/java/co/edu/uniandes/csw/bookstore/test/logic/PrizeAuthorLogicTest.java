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

import co.edu.uniandes.csw.bookstore.ejb.PrizeAuthorLogic;
import co.edu.uniandes.csw.bookstore.entities.AuthorEntity;
import co.edu.uniandes.csw.bookstore.entities.OrganizationEntity;
import co.edu.uniandes.csw.bookstore.entities.PrizeEntity;
import co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.bookstore.persistence.AuthorPersistence;
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
 * Pruebas de logica de la relacion Prize - Author
 *
 * @author ISIS2603
 */
@RunWith(Arquillian.class)
public class PrizeAuthorLogicTest {

    private PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private PrizeAuthorLogic prizeAuthorLogic;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    private List<AuthorEntity> data = new ArrayList<AuthorEntity>();

    private List<PrizeEntity> prizesData = new ArrayList();

    /**
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     * El jar contiene las clases, el descriptor de la base de datos y el
     * archivo beans.xml para resolver la inyección de dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(AuthorEntity.class.getPackage())
                .addPackage(PrizeAuthorLogic.class.getPackage())
                .addPackage(AuthorPersistence.class.getPackage())
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
        em.createQuery("delete from OrganizationEntity ").executeUpdate();
        em.createQuery("delete from AuthorEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        for (int i = 0; i < 3; i++) {
            PrizeEntity prizes = factory.manufacturePojo(PrizeEntity.class);
            OrganizationEntity org = factory.manufacturePojo(OrganizationEntity.class);

            prizes.setOrganization(org);
            org.setPrize(prizes);

            em.persist(org);
            em.persist(prizes);
            prizesData.add(prizes);
        }
        for (int i = 0; i < 3; i++) {
            AuthorEntity entity = factory.manufacturePojo(AuthorEntity.class);
            em.persist(entity);
            data.add(entity);
            if (i == 0) {
                prizesData.get(i).setAuthor(entity);
            }
        }
    }

    /**
     * Prueba para asociar un Prizes existente a un Author.
     */
    @Test
    public void addAuthorTest() {
        AuthorEntity entity = data.get(0);
        PrizeEntity prizeEntity = prizesData.get(1);
        AuthorEntity response = prizeAuthorLogic.addAuthor(entity.getId(), prizeEntity.getId());

        Assert.assertNotNull(response);
        Assert.assertEquals(entity.getId(), response.getId());
    }

    /**
     * Prueba para consultar un Author.
     */
    @Test
    public void getAuthorTest() {
        PrizeEntity entity = prizesData.get(0);
        AuthorEntity resultEntity = prizeAuthorLogic.getAuthor(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getAuthor().getId(), resultEntity.getId());
    }

    /**
     * Prueba para remplazar las instancias de Prizes asociadas a una instancia
     * de Author.
     */
    @Test
    public void replaceAuthorTest() {
        AuthorEntity entity = data.get(0);
        prizeAuthorLogic.replaceAuthor(prizesData.get(1).getId(), entity.getId());
        entity = prizeAuthorLogic.getAuthor(prizesData.get(1).getId());
        Assert.assertTrue(entity.getPrizes().contains(prizesData.get(1)));
    }

    /**
     * Prueba para desasociar un Prize existente de un Author existente.
     *
     * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException
     */
    @Test
    public void removePrizeTest() throws BusinessLogicException {
        prizeAuthorLogic.removeAuthor(prizesData.get(0).getId());
        Assert.assertNull(prizeAuthorLogic.getAuthor(prizesData.get(0).getId()));
    }

    /**
     * Prueba para desasociar un Prize existente de un Author existente.
     *
     * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void removePrizeSinAuthorTest() throws BusinessLogicException {
        prizeAuthorLogic.removeAuthor(prizesData.get(1).getId());
    }
}
