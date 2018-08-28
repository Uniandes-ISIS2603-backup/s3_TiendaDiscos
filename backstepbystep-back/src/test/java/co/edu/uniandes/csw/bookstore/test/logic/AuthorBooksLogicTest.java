package co.edu.uniandes.csw.bookstore.test.logic;

import co.edu.uniandes.csw.bookstore.ejb.AuthorBooksLogic;
import co.edu.uniandes.csw.bookstore.ejb.BookLogic;
import co.edu.uniandes.csw.bookstore.entities.AuthorEntity;
import co.edu.uniandes.csw.bookstore.entities.BookEntity;
import co.edu.uniandes.csw.bookstore.entities.EditorialEntity;
import co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.bookstore.persistence.AuthorPersistence;
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
 * Pruebas de logica de la relacion Author - Books
 *
 * @author ISIS2603
 */
@RunWith(Arquillian.class)
public class AuthorBooksLogicTest {

    private PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private AuthorBooksLogic authorBookLogic;

    @Inject
    private BookLogic bookLogic;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    private AuthorEntity author = new AuthorEntity();
    private EditorialEntity editorial = new EditorialEntity();
    private List<BookEntity> data = new ArrayList<>();

    /**
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     * El jar contiene las clases, el descriptor de la base de datos y el
     * archivo beans.xml para resolver la inyección de dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(AuthorEntity.class.getPackage())
                .addPackage(BookEntity.class.getPackage())
                .addPackage(AuthorBooksLogic.class.getPackage())
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
        em.createQuery("delete from AuthorEntity").executeUpdate();
        em.createQuery("delete from BookEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        for (int i = 0; i < 3; i++) {
            editorial = factory.manufacturePojo(EditorialEntity.class);
            em.persist(editorial);
        }

        author = factory.manufacturePojo(AuthorEntity.class);
        author.setId(1L);
        author.setBooks(new ArrayList<>());
        em.persist(author);

        for (int i = 0; i < 3; i++) {
            BookEntity entity = factory.manufacturePojo(BookEntity.class);
            entity.setAuthors(new ArrayList<>());
            entity.getAuthors().add(author);
            em.persist(entity);
            data.add(entity);
            author.getBooks().add(entity);
        }
    }

    /**
     * Prueba para asociar un autor a un libro.
     *
     *
     * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException
     */
    @Test
    public void addBookTest() throws BusinessLogicException {
        BookEntity newBook = factory.manufacturePojo(BookEntity.class);
        newBook.setEditorial(editorial);
        bookLogic.createBook(newBook);
        BookEntity bookEntity = authorBookLogic.addBook(author.getId(), newBook.getId());
        Assert.assertNotNull(bookEntity);

        Assert.assertEquals(bookEntity.getId(), newBook.getId());
        Assert.assertEquals(bookEntity.getName(), newBook.getName());
        Assert.assertEquals(bookEntity.getDescription(), newBook.getDescription());
        Assert.assertEquals(bookEntity.getIsbn(), newBook.getIsbn());
        Assert.assertEquals(bookEntity.getImage(), newBook.getImage());

        BookEntity lastBook = authorBookLogic.getBook(author.getId(), newBook.getId());

        Assert.assertEquals(lastBook.getId(), newBook.getId());
        Assert.assertEquals(lastBook.getName(), newBook.getName());
        Assert.assertEquals(lastBook.getDescription(), newBook.getDescription());
        Assert.assertEquals(lastBook.getIsbn(), newBook.getIsbn());
        Assert.assertEquals(lastBook.getImage(), newBook.getImage());
    }

    /**
     * Prueba para consultar la lista de Books de un autor.
     */
    @Test
    public void getBooksTest() {
        List<BookEntity> bookEntities = authorBookLogic.getBooks(author.getId());

        Assert.assertEquals(data.size(), bookEntities.size());

        for (int i = 0; i < data.size(); i++) {
            Assert.assertTrue(bookEntities.contains(data.get(0)));
        }
    }

    /**
     * Prueba para cpnsultar un libro de un autor.
     *
     * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException
     */
    @Test
    public void getBookTest() throws BusinessLogicException {
        BookEntity bookEntity = data.get(0);
        BookEntity book = authorBookLogic.getBook(author.getId(), bookEntity.getId());
        Assert.assertNotNull(book);

        Assert.assertEquals(bookEntity.getId(), book.getId());
        Assert.assertEquals(bookEntity.getName(), book.getName());
        Assert.assertEquals(bookEntity.getDescription(), book.getDescription());
        Assert.assertEquals(bookEntity.getIsbn(), book.getIsbn());
        Assert.assertEquals(bookEntity.getImage(), book.getImage());
    }

    /**
     * Prueba para actualizar los libros de un autor.
     *
     * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException
     */
    @Test
    public void replaceBooksTest() throws BusinessLogicException {
        List<BookEntity> nuevaLista = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            BookEntity entity = factory.manufacturePojo(BookEntity.class);
            entity.setAuthors(new ArrayList<>());
            entity.getAuthors().add(author);
            entity.setEditorial(editorial);
            bookLogic.createBook(entity);
            nuevaLista.add(entity);
        }
        authorBookLogic.replaceBooks(author.getId(), nuevaLista);
        List<BookEntity> bookEntities = authorBookLogic.getBooks(author.getId());
        for (BookEntity aNuevaLista : nuevaLista) {
            Assert.assertTrue(bookEntities.contains(aNuevaLista));
        }
    }

    /**
     * Prueba desasociar un libro con un autor.
     *
     */
    @Test
    public void removeBookTest() {
        for (BookEntity book : data) {
            authorBookLogic.removeBook(author.getId(), book.getId());
        }
        Assert.assertTrue(authorBookLogic.getBooks(author.getId()).isEmpty());
    }
}
