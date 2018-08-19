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
package co.edu.uniandes.csw.bookstore.resources;

import co.edu.uniandes.csw.bookstore.dtos.BookDTO;
import co.edu.uniandes.csw.bookstore.ejb.BookEditorialLogic;
import co.edu.uniandes.csw.bookstore.ejb.BookLogic;
import co.edu.uniandes.csw.bookstore.ejb.EditorialLogic;
import co.edu.uniandes.csw.bookstore.entities.BookEntity;
import co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.bookstore.mappers.BusinessLogicExceptionMapper;
import co.edu.uniandes.csw.bookstore.mappers.WebApplicationExceptionMapper;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;

/**
 * Clase que implementa el recurso "books".
 *
 * @author ISIS2603
 * @version 1.0
 */
@Path("books")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class BookResource {

    private static final Logger LOGGER = Logger.getLogger(BookResource.class.getName());

    @Inject
    BookLogic bookLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    @Inject
    EditorialLogic editorialLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    @Inject
    BookEditorialLogic bookEditorialLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    /**
     * Crea un nuevo libro con la informacion que se recibe en el cuerpo de la
     * petición y se regresa un objeto identico con un id auto-generado por la
     * base de datos.
     *
     * @param book {@link BookDTO} - EL libro que se desea guardar.
     * @return JSON {@link BookDTO} - El libro guardado con el atributo id
     * autogenerado.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando ya existe el libro o el isbn es
     * inválido o si la editorial ingresada es invalida.
     */
    @POST
    public BookDTO createBook(BookDTO book) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "BookResource createBook: input: {0}", book.toString());
        BookDTO nuevoBookDTO = new BookDTO(bookLogic.createBook(book.toEntity()));
        LOGGER.log(Level.INFO, "BookResource createBook: output: {0}", nuevoBookDTO.toString());
        return nuevoBookDTO;
    }

    /**
     * Busca y devuelve todos los libros que existen en la aplicacion.
     *
     * @return JSONArray {@link BookDTO} - Los libros encontrados en la
     * aplicación. Si no hay ninguno retorna una lista vacía.
     */
    @GET
    public List<BookDTO> getBooks() {
        LOGGER.info("BookResource getBooks: input: void");
        List<BookDTO> listaBooks = listBookEntity2DTO(bookLogic.getBooks());
        LOGGER.log(Level.INFO, "BookResource getBooks: output: {0}", listaBooks.toString());
        return listaBooks;
    }

    /**
     * Busca el libro con el id asociado recibido en la URL y lo devuelve.
     *
     * @param booksId Identificador del libro que se esta buscando. Este debe
     * ser una cadena de dígitos.
     * @return JSON {@link BookDTO} - El libro buscado
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el libro.
     */
    @GET
    @Path("{booksId: \\d+}")
    public BookDTO getBook(@PathParam("booksId") Long booksId) {
        LOGGER.log(Level.INFO, "BookResource getBook: input: {0}", booksId);
        BookEntity entity = bookLogic.getBook(booksId);
        if (entity == null) {
            throw new WebApplicationException("El recurso /books/" + booksId + " no existe.", 404);
        }
        BookDTO bookDTO = new BookDTO(bookLogic.getBook(booksId));
        LOGGER.log(Level.INFO, "BookResource getBook: output: {0}", bookDTO.toString());
        return bookDTO;
    }

    /**
     * Actualiza el libro con el id recibido en la URL con la información que se
     * recibe en el cuerpo de la petición.
     *
     * @param booksId Identificador del libro que se desea actualizar. Este debe
     * ser una cadena de dígitos.
     * @param book {@link BookDTO} El libro que se desea guardar.
     * @return JSON {@link BookDTO} - El libro guardada.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el libro a
     * actualizar.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando no se puede actualizar el libro.
     */
    @PUT
    @Path("{booksId: \\d+}")
    public BookDTO updateBook(@PathParam("booksId") Long booksId, BookDTO book) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "BookResource updateBook: input: booksId: {0} , book: {1}", new Object[]{booksId, book.toString()});
        book.setId(booksId);
        BookEntity entity = bookLogic.getBook(booksId);
        if (entity == null) {
            throw new WebApplicationException("El recurso /books/" + booksId + " no existe.", 404);
        }
        BookDTO detailDTO = new BookDTO(bookLogic.updateBook(booksId, book.toEntity()));
        LOGGER.log(Level.INFO, "BookResource updateBook: output: {0}", detailDTO.toString());
        return detailDTO;
    }

    /**
     * Borra el libro con el id asociado recibido en la URL.
     *
     * @param booksId Identificador del libro que se desea borrar. Este debe ser
     * una cadena de dígitos.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el libro.
     */
    @DELETE
    @Path("{booksId: \\d+}")
    public void deleteBook(@PathParam("booksId") Long booksId) {
        LOGGER.log(Level.INFO, "BookResource deleteBook: input: {0}", booksId);
        BookEntity entity = bookLogic.getBook(booksId);
        if (entity == null) {
            throw new WebApplicationException("El recurso /books/" + booksId + " no existe.", 404);
        }
        bookEditorialLogic.removeEditorial(booksId);
        bookLogic.deleteBook(booksId);
        LOGGER.info("BookResource deleteBook: output: void");
    }

    /**
     * Convierte una lista de BookEntity a una lista de BookDTO.
     *
     * @param entityList Lista de BookEntity a convertir.
     * @return Lista de BookDTO convertida.
     */
    private List<BookDTO> listBookEntity2DTO(List<BookEntity> entityList) {
        List<BookDTO> list = new ArrayList();
        for (BookEntity entity : entityList) {
            list.add(new BookDTO(entity));
        }
        return list;
    }
}
