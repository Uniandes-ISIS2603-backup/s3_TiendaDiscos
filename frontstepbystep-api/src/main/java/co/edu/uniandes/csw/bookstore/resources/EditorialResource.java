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

import co.edu.uniandes.csw.bookstore.dtos.EditorialDTO;
import co.edu.uniandes.csw.bookstore.ejb.EditorialLogic;
import co.edu.uniandes.csw.bookstore.entities.EditorialEntity;
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
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;

/**
 * Clase que implementa el recurso "editorials".
 *
 * @author ISIS2603
 * @version 1.0
 */
@Path("editorials")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class EditorialResource {

    private static final Logger LOGGER = Logger.getLogger(EditorialResource.class.getName());

    @Inject
    EditorialLogic editorialLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    /**
     * Crea una nueva editorial con la informacion que se recibe en el cuerpo de
     * la petición y se regresa un objeto identico con un id auto-generado por
     * la base de datos.
     *
     * @param editorial {@link EditorialDTO} - La editorial que se desea
     * guardar.
     * @return JSON {@link EditorialDTO} - La editorial guardada con el atributo
     * id autogenerado.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando ya existe la editorial.
     */
    @POST
    public EditorialDTO createEditorial(EditorialDTO editorial) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "EditorialResource createEditorial: input: {0}", editorial.toString());
        // Convierte el DTO (json) en un objeto Entity para ser manejado por la lógica.
        EditorialEntity editorialEntity = editorial.toEntity();
        // Invoca la lógica para crear la editorial nueva
        EditorialEntity nuevoEditorialEntity = editorialLogic.createEditorial(editorialEntity);
        // Como debe retornar un DTO (json) se invoca el constructor del DTO con argumento el entity nuevo
        EditorialDTO nuevoEditorialDTO = new EditorialDTO(nuevoEditorialEntity);
        LOGGER.log(Level.INFO, "EditorialResource createEditorial: output: {0}", nuevoEditorialDTO.toString());
        return nuevoEditorialDTO;
    }

    /**
     * Busca y devuelve todas las editoriales que existen en la aplicacion.
     *
     * @return JSONArray {@link EditorialDTO} - Las editoriales encontradas en
     * la aplicación. Si no hay ninguna retorna una lista vacía.
     */
    @GET
    public List<EditorialDTO> getEditorials() {
        LOGGER.info("EditorialResource getEditorials: input: void");
        List<EditorialDTO> listaEditoriales = listEntity2DetailDTO(editorialLogic.getEditorials());
        LOGGER.log(Level.INFO, "EditorialResource getEditorials: output: {0}", listaEditoriales.toString());
        return listaEditoriales;
    }

    /**
     * Busca la editorial con el id asociado recibido en la URL y la devuelve.
     *
     * @param editorialsId Identificador de la editorial que se esta buscando.
     * Este debe ser una cadena de dígitos.
     * @return JSON {@link EditorialDTO} - La editorial buscada
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra la editorial.
     */
    @GET
    @Path("{editorialsId: \\d+}")
    public EditorialDTO getEditorial(@PathParam("editorialsId") Long editorialsId) throws WebApplicationException {
        LOGGER.log(Level.INFO, "EditorialResource getEditorial: input: {0}", editorialsId);
        EditorialEntity editorialEntity = editorialLogic.getEditorial(editorialsId);
        if (editorialEntity == null) {
            throw new WebApplicationException("El recurso /editorials/" + editorialsId + " no existe.", 404);
        }
        EditorialDTO detailDTO = new EditorialDTO(editorialEntity);
        LOGGER.log(Level.INFO, "EditorialResource getEditorial: output: {0}", detailDTO.toString());
        return detailDTO;
    }

    /**
     * Actualiza la editorial con el id recibido en la URL con la informacion
     * que se recibe en el cuerpo de la petición.
     *
     * @param editorialsId Identificador de la editorial que se desea
     * actualizar. Este debe ser una cadena de dígitos.
     * @param editorial {@link EditorialDTO} La editorial que se desea guardar.
     * @return JSON {@link EditorialDTO} - La editorial guardada.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra la editorial a
     * actualizar.
     */
    @PUT
    @Path("{editorialsId: \\d+}")
    public EditorialDTO updateEditorial(@PathParam("editorialsId") Long editorialsId, EditorialDTO editorial) throws WebApplicationException {
        LOGGER.log(Level.INFO, "EditorialResource updateEditorial: input: id:{0} , editorial: {1}", new Object[]{editorialsId, editorial.toString()});
        editorial.setId(editorialsId);
        if (editorialLogic.getEditorial(editorialsId) == null) {
            throw new WebApplicationException("El recurso /editorials/" + editorialsId + " no existe.", 404);
        }
        EditorialDTO detailDTO = new EditorialDTO(editorialLogic.updateEditorial(editorialsId, editorial.toEntity()));
        LOGGER.log(Level.INFO, "EditorialResource updateEditorial: output: {0}", detailDTO.toString());
        return detailDTO;
    }

    /**
     * Borra la editorial con el id asociado recibido en la URL.
     *
     * @param editorialsId Identificador de la editorial que se desea borrar.
     * Este debe ser una cadena de dígitos.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando no se puede eliminar la editorial.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra la editorial.
     */
    @DELETE
    @Path("{editorialsId: \\d+}")
    public void deleteEditorial(@PathParam("editorialsId") Long editorialsId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "EditorialResource deleteEditorial: input: {0}", editorialsId);
        if (editorialLogic.getEditorial(editorialsId) == null) {
            throw new WebApplicationException("El recurso /editorials/" + editorialsId + " no existe.", 404);
        }
        editorialLogic.deleteEditorial(editorialsId);
        LOGGER.info("EditorialResource deleteEditorial: output: void");
    }

    /**
     * Convierte una lista de entidades a DTO.
     *
     * Este método convierte una lista de objetos EditorialEntity a una lista de
     * objetos EditorialDTO (json)
     *
     * @param entityList corresponde a la lista de editoriales de tipo Entity
     * que vamos a convertir a DTO.
     * @return la lista de editoriales en forma DTO (json)
     */
    private List<EditorialDTO> listEntity2DetailDTO(List<EditorialEntity> entityList) {
        List<EditorialDTO> list = new ArrayList<>();
        for (EditorialEntity entity : entityList) {
            list.add(new EditorialDTO(entity));
        }
        return list;
    }
}
