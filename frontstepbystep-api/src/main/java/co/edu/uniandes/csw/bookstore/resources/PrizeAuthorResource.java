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

import co.edu.uniandes.csw.bookstore.dtos.AuthorDTO;
import co.edu.uniandes.csw.bookstore.dtos.AuthorDetailDTO;
import co.edu.uniandes.csw.bookstore.ejb.AuthorLogic;
import co.edu.uniandes.csw.bookstore.ejb.PrizeAuthorLogic;
import co.edu.uniandes.csw.bookstore.entities.AuthorEntity;
import co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.bookstore.mappers.WebApplicationExceptionMapper;
import java.util.logging.Level;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.logging.Logger;
import javax.ws.rs.DELETE;
import javax.ws.rs.WebApplicationException;

/**
 * Clase que implementa el recurso "prize/{id}/author".
 *
 * @author ISIS2603
 * @version 1.0
 */
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PrizeAuthorResource {

    private static final Logger LOGGER = Logger.getLogger(PrizeAuthorResource.class.getName());

    @Inject
    private PrizeAuthorLogic prizeAuthorLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    @Inject
    private AuthorLogic authorLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    /**
     * Guarda un author dentro de un premio con la informacion que recibe el la
     * URL.
     *
     * @param prizesId Identificador de el premio que se esta actualizando. Este
     * debe ser una cadena de dígitos.
     * @param authorsId Identificador del autor que se desea guardar. Este debe
     * ser una cadena de dígitos.
     * @return JSON {@link AuthorDTO} - El autor guardado en el premio.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el autor.
     */
    @POST
    @Path("{authorsId: \\d+}")
    public AuthorDTO addAuthor(@PathParam("prizesId") Long prizesId, @PathParam("authorsId") Long authorsId) {
        LOGGER.log(Level.INFO, "PrizeAuthorResource addAuthor: input: prizesID: {0} , authorsId: {1}", new Object[]{prizesId, authorsId});
        if (authorLogic.getAuthor(authorsId) == null) {
            throw new WebApplicationException("El recurso /authors/" + authorsId + " no existe.", 404);
        }
        AuthorDTO authorDTO = new AuthorDTO(prizeAuthorLogic.addAuthor(authorsId, prizesId));
        LOGGER.log(Level.INFO, "PrizeAuthorResource addAuthor: output: {0}", authorDTO.toString());
        return authorDTO;
    }

    /**
     * Busca el autor dentro de el premio con id asociado.
     *
     * @param prizesId Identificador de el premio que se esta buscando. Este
     * debe ser una cadena de dígitos.
     * @return JSON {@link AuthorDetailDTO} - El autor buscado
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando el premio no tiene autor.
     */
    @GET
    public AuthorDetailDTO getAuthor(@PathParam("prizesId") Long prizesId) {
        LOGGER.log(Level.INFO, "PrizeAuthorResource getAuthor: input: {0}", prizesId);
        AuthorEntity authorEntity = prizeAuthorLogic.getAuthor(prizesId);
        if (authorEntity == null) {
            throw new WebApplicationException("El recurso /prizes/" + prizesId + "/author no existe.", 404);
        }
        AuthorDetailDTO authorDetailDTO = new AuthorDetailDTO(authorEntity);
        LOGGER.log(Level.INFO, "PrizeAuthorResource getAuthor: output: {0}", authorDetailDTO.toString());
        return authorDetailDTO;
    }

    /**
     * Remplaza la instancia de Author asociada a una instancia de Prize
     *
     * @param prizesId Identificador de el premio que se esta actualizando. Este
     * debe ser una cadena de dígitos.
     * @param authorsId Identificador de el author que se esta remplazando. Este
     * debe ser una cadena de dígitos.
     * @return JSON {@link AuthorDetailDTO} - El autor actualizado
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el autor.
     */
    @PUT
    @Path("{authorsId: \\d+}")
    public AuthorDetailDTO replaceAuthor(@PathParam("prizesId") Long prizesId, @PathParam("authorsId") Long authorsId) {
        LOGGER.log(Level.INFO, "PrizeAuthorResource replaceAuthor: input: prizesId: {0} , authorsId: {1}", new Object[]{prizesId, authorsId});
        if (authorLogic.getAuthor(authorsId) == null) {
            throw new WebApplicationException("El recurso /authors/" + authorsId + " no existe.", 404);
        }
        AuthorDetailDTO authorDetailDTO = new AuthorDetailDTO(prizeAuthorLogic.replaceAuthor(prizesId, authorsId));
        LOGGER.log(Level.INFO, "PrizeAuthorResource replaceAuthor: output: {0}", authorDetailDTO.toString());
        return authorDetailDTO;
    }

    /**
     * Elimina la conexión entre el autor y el premio recibido en la URL.
     *
     * @param prizesId El ID del premio al cual se le va a desasociar el autor
     * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException
     * Error de lógica que se genera cuando el premio no tiene autor.
     */
    @DELETE
    public void removeAuthor(@PathParam("prizesId") Long prizesId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "PrizeAuthorResource removeAuthor: input: {0}", prizesId);
        prizeAuthorLogic.removeAuthor(prizesId);
        LOGGER.info("PrizeAuthorResource removeAuthor: output: void");
    }
}
