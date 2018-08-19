package co.edu.uniandes.csw.bookstore.ejb;

import co.edu.uniandes.csw.bookstore.entities.AuthorEntity;
import co.edu.uniandes.csw.bookstore.entities.BookEntity;
import co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.bookstore.persistence.AuthorPersistence;
import co.edu.uniandes.csw.bookstore.persistence.BookPersistence;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase que implementa la conexion con la persistencia para la relación entre
 * la entidad de Author y Book.
 *
 * @author ISIS2603
 */
@Stateless
public class AuthorBooksLogic {

    private static final Logger LOGGER = Logger.getLogger(AuthorBooksLogic.class.getName());

    @Inject
    private BookPersistence bookPersistence;

    @Inject
    private AuthorPersistence authorPersistence;

    /**
     * Asocia un Book existente a un Author
     *
     * @param authorsId Identificador de la instancia de Author
     * @param booksId Identificador de la instancia de Book
     * @return Instancia de BookEntity que fue asociada a Author
     */
    public BookEntity addBook(Long authorsId, Long booksId) {
        LOGGER.log(Level.INFO, "Inicia proceso de asociarle un libro al autor con id = {0}", authorsId);
        AuthorEntity authorEntity = authorPersistence.find(authorsId);
        BookEntity bookEntity = bookPersistence.find(booksId);
        bookEntity.getAuthors().add(authorEntity);
        LOGGER.log(Level.INFO, "Termina proceso de asociarle un libro al autor con id = {0}", authorsId);
        return bookPersistence.find(booksId);
    }

    /**
     * Obtiene una colección de instancias de BookEntity asociadas a una
     * instancia de Author
     *
     * @param authorsId Identificador de la instancia de Author
     * @return Colección de instancias de BookEntity asociadas a la instancia de
     * Author
     */
    public List<BookEntity> getBooks(Long authorsId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todos los libros del autor con id = {0}", authorsId);
        return authorPersistence.find(authorsId).getBooks();
    }

    /**
     * Obtiene una instancia de BookEntity asociada a una instancia de Author
     *
     * @param authorsId Identificador de la instancia de Author
     * @param booksId Identificador de la instancia de Book
     * @return La entidadd de Libro del autor
     * @throws BusinessLogicException Si el libro no está asociado al autor
     */
    public BookEntity getBook(Long authorsId, Long booksId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar el libro con id = {0} del autor con id = " + authorsId, booksId);
        List<BookEntity> books = authorPersistence.find(authorsId).getBooks();
        BookEntity bookEntity = bookPersistence.find(booksId);
        int index = books.indexOf(bookEntity);
        LOGGER.log(Level.INFO, "Termina proceso de consultar el libro con id = {0} del autor con id = " + authorsId, booksId);
        if (index >= 0) {
            return books.get(index);
        }
        throw new BusinessLogicException("El libro no está asociado al autor");
    }

    /**
     * Remplaza las instancias de Book asociadas a una instancia de Author
     *
     * @param authorId Identificador de la instancia de Author
     * @param books Colección de instancias de BookEntity a asociar a instancia
     * de Author
     * @return Nueva colección de BookEntity asociada a la instancia de Author
     */
    public List<BookEntity> replaceBooks(Long authorId, List<BookEntity> books) {
        LOGGER.log(Level.INFO, "Inicia proceso de reemplazar los libros asocidos al author con id = {0}", authorId);
        AuthorEntity authorEntity = authorPersistence.find(authorId);
        List<BookEntity> bookList = bookPersistence.findAll();
        for (BookEntity book : bookList) {
            if (books.contains(book)) {
                if (!book.getAuthors().contains(authorEntity)) {
                    book.getAuthors().add(authorEntity);
                }
            } else {
                book.getAuthors().remove(authorEntity);
            }
        }
        authorEntity.setBooks(books);
        LOGGER.log(Level.INFO, "Termina proceso de reemplazar los libros asocidos al author con id = {0}", authorId);
        return authorEntity.getBooks();
    }

    /**
     * Desasocia un Book existente de un Author existente
     *
     * @param authorsId Identificador de la instancia de Author
     * @param booksId Identificador de la instancia de Book
     */
    public void removeBook(Long authorsId, Long booksId) {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar un libro del author con id = {0}", authorsId);
        AuthorEntity authorEntity = authorPersistence.find(authorsId);
        BookEntity bookEntity = bookPersistence.find(booksId);
        bookEntity.getAuthors().remove(authorEntity);
        LOGGER.log(Level.INFO, "Termina proceso de borrar un libro del author con id = {0}", authorsId);
    }
}
