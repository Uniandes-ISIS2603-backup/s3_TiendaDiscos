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
package co.edu.uniandes.csw.bookstore.ejb;

import co.edu.uniandes.csw.bookstore.entities.BookEntity;
import co.edu.uniandes.csw.bookstore.entities.EditorialEntity;
import co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.bookstore.persistence.BookPersistence;
import co.edu.uniandes.csw.bookstore.persistence.EditorialPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * Clase que implementa la conexion con la persistencia para la relación entre
 * la entidad de Editorial y Book.
 *
 * @author ISIS2603
 */
@Stateless
public class EditorialBooksLogic {

    private static final Logger LOGGER = Logger.getLogger(EditorialBooksLogic.class.getName());

    @Inject
    private BookPersistence bookPersistence;

    @Inject
    private EditorialPersistence editorialPersistence;

    /**
     * Agregar un book a la editorial
     *
     * @param booksId El id libro a guardar
     * @param editorialsId El id de la editorial en la cual se va a guardar el
     * libro.
     * @return El libro agregado a la editorial.
     */
    public BookEntity addBook(Long booksId, Long editorialsId) {
        LOGGER.log(Level.INFO, "Inicia proceso de agregarle un libro a la editorial con id = {0}", editorialsId);
        EditorialEntity editorialEntity = editorialPersistence.find(editorialsId);
        BookEntity bookEntity = bookPersistence.find(booksId);
        bookEntity.setEditorial(editorialEntity);
        editorialEntity.getBooks().add(bookEntity);
        LOGGER.log(Level.INFO, "Termina proceso de agregarle un libro a la editorial con id = {0}", editorialsId);
        return bookEntity;
    }

    /**
     * Retorna todos los books asociados a una editorial
     *
     * @param editorialsId El ID de la editorial buscada
     * @return La lista de libros de la editorial
     */
    public List<BookEntity> getBooks(Long editorialsId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar los libros asociados a la editorial con id = {0}", editorialsId);
        return editorialPersistence.find(editorialsId).getBooks();
    }

    /**
     * Retorna un book asociado a una editorial
     *
     * @param editorialsId El id de la editorial a buscar.
     * @param booksId El id del libro a buscar
     * @return El libro encontrado dentro de la editorial.
     * @throws BusinessLogicException Si el libro no se encuentra en la
     * editorial
     */
    public BookEntity getBook(Long editorialsId, Long booksId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar el libro con id = {0} de la editorial con id = " + editorialsId, booksId);
        List<BookEntity> books = editorialPersistence.find(editorialsId).getBooks();
        BookEntity bookEntity = bookPersistence.find(booksId);
        int index = books.indexOf(bookEntity);
        LOGGER.log(Level.INFO, "Termina proceso de consultar el libro con id = {0} de la editorial con id = " + editorialsId, booksId);
        if (index >= 0) {
            return books.get(index);
        }
        throw new BusinessLogicException("El libro no está asociado a la editorial");
    }

    /**
     * Remplazar books de una editorial
     *
     * @param books Lista de libros que serán los de la editorial.
     * @param editorialsId El id de la editorial que se quiere actualizar.
     * @return La lista de libros actualizada.
     */
    public List<BookEntity> replaceBooks(Long editorialsId, List<BookEntity> books) {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar la editorial con id = {0}", editorialsId);
        EditorialEntity editorialEntity = editorialPersistence.find(editorialsId);
        List<BookEntity> bookList = bookPersistence.findAll();
        for (BookEntity book : bookList) {
            if (books.contains(book)) {
                book.setEditorial(editorialEntity);
            } else if (book.getEditorial() != null && book.getEditorial().equals(editorialEntity)) {
                book.setEditorial(null);
            }
        }
        editorialEntity.setBooks(books);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar la editorial con id = {0}", editorialsId);
        return books;
    }
}
