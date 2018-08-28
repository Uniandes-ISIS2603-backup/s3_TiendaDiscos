package co.edu.uniandes.csw.bookstore.ejb;

import co.edu.uniandes.csw.bookstore.entities.AuthorEntity;
import co.edu.uniandes.csw.bookstore.entities.BookEntity;
import co.edu.uniandes.csw.bookstore.persistence.AuthorPersistence;
import co.edu.uniandes.csw.bookstore.persistence.BookPersistence;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase que implementa la conexion con la persistencia para la relación entre
 * la entidad de Book y Author.
 *
 * @author ISIS2603
 */
@Stateless
public class BookAuthorLogic {

    private static final Logger LOGGER = Logger.getLogger(BookAuthorLogic.class.getName());

    @Inject
    private BookPersistence bookPersistence;

    @Inject
    private AuthorPersistence authorPersistence;

    /**
     * Asocia un Author existente a un Book
     *
     * @param booksId Identificador de la instancia de Book
     * @param authorsId Identificador de la instancia de Author
     * @return Instancia de AuthorEntity que fue asociada a Book
     */
    public AuthorEntity addAuthor(Long booksId, Long authorsId) {
        LOGGER.log(Level.INFO, "Inicia proceso de asociarle un autor al libro con id = {0}", booksId);
        AuthorEntity authorEntity = authorPersistence.find(authorsId);
        BookEntity bookEntity = bookPersistence.find(booksId);
        bookEntity.getAuthors().add(authorEntity);
        LOGGER.log(Level.INFO, "Termina proceso de asociarle un autor al libro con id = {0}", booksId);
        return authorPersistence.find(authorsId);
    }

    /**
     * Obtiene una colección de instancias de AuthorEntity asociadas a una
     * instancia de Book
     *
     * @param booksId Identificador de la instancia de Book
     * @return Colección de instancias de AuthorEntity asociadas a la instancia
     * de Book
     */
    public List<AuthorEntity> getAuthors(Long booksId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todos los autores del libro con id = {0}", booksId);
        return bookPersistence.find(booksId).getAuthors();
    }

    /**
     * Obtiene una instancia de AuthorEntity asociada a una instancia de Book
     *
     * @param booksId Identificador de la instancia de Book
     * @param authorsId Identificador de la instancia de Author
     * @return La entidad del Autor asociada al libro
     */
    public AuthorEntity getAuthor(Long booksId, Long authorsId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar un autor del libro con id = {0}", booksId);
        List<AuthorEntity> authors = bookPersistence.find(booksId).getAuthors();
        AuthorEntity authorEntity = authorPersistence.find(authorsId);
        int index = authors.indexOf(authorEntity);
        LOGGER.log(Level.INFO, "Termina proceso de consultar un autor del libro con id = {0}", booksId);
        if (index >= 0) {
            return authors.get(index);
        }
        return null;
    }

    /**
     * Remplaza las instancias de Author asociadas a una instancia de Book
     *
     * @param booksId Identificador de la instancia de Book
     * @param list Colección de instancias de AuthorEntity a asociar a instancia
     * de Book
     * @return Nueva colección de AuthorEntity asociada a la instancia de Book
     */
    public List<AuthorEntity> replaceAuthors(Long booksId, List<AuthorEntity> list) {
        LOGGER.log(Level.INFO, "Inicia proceso de reemplazar los autores del libro con id = {0}", booksId);
        BookEntity bookEntity = bookPersistence.find(booksId);
        bookEntity.setAuthors(list);
        LOGGER.log(Level.INFO, "Termina proceso de reemplazar los autores del libro con id = {0}", booksId);
        return bookPersistence.find(booksId).getAuthors();
    }

    /**
     * Desasocia un Author existente de un Book existente
     *
     * @param booksId Identificador de la instancia de Book
     * @param authorsId Identificador de la instancia de Author
     */
    public void removeAuthor(Long booksId, Long authorsId) {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar un autor del libro con id = {0}", booksId);
        AuthorEntity authorEntity = authorPersistence.find(authorsId);
        BookEntity bookEntity = bookPersistence.find(booksId);
        bookEntity.getAuthors().remove(authorEntity);
        LOGGER.log(Level.INFO, "Termina proceso de borrar un autor del libro con id = {0}", booksId);
    }
}
