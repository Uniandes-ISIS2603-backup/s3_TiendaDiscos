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
import co.edu.uniandes.csw.bookstore.ejb.BookLogic;
import co.edu.uniandes.csw.bookstore.ejb.EditorialBooksLogic;
import co.edu.uniandes.csw.bookstore.entities.BookEntity;
import co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.bookstore.mappers.BusinessLogicExceptionMapper;
import co.edu.uniandes.csw.bookstore.mappers.WebApplicationExceptionMapper;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.WebApplicationException;

/**
 * Clase que implementa el recurso "editorials/{id}/books".
 *
 * @author ISIS2603
 * @version 1.0
 */
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class EditorialBooksResource {

    private static final Logger LOGGER = Logger.getLogger(EditorialBooksResource.class.getName());

    @Inject
    private EditorialBooksLogic editorialBooksLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    @Inject
    private BookLogic bookLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    /**
     * Guarda un libro dentro de una editorial con la informacion que recibe el
     * la URL. Se devuelve el libro que se guarda en la editorial.
     *
     * @param editorialsId Identificador de la editorial que se esta
     * actualizando. Este debe ser una cadena de dígitos.
     * @param booksId Identificador del libro que se desea guardar. Este debe
     * ser una cadena de dígitos.
     * @return JSON {@link BookDTO} - El libro guardado en la editorial.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el libro.
     */
    @POST
    @Path("{booksId: \\d+}")
    public BookDTO addBook(@PathParam("editorialsId") Long editorialsId, @PathParam("booksId") Long booksId) {
        LOGGER.log(Level.INFO, "EditorialBooksResource addBook: input: editorialsID: {0} , booksId: {1}", new Object[]{editorialsId, booksId});
        if (bookLogic.getBook(booksId) == null) {
            throw new WebApplicationException("El recurso /books/" + booksId + " no existe.", 404);
        }
        BookDTO bookDTO = new BookDTO(editorialBooksLogic.addBook(booksId, editorialsId));
        LOGGER.log(Level.INFO, "EditorialBooksResource addBook: output: {0}", bookDTO.toString());
        return bookDTO;
    }

    /**
     * Busca y devuelve todos los libros que existen en la editorial.
     *
     * @param editorialsId Identificador de la editorial que se esta buscando.
     * Este debe ser una cadena de dígitos.
     * @return JSONArray {@link BookDTO} - Los libros encontrados en la
     * editorial. Si no hay ninguno retorna una lista vacía.
     */
    @GET
    public List<BookDTO> getBooks(@PathParam("editorialsId") Long editorialsId) {
        LOGGER.log(Level.INFO, "EditorialBooksResource getBooks: input: {0}", editorialsId);
        List<BookDTO> listaDetailDTOs = booksListEntity2DTO(editorialBooksLogic.getBooks(editorialsId));
        LOGGER.log(Level.INFO, "EditorialBooksResource getBooks: output: {0}", listaDetailDTOs.toString());
        return listaDetailDTOs;
    }

    /**
     * Busca el libro con el id asociado dentro de la editorial con id asociado.
     *
     * @param editorialsId Identificador de la editorial que se esta buscando.
     * Este debe ser una cadena de dígitos.
     * @param booksId Identificador del libro que se esta buscando. Este debe
     * ser una cadena de dígitos.
     * @return JSON {@link BookDTO} - El libro buscado
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el libro en la
     * editorial.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el libro.
     */
    @GET
    @Path("{booksId: \\d+}")
    public BookDTO getBook(@PathParam("editorialsId") Long editorialsId, @PathParam("booksId") Long booksId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "EditorialBooksResource getBook: input: editorialsID: {0} , booksId: {1}", new Object[]{editorialsId, booksId});
        if (bookLogic.getBook(booksId) == null) {
            throw new WebApplicationException("El recurso /editorials/" + editorialsId + "/books/" + booksId + " no existe.", 404);
        }
        BookDTO bookDTO = new BookDTO(editorialBooksLogic.getBook(editorialsId, booksId));
        LOGGER.log(Level.INFO, "EditorialBooksResource getBook: output: {0}", bookDTO.toString());
        return bookDTO;
    }

    /**
     * Remplaza las instancias de Book asociadas a una instancia de Editorial
     *
     * @param editorialsId Identificador de la editorial que se esta
     * remplazando. Este debe ser una cadena de dígitos.
     * @param books JSONArray {@link BookDTO} El arreglo de libros nuevo para la
     * editorial.
     * @return JSON {@link BookDTO} - El arreglo de libros guardado en la
     * editorial.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el libro.
     */
    @PUT
    public List<BookDTO> replaceBooks(@PathParam("editorialsId") Long editorialsId, List<BookDTO> books) {
        LOGGER.log(Level.INFO, "EditorialBooksResource replaceBooks: input: editorialsId: {0} , books: {1}", new Object[]{editorialsId, books.toString()});
        for (BookDTO book : books) {
            if (bookLogic.getBook(book.getId()) == null) {
                throw new WebApplicationException("El recurso /books/" + book.getId() + " no existe.", 404);
            }
        }
        List<BookDTO> listaDetailDTOs = booksListEntity2DTO(editorialBooksLogic.replaceBooks(editorialsId, booksListDTO2Entity(books)));
        LOGGER.log(Level.INFO, "EditorialBooksResource replaceBooks: output: {0}", listaDetailDTOs.toString());
        return listaDetailDTOs;
    }

    /**
     * Convierte una lista de BookEntity a una lista de BookDTO.
     *
     * @param entityList Lista de BookEntity a convertir.
     * @return Lista de BookDTO convertida.
     */
    private List<BookDTO> booksListEntity2DTO(List<BookEntity> entityList) {
        List<BookDTO> list = new ArrayList();
        for (BookEntity entity : entityList) {
            list.add(new BookDTO(entity));
        }
        return list;
    }

    /**
     * Convierte una lista de BookDTO a una lista de BookEntity.
     * 
     * @param dtos Lista de BookDTO a convertir.
     * @return Lista de BookEntity convertida.
     */
    private List<BookEntity> booksListDTO2Entity(List<BookDTO> dtos) {
        List<BookEntity> list = new ArrayList<>();
        for (BookDTO dto : dtos) {
            list.add(dto.toEntity());
        }
        return list;
    }
}
