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
import co.edu.uniandes.csw.bookstore.dtos.EditorialDTO;
import co.edu.uniandes.csw.bookstore.ejb.BookEditorialLogic;
import co.edu.uniandes.csw.bookstore.ejb.BookLogic;
import co.edu.uniandes.csw.bookstore.ejb.EditorialLogic;
import co.edu.uniandes.csw.bookstore.mappers.WebApplicationExceptionMapper;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;

/**
 * Clase que implementa el recurso "books/{id}/editorial".
 *
 * @author ISIS2603
 * @version 1.0
 */
@Path("books/{booksId: \\d+}/editorial")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class BookEditorialResource {

    private static final Logger LOGGER = Logger.getLogger(BookEditorialResource.class.getName());

    @Inject
    private BookLogic bookLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    @Inject
    private BookEditorialLogic bookEditorialLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    @Inject
    private EditorialLogic editorialLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    /**
     * Remplaza la instancia de Editorial asociada a un Book.
     *
     * @param booksId Identificador del libro que se esta actualizando. Este
     * debe ser una cadena de dígitos.
     * @param editorial La editorial que se será del libro.
     * @return JSON {@link BookDTO} - El arreglo de libros guardado en la
     * editorial.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra la editorial o el
     * libro.
     */
    @PUT
    public BookDTO replaceEditorial(@PathParam("booksId") Long booksId, EditorialDTO editorial) {
        LOGGER.log(Level.INFO, "BookEditorialResource replaceEditorial: input: booksId{0} , Editorial:{1}", new Object[]{booksId, editorial.toString()});
        if (bookLogic.getBook(booksId) == null) {
            throw new WebApplicationException("El recurso /books/" + booksId + " no existe.", 404);
        }
        if (editorialLogic.getEditorial(editorial.getId()) == null) {
            throw new WebApplicationException("El recurso /editorials/" + editorial.getId() + " no existe.", 404);
        }
        BookDTO bookDTO = new BookDTO(bookEditorialLogic.replaceEditorial(booksId, editorial.getId()));
        LOGGER.log(Level.INFO, "BookEditorialResource replaceEditorial: output: {0}", bookDTO.toString());
        return bookDTO;
    }
}
