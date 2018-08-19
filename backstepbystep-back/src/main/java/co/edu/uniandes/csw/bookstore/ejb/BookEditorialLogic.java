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
import co.edu.uniandes.csw.bookstore.persistence.BookPersistence;
import co.edu.uniandes.csw.bookstore.persistence.EditorialPersistence;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * Clase que implementa la conexion con la persistencia para la relación entre
 * la entidad de Book e Editorial.
 *
 * @author ISIS2603
 */
@Stateless
public class BookEditorialLogic {

    private static final Logger LOGGER = Logger.getLogger(BookEditorialLogic.class.getName());

    @Inject
    private BookPersistence bookPersistence;

    @Inject
    private EditorialPersistence editorialPersistence;

    /**
     * Remplazar la editorial de un book.
     *
     * @param booksId id del libro que se quiere actualizar.
     * @param editorialsId El id de la editorial que se será del libro.
     * @return el nuevo libro.
     */
    public BookEntity replaceEditorial(Long booksId, Long editorialsId) {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar libro con id = {0}", booksId);
        EditorialEntity editorialEntity = editorialPersistence.find(editorialsId);
        BookEntity bookEntity = bookPersistence.find(booksId);
        bookEntity.setEditorial(editorialEntity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar libro con id = {0}", bookEntity.getId());
        return bookEntity;
    }

    /**
     * Borrar un book de una editorial. Este metodo se utiliza para borrar la
     * relacion de un libro.
     *
     * @param booksId El libro que se desea borrar de la editorial.
     */
    public void removeEditorial(Long booksId) {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar la Editorial del libro con id = {0}", booksId);
        BookEntity bookEntity = bookPersistence.find(booksId);
        EditorialEntity editorialEntity = editorialPersistence.find(bookEntity.getEditorial().getId());
        bookEntity.setEditorial(null);
        editorialEntity.getBooks().remove(bookEntity);
        LOGGER.log(Level.INFO, "Termina proceso de borrar la Editorial del libro con id = {0}", bookEntity.getId());
    }
}
